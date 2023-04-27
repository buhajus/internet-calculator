package com.spring.calculator.service;

import com.spring.calculator.model.Number;
import com.spring.calculator.model.NumberDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;

//@Service - sluoknis skirtas "verslo" logikai
//po service sluoksniu keipiamės į DB
@Service
public class NumberServiceImpl implements NumberService {

    // @Autowired - naudojama automatinei priklausomybių injekcijai (IOC - inversion of control)
    //kad panaudoti @Autowired anotaciją reikia pirmiausia turėti apsirašius
    // @Bean @Configuration klasę
    @Autowired
    //@Qualifier anotacija kartu su @Autowired patikslina su kuriuo konkrečiai
    // Bean susieti priklausomybę.
    //Jeigu @Configuration klasėje yra daugiau negu vienas Bean, @Qualifier anotacija - privaloma
    //kitu atveju metama klaida -
    // Consider marking one of the beans as @Primary, updating the consumer to accept multiple beans
    // or using @Qualifier to identify the bean that should be consumed
    @Qualifier("NumberDAO")
    private NumberDAO numberDAO;

    @Override
    public void insert(Number number) {
        numberDAO.insertEntity(number);
    }

    @Override
    public Number getById(int id) {
        return numberDAO.findEntityById(id);
    }

    @Override
    public List<Number> getAll() {
        return numberDAO.findEntities();
    }

    @Override
    public void update(Number number) {
        numberDAO.updateEntity(number);
    }

    @Override
    public void delete(int id) {
        numberDAO.removeEntityById(id);
    }
}
