package com.udacity.sandwichclub.utils;

import android.support.annotation.Nullable;

import com.udacity.sandwichclub.helpers.ResourceProvider;
import com.udacity.sandwichclub.model.Sandwich;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;

import static com.udacity.sandwichclub.utils.AnyUtils.tryOrDefault;
import static com.udacity.sandwichclub.utils.AnyUtils.tryOrPrintException;
import static com.udacity.sandwichclub.utils.StringUtils.orDefault;

public class JsonUtils {

    private static String MAIN_NAME_KEY = "mainName";
    private static String NAME_KEY = "name";
    private static String ALSO_KNOW_AS_KEY = "alsoKnownAs";
    private static String PLACE_OF_ORIGIN_KEY = "placeOfOrigin";
    private static String DESCRIPTION_KEY = "description";
    private static String IMAGE_KEY = "image";
    private static String INGREDIENTS_KEY = "ingredients";

    @Nullable
    public static Sandwich parseSandwichJson(String json, String defaultValue) {
        JSONObject object = createJSONObjectFromString(json);
        JSONObject mainNameObject = getJSONObjectDefault(object, NAME_KEY);

        String mainName = getStringOrDefault(mainNameObject, MAIN_NAME_KEY, "");
        Sandwich sandwich;
        if (mainName.isEmpty()) {
            sandwich = null;
        } else {
            List<String> alsoKnowAs = mapJSONArray(getJSONArrayDefault(mainNameObject, ALSO_KNOW_AS_KEY));
            String placeOfOrigin = orDefault(getStringOrDefault(object, PLACE_OF_ORIGIN_KEY, defaultValue), defaultValue) ;
            String description = orDefault(getStringOrDefault(object, DESCRIPTION_KEY, defaultValue), defaultValue);
            String image = getStringOrDefault(object, IMAGE_KEY, "");
            List<String> ingredients = mapJSONArray(getJSONArrayDefault(object, INGREDIENTS_KEY));
            sandwich = new Sandwich(mainName, alsoKnowAs, placeOfOrigin, description, image, ingredients);
        }
        return sandwich;
    }

    private static JSONObject createJSONObjectFromString(final String stringObject) {
        return tryOrDefault(new Callable<JSONObject>() {
            @Override
            public JSONObject call() throws Exception {
                return new JSONObject(stringObject);
            }
        }, new JSONObject());
    }

    private static String getStringOrDefault(final JSONObject object, final String key, String defaultValue) {
        return tryOrDefault(new Callable<String>() {
            @Override
            public String call() throws Exception {
                return object.getString(key);
            }
        }, defaultValue);
    }

    private static JSONObject getJSONObjectDefault(final JSONObject object, final String key) {
        return tryOrDefault(new Callable<JSONObject>() {
            @Override
            public JSONObject call() throws Exception {
                return object.getJSONObject(key);
            }
        }, new JSONObject());
    }

    private static JSONArray getJSONArrayDefault(final JSONObject object, final String key) {
        return tryOrDefault(new Callable<JSONArray>() {
            @Override
            public JSONArray call() throws Exception {
                return object.getJSONArray(key);
            }
        }, new JSONArray());
    }

    @SuppressWarnings("unchecked")
    private static <T> List<T> mapJSONArray(final JSONArray jsonArray) {
        int arraySize = jsonArray.length();
        final ArrayList<T> items = new ArrayList<>(arraySize);
        for (int index = 0; index< arraySize; index++) {
            final int internalIndex = index;
            tryOrPrintException(new Callable<Void>() {
                @Override
                public Void call() throws Exception {
                     items.add((T) jsonArray.get(internalIndex));
                     return null;
                }
            });
        }
        return items;
    }

}
