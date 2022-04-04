package com.nero.socialmedia.analysis.instagram.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import java.util.function.Supplier;

@Service
public class TransactionServiceImpl implements TransactionService {
    private final EntityManagerFactory entityManagerFactory;

    public TransactionServiceImpl(@Autowired EntityManagerFactory entityManagerFactory) {
        this.entityManagerFactory = entityManagerFactory;
    }

    @Override
    public <T> T runTransaction(Supplier<T> executor) {
        EntityManager em = entityManagerFactory.createEntityManager();
        EntityTransaction entityTransaction = em.getTransaction();
        try {
            entityTransaction.begin();
            T result = executor.get();
            em.getTransaction().commit();
            return result;
        } catch (Exception e) {
            entityTransaction.rollback();
            throw e;
        }
    }
}
