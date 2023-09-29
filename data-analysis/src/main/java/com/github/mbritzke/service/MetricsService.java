package com.github.mbritzke.service;

import com.github.mbritzke.model.*;
import com.github.mbritzke.model.Store;

import java.util.*;

public class MetricsService {

    private ParseService parseService;

    public MetricsService(ParseService parseService) {
        this.parseService = parseService;
    }

    Metrics generate(String filePath){
        Store store = parseService.parse(filePath);
        return new Metrics.Builder()
                .withAmmountOfSalesman(findNumberOfSalesman(store.getSalesmanList()))
                .withAmmounOfClientes(findNumberOfClients(store.getCustomerList()))
                .withMostExpensiveSaleId(findMostExpensiveSale(store.getSaleList()))
                .withWorstSalesman(findWorstSalesman(store.getSaleList()))
                .build();
    }

    private int findNumberOfSalesman(List<Salesman> salesmanList) {
        return salesmanList.size();
    }

    private int findNumberOfClients(List<Customer> customerList) {
        return customerList.size();
    }

    private int findMostExpensiveSale(List<Sale> saleList){
        return saleList.stream().max(Comparator.comparing(this::getTotalSaleValue)).map(Sale::getId).orElse(0);
    }

    private double getTotalSaleValue(Sale sale) {
        double totalSaleValue = 0.0;
        for (Item item: sale.getItemsList()){
            totalSaleValue += item.getPrice() * item.getQuantity();
        }
        return totalSaleValue;
    }

    private String findWorstSalesman(List<Sale> saleList){
        Map<String, Double> salesMap = new HashMap<>();
        saleList.forEach(
                sale -> {
                    double saleValue = getTotalSaleValue(sale);
                    if(!salesMap.containsKey(sale.getSalesmanName()))
                        salesMap.put(sale.getSalesmanName(), saleValue);
                    else
                        salesMap.computeIfPresent(sale.getSalesmanName(), (k, v) -> v += saleValue);
                });
        return salesMap.entrySet().stream().min(Comparator.comparing(Map.Entry::getValue)).map(Map.Entry::getKey).orElse("");
    }
}
