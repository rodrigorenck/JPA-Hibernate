package com.rodrigorenck.loja.testes.curso1;

import com.rodrigorenck.loja.dao.CategoriaDao;
import com.rodrigorenck.loja.model.Categoria;
import com.rodrigorenck.loja.util.JPAUtil;

import javax.persistence.EntityManager;

public class TesteMerge {

    public static void main(String[] args) {

        Categoria categoria = new Categoria("MAMAO");

        EntityManager entityManager = JPAUtil.createEntityManager();

        CategoriaDao dao = new CategoriaDao(entityManager);


        entityManager.getTransaction().begin();
        categoria.setNome("MATHEUS");
        entityManager.flush();
//        entityManager.getTransaction().commit();
        entityManager.clear();
        //DETACHED
        categoria = entityManager.merge(categoria);
        //MANAGED
        categoria.setNome("JOSE");
        entityManager.getTransaction().commit();
        entityManager.close();
    }
}
