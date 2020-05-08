package com.persistantcoder.isbntools;

/**
 * Created by Evans K F C on May ,2020
 **/
public class ValidatorISBN {


    public boolean checkISBN(String isbnNumber) {

        if(isbnNumber.length()!=10) throw  new NumberFormatException("ISBN numbers must be 10 digit long");


        int total = 0;

        for(int i= 0; i<10 ;i++){

            total+= isbnNumber.charAt(i) * (10-i);
        }
        return total % 11 == 0;

    }
}
