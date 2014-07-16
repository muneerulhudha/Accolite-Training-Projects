package com.junit.app;

import org.junit.Test;

import static org.junit.Assert.*;

public class IEEE754rUtilsTest{
	
	@Test
	public void testConstructor()
	{
		IEEE754rUtils a= new IEEE754rUtils();
	}
	
   @Test
    public void testMinDoubleArray() 
    {
       double[] doub_arr={3.3,5.5,4.4};
       assertEquals(3.3,IEEE754rUtils.min(doub_arr),0.1);
    
       try
       {  	 
    	 IEEE754rUtils.min( (double[]) null);
    	 fail("IllegalArgumentException expected for null input");
       }
       catch(Exception e)
       {
    	   
       }
       
       try
       {
    	 double doub_arr2[]= new double[0];
    
    	 IEEE754rUtils.min(doub_arr2);
    	 fail("IllegalArgumentException expected for empty input");
       }
       catch(Exception e)
       {
    	   
       }
    }
   
   @Test
   public void testMinFloatArray() 
   {
      float float_arr[]={3.3f,4.4f,5.5f}; 
        
      assertEquals(3.3f,IEEE754rUtils.min(float_arr),0.00001f);
    
     try
     {
  	      	 
    	 IEEE754rUtils.min( (float[]) null);
    	 fail("The Array must not be null");
     }
     catch(Exception e)
     {
  	   
     }
    
     try
     {
  	   	float float_arr2[]= new float[0];
  	  	IEEE754rUtils.min(float_arr2);
  	  	fail("Array cannot be empty.");
     }
     catch(Exception e)
     {
  	   
     }
   }
   
   @Test
   public void testMinDouble() 
   {
     double d1=3.3,d2=4.4, d3=5.5;
     
     assertTrue(IEEE754rUtils.min(d1,d2,d3)==3.3);
     
     assertEquals(IEEE754rUtils.min(Double.NaN,Double.NaN,Double.NaN),Double.NaN,0.1);
     
    
     d1=-10.5;
     d2=3.3;
  
     assertTrue(IEEE754rUtils.min(d1,d2)==-10.5);
  
     assertEquals(IEEE754rUtils.min(Double.NaN,Double.NaN),Double.NaN,0.1);
     assertEquals(IEEE754rUtils.min(3.5,Double.NaN),3.5,0.1);
 
   }
   
   @Test
   public void testMinFloat() 
   {
	   float f1=3.3f, f2=-4.4f,f3=5.5f;
 
	   assertTrue(IEEE754rUtils.min(f1,f2,f3)==-4.4f);
	   assertEquals(IEEE754rUtils.min(Float.NaN,Float.NaN,Float.NaN),Float.NaN,0.01f);
 
 
	   f1=0.0f;
	   f2=4.5f;
	   assertTrue(IEEE754rUtils.min(f1,f2) == 0.0f);
	   assertEquals(IEEE754rUtils.min(Float.NaN,Float.NaN),Float.NaN,0.01f);
	   assertEquals(IEEE754rUtils.min(1.2f,Float.NaN),1.2f,0.01f);
   }
   
   @Test
   public void testMaxDoubleArray() 
   {
	   double[] doub_arr={3.3,4.4,5.5};

	   assertEquals(IEEE754rUtils.max(doub_arr),5.5,0.1);

	   try
	   { 
		   IEEE754rUtils.max( (double[]) null);
		   fail("IllegalArgumentException expected for null input");
	   }
	   catch(Exception e)
	   {
	   
	   }
	   try
	   {
		   double doub_arr2[]= new double[0];

		   IEEE754rUtils.max(doub_arr2);
		   fail("IllegalArgumentException expected for empty input");
	   }
	   catch(Exception e)
	   {
	   
	   }
   }
   
   @Test
   public void testMaxFloatArray() 
   {
	   float farr[]={3.3f,4.4f,5.5f}; 
	   assertTrue(5.5f==IEEE754rUtils.max(farr));


	   try
	   {
      	 	IEEE754rUtils.max( (float[]) null);
      	 	fail("The Array must not be null");
	   }
	   catch(Exception e)
	   {
   
	   }

	   try
	   {
		   float farr2[]= new float[0];

		   IEEE754rUtils.max(farr2);
		   fail("Array cannot be empty.");
	   }
	   catch(Exception e)
	   {
   
	   }
   }
   
   @Test
   public void testForMaxDouble() 
   {
	   double d1,d2,d3;
	   d1=5.5; d2=4.4; d3=3.3;

	   assertTrue(IEEE754rUtils.max(d1,d2,d3)==5.5);
	   assertEquals(IEEE754rUtils.max(Double.NaN,Double.NaN,Double.NaN),Double.NaN,0.1);


	   d1=-3.5;
	   d2=3.5;

	   assertTrue(IEEE754rUtils.max(d1,d2)==3.5);
	   assertEquals(IEEE754rUtils.max(Double.NaN,Double.NaN),Double.NaN,0.1);
	   assertEquals(IEEE754rUtils.max(Double.NaN,4.5),4.5,0.1);
	   assertEquals(IEEE754rUtils.max(5.5,Double.NaN),5.5,0.1);
   }
   
   @Test
   public void testMaxFloat() 
   { 
	   float f1,f2,f3;
	   f1=3.3f;
	   f2=-4.4f;
	   f3=5.5f;

	   assertTrue(IEEE754rUtils.max(f1,f2,f3)==5.5f);
	   assertEquals(IEEE754rUtils.max(Float.NaN,Float.NaN,Float.NaN),Float.NaN,0.01f);

	   f1=0.0f;
	   f2=4.5f;
	   assertTrue(IEEE754rUtils.max(f1,f2)==4.5f);
	   assertEquals(IEEE754rUtils.max(Float.NaN,Float.NaN),Float.NaN,0.01f);
	   assertEquals(IEEE754rUtils.max(1.1f,Float.NaN),1.1f,0.01f);
   }
}


  