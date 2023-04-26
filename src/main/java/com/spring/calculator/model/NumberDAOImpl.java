package com.spring.calculator.model;

import com.spring.calculator.config.JPAUtil;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import java.util.List;

public class NumberDAOImpl implements NumberDAO {

    @Override
    public void insertEntity(Number number) {
        EntityManager entityManager = JPAUtil.getEntityManagerFactory().createEntityManager();

        EntityTransaction entityTransaction = entityManager.getTransaction();
        entityTransaction.begin();

        entityManager.persist(number);//išsaugo objektą DB(ojektas = įrašas lentelėje) - ORM

        entityManager.getTransaction().commit();
        entityManager.close();
    }

    @Override
    public Number findEntityById(int id) {
        EntityManager entityManager = JPAUtil.getEntityManagerFactory().createEntityManager();

        EntityTransaction entityTransaction = entityManager.getTransaction();
        entityTransaction.begin();

        List<Number> numbers = entityManager
                //ORM modelyje ieškome ne DB lentelėjė, bet Entity
                .createQuery("SELECT n FROM Number n WHERE n.id = :id")//n
                .setParameter("id", id)
                .getResultList();//negrąžinamas ResultSet, o grąžinamas List

        entityManager.getTransaction().commit();
        entityManager.close();

        return numbers.get(0);
    }

    @Override
    public List<Number> findEntities() {
        EntityManager entityManager = JPAUtil.getEntityManagerFactory().createEntityManager();

        EntityTransaction entityTransaction = entityManager.getTransaction();
        entityTransaction.begin();

        List<Number> numbers = entityManager
                .createQuery("SELECT n FROM Number n")
                .getResultList();

        entityManager.getTransaction().commit();
        entityManager.close();

        return numbers;
    }

    @Override
    public void updateEntity(Number number) {

        EntityManager entityManager = JPAUtil.getEntityManagerFactory().createEntityManager();

        EntityTransaction entityTransaction = entityManager.getTransaction();
        entityTransaction.begin();

        //atnaujinti įrašą reikia pirmaisia jį surasti DB
        Number numberDB = entityManager.find(Number.class /* Entity*/, number.getId());
        numberDB.setSk1(number.getSk1());
        numberDB.setSk2(number.getSk2());
        numberDB.setAction(number.getAction());
        numberDB.setResult(number.getResult());

        entityManager.getTransaction().commit();
        entityManager.close();
    }

    @Override
    public void removeEntityById(int id) {

        EntityManager entityManager = JPAUtil.getEntityManagerFactory().createEntityManager();

        EntityTransaction entityTransaction = entityManager.getTransaction();
        entityTransaction.begin();

        Number numberDB = entityManager.find(Number.class /* Entity*/, id);
        entityManager.remove(numberDB);

        entityManager.getTransaction().commit();
        entityManager.close();

    }
}
