package com.rodrigorenck.loja.testes.curso1;

import com.rodrigorenck.loja.model.Categoria;
import com.rodrigorenck.loja.util.JPAUtil;

import javax.persistence.EntityManager;

public class RemovendoDado {

    public static void main(String[] args) {

        EntityManager entityManager = JPAUtil.createEntityManager();

        entityManager.getTransaction().begin();
        Categoria id8 = entityManager.find(Categoria.class, 8L);
        entityManager.remove(id8);
        entityManager.getTransaction().commit();
        entityManager.close();

    }
}
