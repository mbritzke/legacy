package com.github.mbritzke.model;

import java.util.Objects;

public class Salesman {

    private String cpf;
    private String name;
    private Double salary;

    public static class Builder {

        private String cpf;
        private String name;
        private Double salary;

        public Builder(String cpf) {
            this.cpf = cpf;
        }

        public Builder withName(String name) {
            this.name = name;
            return this;
        }

        public Builder withSalary(Double salary) {
            this.salary = salary;
            return this;
        }

        public Salesman build(){
            Salesman salesman = new Salesman();
            salesman.cpf = this.cpf;
            salesman.name = this.name;
            salesman.salary = this.salary;
            return salesman;
        }

    }

    private Salesman(){}

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Salesman salesman = (Salesman) o;
        return Objects.equals(cpf, salesman.cpf) &&
                Objects.equals(name, salesman.name) &&
                Objects.equals(salary, salesman.salary);
    }

    @Override
    public int hashCode() {
        return Objects.hash(cpf, name, salary);
    }
}
