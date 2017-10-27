package com.randomizer.android.utils;


import com.randomizer.android.model.RandomUser;
import com.randomizer.android.model.response.Location;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class AppUtils {

    private static final String PERIOD = ", ";

    /**
     * Formate Name along with appropriate title
     * @param randomUser
     * @return
     */
    public static String getFormatedName(RandomUser randomUser) {

        return capitalize(
                ((randomUser.getTitle().length() <= 3) ? randomUser.getTitle() + "." : randomUser.getTitle()) + " " +
                        randomUser.getFirstName() +
                        " " + randomUser.getLastName());
    }

    public static String getFormatedGender(RandomUser randomUser) {
        return capitalize(randomUser.getGender());
    }

    /**
     * Formats attributes of @Location object
     * @param location
     * @return
     */
    public static String getFormatedAddress(Location location) {
        return capitalize(location.getStreet() + PERIOD + location.getCity() + PERIOD + location.getState());
    }

    /**
     * Using RegEx to capitalise each Token by its first Char
     * @param capString
     * @return
     */
    private static String capitalize(String capString) {
        StringBuffer capBuffer = new StringBuffer();
        Matcher capMatcher = Pattern.compile("([a-z])([a-z]*)", Pattern.CASE_INSENSITIVE).matcher(capString);
        while (capMatcher.find()) {
            capMatcher.appendReplacement(capBuffer, capMatcher.group(1).toUpperCase() + capMatcher.group(2).toLowerCase());
        }
        return capMatcher.appendTail(capBuffer).toString();
    }
}
