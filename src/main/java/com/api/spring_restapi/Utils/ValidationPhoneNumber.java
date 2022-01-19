package com.api.spring_restapi.Utils;

import java.util.function.Predicate;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ValidationPhoneNumber implements Predicate<String> {

    @Override
    //Vietnam has phone number start with 09*, 01(2|6|8|9).
    //After that, the phone number can start with 03, 05, 07 or 08

    public boolean test(String s) {
        //Create regex rule
        Pattern pattern = Pattern.compile("^\\d{10}$");
        Matcher m = pattern.matcher(s);
        //return true if phone match pattern
        return m.matches();
    }
}
