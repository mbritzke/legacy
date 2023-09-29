package com.github.mbritzke.service;

import com.github.mbritzke.configuration.FileConfiguration;
import com.github.mbritzke.file.ReadFile;
import com.github.mbritzke.model.Customer;
import com.github.mbritzke.model.Item;
import com.github.mbritzke.model.Sale;
import com.github.mbritzke.model.Salesman;
import com.github.mbritzke.model.Store;

import java.util.ArrayList;
import java.util.List;

public class ParseService {

    private FileConfiguration fileConfiguration;
    private ReadFile readFile;

    public ParseService(FileConfiguration fileConfiguration, ReadFile readFile) {
        this.fileConfiguration = fileConfiguration;
        this.readFile = readFile;
    }

    Store parse(String filePath){
        List<String> fileContent = readFile.read(filePath);
        Store store = new Store();
        fileContent.forEach(
                line -> {
                    if (line.startsWith("001"))
                        store.getSalesmanList().add(createSalesman(line));
                    if (line.startsWith("002"))
                        store.getCustomerList().add(createCustomer(line));
                    if(line.startsWith("003"))
                        store.getSaleList().add(createSale(line));
                });

        return store;
    }

    private Salesman createSalesman(String line) {
        String[] salesmanContent = line.split(fileConfiguration.getAttributeSeparator());
        return new Salesman.Builder(salesmanContent[1])
                            .withName(salesmanContent[2])
                            .withSalary(Double.parseDouble(salesmanContent[3]))
                            .build();
    }

    private Customer createCustomer(String line) {
        String[] costumerContent = line.split(fileConfiguration.getAttributeSeparator());
        return new Customer.Builder(costumerContent[1])
                                        .withName(costumerContent[2])
                                        .withBusinessArea(costumerContent[3])
                                        .build();
    }

    private Sale createSale(String line) {
        String[] saleContent = line.split(fileConfiguration.getAttributeSeparator());
        return new Sale.Builder(Integer.parseInt(saleContent[1]))
                        .withItemsList(createItensList(saleContent[2]))
                        .withSalesmanName(saleContent[3])
                        .build();
    }

    private List<Item> createItensList(String line){
        List<Item> itemList = new ArrayList<>();
        String[] allItens = line.split(fileConfiguration.getItemSeparator());
        for (String itemLine : allItens) {
            String[] itemContent = itemLine.split(fileConfiguration.getItemAttributeSeparator());
            itemList.add(new Item.Builder(
                    Integer.parseInt(itemContent[0].replace(fileConfiguration.getListStartSeparator(), "")))
                    .setQuantity(Integer.parseInt(itemContent[1]))
                    .setPrice(Double.parseDouble(
                            itemContent[2].replace(fileConfiguration.getListEndSeparator(), ""))
                    )
                    .build());
        }
        return itemList;
    }
}
