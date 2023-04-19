package com.spring.calculator;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

//Web controller, leidžia naudoti @RequestMapping
//@RestContoller anotacija nurodo, kad pvz. String tipo rezultatas turėtų būti atvaizduojamas, toks, koks yra
@RestController

//Žymi konfigūracijos komponentą, viduje leidžia kurti BEAN per metodus su @Bean anotacija
//Ši klasės lygio anotacija nurodo Spring   framwork atspėti konfigūraciją
//Remiantis priklausomybėmis (JAR bibliotekomis), kurias programuotojas įtraukė į projektą (pom.xml)
//Šiuo atveju ji veikia kartu su main method
@EnableAutoConfiguration
public class CalculatorController {

    @RequestMapping(method = RequestMethod.GET, value = "/")
    public String index() {
        // ApplicationContext yra interface skirtassuteikti informaciją apie aplikacijos konfigūraciją.
        //Šiuo atveju naudojama konfigūracija paimam iš xml failo
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("beans.xml");


        //        return "Internet calculator <br><br>" +
        //                "Galimos operacijos :<br>" +
        //                "<ul><li> Sudėti </li>" +
        //                "<li> Atimti </li>" +
        //                "<li> Dauginti </li>" +
        //                "<li> <a href='dalinti'>Dalinti</a> </li></ul>";

        //bean -  kalses objektas, kuris atitinka singleton pattern
        HelloWorld bean = (HelloWorld) applicationContext.getBean("helloWorld");
        return bean.getHello();

    }


    @RequestMapping("/dalinti")
    public String dalint() {
        double dalyba = 8 / 3;

        return "Dalyba 8/3 = " + dalyba;
    }
}
