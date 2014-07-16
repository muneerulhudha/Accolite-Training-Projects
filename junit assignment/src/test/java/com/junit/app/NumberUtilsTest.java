package com.junit.app;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.lang.reflect.Constructor;
import java.lang.reflect.Modifier;
import java.math.BigDecimal;
import java.math.BigInteger;

import org.junit.Test;

public class NumberUtilsTest{
	
	// Test for Constants 
	@Test
    public void testConstants() {
        assertEquals(0,NumberUtils.LONG_ZERO.longValue());
        assertEquals(1,NumberUtils.LONG_ONE.longValue());
        assertEquals(-1,NumberUtils.LONG_MINUS_ONE.longValue());
        assertEquals(0,NumberUtils.INTEGER_ZERO.intValue());
        assertEquals(1,NumberUtils.INTEGER_ONE.intValue());
        assertEquals(-1,NumberUtils.INTEGER_MINUS_ONE.intValue());
        assertEquals(0,NumberUtils.SHORT_ZERO.shortValue());
        assertEquals(1,NumberUtils.SHORT_ONE.shortValue());
        assertEquals(-1,NumberUtils.SHORT_MINUS_ONE.shortValue());
        assertEquals(0,NumberUtils.BYTE_ZERO.byteValue());
        assertEquals(1,NumberUtils.BYTE_ONE.byteValue());
        assertEquals(-1,NumberUtils.BYTE_MINUS_ONE.byteValue());
        assertEquals(0.0d,NumberUtils.DOUBLE_ZERO.doubleValue(),0.00001d);
        assertEquals(1.0d,NumberUtils.DOUBLE_ONE.doubleValue(),0.00001d);
        assertEquals(-1.0d,NumberUtils.DOUBLE_MINUS_ONE.doubleValue(),0.00001d);
        assertEquals(0.0f,NumberUtils.FLOAT_ZERO.floatValue(),0.00001f);
        assertEquals(1.0f,NumberUtils.FLOAT_ONE.floatValue(),0.00001f);
        assertEquals(-1.0f,NumberUtils.FLOAT_MINUS_ONE.floatValue(),0.00001f);
    }

	//Test for toInt(String str)
	@Test
    public void testToIntString() {
		assertEquals(123, NumberUtils.toInt("123"));
        assertEquals(0, NumberUtils.toInt("muneer"));
        assertEquals(0, NumberUtils.toInt(""));
        assertEquals(0, NumberUtils.toInt(null));
    }
	
	
	//Test for toInt(String str, int defaultValue)
	@Test
	public void testToIntString1(){
		assertEquals(123, NumberUtils.toInt("123", 10));
		assertEquals(10,NumberUtils.toInt(null, 10));
		assertEquals(10,NumberUtils.toInt("", 10));
		assertEquals(10,NumberUtils.toInt("muneer", 10));
	}
	
	//Test for toLong(String str)
	@Test
    public void testToLongString() {
        assertEquals(123l, NumberUtils.toLong("123"));
        assertEquals(0l, NumberUtils.toLong("muneer"));
        assertEquals(0l, NumberUtils.toLong("1L"));
        assertEquals(0l, NumberUtils.toLong("1l"));
        assertEquals(0l, NumberUtils.toLong(""));
        assertEquals(0l, NumberUtils.toLong(null));
        assertEquals(Long.MAX_VALUE, NumberUtils.toLong(Long.MAX_VALUE+""));
        assertEquals(Long.MIN_VALUE, NumberUtils.toLong(Long.MIN_VALUE+""));
    }

	//Test case for toLong(String str, long defaultValue)
	@Test
	public void testToLongString1(){
		assertEquals(123l, NumberUtils.toLong("123", 10l));
        assertEquals(10l, NumberUtils.toLong("123.45", 10l));
	}
	
	//Test case for toFloat(String str)
	@Test
	public void testToFloat(){
		assertEquals(123.4f, NumberUtils.toFloat("123.4"),0.00001f);
        assertEquals(0.0f, NumberUtils.toFloat("muneer"),0.00001f);
        assertEquals(-123.4f, NumberUtils.toFloat("-123.4"),0.00001f);
        assertEquals(0.0f, NumberUtils.toFloat(""),0.00001f);
        assertEquals(0.0f, NumberUtils.toFloat(null),0.00001f);
        assertEquals(Float.MAX_VALUE, NumberUtils.toFloat(Float.MAX_VALUE+""),0.00001f);
        assertEquals(Float.MIN_VALUE, NumberUtils.toFloat(Float.MIN_VALUE+""),0.00001f);
	}
	
	//Test case for toFLoat(String str, float defaultValue)
	@Test
	public void testToFloat1(){
		assertEquals(123.45f, NumberUtils.toFloat("123.45", 10.0f),0.00001f);
        assertEquals(10.0f, NumberUtils.toFloat("muneer", 10.0f),0.00001f);
	}
	
	//Test case for toDouble(String str)
	@Test
    public void testStringToDoubleString() {
        assertEquals(-123.4d, NumberUtils.toDouble("-123.4"),0.00001d);
        assertEquals(123.4d, NumberUtils.toDouble("123.4"),0.00001d);
        assertEquals(0.0d, NumberUtils.toDouble("muneer"),0.00001d);
        assertEquals(0.0d, NumberUtils.toDouble(""),0.00001d);
        assertEquals(0.0d, NumberUtils.toDouble(null),0.00001d);
        assertEquals(Double.MAX_VALUE, NumberUtils.toDouble(Double.MAX_VALUE+""),0.00001d);
        assertEquals(Double.MIN_VALUE, NumberUtils.toDouble(Double.MIN_VALUE+""),0.00001d);
    }
	
	
	//Test case for toDouble(String str, Double defaultValue)
	@Test
    public void testStringToDoubleStringD() {
        assertEquals(123.45d, NumberUtils.toDouble("123.45", 10.0d),0.00001d);
        assertEquals(10.0d, NumberUtils.toDouble("muneer", 10.0d),0.00001d);
    }
	
	//Test case for toByte(String str)
	@Test
    public void testToByteString() {
        assertEquals(123, NumberUtils.toByte("123"));
        assertEquals(0, NumberUtils.toByte("muneer"));
        assertEquals(0, NumberUtils.toByte(""));
        assertEquals(0, NumberUtils.toByte(null));
    }
	
	//Test case for toByte(String str, byte defaultValue)
	@Test
    public void testToByteString1() {
        assertEquals(123, NumberUtils.toByte("123", (byte) 10));
        assertEquals(10, NumberUtils.toByte("12.34", (byte) 10));
        assertEquals(10, NumberUtils.toByte("muneer", (byte) 10));
    }
	
	//Test case for toShort(String str)
	@Test
    public void testToShortString() {
        assertEquals(12345, NumberUtils.toShort("12345"));
        assertEquals(0, NumberUtils.toShort("muneer"));
        assertEquals(0, NumberUtils.toShort(""));
        assertEquals(0, NumberUtils.toShort(null));
    }
	
	//Test case for toShort(String str, Short defaultValue)
	@Test
	public void testToShortString1(){
		assertEquals(12345, NumberUtils.toShort("12345", (short) 10));
        assertEquals(10, NumberUtils.toShort("muneer", (short) 10));
	}
	
	//Test case for createNumber(String str)
	@Test
	public void testCreateNumber(){
		
		 assertEquals(1.23f,NumberUtils.createNumber("1.23") );
	       // assertEquals(1d,NumberUtils.createNumber("OX1"));
	        
	         assertEquals(23,NumberUtils.createNumber("23"));
	         assertEquals(23d,NumberUtils.createNumber("23D"));
	         assertEquals(2.333f,NumberUtils.createNumber("2.333F"));
	         assertEquals(null,NumberUtils.createNumber("--12"));
	         assertEquals(0xFADE, NumberUtils.createNumber("0xFADE").intValue());
	         assertEquals(Double.valueOf("1.1E200"), NumberUtils.createNumber("1.1E200"));
	         assertEquals(Float.valueOf("1.1E000"), NumberUtils.createNumber("1.1E000"));
	         assertEquals(null, NumberUtils.createNumber(null));
	         assertEquals(Float.valueOf("0.0E000"), NumberUtils.createNumber("0.0E000"));	        
	        
	        try{
	        	NumberUtils.createNumber("");
	        	fail("Expecting numberformatException");
	        }
	        catch(NumberFormatException e)
	        {
	        	
	        }
	        
	        try{
	        	NumberUtils.createNumber("NULL");
	        	fail("Expecting numberformatException");
	        }
	        catch(NumberFormatException e)
	        {
	        	
	        }
	        try
	         {
	           NumberUtils.createNumber("0E.521");
	           fail("Expecting numberformatException");
	         }
	         catch(NumberFormatException e)
	         {
	          
	         }
	         try
	         {
	           NumberUtils.createNumber("1.521E");
	           fail("Expecting numberformatException");
	         }
	         catch(NumberFormatException e)
	         {
	          
	         }
	       /* try{
	        	NumberUtils.createNumber("--12");
	        	fail("Expecting numberformatException");
	        }
	        catch(NumberFormatException e)
	        {
	        	
	        }*/
	                
	}
	
	//Test case for createFloat(String str)
	@Test
	public void testCreateFloat(){
		assertEquals(Float.valueOf("123.45"),NumberUtils.createFloat("123.45"));
		assertEquals(null,NumberUtils.createFloat(null));
	}
	
	@Test
	public void testCreateDouble(){
		assertEquals(Double.valueOf("123.45"),NumberUtils.createDouble("123.45"));
		assertEquals(null,NumberUtils.createDouble(null));
	}

	@Test
	public void testCreateInteger(){
		assertEquals(Integer.valueOf("123"),NumberUtils.createInteger("123"));
		assertEquals(null,NumberUtils.createInteger(null));
	}
	
	@Test
	public void testCreateLong(){
		assertEquals(Long.valueOf("9876543210"),NumberUtils.createLong("9876543210"));
		assertEquals(null,NumberUtils.createLong(null));
	}

	@Test
	public void testCreateBigInteger(){
		assertEquals(new BigInteger("98765"), NumberUtils.createBigInteger("98765"));
        assertEquals(null, NumberUtils.createBigInteger(null));
        assertEquals(new BigInteger("-0"), NumberUtils.createBigInteger("-0"));
        assertEquals(new BigInteger("0"), NumberUtils.createBigInteger("0"));
	}
	
	@Test
	public void testBigDecimal(){
		assertEquals(new BigDecimal("9876543.21"), NumberUtils.createBigDecimal("9876543.21"));
        assertEquals(null, NumberUtils.createBigDecimal(null));
	}
	
	@Test
    public void testMinLong() {
        assertEquals(10, NumberUtils.min(new long[] { 10 }));
        assertEquals(11, NumberUtils.min(new long[] { 11, 17 }));

        assertEquals(-15, NumberUtils.min(new long[] { -15, -4, 0, 1, 3 }));
        assertEquals(-10, NumberUtils.min(new long[] { -5, 0, -10, 5, 10 }));
    }
	
	@Test(expected = IllegalArgumentException.class)
    public void testMinLong_nullArray() {
        NumberUtils.min((long[]) null);
    }
	
	@Test
    public void testMinInt() {
        assertEquals(10, NumberUtils.min(new int[] { 10 }));
        assertEquals(11, NumberUtils.min(new int[] { 11, 17 }));

        assertEquals(-15, NumberUtils.min(new int[] { -15, -4, 0, 1, 3 }));
        assertEquals(-10, NumberUtils.min(new int[] { -5, 0, -10, 5, 10 }));
    }
	
	@Test(expected = IllegalArgumentException.class)
    public void testMinInt_nullArray() {
        NumberUtils.min((int[]) null);
    }
	
	@Test
    public void testMinShort() {
        assertEquals(10, NumberUtils.min(new short[] { 10 }));
        assertEquals(11, NumberUtils.min(new short[] { 11, 17 }));

        assertEquals(-15, NumberUtils.min(new short[] { -15, -4, 0, 1, 3 }));
        assertEquals(-10, NumberUtils.min(new short[] { -5, 0, -10, 5, 10 }));
    }
	
	@Test(expected = IllegalArgumentException.class)
    public void testMinShort_nullArray() {
        NumberUtils.min((short[]) null);
    }

	@Test
    public void testMinByte() {
        assertEquals(10, NumberUtils.min(new byte[] { 10 }));
        assertEquals(11, NumberUtils.min(new byte[] { 11, 17 }));

        assertEquals(-15, NumberUtils.min(new byte[] { -15, -4, 0, 1, 3 }));
        assertEquals(-10, NumberUtils.min(new byte[] { -5, 0, -10, 5, 10 }));
    }
	
	@Test(expected = IllegalArgumentException.class)
    public void testMinByte_nullArray() {
        NumberUtils.min((byte[]) null);
    }
	
	@Test
    public void testMinDouble() {
        assertEquals(10.0, NumberUtils.min(new double[] { 10.0 }),0.0001d);
        assertEquals(11.2, NumberUtils.min(new double[] { 11.2, 17.4 }),0.0001d);

        assertEquals(-15.0, NumberUtils.min(new double[] { -15.0, -4.3, 0.1, 1.2, 3.4 }),0.0001d);
        assertEquals(-10.2, NumberUtils.min(new double[] { -5.2, 0.5, -10.2, 5.3, 10.6 }),0.0001d);
    }
	
	@Test(expected = IllegalArgumentException.class)
    public void testMinDouble_nullArray() {
        NumberUtils.min((double[]) null);
    }

	@Test
    public void testMinFloat() {
        assertEquals(10.0f, NumberUtils.min(new float[] { 10.0f }),0.0001f);
        assertEquals(11.2f, NumberUtils.min(new float[] { 11.2f, 17.4f }),0.0001f);

        assertEquals(-15.0f, NumberUtils.min(new float[] { -15.0f, -4.3f, 0.1f, 1.2f, 3.4f }),0.0001f);
        assertEquals(-10.2f, NumberUtils.min(new float[] { -5.2f, 0.5f, -10.2f, 5.3f, 10.6f }),0.0001f);
    }
	
	@Test(expected = IllegalArgumentException.class)
    public void testMinFloat_nullArray() {
        NumberUtils.min((float[]) null);
    }
	
	@Test
    public void testMaxLong() {
        assertEquals(10, NumberUtils.max(new long[] { 10 }));
        assertEquals(17, NumberUtils.max(new long[] { 11, 17 }));

        assertEquals(3, NumberUtils.max(new long[] { -15, -4, 0, 1, 3 }));
        assertEquals(10, NumberUtils.max(new long[] { -5, 0, -10, 5, 10 }));
    }
	
	@Test(expected = IllegalArgumentException.class)
    public void testMaxLong_nullArray() {
        NumberUtils.max((long[]) null);
    }
	
	@Test
    public void testMaxInt() {
        assertEquals(10, NumberUtils.max(new int[] { 10 }));
        assertEquals(17, NumberUtils.max(new int[] { 11, 17 }));

        assertEquals(3, NumberUtils.max(new int[] { -15, -4, 0, 1, 3 }));
        assertEquals(10, NumberUtils.max(new int[] { -5, 0, -10, 5, 10 }));
    }
	
	@Test(expected = IllegalArgumentException.class)
    public void testMaxInt_nullArray() {
        NumberUtils.max((int[]) null);
    }
	
	@Test
    public void testMaxShort() {
        assertEquals(10, NumberUtils.max(new short[] { 10 }));
        assertEquals(17, NumberUtils.max(new short[] { 11, 17 }));

        assertEquals(3, NumberUtils.max(new short[] { -15, -4, 0, 1, 3 }));
        assertEquals(10, NumberUtils.max(new short[] { -5, 0, -10, 5, 10 }));
    }
	
	@Test(expected = IllegalArgumentException.class)
    public void testMaxShort_nullArray() {
        NumberUtils.max((short[]) null);
    }

	@Test
    public void testMaxByte() {
        assertEquals(10, NumberUtils.max(new byte[] { 10 }));
        assertEquals(17, NumberUtils.max(new byte[] { 11, 17 }));

        assertEquals(3, NumberUtils.max(new byte[] { -15, -4, 0, 1, 3 }));
        assertEquals(10, NumberUtils.max(new byte[] { -5, 0, -10, 5, 10 }));
    }
	
	@Test(expected = IllegalArgumentException.class)
    public void testMaxByte_nullArray() {
        NumberUtils.max((byte[]) null);
    }
	
	@Test
    public void testMaxDouble() {
        assertEquals(10.0, NumberUtils.max(new double[] { 10.0 }),0.0001d);
        assertEquals(17.4, NumberUtils.max(new double[] { 11.2, 17.4 }),0.0001d);

        assertEquals(3.4, NumberUtils.max(new double[] { -15.0, -4.3, 0.1, 1.2, 3.4 }),0.0001d);
        assertEquals(10.6, NumberUtils.max(new double[] { -5.2, 0.5, -10.2, 5.3, 10.6 }),0.0001d);
    }
	
	@Test(expected = IllegalArgumentException.class)
    public void testMaxDouble_nullArray() {
        NumberUtils.max((double[]) null);
    }

	@Test
    public void testMaxFloat() {
        assertEquals(10.0f, NumberUtils.max(new float[] { 10.0f }),0.0001f);
        assertEquals(17.4f, NumberUtils.max(new float[] { 11.2f, 17.4f }),0.0001f);

        assertEquals(3.4f, NumberUtils.max(new float[] { -15.0f, -4.3f, 0.1f, 1.2f, 3.4f }),0.0001f);
        assertEquals(10.6f, NumberUtils.max(new float[] { -5.2f, 0.5f, -10.2f, 5.3f, 10.6f }),0.0001f);
    }
	
	@Test(expected = IllegalArgumentException.class)
    public void testMaxFloat_nullArray() {
        NumberUtils.max((float[]) null);
    }
	
	@Test
    public void testMinimumLong() {
        assertEquals(123L, NumberUtils.min(123L, 123L + 1L, 123L + 2L));
        assertEquals(123L, NumberUtils.min(123L + 1L, 123L, 123L + 2L));
        assertEquals(123L, NumberUtils.min(123L + 1L, 123L + 2L, 123L));
        assertEquals(123L, NumberUtils.min(123L + 1L, 123L, 123L));
        assertEquals(123L, NumberUtils.min(123L, 123L, 123L));
    }

    @Test
    public void testMinimumInt() {
        assertEquals(10, NumberUtils.min(10, 11, 12));
        assertEquals(10, NumberUtils.min(12, 11, 10));
        assertEquals(10, NumberUtils.min(12, 10 ,11));
        assertEquals(10, NumberUtils.min(10, 10, 10));
    }

    @Test
    public void testMinimumShort() {
        final short low = 12;
        final short mid = 12 + 1;
        final short high = 12 + 2;
        assertEquals(low, NumberUtils.min(low, mid, high));
        assertEquals(low, NumberUtils.min(mid, low, high));
        assertEquals(low, NumberUtils.min(mid, high, low));
        assertEquals(low, NumberUtils.min(low, mid, low));
    }

    @Test
    public void testMinimumByte() {
        final byte low = 10;
        final byte mid = 11;
        final byte high = 12;
        assertEquals(low, NumberUtils.min(low, mid, high));
        assertEquals(low, NumberUtils.min(mid, low, high));
        assertEquals(low, NumberUtils.min(mid, high, low));
        assertEquals(low, NumberUtils.min(low, mid, low));
    }

    @Test
    public void testMinimumDouble() {
        final double low = 12.3;
        final double mid = 13.3;
        final double high = 14.3;
        assertEquals(low, NumberUtils.min(low, mid, high), 0.0001d);
        assertEquals(low, NumberUtils.min(mid, low, high), 0.0001d);
        assertEquals(low, NumberUtils.min(mid, high, low), 0.0001d);
    }

    @Test
    public void testMinimumFloat() {
        final float low = 12.3f;
        final float mid = 13.3f;
        final float high = 14.3f;
        assertEquals(low, NumberUtils.min(low, mid, high), 0.0001f);
        assertEquals(low, NumberUtils.min(mid, low, high), 0.0001f);
        assertEquals(low, NumberUtils.min(mid, high, low), 0.0001f);
    }
	
	@Test
    public void testMaximumLong() {
        assertEquals(123L + 2L, NumberUtils.max(123L, 123L + 1L, 123L + 2L));
        assertEquals(123L + 2L, NumberUtils.max(123L + 1L, 123L, 123L + 2L));
        assertEquals(123L + 2L, NumberUtils.max(123L + 1L, 123L + 2L, 123L));
        assertEquals(123L + 1L, NumberUtils.max(123L + 1L, 123L, 123L));
        assertEquals(123L, NumberUtils.max(123L, 123L, 123L));
    }

    @Test
    public void testMaximumInt() {
        assertEquals(12, NumberUtils.max(10, 11, 12));
        assertEquals(12, NumberUtils.max(12, 11, 10));
        assertEquals(12, NumberUtils.max(12, 10 ,11));
        assertEquals(10, NumberUtils.max(10, 10, 10));
    }

    @Test
    public void testMaximumShort() {
        final short low = 12;
        final short mid = 12 + 1;
        final short high = 12 + 2;
        assertEquals(high, NumberUtils.max(low, mid, high));
        assertEquals(high, NumberUtils.max(mid, low, high));
        assertEquals(high, NumberUtils.max(mid, high, low));
        assertEquals(mid, NumberUtils.max(low, mid, low));
    }

    @Test
    public void testMaximumByte() {
        final byte low = 10;
        final byte mid = 11;
        final byte high = 12;
        assertEquals(high, NumberUtils.max(low, mid, high));
        assertEquals(high, NumberUtils.max(mid, low, high));
        assertEquals(high, NumberUtils.max(mid, high, low));
        assertEquals(mid, NumberUtils.max(low, mid, low));
    }

    @Test
    public void testMaximumDouble() {
        final double low = 12.3;
        final double mid = 13.3;
        final double high = 14.3;
        assertEquals(high, NumberUtils.max(low, mid, high), 0.0001d);
        assertEquals(high, NumberUtils.max(mid, low, high), 0.0001d);
        assertEquals(high, NumberUtils.max(mid, high, low), 0.0001d);
    }

    @Test
    public void testMaximumFloat() {
        final float low = 12.3f;
        final float mid = 13.3f;
        final float high = 14.3f;
        assertEquals(high, NumberUtils.max(low, mid, high), 0.0001f);
        assertEquals(high, NumberUtils.max(mid, low, high), 0.0001f);
        assertEquals(high, NumberUtils.max(mid, high, low), 0.0001f);
    }
	
    @Test
    public void testIsNumber()
    {
    	/*try
    	{
    		//String a;
    		assertTrue(NumberUtils.isNumber(""));
    		fail("String must not be empty");
    	}
    	catch(Exception e)
    	{
    		
    	}*/
    	assertTrue(NumberUtils.isNumber("1.1f"));
    	assertTrue(NumberUtils.isNumber("6475E+5"));
    	assertTrue(NumberUtils.isNumber("-123.4"));
    	assertTrue(NumberUtils.isNumber("-0xff"));
    	assertTrue(NumberUtils.isNumber("76L"));
    	assertTrue(NumberUtils.isNumber(".1f"));
    	assertTrue(NumberUtils.isNumber("5.6d"));
    	assertTrue(NumberUtils.isNumber("0123"));
    	assertTrue(NumberUtils.isNumber("6."));
    	assertTrue(NumberUtils.isNumber("0xABCD"));
    	
    }


}