package com.spring.calculator.config;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

//klasė skirta sukurti entity manager factory
public class JPAUtil {

    private static final String PERSISTENCE_UNIT_NAME = "PERSISTENCE";
    private static EntityManagerFactory entityManagerFactory;

    //entityManagerFactory per aplikacijos veikimą bus tik viena (tuo tarpu sesijų, bus daug..)
    //entityManagerFactory veikia SINGLETON šablonu (pattern)
    public static EntityManagerFactory getEntityManagerFactory() {
        if (entityManagerFactory == null) {
            entityManagerFactory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
        }
        return entityManagerFactory;
    }

    public static void shutdown() {
        if (entityManagerFactory != null) {
            entityManagerFactory.close();
        }
    }

}
