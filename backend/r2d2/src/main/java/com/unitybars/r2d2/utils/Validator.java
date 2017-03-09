package com.unitybars.r2d2.utils;

import java.util.regex.Pattern;

/**
 * Created by oleg.nestyuk
 * Date: 09-Mar-17.
 */
public class Validator {
    public static final Pattern EMAIL_ADDRESS
            = Pattern.compile(
            "[a-zA-Z0-9\\+\\.\\_\\%\\-\\+]{1,256}" +
                    "\\@" +
                    "[a-zA-Z0-9][a-zA-Z0-9\\-]{0,64}" +
                    "(" +
                    "\\." +
                    "[a-zA-Z0-9][a-zA-Z0-9\\-]{0,25}" +
                    ")+"
    );


    public static boolean validateEmail(String email) {
        return email != null && EMAIL_ADDRESS.matcher(email).matches();
    }
}