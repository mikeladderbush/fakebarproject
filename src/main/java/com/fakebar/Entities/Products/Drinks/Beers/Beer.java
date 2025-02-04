package com.fakebar.Entities.Products.Drinks.Beers;

import com.fakebar.Entities.Products.Drinks.Drink;
import com.fakebar.Entities.Products.Drinks.Enums.BeerStyles;

public class Beer extends Drink {

    private BeerStyles style;

    public BeerStyles getStyle() {
        return style;
    }

    public void setStyle(BeerStyles style) {
        this.style = style;
    }

    public Beer() {
    }

    public Beer(Long id, String name, BeerStyles style, String description, int rating, float abv) {
        super(id, name, description, rating, abv);
        this.style = style;
    }

}
