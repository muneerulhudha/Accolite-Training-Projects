package com.au.thread;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;

public class JobAndConfigReader {

	Timer timer;

	public JobAndConfigReader(int seconds) {
		timer = new Timer();
		timer.schedule(new RemindTask(), seconds * 1000, seconds * 1000);
	}

	class RemindTask extends TimerTask {
		Map<String, ArrayList<String>> map = new HashMap<String, ArrayList<String>>();
		String sleepfor;

		public void run() {
			// System.out.println("\n\n\nTask is done here");

			File cronFile = new File("E:/threading_test/Cron.txt");
			BufferedReader br = null;
			BufferedReader configUtils = null;
			File configFile = new File("E:/threading_test/configUtils.txt");

			try {
				br = new BufferedReader(new FileReader(configFile));
				String sleepln = br.readLine();
				sleepfor = sleepln.split(":")[1];
				System.out.println("sleep for " + sleepfor);
				String fileln = br.readLine();
				String[] fileNames = fileln.split(", ");

				fileNames[0] = fileNames[0].substring(("prohibittedFiles:\\").length(), fileNames[0].length());
				for (String s : fileNames) {
					System.out.println("FileName " + s);
				}

				prohibit(fileNames);

			} catch (Exception e) {
				e.printStackTrace();
			}
			try {
				br = new BufferedReader(new FileReader(cronFile));
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			}
			
			String str = null;
			
			try {
				while ((str = br.readLine()) != null) {
					String[] ds = str.split("\t");

					for (int i = 0; i < ds.length; i += 2) {
						if (map.containsKey(ds[i])) {
							ArrayList<String> temp = map.get(ds[i]);
							if (temp.contains(ds[i + 1])) {
								System.out.println("Already contains");
							} else {
								System.out.println("Contains");
								
								temp.add(ds[i + 1]);
								map.put(ds[i], temp);
							}
						} else {
							ArrayList<String> t = new ArrayList<String>();
							t.add(ds[i + 1]);
							map.put(ds[i], t);
						}
					}

				}

				System.out.println("Processess to be executed");

				if (!map.isEmpty()) {
					System.out.println("Calling next thread");
					Thread t2 = new Thread(new JobScheduler(map, Integer.parseInt(sleepfor)));
					t2.start();
				} else {
					System.out.println("No jobs to schedule");
				}

			} catch (IOException e) {
				e.printStackTrace();
			} finally {
				try {
					if (br != null)
						br.close();
					if (configUtils != null)
						configUtils.close();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}

		}

		private void prohibit(String[] fileNames) {
			
			for(String file : fileNames)
			  {
			   File lockFile = new File(file);
			   lockFile.setExecutable(false);
			   lockFile.setReadable(false);
			   lockFile.setWritable(false);
			  }
		}
	}

	/*
	 * public void print(Map<String, ArrayList<String>> map) {
	 * System.out.println("Printing contents"); Iterator iterator =
	 * map.entrySet().iterator(); while (iterator.hasNext()) { Map.Entry
	 * mapEntry = (Map.Entry) iterator.next(); System.out.println("Time: " +
	 * mapEntry.getKey()); ArrayList<String> process = (ArrayList<String>)
	 * mapEntry.getValue(); for (String s : process) {
	 * System.out.println("Process :" + s); } }
	 * 
	 * }
	 */

	public static void main(String args[]) {
		// System.out.println("About to schedule task.");

		Thread t = new Thread(new Runnable() {

			@Override
			public void run() {
				System.out.println("Entering");
				new JobAndConfigReader(10);

			}
		});
		t.start();
		// System.out.println("Task scheduled.");
	}
}
