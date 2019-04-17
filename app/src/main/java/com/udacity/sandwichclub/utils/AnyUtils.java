package com.udacity.sandwichclub.utils;

import android.util.Log;

import java.util.concurrent.Callable;

public class AnyUtils {

    public static <T> T tryOrDefault(Callable<T> function, T defaultValue) {
        try {
            return function.call();
        } catch (Exception e) {
            Log.e(AnyUtils.class.getSimpleName(), e.getLocalizedMessage());
            return defaultValue;
        }
    }

    static void tryOrPrintException(Callable<Void> function) {
        try {
            function.call();
        } catch (Exception e) {
            Log.e(AnyUtils.class.getSimpleName(), e.getLocalizedMessage());
        }
    }

}
