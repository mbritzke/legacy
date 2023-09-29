package com.github.mbritzke.model;

import java.util.Objects;

public class Item {

    private int id;
    private int quantity;
    private double price;

    public static class Builder {

        private int id;
        private int quantity;
        private double price;

        public Builder(int id) {
            this.id = id;
        }

        public Builder setQuantity(int quantity) {
            this.quantity = quantity;
            return this;
        }

        public Builder setPrice(double price) {
            this.price = price;
            return this;
        }

        public Item build() {
            Item item = new Item();
            item.id = this.id;
            item.quantity = this.quantity;
            item.price = this.price;
            return item;
        }
    }

    private Item(){}

    public int getId() {
        return id;
    }

    public int getQuantity() {
        return quantity;
    }

    public double getPrice() {
        return price;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Item item = (Item) o;
        return id == item.id &&
                quantity == item.quantity &&
                Double.compare(item.price, price) == 0;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, quantity, price);
    }
}
