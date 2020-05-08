package com.persistantcoder.isbntools;

/**
 * Created by Evans K F C on May ,2020
 **/
public class ValidatorISBN {


    public static final int LONG_ISBN_LENGTH = 13;
    public static final int SHORT_ISBN_LENGTH = 10;
    public static final int SHORT_ISBN_DIGIT_MULTIPLIER = 11;
    public static final int LONG_ISBN_DIGIT_MULTIPLIER = 10;

    public boolean checkISBN(String isbnNumber) {

        if (isbnNumber.length() == LONG_ISBN_LENGTH) {
            return isThisAValidThirteenDigitISBN(isbnNumber);

        } else {

            return isThisValidTenDigitISBN(isbnNumber);


        }


    }

    private boolean isThisValidTenDigitISBN(String isbnNumber) {
        if (isbnNumber.length() !=SHORT_ISBN_LENGTH) throw new NumberFormatException("ISBN numbers must be 10 digit long");


        int total = 0;

        for (int i = 0; i < SHORT_ISBN_LENGTH; i++) {

            if (!Character.isDigit(isbnNumber.charAt(i))) {
                if (i == 9 && isbnNumber.charAt(i) == 'X') {
                    total += 10;
                } else {
                    throw new NumberFormatException("ISBN number can only contain numeric digits");
                }
            } else {
                total += Character.getNumericValue(isbnNumber.charAt(i)) * (SHORT_ISBN_LENGTH - i);
            }

        }
        return total % SHORT_ISBN_DIGIT_MULTIPLIER == 0;
    }

    private boolean isThisAValidThirteenDigitISBN(String isbnNumber) {
        int total = 0;

        for (int i = 0; i < LONG_ISBN_LENGTH; i++) {

            if (i % 2 == 0) {
                total += Character.getNumericValue(isbnNumber.charAt(i));
            } else {
                total += Character.getNumericValue(isbnNumber.charAt(i)) * 3;
            }
        }


        final boolean b = total % LONG_ISBN_DIGIT_MULTIPLIER == 0 ? true : false;

        return b;
    }
}
