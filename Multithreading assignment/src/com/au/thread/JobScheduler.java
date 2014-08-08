package com.au.thread;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class JobScheduler extends Thread {
	String result;
	Map<String, ArrayList<String>> jobs = new HashMap<String, ArrayList<String>>();
	int sleepfor;

	JobScheduler(Map<String, ArrayList<String>> s, int sleepTime) {
		jobs = s;
		this.sleepfor = sleepTime;
		System.out.println("Sleep for :" + sleepfor);
	}

	public void setResult(String st) {
		System.out.println("Result :" + st);
		this.result = st;
	}

	public void run() {
		//System.out.println("Executing jobscheduler");
		//System.out.println("Jobs to schedule");

		String currentTime = new SimpleDateFormat("HH:mm").format(new Date());
		if (jobs.containsKey(currentTime)) {
			//System.out.println("Jobs are there to execute");
			ArrayList<String> toBeDone = jobs.get(currentTime);
			ExecutorService service = Executors.newFixedThreadPool(10);
			for (String s : toBeDone) {
				System.out.println("Process :" + s);

				@SuppressWarnings("unchecked")
				Future<String> future = service.submit(new Job(s, sleepfor));
				try {
					System.out.println("Result: " + future.get());
				} catch (InterruptedException e) {
					e.printStackTrace();
				} catch (ExecutionException e) {
					e.printStackTrace();
				}
			}

		}

	}

}
