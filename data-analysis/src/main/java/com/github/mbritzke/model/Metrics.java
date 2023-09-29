package com.github.mbritzke.model;

import java.util.Objects;

public class Metrics {

    private int ammountOfClients;
    private int amountOfSalesman;
    private int mostExpensiveSaleId;
    private String worstSalesman;

    public static class Builder {

        private int ammountOfClients;
        private int amountOfSalesman;
        private int mostExpensiveSaleId;
        private String worstSalesman;

        public Builder(){}

        public Builder withAmmounOfClientes(int ammounOfClientes){
            this.ammountOfClients = ammounOfClientes;
            return this;
        }

        public Builder withAmmountOfSalesman(int ammountOfSalesman){
            this.amountOfSalesman = ammountOfSalesman;
            return this;
        }

        public Builder withMostExpensiveSaleId(int saleId){
            this.mostExpensiveSaleId = saleId;
            return this;
        }

        public Builder withWorstSalesman(String salesman){
            this.worstSalesman = salesman;
            return this;
        }

        public Metrics build(){
            Metrics metrics = new Metrics();
            metrics.ammountOfClients = this.ammountOfClients;
            metrics.amountOfSalesman = this.amountOfSalesman;
            metrics.mostExpensiveSaleId = this.mostExpensiveSaleId;
            metrics.worstSalesman = this.worstSalesman;
            return metrics;
        }
    }

    private Metrics(){}

    @Override
    public String toString() {
        return "Metrics :\n" +
                " Ammount of Clients = " + ammountOfClients +
                ", \n Amount of Salesman = " + amountOfSalesman +
                ", \n ID of most expensive sale = " + mostExpensiveSaleId +
                ", \n Worst salesman ever = " + worstSalesman ;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Metrics metrics = (Metrics) o;
        return ammountOfClients == metrics.ammountOfClients &&
                amountOfSalesman == metrics.amountOfSalesman &&
                mostExpensiveSaleId == metrics.mostExpensiveSaleId &&
                Objects.equals(worstSalesman, metrics.worstSalesman);
    }

    @Override
    public int hashCode() {
        return Objects.hash(ammountOfClients, amountOfSalesman, mostExpensiveSaleId, worstSalesman);
    }
}
