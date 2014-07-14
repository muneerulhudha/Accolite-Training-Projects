package com.assignment.app;

import java.util.*;

public class Count_using_collections {

	static int size_of_string;
	
	public static boolean isValidString(String str){
		
		size_of_string=str.length();
		if(size_of_string<=0)
		{
			return false;
		}
		else
		{
			return true;
		}
		
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public static int countString(String str){
		
		int count=0,i;
		
		Map m=new HashMap();
		
		size_of_string = str.length();
		
		for(i=0;i<size_of_string;i++)
		{
			if(m.put(str.charAt(i),1)==null)
				count++;
		}
				
		return count;
	}
	
	public static void main(String args[])
	{
		String input_string;
		
		//int i,count=0,sizeOfString;
		
		System.out.println("Enter the string");
		Scanner inputScanner=new Scanner(System.in);
		input_string=inputScanner.nextLine();
				
			
		//Validating the string
		if(!isValidString(input_string))
		{
			System.out.println("Not a valid String");
			System.exit(0);
		}
			
		System.out.println("No. of unique characters "+countString(input_string));		
		
	}
	
}
