package com.fakebar.Entities.Products.Drinks;

import com.fakebar.Entities.Products.Product;

public class Drink extends Product implements DrinkInterface {

    private float abv;

    public Drink(){}

    public Drink(Long id, String name, String description, int rating, float abv) {
        super(id, name, description, rating);
        this.abv = abv;
    }

    public float getAbv() {
        return abv;
    }

    public void setAbv(float abv) {
        this.abv = abv;
    }

}
