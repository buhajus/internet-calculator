package com.spring.calculator;

import jakarta.validation.constraints.Min;

public class Number {

    @Min(value = 0, message = "Validacijos klaida: skaičius negali būti neigiamas")
    private int sk1;

    @Min(value = 0, message = "Validacijos klaida: skaičius negali būti neigiamas")
    private int sk2;

    private String action;
    private int result;


    //būtinas naudojant Spring framework
    public Number() {
    }

    public Number(int sk1, int sk2, String action, int result) {
        this.sk1 = sk1;
        this.sk2 = sk2;
        this.action = action;
        this.result = result;
    }

    public int getSk1() {
        return sk1;
    }

    public void setSk1(int sk1) {
        this.sk1 = sk1;
    }

    public int getSk2() {
        return sk2;
    }

    public void setSk2(int sk2) {
        this.sk2 = sk2;
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public int getResult() {
        return result;
    }

    public void setResult(int result) {
        this.result = result;
    }

    @Override
    public String toString() {
        return "Number{" +
                "sk1=" + sk1 +
                ", sk2=" + sk2 +
                ", action='" + action + '\'' +
                ", result=" + result +
                '}';
    }
}
