package com.persistantcoder.isbntools;

/**
 * Created by Evans K F C on May ,2020
 **/
public class ValidatorISBN {


    public boolean checkISBN(String isbnNumber) {

        if(isbnNumber.length()!=10) throw  new NumberFormatException("ISBN numbers must be 10 digit long");


        int total = 0;

        for(int i= 0; i<10 ;i++){

            if(!Character.isDigit(isbnNumber.charAt(i))) {
                if(i==9 && isbnNumber.charAt(i)=='X'){
                    total+=10;
                }else{
                    throw new NumberFormatException("ISBN number can only contain numeric digits");
                }
            }else{
                total+= isbnNumber.charAt(i) * (10-i);
            }

        }
        return total % 11 == 0;

    }
}
