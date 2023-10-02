package com.github.matheusbritzke.functions;

import org.springframework.stereotype.Component;

@Component
public class Division implements Operation {

    @Override
    public double calculate(double firstNumber, double secondNumber) {
        return firstNumber / secondNumber;
    }
}
