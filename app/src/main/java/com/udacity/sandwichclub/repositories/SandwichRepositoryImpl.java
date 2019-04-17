package com.udacity.sandwichclub.repositories;

import com.udacity.sandwichclub.R;
import com.udacity.sandwichclub.helpers.ResourceProvider;
import com.udacity.sandwichclub.helpers.ResourceProviderImpl;
import com.udacity.sandwichclub.model.Sandwich;
import com.udacity.sandwichclub.utils.AnyUtils;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.Callable;

import static com.udacity.sandwichclub.utils.JsonUtils.parseSandwichJson;

public class SandwichRepositoryImpl implements SandwichRepository {

    private Map<String, Sandwich> sandwichMap = new HashMap<>();
    private static SandwichRepository INSTANCE = null;

    private SandwichRepositoryImpl(ResourceProvider resourceProvider) {
        for (String jsonString : resourceProvider.getStringArray(R.array.sandwich_details)) {
            Sandwich sandwich = parseSandwichJson(jsonString, resourceProvider.getString(R.string.copy_unknown));
            if (sandwich != null) {
                sandwichMap.put(sandwich.getMainName(), sandwich);
            }
        }
    }

    @Override
    public Sandwich getSandwichFromKeyName(final String keyName) {
        return AnyUtils.tryOrDefault(new Callable<Sandwich>() {
            @Override
            public Sandwich call() {
                return sandwichMap.get(keyName);
            }
        }, null);
    }

    public static SandwichRepository getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new SandwichRepositoryImpl(ResourceProviderImpl.getInstance());
        }
        return INSTANCE;
    }
}