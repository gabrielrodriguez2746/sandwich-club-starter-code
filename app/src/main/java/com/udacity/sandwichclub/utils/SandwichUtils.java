package com.udacity.sandwichclub.utils;

import android.support.annotation.Nullable;

import com.udacity.sandwichclub.model.Sandwich;

public class SandwichUtils {

    public static String getIngredientListAsString(Sandwich sandwich, @Nullable String itemsSeparator) {
        return ListUtils.mapToListedString(sandwich.getIngredients(), itemsSeparator);
    }

    public static String getAlsoKnowListAsString(Sandwich sandwich, @Nullable String itemsSeparator) {
        return ListUtils.mapToListedString(sandwich.getAlsoKnownAs(), itemsSeparator);
    }

}
