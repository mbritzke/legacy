package com.github.matheusbritzke.calculator;

import com.github.matheusbritzke.maps.HistoryOperations;
import com.github.matheusbritzke.maps.MapOperations;

import static jdk.nashorn.internal.objects.Global.Infinity;

public class Calculator {

    private MapOperations mapOperations;
    private HistoryOperations historyOperations;

    public Calculator(MapOperations mapOperations, HistoryOperations historyOperations) {
        this.mapOperations = mapOperations;
        this.historyOperations = historyOperations;
    }

    public String calculate(double firstNumber, double secondNumber, String operation){
        double answer;
        try{
            answer = mapOperations.getMapOperations().get(operation).calculate(firstNumber, secondNumber);
            if(answer == Infinity)
                return saveOperations(firstNumber, secondNumber, "Invalid Operation", "Division By Zero");
        } catch (Exception e){
            return saveOperations(firstNumber, secondNumber, "Invalid Operation", "Unmapped Operation");
        }
        return saveOperations(firstNumber, secondNumber, operation, String.valueOf(answer));
    }

    private String saveOperations(double firstNumber, double secondNumber, String operation, String answer) {
        String completeOperation = firstNumber + " "+ operation + " " + secondNumber + " = " + answer;
        historyOperations.getHistoryOperations().get(operation).add(completeOperation);
        return completeOperation;
    }

    public void showHistory(){
        for(String aux: historyOperations.getHistoryOperations().keySet())
            System.out.println(historyOperations.getHistoryOperations().get(aux) + "\n");
    }

    public void showHistoryByOperation(String operation) {
        for(String aux: historyOperations.getHistoryOperations().keySet())
            if (aux.contains(operation))
                System.out.println(historyOperations.getHistoryOperations().get(aux) + "\n");

    }
}
