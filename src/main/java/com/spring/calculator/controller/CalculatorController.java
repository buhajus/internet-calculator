package com.spring.calculator.controller;


import com.pi4j.io.gpio.*;
import com.pi4j.io.gpio.event.GpioPinDigitalStateChangeEvent;
import com.pi4j.util.Console;
import com.spring.calculator.model.Number;
import com.spring.calculator.service.NumberService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;

import org.springframework.web.bind.annotation.*;

import java.util.HashMap;


//Web controller, leidžia naudoti @RequestMapping
//@RestContoller anotacija nurodo, kad pvz. String tipo rezultatas turėtų būti atvaizduojamas, toks, koks yra
//@RestContoller anotacija naudojama tada kai frontedne nenaudojam springo (javascirpt, react, angular)
//dažniausiai gražinami formatai, JSON, .xml
//t.y. negražinami vaizdo (html, jsp)
//@RestController

//kadangi mus reikia gražinti (view) pagal spring MVC, naudosime anotacija @Controller
@Controller
//Žymi konfigūracijos komponentą, viduje leidžia kurti BEAN per metodus su @Bean anotacija
//Ši klasės lygio anotacija nurodo Spring   framwork atspėti konfigūraciją
//Remiantis priklausomybėmis (JAR bibliotekomis), kurias programuotojas įtraukė į projektą (pom.xml)
//Šiuo atveju ji veikia kartu su main method
@EnableAutoConfiguration
public class CalculatorController {

    //Kaip perduodami duomenys skirtiengiems komponentams:
    //vartotojas -> CalculatorContoller -> NumberServiceImpl -> NumberDAOImpl

    @Autowired
    @Qualifier("NumberService")
    public NumberService numberService;
    private static GpioController pin;
    final GpioController gpioController = GpioFactory.getInstance();
    final Console console = new Console();

    //kadangi skaiciuotuvo forma naudoja POST f-ja, cia irgi nurodysime POST
    @PostMapping("/calculate")
    // @RequestParam anotacija perduoda per URL perduodus duomenis (String, String) kurie patalpinami HasMap (K,V)

    public String calculate(
            //Svarbu: parametras BindingResult turi eiti iškarto po anotacijos @Valid
            //Kitu atveju gausime klaidą "Validation failed for object"
            @Valid @ModelAttribute("number") Number numb, BindingResult bindingResult,
            @RequestParam HashMap<String, String> inputForm, ModelMap outputForm) {

        //Per URL perduodamas key, turi pavadinima sk1
        //pagal key sk1 ištraukiama reikšmė, pvz. vartotojas įvėdė 8
        //mums reikia konvertuoti iš String į int, kad paskaičiuoti rezultatą
        //Pirmas String yra key (sk1,sk2, action) o antras  - value
        //Key tiek fronted tiek backed turi sutapti
        //URL pvz  http://localhost:8080/calc?sk1=20&sk2=20&action=*
        //simboliai koduojasi https://meyerweb.com/eric/tools/dencoder/

        //jeigu validacijos klaidos(žr. Number class aprašytą validacija prie kiekvieno skaičiaus)
        if (bindingResult.hasErrors()) {
            //vartotojas lieka skaičiuotuvo puslapyje tol, kol neištaiso validacijos klaidų
            return "calculator";
        } else {//vartotojas praėjo validaciją-skaičiuojamas rezultatas

            int sk1 = Integer.parseInt(inputForm.get("sk1"));
            int sk2 = Integer.parseInt(inputForm.get("sk2"));
            String action = inputForm.get("action");

            double result = 0;
            switch (action) {
                case "*":
                    result = sk1 * sk2;
                    break;
                case "/":
                    if (sk2 != 0) {
                        result = (double) sk1 / sk2;
                    }
                    break;
                case "-":
                    result = sk1 - sk2;
                    break;
                case "+":
                    result = sk1 + sk2;
                    break;
            }

            //TODO suskaičiuoti ir atspausdinti rezultata, kas iš ko
            // int result = sk1 * sk2 ;
            //inputForm naudojamas siųsi duomenis iš spring MVC controller į JSP failą (vaizdą)
            outputForm.put("sk1", sk1);
            outputForm.put("sk2", sk2);
            outputForm.put("action", action);
            outputForm.put("result", result);

            //Kreipiamės į Service kuris savo ruoštu krepiasi į DAO ir išsaugoja įrašą DB
            numberService.insert(new Number(sk1, sk2, action, result));

            return "calculate";
        }


        //grąžinamas vaizdas (forma .jsp)
        //svarbu nurodyti per application.properties prefix ir suffix nes pagal tai ieškos vaizdo projekte
        // return "calculate";
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

    @GetMapping("/")
    public String homePage(Model model) {
        //Jeigu Model "number" nepraeina validacijos - per jį grąžinamos validacijos
        //klaidos į View
        model.addAttribute("number", new Number());
        //grąžiname JSP failą, kuris turi būti talpinamas "webapp -> WEB-INF ->  JSP" folderi
        return "calculator";
    }

    @RequestMapping("/sd")
    public String sdCard() throws InterruptedException {
        int pinNumber = 27;
        if(pin == null){
            GpioController gpio = GpioFactory.getInstance();
            pin = (GpioController) gpio.provisionDigitalOutputPin(RaspiPin.getPinByAddress(pinNumber),"sensor",PinState.LOW);
            console.println("high");
            return "high";
        }
        pin.toggle();

       //Thread.sleep(1000);

//        while (true){
//            GpioPinDigitalInput pin = (GpioPinDigitalInput) gpioController.provisionDigitalOutputPin(RaspiPin.getPinByAddress(pinNumber));
//            PinState pinState = pin.getState();
//            if (pinState.isHigh()) {
//
//
//            } else {
//                console.println("Low");
//
//            }
//
//
//        }

        return "low";

    }





    @RequestMapping(method = RequestMethod.GET, value = "/numbers")
    public String getAllNumbers(Model model) {
        model.addAttribute("numbers", numberService.getAll());
        return "numbers";
    }


    //id - gaunamas iš front end vartotojui pasrinkus konkretų įrašą
    @RequestMapping(method = RequestMethod.GET, value = "/show{id}")
    public String getById(@RequestParam("id") int id, Model model) {
        model.addAttribute("number", numberService.getById(id));
        return "number";
    }

    @RequestMapping(method = RequestMethod.GET, value = "/delete{id}")
    public String deleteById(@RequestParam("id") int id, Model model) {
        numberService.delete(id);
        model.addAttribute("numbers", numberService.getAll());
        return "numbers";
    }

    //atnaujinat išrašą, pirmiausia reikia jį parodyti
    @RequestMapping(method = RequestMethod.GET, value = "/update{id}")
    public String updateById(@RequestParam("id") int id, Model model) {
        //Kai užkrauname įrašo redagavimo formą, privalome jos laukelius užpildyti įrašo informacija
        model.addAttribute("number", numberService.getById(id));
        return "update";
    }

    //Kadangi forma naudoja metoda POST, čia irgi nurodome POST
    @RequestMapping(method = RequestMethod.POST, value = "/updateNumber")
    public String updateNumber(@ModelAttribute("number") Number number) {
        numberService.update(number);
        //redirect - nukreipia vartotoją į įrašą atvaizdavimo puslapį(getById)
        return "redirect:/show?id=" + number.getId();
    }

}


//
//    @RequestMapping("/test")
//    public String dalint() {
//        double dalyba = 8 / 3;
//
//        return "Dalyba 8/3 = " + dalyba;
//    }
//
//
//    @GetMapping("/list")
//    public List<Student> all() {
//        List<Student> students = new ArrayList<>();
//
//        Student st1 = new Student(10, "Virgis", "Saule", 15);
//        Student st2 = new Student(11, "Tadas", "Morkauskas", 20);
//
//        students.add(new Student(12, "Karolis", "Kirvis", 20));
//
//        students.add(st1);
//        students.add(st2);
//
//        return students;
//    }
//
//    @GetMapping("/list/{name}/{lastName}")
//    public Student path(@PathVariable String name,
//                        @PathVariable String lastName) {
//        Student student = new Student();
//        //int id = student.getId();
//        // int age = student.getAge();
//
//        return new Student(name, lastName);
//
//    }
//
//    @GetMapping("/list/query")
//    public Student studentQuery(@RequestParam(name = "name") String name,
//                                @RequestParam(name = "lastName") String lastName) {
//
//        return new Student(name, lastName);
//    }



