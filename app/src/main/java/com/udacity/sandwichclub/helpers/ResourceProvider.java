package com.udacity.sandwichclub.helpers;

import android.support.annotation.ArrayRes;
import android.support.annotation.StringRes;

public interface ResourceProvider {

    String[] getStringArray(@ArrayRes int resource);

    String getString(@StringRes int resource);

}

