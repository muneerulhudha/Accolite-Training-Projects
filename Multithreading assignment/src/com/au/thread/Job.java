package com.au.thread;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.concurrent.Callable;

@SuppressWarnings("rawtypes")
public class Job implements Callable {

	Thread t;
	String classToExecute;
	int sleepfor;

	// JobSchedulerTest j;
	Job(String classToExecute, int sleepTime) {
		// this.count = count;
		// this.j=j;
		this.sleepfor = sleepTime;
		this.classToExecute = classToExecute;
		System.out.println("Initalised");
	}

	@SuppressWarnings("unchecked")
	public String call() {
		
		//System.out.println("Running job");
		Class c = null;
		try {
			c = Class.forName(classToExecute);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		Constructor con = null;
		try {
			con = c.getConstructor();
		} catch (SecurityException e) {
			e.printStackTrace();
		} catch (NoSuchMethodException e) {
			e.printStackTrace();
		}
		Object object = null;
		try {
			object = con.newInstance();
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		}

		Thread t = new Thread((Runnable) object);
		//System.out.println("Sleeping");
		try {
			Thread.sleep(sleepfor);
			//System.out.println("Woke up");
		} catch (InterruptedException e) {
			e.printStackTrace();
			return "failed";
		}
		t.start();
		
		return "success";

	}

}
