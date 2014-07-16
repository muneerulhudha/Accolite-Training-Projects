package com.junit.app;

/**
 * Test cases for the Fraction class
 * @version: 1.0 
 * Owner: Narendrakumar NJ
 */
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import org.junit.Test;

public class FractionTest  {
    
    // Test case for testing the constants
    @Test
    public void testConstants() {
        assertEquals(0,Fraction.ZERO.getNumerator());
        assertEquals(1,Fraction.ZERO.getDenominator());
        
        assertEquals(1,Fraction.ONE.getNumerator());
        assertEquals(1,Fraction.ONE.getDenominator());
        
        assertEquals(1,Fraction.ONE_HALF.getNumerator());
        assertEquals(2,Fraction.ONE_HALF.getDenominator());
        
        assertEquals(1,Fraction.ONE_THIRD.getNumerator());
        assertEquals(3,Fraction.ONE_THIRD.getDenominator());
        
        assertEquals(2,Fraction.TWO_THIRDS.getNumerator());
        assertEquals(3,Fraction.TWO_THIRDS.getDenominator());
        
        assertEquals(1,Fraction.ONE_QUARTER.getNumerator());
        assertEquals(4,Fraction.ONE_QUARTER.getDenominator());
        
        assertEquals(2,Fraction.TWO_QUARTERS.getNumerator());
        assertEquals(4,Fraction.TWO_QUARTERS.getDenominator());
        
        assertEquals(3,Fraction.THREE_QUARTERS.getNumerator());
        assertEquals(4,Fraction.THREE_QUARTERS.getDenominator());
        
        assertEquals(1,Fraction.ONE_FIFTH.getNumerator());
        assertEquals(5,Fraction.ONE_FIFTH.getDenominator());
        
        assertEquals(2,Fraction.TWO_FIFTHS.getNumerator());
        assertEquals(5,Fraction.TWO_FIFTHS.getDenominator());
        
        assertEquals(3,Fraction.THREE_FIFTHS.getNumerator());
        assertEquals(5,Fraction.THREE_FIFTHS.getDenominator());
        
        assertEquals(4,Fraction.FOUR_FIFTHS.getNumerator());
        assertEquals(5,Fraction.FOUR_FIFTHS.getDenominator());
      }
    
    @Test
    public void testConstructor()
    {
    	Fraction f;
    	
    	f=Fraction.getFraction(0, 1);
    	assertEquals(0,f.getNumerator());
        assertEquals(1,f.getDenominator());
        
    	f=Fraction.getFraction(1, 1);
    	assertEquals(1,f.getNumerator());
        assertEquals(1,f.getDenominator());
        
    	
    	f=Fraction.getFraction(1, 2);
    	assertEquals(1,f.getNumerator());
        assertEquals(2,f.getDenominator());
        
    	
    	f=Fraction.getFraction(1, 5);
    	assertEquals(1,f.getNumerator());
        assertEquals(5,f.getDenominator());
        
        f=Fraction.getFraction(2, 5);
        assertEquals(2,f.getNumerator());
        assertEquals(5,f.getDenominator());
        
        f=Fraction.getFraction(1, -1);
        assertEquals(-1,f.getNumerator());
        assertEquals(1,f.getDenominator());
        
        		//Checking zero
        try{
        	f=Fraction.getFraction(1,0);
        	fail("Expecting divide by 0");
        }
        catch(ArithmeticException e)
        {
        }
           	//Checking -ve denominator with mini numerator
        try{
        	f=Fraction.getFraction(Integer.MIN_VALUE,-1);
        	fail("Expecting divide can't negate");
        }
        catch(ArithmeticException e)
        {
        }
    }
    
    @Test
    public void testWithWhole()
    {
    	Fraction f;
    	
    	f=Fraction.getFraction(5,1,2);
    	assertEquals(11,f.getNumerator());
        assertEquals(2,f.getDenominator());
        
        
        
        f=Fraction.getFraction(-5,1,4);
    	assertEquals(-21,f.getNumerator());
        assertEquals(4,f.getDenominator());
        
        
    	//testing denominator 0
        try{
        	f=Fraction.getFraction(1,1,0);
        	fail("expecting denominator is zero");
        }
        catch(ArithmeticException e)
        {
        }
        	//testing denominator <0
        try{
        	f=Fraction.getFraction(1,1,-1);
        	fail("expecting denominator is negative");
        }
        catch(ArithmeticException e)
        {
        }
     		
        
        	//testing numerator 0
        try{
        	f=Fraction.getFraction(1,-1,3);
        	fail("expecting numerator is -ve");
        }
        catch(ArithmeticException e)
        {
        }
        
        	//testing numerator >maxi integer
        try{
        	f=Fraction.getFraction(0,Integer.MAX_VALUE+1,4);
        	fail("expecting  maxi integer");
        }
        catch(ArithmeticException e)
        {
        }
        
        try{
        	f=Fraction.getFraction(3,Integer.MAX_VALUE+1,4);
        	fail("expecting  maxi integer");
        }
        catch(ArithmeticException e)
        {
        }
        
       
    }
    
    @Test
    public void testReducedFraction()
    {
    	Fraction f;
    	
    	f=Fraction.getReducedFraction(4,8);
    	assertEquals(1,f.getNumerator());
    	assertEquals(2,f.getDenominator());
    	
    		//numerator 0
    	f=Fraction.getReducedFraction(0,3);
    	assertEquals(0,f.getNumerator());
    	assertEquals(1,f.getDenominator());
    	
    		//denominator <0
    	
    	f=Fraction.getReducedFraction(2,-2);
    	assertEquals(-1,f.getNumerator());
    	assertEquals(1,f.getDenominator());
    	
    		//denominator 0 test
    	try{
    		f=Fraction.getReducedFraction(1,0);
    		fail("expecting deonimator is 0");
    	}
    	catch(ArithmeticException e)
    	{
    	}
    }
    
    @Test
    public void testDoubleValueFunction()
    {
    	Fraction f;
    	
    	f=Fraction.getFraction(0.50);
    	assertEquals(1,f.getNumerator());
    	assertEquals(2,f.getDenominator());
    	
    	
    	f=Fraction.getFraction(0.666666d);
    	assertEquals(2,f.getNumerator());
    	assertEquals(3,f.getDenominator());
    	
    		//denominator is 0
    	try{
    		f=Fraction.getFraction(Double.NEGATIVE_INFINITY);
    		fail("expecting denominator 0");
    	}
    	catch(ArithmeticException e)
    	{
    	}
    	
    	try{
    		f=Fraction.getFraction(Double.POSITIVE_INFINITY);
    		fail("expecting denominator 0");
    	}
    	catch(ArithmeticException e)
    	{
    	}
    		//not a number
    	try{
    		f=Fraction.getFraction(Double.NaN);
    		fail("expecting not a number");
    	}
    	catch(ArithmeticException e)
    	{
    	}
    	
    		// >maximum integer
    	try{
    		f=Fraction.getFraction(Integer.MAX_VALUE+1);
    		fail("expecting denominator 0");
    	}
    	catch(ArithmeticException e)
    	{
    	}
    }
    
    @Test
    public void testFractionWithString()
    {
    	Fraction f;
    	
    	f=Fraction.getFraction("3.5");
    	assertEquals(7,f.getNumerator());
    	assertEquals(2,f.getDenominator());
    	
    	f=Fraction.getFraction("4 1/3");
    	assertEquals(13,f.getNumerator());
    	assertEquals(3,f.getDenominator());
    	
    	f=Fraction.getFraction("5/2");
    	assertEquals(5,f.getNumerator());
    	assertEquals(2,f.getDenominator());
    	
    	f=Fraction.getFraction("15");
    	assertEquals(15,f.getNumerator());
    	assertEquals(1,f.getDenominator());
    	
    		//null string
    	try{
    		f=Fraction.getFraction(null);
    		fail("expecting null string");
    	}
    	catch(IllegalArgumentException e)
    	{
    		
    	}
    		//invalid number format
    	try{
    		f=Fraction.getFraction("abc");
    		fail("expecting invalid string");
    	}
    	catch(IllegalArgumentException e)
    	{
    		
    	}
    }
    
    @Test
    public void testPropers()
    {
    	Fraction f;
    	
    	f=Fraction.getFraction(1,1,4);
    	assertEquals(1,f.getProperWhole());
    	assertEquals(1,f.getProperNumerator());
    	assertEquals(4,f.getDenominator());
    	
    	f=Fraction.getFraction(7,4);
    	assertEquals(1,f.getProperWhole());
    	assertEquals(3,f.getProperNumerator());
    	assertEquals(4,f.getDenominator());
    	
    	f=Fraction.getFraction(-7,4);
    	assertEquals(-1,f.getProperWhole());
    	assertEquals(3,f.getProperNumerator());
    	assertEquals(4,f.getDenominator());
    	
    }
    
    @Test
    public void testValues()
    {
    	Fraction f;
    	
    	f=Fraction.getFraction(4,5,2);
    	assertEquals(6.50f,f.floatValue(),0.00001f);
    	assertEquals(6L,f.longValue());
    	assertEquals(6,f.intValue());
    	assertEquals(6.5d,f.doubleValue(),0.00001d);
    	
    }
    
    @Test
    public void testReduce()
    {
    	Fraction f,output;
    	
    	f=Fraction.getFraction(4,8);
    	output=f.reduce();
    	assertEquals(1,output.getNumerator());
    	assertEquals(2,output.getDenominator());
    	
    	f=Fraction.getFraction(0,10);
    	output=f.reduce();
    	assertEquals(0,output.getNumerator());
    	assertEquals(1,output.getDenominator());
    	
    	
    }
    
    @Test
    public void testInvert()
    {
    	Fraction f,output;
    	
    	f=Fraction.getFraction(3,5);
    	output=f.invert();
    	assertEquals(5,output.getNumerator());
    	assertEquals(3,output.getDenominator());
    	
    	f=Fraction.getFraction(-1,1);
        output=f.invert();
        assertEquals(-1, f.getNumerator());
        assertEquals(1, f.getDenominator());
    	try{
    		f=Fraction.getFraction(0,1);
    		output=f.invert();
    		fail("expecting divide by 0");
    	
    	}
    	catch(ArithmeticException e)
    	{
    		
    	}
    	 f = Fraction.getFraction(Integer.MIN_VALUE, 1);
         try {
             output=f.invert();
             fail("expecting ArithmeticException");
         } 
         catch (final ArithmeticException ex)
         {
        	 
         }

    	
    	
    }
    
    @Test
    public void testNegate()
    {
    	Fraction f;
    	
    	f=Fraction.getFraction(2,4);
        f=f.negate();
        assertEquals(-2, f.getNumerator());
        assertEquals(4, f.getDenominator());
        
        f=Fraction.getFraction(-3,5);
        f=f.negate();
        assertEquals(3,f.getNumerator());
        assertEquals(5,f.getDenominator());
        
        try{
        	f=Fraction.getFraction(Integer.MIN_VALUE,2);
        	f=f.negate();
        	fail("Expecting ArithmeticException");
        }
        catch(ArithmeticException e)
        {
        }

    }
    
    @Test
    public void testAbs()
    {
    	Fraction f;
    	
    	f=Fraction.getFraction(2,3);
        f=f.abs();
        assertEquals(2, f.getNumerator());
        assertEquals(3, f.getDenominator());
        
        f = Fraction.getFraction(-2,3);
        f = f.abs();
        assertEquals(2, f.getNumerator());
        assertEquals(3, f.getDenominator());
    }
    
    @Test
    public void testPow()
    {
    	Fraction f,output;
    	
    	f=Fraction.getFraction(2,3);
    	output=f.pow(2);
    	assertEquals(4,output.getNumerator());
    	assertEquals(9,output.getDenominator());
    	
    	f=Fraction.getFraction(-1,2);
    	output=f.pow(2);
    	assertEquals(1,output.getNumerator());
    	assertEquals(4,output.getDenominator());
    	
    	f=Fraction.getFraction(2,4);
    	output=f.pow(0);
    	assertEquals(1,output.getNumerator());
    	assertEquals(1,output.getDenominator());
    	
    	f=Fraction.getFraction(2,4);
    	output=f.pow(-1);
    	assertEquals(4,output.getNumerator());
    	assertEquals(2,output.getDenominator());
    	
    	f=Fraction.getFraction(1,3);
    	output=f.pow(3);
    	assertEquals(1,output.getNumerator());
    	assertEquals(27,output.getDenominator());
    }
    
    @Test
    public void testAdd()
    {
    	Fraction f1,f2,output;
    	
    	f1=Fraction.getFraction(3,4);
    	f2=Fraction.getFraction(1,4);
    	output=f1.add(f2);
    	assertEquals(1,output.getNumerator());
    	assertEquals(1,output.getDenominator());
    	
    	f1=Fraction.getFraction(0,4);
    	f2=Fraction.getFraction(3,4);
    	output=f1.add(f2);
    	assertEquals(3,output.getNumerator());
    	assertEquals(4,output.getDenominator());
    	
    	//f2 null
    	try{
    		f1=Fraction.getFraction(1,2);
    		f2=null;
    		output=f1.add(f2);
    		fail("expecting IllegalArgumentException");
    	}
    	catch(IllegalArgumentException e)
    	{
    		
    	}
    		// value>Integer.Max
    	try{
    		
    		f1=Fraction.getFraction(Integer.MAX_VALUE,1);
    		f2=Fraction.getFraction(1,2);
    		output=f1.add(f2);
    		fail("expecting ArithmeticException");
    	}
    	catch(ArithmeticException e)
    	{
    		
    	}
    }
    
    @Test
    public void testSubtract()
        {
        	Fraction f1,f2,output;
        	
        	f1=Fraction.getFraction(3,4);
        	f2=Fraction.getFraction(1,4);
        	output=f1.subtract(f2);
        	assertEquals(1,output.getNumerator());
        	assertEquals(2,output.getDenominator());
        	
        		//f2 null
        	try{
        		f1=Fraction.getFraction(1,2);
        		f2=null;
        		output=f1.subtract(f2);
        		fail("expecting IllegalArgumentException");
        	}
        	catch(IllegalArgumentException e)
        	{
        		
        	}
        	
        		//numerator 0
        	try{
        		f1=Fraction.getFraction(1,2);
        		f2=Fraction.getFraction(0,0);
        		output=f1.subtract(f2);
        		fail("expecting ArithemticException");
        	}
        	catch(ArithmeticException e)
        	{
        		
        	}
        		// value>Integer.Max
        	try{
        		
        		f1=Fraction.getFraction(Integer.MAX_VALUE+2,1);
        		f2=Fraction.getFraction(1,2);
        		output=f1.subtract(f2);
        		fail("expecting ArithmeticException");
        	}
        	catch(ArithmeticException e)
        	{
        		
        	}
    }
    @Test
    public void testingMultiplyBy()
    {
    	Fraction f1,f2,output;
    	
    	f1=Fraction.getFraction(2,4);
    	f2=Fraction.getFraction(1,2);
    	output=f1.multiplyBy(f2);
    	assertEquals(1,output.getNumerator());
    	assertEquals(4,output.getDenominator());
    	
    	f1=Fraction.getFraction(-2,4);
    	f2=Fraction.getFraction(1,2);
    	output=f1.multiplyBy(f2);
    	assertEquals(-1,output.getNumerator());
    	assertEquals(4,output.getDenominator());
   
    	
    			//IllegalArgumentException
    	try
    	{
    		f1=Fraction.getFraction(1,2);
    		f2=null;
    		output=f1.multiplyBy(f2);
    		fail("expecting IllegalArgumentException");
    	}
    	catch(IllegalArgumentException e)
    	{
    		
    	}
    			//ArithmeticException 
    	try{
    		f1=Fraction.getFraction(Integer.MAX_VALUE,1);
    		f2=Fraction.getFraction(2,1);
    		output=f1.multiplyBy(f2);
    		fail("expecting arithmeticException");
    	}
    	catch(ArithmeticException e)
    	{
    		
    	}
    }
    
    @Test
    public void testDivideBy()
    {
    	Fraction f1,f2,output;
    	
    	f1=Fraction.getFraction(2,4);
    	f2=Fraction.getFraction(2,4);
    	output=f1.divideBy(f2);
    	assertEquals(1,output.getNumerator());
    	assertEquals(1,output.getDenominator());
    	
    		//checking null 
    	try{
    		f1=Fraction.getFraction(2,4);
    		f2=null;
    		output=f1.divideBy(f2);
    		fail("IllegalArgumentException expected");
    	}
    	catch(IllegalArgumentException e)
    	{
    		
    	}
    		//checking divide by 0 
    	try{
    		f1=Fraction.getFraction(2,4);
    		f2=Fraction.getFraction(0,1);
    		output=f1.divideBy(f2);
    		fail("Divide by 0 expected");
    	}
    	catch(ArithmeticException e)
    	{
    		
    	}
    		//checking >Integer.Max 
    	try {
            
    		f1= Fraction.getFraction(1,Integer.MAX_VALUE);
            f2=Fraction.getFraction(Integer.MAX_VALUE,1);
            output=f1.divideBy(f2);  // should overflow
            fail("expecting ArithmeticException");
        
    	} 
    	catch (ArithmeticException ex) 
    	{
    	}
    }
    
    @Test
    public void testEquals()
    {
    	Fraction f1,f2;
    	
    	f1=Fraction.getFraction(1,2);
    	f2=Fraction.getFraction(-1,-2);
    	assertEquals(true,f1.equals(f2));
    	
    	f1=Fraction.getFraction(1,2);
    	f2=Fraction.getFraction(1,2);
    	assertEquals(true,f1.equals(f2));
    }
    
    @Test
    public void testHashCode()
    {
    	Fraction f1,f2;
    	
    	f1=Fraction.getFraction(3,4);
    	f2=Fraction.getFraction(3,4);
    	assertTrue(f1.hashCode()==f2.hashCode());
    	
    	f1=Fraction.getFraction(1,4);
    	f2=Fraction.getFraction(3,4);
    	assertTrue(f1.hashCode()!=f2.hashCode());
    }
    
    @Test
    public void testCompareTo()
    {	
    	Fraction f1,f2;
    	
    	f1=Fraction.getFraction(1,2);
    	f2=Fraction.getFraction(2,4);
    	assertTrue(f1.compareTo(f1)==0);
    	assertTrue(f1.compareTo(f2)==0);
    	
    	f1=Fraction.getFraction(3,5);
    	f2=Fraction.getFraction(1,5);
    	assertTrue(f1.compareTo(f2)>0);
    	
    	f1=Fraction.getFraction(2,5);
    	f2=Fraction.getFraction(6,5);
    	assertTrue(f1.compareTo(f2)<0);
    	
    	f1=Fraction.getFraction(1,2);
    	f2=Fraction.getFraction(1,2);
    	assertTrue(f1.compareTo(f2)==0);
    	
    	try{
    		f1=Fraction.getFraction(2,4);
    		f2=null;
    		f1.compareTo(f2);
    		fail("Expecting null pointer exception");
    	}
    	catch(NullPointerException e)
    	{
    		
    	}
    	
    }
    
    @Test
    public void testToString()
    {
    	Fraction f;
    	
    	f=Fraction.getFraction(1,4);
    	assertEquals("1/4",f.toString());
    }
    
    @Test
    public void testToProperString()
    {
    	Fraction f;
    	
    	f=Fraction.getFraction(3,1,4);
    	assertEquals("3 1/4",f.toProperString());
    	
    	f=Fraction.getFraction(-3,1,4);
    	assertEquals("-3 1/4",f.toProperString());
    	
    	f=Fraction.getFraction(-3,0,4);
    	assertEquals("-3",f.toProperString());
    	
    	f=Fraction.getFraction(5,5);
    	assertEquals("1",f.toProperString());
    	
    }
}	

