package com.fakebar.Entities.Products;

public abstract class ProductService<T extends Product> {

    public T getProductById(Long id) {
        return null;
    }

    public void saveProduct(T product) {

    }

    protected abstract void validateProduct(T product);

}
