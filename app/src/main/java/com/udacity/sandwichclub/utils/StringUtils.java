package com.udacity.sandwichclub.utils;

class StringUtils {

    static String orDefault(String string, String defaultValue) {
        String stringToReturn;
        if (string == null || string.isEmpty()) {
            stringToReturn = defaultValue;
        } else {
            stringToReturn = string;
        }
        return stringToReturn;
    }

}
