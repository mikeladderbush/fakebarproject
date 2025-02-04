package com.fakebar.Entities.Products.Drinks.Cocktails;

import com.fakebar.Entities.Products.Drinks.Drink;

public class Cocktail extends Drink {

    private String alcohol;
    private String recipe;

    public Cocktail() {
    }

    public Cocktail(Long id, String name, String description, int rating, float abv, String alcohol, String recipe) {
        super(id, name, description, rating, abv);
        this.alcohol = alcohol;
        this.recipe = recipe;
    }

    public String getAlcohol() {
        return alcohol;
    }

    public void setAlcohol(String alcohol) {
        this.alcohol = alcohol;
    }

    public String getRecipe() {
        return recipe;
    }

    public void setRecipe(String recipe) {
        this.recipe = recipe;
    }
}
