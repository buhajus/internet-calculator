package com.spring.calculator.config;

import com.spring.calculator.CalculatorApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

//Servlet yra JAVA programa veikianti web serberyje
//Servlet paleidžiamas kai varotojojas paspaudžia nuorodą, pateikia formą ar atlieka kito tipo veiksmus webe
// Kiekviena kliento užklasusa (request) praeina per servlet, kuris jį perduoda Controlerio request mapping atributui
public class ServletInitializer extends SpringBootServletInitializer {

	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
		return application.sources(CalculatorApplication.class);
	}

}
