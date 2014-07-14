package com.assignment.app;

import java.util.Scanner;

public class Count_without_using_collections {

	static int size_of_string;
	
	
	//method to validate the string
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
	
	public static int countString(String str){
		
		int count=0,i;
		
		//Using the default value of 0 in integers
		int[] array=new int[256];
		
		size_of_string = str.length();
		
		//Using array indices as a kind of Hash
		for(i=0;i<size_of_string;i++)
		{
			if(array[str.charAt(i)]==0){
				array[str.charAt(i)]++;
				count++;
			}
		}		
		
		return count;
	}
	
	public static void main(String args[])
	{
		// Considering small and capital letters as different characters
		String input_string;
		
		// Receiving the input from the user
		Scanner input=new Scanner(System.in);
		System.out.println("Enter the string");
		input_string=input.nextLine();
		
		if(!isValidString(input_string))
		{
			System.out.println("Not a valid String");
			System.exit(0);
		}
		
		//Printing the number of unique characters
		System.out.println("No. of unique characters "+countString(input_string));
	}
}
