package com.spring.calculator.model;

import jakarta.validation.constraints.Min;

import javax.persistence.*;

//@Entity tai, POJO klasė sujungta su DB esančia lentele nudojant ORM techniką
@Entity
//Anotacija @Table nurodo, jog susiesime POJO klasę su DB esančia lentele, kurios pavadinimas "numbers"
@Table(name = "numbers") //DB lentelė - daugiskaita pagal kalsės pavadinimą
public class Number {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)//AKA DB autoincrement f-ja
    @Column(name = "id")
    private int id;

    @Column(name = "sk1")
    @Min(value = 0, message = "Validacijos klaida: skaičius negali būti neigiamas")
    private int sk1;

    @Column(name = "sk2")
    @Min(value = 0, message = "Validacijos klaida: skaičius negali būti neigiamas")
    private int sk2;

    @Column(name = "action")
    private String action;

    @Column(name = "result")
    private double result;


    //būtinas tuščias konstruktorius naudojant Spring framework
    public Number() {
    }

    //Esamų DB įrašų paieškai, redagavimui ir trynimui
    public Number(int id, int sk1, int sk2, String action, double result) {
        this.id = id;
        this.sk1 = sk1;
        this.sk2 = sk2;
        this.action = action;
        this.result = result;
    }

    //Naujam įrašui kuriant
    public Number(int sk1, int sk2, String action, double result) {
        this.sk1 = sk1;
        this.sk2 = sk2;
        this.action = action;
        this.result = result;
    }

    public int getSk1() {
        return sk1;
    }

    public int getId() {
        return id;
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

    public double getResult() {
        return result;
    }

    public void setResult(double result) {
        this.result = result;
    }

    @Override
    public String toString() {
        return "Number{" +
                "id=" + id +
                ", sk1=" + sk1 +
                ", sk2=" + sk2 +
                ", action='" + action + '\'' +
                ", result=" + result +
                '}';
    }

}
