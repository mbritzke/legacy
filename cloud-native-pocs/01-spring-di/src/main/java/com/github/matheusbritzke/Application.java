package com.github.matheusbritzke;

import com.github.matheusbritzke.calculator.Calculator;
import com.github.matheusbritzke.configurationApp.AppConfig;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Application {

    public static void main(String args[]){
        ApplicationContext applicationContext = new AnnotationConfigApplicationContext(AppConfig.class);
        Calculator currentCalculator = (Calculator) applicationContext.getBean("calculator");

        System.out.println("OPERATIONS: ");
        System.out.println("a) 3+1 : \n" + currentCalculator.calculate(3,1, "+"));
        System.out.println("b) 2-2 : \n" + currentCalculator.calculate(2, 2,"-"));
        System.out.println("c) 3*3 : \n" + currentCalculator.calculate(3, 3,"*"));
        System.out.println("d) 16/4 : \n" + currentCalculator.calculate(16, 4,"/"));
        System.out.println("e) 5^3 : \n" + currentCalculator.calculate(5, 3,"^"));
        System.out.println("f) 10c3 : \n" + currentCalculator.calculate(10, 3,"c"));
        System.out.println("g) 15/0 : \n" + currentCalculator.calculate(15, 0,"/"));

        System.out.println("\nLIST OF ALL OPERATIONS: ");
        currentCalculator.showHistory();

        System.out.println("\nLIST OF ALL INVALID OPERATIONS: ");
        currentCalculator.showHistoryByOperation("Invalid Operation");

    }
}
