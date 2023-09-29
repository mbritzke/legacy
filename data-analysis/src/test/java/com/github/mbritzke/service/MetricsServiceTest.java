package com.github.mbritzke.service;

import com.github.mbritzke.model.*;
import com.github.mbritzke.model.Store;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class MetricsServiceTest {

    private ParseService parseServiceMock;
    private MetricsService metricsService;

    @Before
    public void setUp() {
        parseServiceMock = mock(ParseService.class);
        metricsService = new MetricsService(parseServiceMock);
    }

    @Test
    public void shouldFindCorrectMetrics(){
        List<Salesman> expectedSalesmanList = new ArrayList<>();
        expectedSalesmanList.add(new Salesman.Builder("1234567891234").withName("Diego").withSalary(50000.0).build());
        List<Customer> expectedCustomersList = new ArrayList<>();
        expectedCustomersList.add(new Customer.Builder("2345675434544345").withName("Jose da Silva").withBusinessArea("Rural").build());
        List<Item> expectedItemList = new ArrayList<>();
        expectedItemList.add(new Item.Builder(1).setQuantity(10).setPrice(100.0).build());
        List<Sale> expectedSaleList = new ArrayList<>();
        expectedSaleList.add(new Sale.Builder(10).withSalesmanName("Diego").withItemsList(expectedItemList).build());
        Store store = new Store();
        store.setSalesmanList(expectedSalesmanList);
        store.setCustomerList(expectedCustomersList);
        store.setSaleList(expectedSaleList);
        when(parseServiceMock.parse("/test/")).thenReturn(store);

        Metrics actualMetrics = metricsService.generate("/test/");
        Metrics expectedMetrics = new Metrics.Builder()
                .withAmmounOfClientes(1)
                .withAmmountOfSalesman(1)
                .withMostExpensiveSaleId(10)
                .withWorstSalesman("Diego").build();
        Assert.assertEquals(expectedMetrics, actualMetrics);
    }

    @Test
    public void emptyMetrics(){
        List<Salesman> expectedSalesmanList = new ArrayList<>();
        expectedSalesmanList.add(new Salesman.Builder(null).withName(null).withSalary(null).build());
        List<Customer> expectedCustomersList = new ArrayList<>();
        expectedCustomersList.add(new Customer.Builder(null).withName(null).withBusinessArea(null).build());
        List<Item> expectedItemList = new ArrayList<>();
        expectedItemList.add(new Item.Builder(0).setQuantity(0).setPrice(0).build());
        List<Sale> expectedSaleList = new ArrayList<>();
        expectedSaleList.add(new Sale.Builder(0).withSalesmanName(null).withItemsList(expectedItemList).build());
        Store store = new Store();
        store.setSalesmanList(expectedSalesmanList);
        store.setCustomerList(expectedCustomersList);
        store.setSaleList(expectedSaleList);
        when(parseServiceMock.parse("/test/")).thenReturn(store);

        Metrics actualMetrics = metricsService.generate("/test/");
        Metrics expectedMetrics = new Metrics.Builder()
                .withAmmounOfClientes(1)
                .withAmmountOfSalesman(1)
                .withMostExpensiveSaleId(0)
                .withWorstSalesman("").build();
        Assert.assertEquals(expectedMetrics, actualMetrics);
    }
}
