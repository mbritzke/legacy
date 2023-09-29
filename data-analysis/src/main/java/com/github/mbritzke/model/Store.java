package com.github.mbritzke.model;

import java.util.ArrayList;
import java.util.List;

public class Store {

    private List<Salesman> salesmanList;
    private List<Customer> customerList;
    private List<Sale> saleList;

    public Store(){
        salesmanList = new ArrayList<>();
        customerList = new ArrayList<>();
        saleList = new ArrayList<>();
    }

    public List<Salesman> getSalesmanList() {
        return salesmanList;
    }

    public void setSalesmanList(List<Salesman> salesmanList) {
        this.salesmanList = salesmanList;
    }

    public List<Customer> getCustomerList() {
        return customerList;
    }

    public void setCustomerList(List<Customer> customerList) {
        this.customerList = customerList;
    }

    public List<Sale> getSaleList() {
        return saleList;
    }

    public void setSaleList(List<Sale> saleList) {
        this.saleList = saleList;
    }
}
