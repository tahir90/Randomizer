package com.randomizer.android.utils;


import com.randomizer.android.model.RandomUser;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class AppUtils {

    public static String getFormatedName(RandomUser randomUser) {

        return  capitalize(
                ((randomUser.getTitle().length()<=3)? randomUser.getTitle()+"."  : randomUser.getTitle()) + " "+
                        randomUser.getFirstName() +
                        " "+ randomUser.getLastName());
    }

    public static String getFormatedGender(RandomUser randomUser) {
        return capitalize(randomUser.getGender());
    }

    private static String capitalize(String capString){
        StringBuffer capBuffer = new StringBuffer();
        Matcher capMatcher = Pattern.compile("([a-z])([a-z]*)", Pattern.CASE_INSENSITIVE).matcher(capString);
        while (capMatcher.find()){
            capMatcher.appendReplacement(capBuffer, capMatcher.group(1).toUpperCase() + capMatcher.group(2).toLowerCase());
        }
        return capMatcher.appendTail(capBuffer).toString();
    }
}
