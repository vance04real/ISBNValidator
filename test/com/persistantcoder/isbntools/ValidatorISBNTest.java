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
    public void checkAValidISBN(){
        boolean result = validator.checkISBN("0140449116");
        assertTrue("first value",result);
        result = validator.checkISBN("0140177396");
        assertTrue("second value",result);

    }

    @Test
    public void checkAnInvalidISBN(){

        boolean result = validator.checkISBN("0140449117");
        assertFalse(result);
    }

    @Test(expected=NumberFormatException.class)
    public void nineDigitsISBNAreNotAllowed(){
        validator.checkISBN("123456789");

    }

}
