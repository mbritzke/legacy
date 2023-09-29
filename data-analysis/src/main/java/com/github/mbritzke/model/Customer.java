package com.github.mbritzke.model;

import java.util.Objects;

public class Customer {

    private String cnpj;
    private String name;
    private String businessArea;

    public static class Builder {

        private String cnpj;
        private String name;
        private String businessArea;

        public Builder(String cnpj) {
            this.cnpj = cnpj;
        }

        public Builder withName(String name){
            this.name = name;
            return this;
        }

        public Builder withBusinessArea(String businessArea){
            this.businessArea = businessArea;
            return this;
        }

        public Customer build(){
            Customer customer = new Customer();
            customer.cnpj = this.cnpj;
            customer.name = this.name;
            customer.businessArea = this.businessArea;
            return customer;
        }

    }
       
    private Customer(){}

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Customer customer = (Customer) o;
        return Objects.equals(cnpj, customer.cnpj) &&
                Objects.equals(name, customer.name) &&
                Objects.equals(businessArea, customer.businessArea);
    }

    @Override
    public int hashCode() {
        return Objects.hash(cnpj, name, businessArea);
    }
}
