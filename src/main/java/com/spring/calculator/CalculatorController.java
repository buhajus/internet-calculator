package com.spring.calculator;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
<<<<<<< HEAD
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
=======
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
>>>>>>> origin/main

//Web controller, leidžia naudoti @RequestMapping
//@RestContoller anotacija nurodo, kad pvz. String tipo rezultatas turėtų būti atvaizduojamas, toks, koks yra
@RestController

//Žymi konfigūracijos komponentą, viduje leidžia kurti BEAN per metodus su @Bean anotacija
//Ši klasės lygio anotacija nurodo Spring   framwork atspėti konfigūraciją
//Remiantis priklausomybėmis (JAR bibliotekomis), kurias programuotojas įtraukė į projektą (pom.xml)
//Šiuo atveju ji veikia kartu su main method
@EnableAutoConfiguration
public class CalculatorController {

<<<<<<< HEAD
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
=======
    @GetMapping("/")
    public String index() {
        // ApplicationContext yra interface skirtassuteikti informaciją apie aplikacijos konfigūraciją.
        //Šiuo atveju naudojama konfigūracija paimam iš xml failo
        // ApplicationContext applicationContext = new ClassPathXmlApplicationContext("beans.xml");


        return "Internet calculator <br><br>" +
                "Galimos operacijos :<br>" +
                "<ul><li> Sudėti </li>" +
                "<li> Atimti </li>" +
                "<li> Dauginti </li>" +
                "<li> <a href='dalinti'>Dalinti</a> </li></ul>";

        //bean -  kalses objektas, kuris atitinka singleton pattern
        // HelloWorld bean = (HelloWorld) applicationContext.getBean("helloWorld");
        // return bean.getHello();
>>>>>>> origin/main

    }


    @RequestMapping("/dalinti")
    public String dalint() {
        double dalyba = 8 / 3;

        return "Dalyba 8/3 = " + dalyba;
    }
<<<<<<< HEAD
=======

    @GetMapping("/list")
    public List<Student> all() {
        List<Student> students = new ArrayList<>();

        Student st1 = new Student(10, "Virgis", "Saule", 15);
        Student st2 = new Student(11, "Tadas", "Morkauskas", 20);

        students.add(st1);
        students.add(st2);

        return students;
    }

    @GetMapping("/list/{name}/{lastName}")
    public Student path(@PathVariable String name, @PathVariable String lastName) {
        Student student = new Student();
        //int id = student.getId();
        // int age = student.getAge();

        return new Student(name, lastName);

    }

    @GetMapping("/list/query")
    public Student studentQuery(@RequestParam(name = "name") String name
            , @RequestParam(name = "lastName") String lastName) {
        return new Student(name, lastName);
    }
>>>>>>> origin/main
}
