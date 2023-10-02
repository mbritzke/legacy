package com.github.matheusbritzke.functions;

import org.springframework.stereotype.Component;

@Component
public class Pow implements Operation {

    @Override
    public double calculate(double firstNumber, double secondNumber) {
        return Math.pow(firstNumber, secondNumber);
    }
}
