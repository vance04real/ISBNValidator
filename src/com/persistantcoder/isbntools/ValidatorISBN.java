package com.persistantcoder.isbntools;

/**
 * Created by Evans K F C on May ,2020
 **/
public class ValidatorISBN {


    public boolean checkISBN(String isbnNumber) {

        if (isbnNumber.length() == 13) {
            int total = 0;

            for (int i = 0; i < 13; i++) {

                if (i % 2 == 0) {
                    total += Character.getNumericValue(isbnNumber.charAt(i));
                } else {
                    total += Character.getNumericValue(isbnNumber.charAt(i)) * 3;
                }
            }

            final boolean b = total % 10 == 0 ? true : false;

            return b;

        } else {

            if (isbnNumber.length() != 10) throw new NumberFormatException("ISBN numbers must be 10 digit long");


            int total = 0;

            for (int i = 0; i < 10; i++) {

                if (!Character.isDigit(isbnNumber.charAt(i))) {
                    if (i == 9 && isbnNumber.charAt(i) == 'X') {
                        total += 10;
                    } else {
                        throw new NumberFormatException("ISBN number can only contain numeric digits");
                    }
                } else {
                    total += Character.getNumericValue(isbnNumber.charAt(i)) * (10 - i);
                }

            }
            return total % 11 == 0;


        }


    }
}
