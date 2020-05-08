package com.persistantcoder.isbntools;

import org.junit.Before;
import org.junit.Test;


import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * Created by Evans K F C on May ,2020
 **/
public class ValidatorISBNTest {

    private ValidatorISBN validator;

    @Before
    public void setup(){
        validator  = new ValidatorISBN();
    }


    @Test
    public void checkTenDigitValidISBN(){
        boolean result = validator.checkISBN("0140449116");
        assertTrue("first value",result);
        result = validator.checkISBN("0140177396");
        assertTrue("second value",result);

    }

    @Test
    public void checkValidThirteenDigitISBNNumber(){

        boolean result=  validator.checkISBN("9781118771334");
        assertTrue(result);

        result=  validator.checkISBN("9780134685991");
        assertTrue(result);
    }

    @Test
    public void TenDigitISBNsEndingInAnXAreValid(){

        boolean result = validator.checkISBN("012000030X");
        assertTrue(result);

    }

    @Test
    public void checkAThirteenDigitInvalidISBN(){

        boolean result = validator.checkISBN("9780134685999");
        assertFalse(result);
    }



    @Test
    public void checkATenDigitInvalidISBN(){

        boolean result = validator.checkISBN("0140449117");
        assertFalse(result);
    }

    @Test(expected=NumberFormatException.class)
    public void nineDigitsISBNAreNotAllowed(){
        validator.checkISBN("123456789");

    }

    @Test(expected=NumberFormatException.class)
    public void nonNumericISBNsAreNotAllowed(){

        validator.checkISBN("helloworld");
    }



}
