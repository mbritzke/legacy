package com.github.mbritzke.model;

import java.util.List;
import java.util.Objects;

public class Sale {

    private int id;
    private String salesmanName;
    private List<Item> itemsList;

    public static class Builder {

        private int id;
        private String salesmanName;
        private List<Item> itemsList;

        public Builder(int id) {
            this.id = id;
        }

        public Builder withSalesmanName(String salesmanName) {
            this.salesmanName = salesmanName;
            return this;
        }

        public Builder withItemsList(List<Item> itemsList) {
            this.itemsList = itemsList;
            return this;
        }

        public Sale build() {
            Sale sale = new Sale();
            sale.id = this.id;
            sale.salesmanName = this.salesmanName;
            sale.itemsList = this.itemsList;
            return sale;
        }

    }

    private Sale(){}

    public int getId() {
        return id;
    }

    public String getSalesmanName() {
        return salesmanName;
    }

    public List<Item> getItemsList() {
        return itemsList;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Sale sale = (Sale) o;
        return id == sale.id &&
                salesmanName.equals(sale.salesmanName) &&
                itemsList.equals(sale.itemsList);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, salesmanName, itemsList);
    }
}
