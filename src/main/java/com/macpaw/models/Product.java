package com.macpaw.models;

public class Product {
    private  String price;
    private  String description;


    public Product(final String price, final String description) {
        this.price = price;
        this.description = description;

    }

    public String getPrice() {
        return this.price;
    }

    public void setPrice(final String price) {
        this.price = price;
    }

    public String getDescription() {
        return this.description;
    }

    public void setDescription(final String description) {
        this.description = description;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Product product = (Product) o;
        return price == product.price &&
                description.equals(product.description);
    }

}
