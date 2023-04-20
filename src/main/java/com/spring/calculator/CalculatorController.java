package com.spring.calculator;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


//Web controller, leidžia naudoti @RequestMapping
//@RestContoller anotacija nurodo, kad pvz. String tipo rezultatas turėtų būti atvaizduojamas, toks, koks yra
@RestController

//Žymi konfigūracijos komponentą, viduje leidžia kurti BEAN per metodus su @Bean anotacija
//Ši klasės lygio anotacija nurodo Spring   framwork atspėti konfigūraciją
//Remiantis priklausomybėmis (JAR bibliotekomis), kurias programuotojas įtraukė į projektą (pom.xml)
//Šiuo atveju ji veikia kartu su main method
@EnableAutoConfiguration
public class CalculatorController {


    @GetMapping("/calc")
    // @RequestParam anotacija perduoda per URL perduodus duomenis (String, String) kurie patalpinami HasMap (K,V)
    public String calculate(@RequestParam HashMap<String, String> numbers) {

        //Per URL perduodamas key, turi pavadinima sk1
        //pagal key sk1 ištraukiama reikšmė, pvz. vartotojas įvėdė 8
        //mums reikia konvertuoti iš String į int, kad paskaičiuoti rezultatą
        //Pirmas String yra key (sk1,sk2, action) o antras  - value
        //Key tiek fronted tiek backed turi sutapti
        //URL pvz  http://localhost:8080/calc?sk1=20&sk2=20&action=*
        //simboliai koduojasi https://meyerweb.com/eric/tools/dencoder/
        int sk1 = Integer.parseInt(numbers.get("sk1"));
        int sk2 = Integer.parseInt(numbers.get("sk2"));

        String action = numbers.get("action");

        double result = 0;
        switch (action) {
            case "*":
                result = sk1 * sk2;
                break;
            case "/":
                if (sk1 > 0) {
                    result = sk1 / sk2;
                }
                break;
            case "-":
                result = sk1 - sk2;
                break;
            case "+":
                result = sk1 + sk2;
        }

        //TODO suskaičiuoti ir atspausdinti rezultata, kas iš ko
        // int result = sk1 * sk2 ;

        return sk1 + " " + action + " " + sk2 + "= " + result;
        // ApplicationContext yra interface skirtassuteikti informaciją apie aplikacijos konfigūraciją.
        //Šiuo atveju naudojama konfigūracija paimam iš xml failo
        //  ApplicationContext applicationContext = new ClassPathXmlApplicationContext("beans.xml");


//        return "Internet calculator <br><br>" +
//                "Galimos operacijos :<br>" +
//                "<ul><li> Sudėti </li>" +
//                "<li> Atimti </li>" +
//                "<li> Dauginti </li>" +
//                "<li> <a href='dalinti'>Dalinti</a> </li></ul>";

        //bean -  kalses objektas, kuris atitinka singleton pattern
        //   HelloWorld bean = (HelloWorld) applicationContext.getBean("helloWorld");
        //  return bean.getHello();


    }


    @RequestMapping("/dalinti")
    public String dalint() {
        double dalyba = 8 / 3;

        return "Dalyba 8/3 = " + dalyba;
    }


    @GetMapping("/list")
    public List<Student> all() {
        List<Student> students = new ArrayList<>();

        Student st1 = new Student(10, "Virgis", "Saule", 15);
        Student st2 = new Student(11, "Tadas", "Morkauskas", 20);

        students.add(new Student(12, "Karolis", "Kirvis", 20));

        students.add(st1);
        students.add(st2);

        return students;
    }

    @GetMapping("/list/{name}/{lastName}")
    public Student path(@PathVariable String name,
                        @PathVariable String lastName) {
        Student student = new Student();
        //int id = student.getId();
        // int age = student.getAge();

        return new Student(name, lastName);

    }

    @GetMapping("/list/query")
    public Student studentQuery(@RequestParam(name = "name") String name,
                                @RequestParam(name = "lastName") String lastName) {

        return new Student(name, lastName);
    }


}

