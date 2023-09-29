package com.github.mbritzke.file;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class ReadFileTest {

    private ReadFile readFile;
    private Path filePath;

    @Before
    public void setUp() {
        filePath = Paths.get("src","test","resources").toAbsolutePath();
        readFile = new ReadFile();
    }

    @Test
    public void shouldReadFile(){
        List<String> expectedList = new ArrayList<>();
        expectedList.add("001ç1234567891234çDiegoç50000");
        expectedList.add("002ç2345675434544345çJose da SilvaçRural");
        expectedList.add("003ç10ç[1-10-100]çDiego");

        String absoluteFilePath = filePath.toString().concat("/test_file.dat");
        List<String> actualList = readFile.read(absoluteFilePath);

        Assert.assertEquals(expectedList, actualList);
    }

    @Test
    public void shouldThrowException(){
        List<String> list = readFile.read("invalid_directory");
        Assert.assertTrue(list.isEmpty());
    }

}