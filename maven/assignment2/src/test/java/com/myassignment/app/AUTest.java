package com.myassignment.app;

import java.util.Calendar;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

/**
 *
 * Unit test for the AU.java App which gives a date which is 
 * a specific number of dates before the current date.
 *
 */
public class AUTest 
    extends TestCase
{
    /**
     * Create the test case
     *
     * @param testName name of the test case
     */
    public AUTest( String testName )
    {
        super( testName );
    }

    /**
     * @return the suite of tests being tested
     */
    public static Test suite()
    {
        return new TestSuite( AUTest.class );
    }

    /**
     * Rigourous Test :-)
     */
    public void testApp()
    {
        assertTrue( true );
    }


    // A test case that will fail was added to see how it affects when packaging is done.
    /*public void testApp1()
    {
        assertTrue( false );
    }*/

    /*
     * Test to validate the date returned by the static method from AU.java
     */
    public void testApp1()
    {
        //assert(org.apache.commons.lang3.StringUtils.isNotBlank(new Date().toString()));
        assertEquals(Calendar.getInstance().getTime().toString(),AU.getDate(0).toString());
    }

}
