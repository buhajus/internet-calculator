package com.spring.calculator;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


//SpringBootApplication anotacija yra lygi @Configuration, @EnableAutoConfiguration ir @ComponentsScan (bean registruoti)
//Ši anotacija nurodoma klasėje, turinčioje main method
@SpringBootApplication
public class CalculatorApplication {


	public static void main(String[] args) {

		//Programos kontrolė, deleguojama statiniam klases method run
		//Nurodant aplikacijos šakninį (root) komponentą
		//Spring karkasas paleis aplikaciją, t.y. startuos TomCat serverį
		SpringApplication.run(CalculatorApplication.class, args);
	}

}
