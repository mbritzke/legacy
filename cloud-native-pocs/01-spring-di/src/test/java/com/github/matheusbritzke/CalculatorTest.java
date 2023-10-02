package com.github.matheusbritzke;

import com.github.matheusbritzke.calculator.Calculator;
import com.github.matheusbritzke.configurationApp.AppConfig;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { AppConfig.class })
public class CalculatorTest {

    @Autowired
    private Calculator calculator;

    @Test
    public void testCalculator(){
        Assert.assertNotNull(calculator);
    }

    @Test
    public void sum(){
        String sum = calculator.calculate(2,2, "+");
        String expected = "2.0 + 2.0 = 4.0";
        Assert.assertEquals(expected, sum);
    }

    @Test
    public void subtraction(){
        String sub = calculator.calculate(2, 2, "-");
        String expected = "2.0 - 2.0 = 0.0";
        Assert.assertEquals(expected, sub);
    }

    @Test
    public void multiplication(){
        String mult = calculator.calculate(3,3, "*");
        String expected = "3.0 * 3.0 = 9.0";
        Assert.assertEquals(expected, mult);
    }

    @Test
    public void division(){
        String div = calculator.calculate(16, 4, "/");
        String expected = "16.0 / 4.0 = 4.0";
        Assert.assertEquals(expected, div);
    }

    @Test
    public void invalidOperation(){
        String invalid = calculator.calculate(16, 4, "a");
        String expected = "16.0 Invalid Operation 4.0 = Unmapped Operation";
        Assert.assertEquals(expected, invalid);
    }

    @Test
    public void divisionByZero(){
        String divZero = calculator.calculate(10, 0, "/");
        String expected = "10.0 Invalid Operation 0.0 = Division By Zero";
        Assert.assertEquals(expected, divZero);
    }
}
