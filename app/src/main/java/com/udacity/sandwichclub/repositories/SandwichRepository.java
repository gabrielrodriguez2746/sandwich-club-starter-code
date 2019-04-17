package com.udacity.sandwichclub.repositories;

import com.udacity.sandwichclub.model.Sandwich;

public interface SandwichRepository {

    Sandwich getSandwichFromKeyName(String keyName);

}
