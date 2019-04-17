package com.udacity.sandwichclub.helpers;

import android.content.Context;
import android.support.annotation.ArrayRes;

import com.udacity.sandwichclub.SandwichApplication;

public class ResourceProviderImpl implements ResourceProvider {

    private Context context;
    private static ResourceProvider INSTANCE = null;

    private ResourceProviderImpl(Context context) {
        this.context = context;
    }

    @Override
    public String[] getStringArray(@ArrayRes int resource) {
        return context.getResources().getStringArray(resource);
    }

    @Override
    public String getString(int resource) {
        return context.getResources().getString(resource);
    }

    public static ResourceProvider getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new ResourceProviderImpl(SandwichApplication.getInstance());
        }
        return INSTANCE;
    }
}