package com.myassignment.app;

/* 
*The AU.java implements an application which gives
*a date which is a specific number of dates before 
*the current date
*
*
* @author  Muneer Hudha
* @version 1.0
* @since   2014-07-08 
*
*/

import java.util.*;

public class AU{


	/* A static function that gets an integer as an input and displays a date that many number of before the current date*/
	public static Date getDate(int number){

		Calendar now = Calendar.getInstance();
   		System.out.println("Current date : " + (now.get(Calendar.MONTH) + 1) + "-" + now.get(Calendar.DATE) + "-" + now.get(Calendar.YEAR));

    	// add or subtract days to current date using Calendar.add method
    	now.add(Calendar.DATE, -number);
		Date date=null;
		date=now.getTime();
		return date;

	}



	public static void main(String[] args) {
		
		int number;

		// Scanner to get the number of days as input
		Scanner in = new Scanner(System.in);

		System.out.println("How many days before current date would you like to see? ");
		number = in.nextInt();

		// getDate calls the static function getDate with the int parameter
		System.out.println(getDate(number));	
	}
}