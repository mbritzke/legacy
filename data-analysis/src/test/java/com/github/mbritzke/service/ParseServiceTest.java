package com.github.mbritzke.service;

import com.github.mbritzke.configuration.FileConfiguration;
import com.github.mbritzke.file.ReadFile;
import com.github.mbritzke.model.Customer;
import com.github.mbritzke.model.Item;
import com.github.mbritzke.model.Sale;
import com.github.mbritzke.model.Salesman;
import com.github.mbritzke.model.Store;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.util.CollectionUtils;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class ParseServiceTest {

    private ParseService parseService;
    private FileConfiguration mockFileConfiguration;
    private ReadFile mockReadFile;
    private Path filePath;
    private String testFilePath;

    @Before
    public void setUp() {
        mockFileConfiguration = mock(FileConfiguration.class);
        filePath = Paths.get("src","test","resources").toAbsolutePath();

        when(mockFileConfiguration.getAttributeSeparator()).thenReturn("ç");
        when(mockFileConfiguration.getItemSeparator()).thenReturn(",");
        when(mockFileConfiguration.getItemAttributeSeparator()).thenReturn("-");
        when(mockFileConfiguration.getListStartSeparator()).thenReturn("[");
        when(mockFileConfiguration.getListEndSeparator()).thenReturn("]");

        testFilePath = "/data/in/";
        mockReadFile = mock(ReadFile.class);
    }

    @Test
    public void invalidFileDestination() {
        parseService = new ParseService(mockFileConfiguration, new ReadFile());
        Store actualStore = parseService.parse(testFilePath);
        Assert.assertTrue(CollectionUtils.isEmpty(actualStore.getSalesmanList()));
        Assert.assertTrue(CollectionUtils.isEmpty(actualStore.getCustomerList()));
        Assert.assertTrue(CollectionUtils.isEmpty(actualStore.getSaleList()));
    }

    @Test
    public void fileWithoutSalesman(){
        List<String> testList = new ArrayList<>();
        testList.add("002ç2345675434544345çJose da SilvaçRural");
        testList.add("003ç10ç[1-10-100]çDiego");

        when(mockReadFile.read(testFilePath)).thenReturn(testList);
        parseService = new ParseService(mockFileConfiguration, mockReadFile);
        Store actualStore = parseService.parse(testFilePath);

        Assert.assertTrue(actualStore.getSalesmanList().isEmpty());

        Assert.assertEquals(1, actualStore.getCustomerList().size());
        Assert.assertTrue(actualStore.getCustomerList().contains((new Customer.Builder("2345675434544345").withName("Jose da Silva").withBusinessArea("Rural").build())));

        List<Item> expectedItemList = new ArrayList<>();
        expectedItemList.add(new Item.Builder(1).setQuantity(10).setPrice(100.0).build());
        Assert.assertEquals(1, actualStore.getSaleList().size());
        Assert.assertEquals(1, actualStore.getSaleList().get(0).getItemsList().size());
        Assert.assertEquals(actualStore.getSaleList().get(0), new Sale.Builder(10).withSalesmanName("Diego").withItemsList(expectedItemList).build());
    }

    @Test
    public void fileWithoutCustomers(){
        List<String> testList = new ArrayList<>();
        testList.add("001ç1234567891234çDiegoç50000");
        testList.add("003ç10ç[1-10-100]çDiego");

        when(mockReadFile.read(testFilePath)).thenReturn(testList);
        parseService = new ParseService(mockFileConfiguration, mockReadFile);
        Store actualStore = parseService.parse(testFilePath);

        Assert.assertEquals(1, actualStore.getSalesmanList().size());
        Assert.assertTrue(actualStore.getSalesmanList().contains(new Salesman.Builder("1234567891234").withName("Diego").withSalary(50000.0).build()));

        Assert.assertTrue(actualStore.getCustomerList().isEmpty());

        List<Item> expectedItemList = new ArrayList<>();
        expectedItemList.add(new Item.Builder(1).setQuantity(10).setPrice(100.0).build());
        Assert.assertEquals(1, actualStore.getSaleList().size());
        Assert.assertEquals(1, actualStore.getSaleList().get(0).getItemsList().size());
        Assert.assertTrue(actualStore.getSaleList().contains(new Sale.Builder(10).withSalesmanName("Diego").withItemsList(expectedItemList).build()));
    }

    @Test
    public void fileWithoutSales(){
        List<String> testList = new ArrayList<>();
        testList.add("001ç1234567891234çDiegoç50000");
        testList.add("002ç2345675434544345çJose da SilvaçRural");

        when(mockReadFile.read(testFilePath)).thenReturn(testList);
        parseService = new ParseService(mockFileConfiguration, mockReadFile);
        Store actualStore = parseService.parse(testFilePath);

        Assert.assertEquals(1, actualStore.getSalesmanList().size());
        Assert.assertTrue(actualStore.getSalesmanList().contains(new Salesman.Builder("1234567891234").withName("Diego").withSalary(50000.0).build()));

        Assert.assertEquals(1, actualStore.getCustomerList().size());
        Assert.assertTrue(actualStore.getCustomerList().contains(new Customer.Builder("2345675434544345").withName("Jose da Silva").withBusinessArea("Rural").build()));

        Assert.assertTrue(actualStore.getSaleList().isEmpty());
    }

    @Test
    public void shouldParseFile(){
        parseService = new ParseService(mockFileConfiguration, new ReadFile());
        String absoluteFilePath = filePath.toString().concat("/test_file.dat");
        Store actualStore = parseService.parse(absoluteFilePath);

        List<Salesman> expectedSalesmanList = new ArrayList<>();
        expectedSalesmanList.add(new Salesman.Builder("1234567891234").withName("Diego").withSalary(50000.0).build());
        List<Customer> expectedCustomersList = new ArrayList<>();
        expectedCustomersList.add(new Customer.Builder("2345675434544345").withName("Jose da Silva").withBusinessArea("Rural").build());
        List<Item> expectedItemList = new ArrayList<>();
        expectedItemList.add(new Item.Builder(1).setQuantity(10).setPrice(100.0).build());
        List<Sale> expectedSaleList = new ArrayList<>();
        expectedSaleList.add(new Sale.Builder(10).withSalesmanName("Diego").withItemsList(expectedItemList).build());

        Assert.assertThat(actualStore.getSalesmanList(), is(expectedSalesmanList));
        Assert.assertThat(actualStore.getCustomerList(), is(expectedCustomersList));
        Assert.assertThat(actualStore.getSaleList(), is(expectedSaleList));

      }

}