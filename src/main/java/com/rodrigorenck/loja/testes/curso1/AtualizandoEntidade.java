package com.rodrigorenck.loja.testes.curso1;

import com.rodrigorenck.loja.dao.CategoriaDao;
import com.rodrigorenck.loja.model.Categoria;
import com.rodrigorenck.loja.util.JPAUtil;

import javax.persistence.EntityManager;

public class AtualizandoEntidade {


    public static void main(String[] args) {


        EntityManager entityManager = JPAUtil.createEntityManager();
        CategoriaDao dao = new CategoriaDao(entityManager);

        entityManager.getTransaction().begin();
        Categoria id22 = entityManager.find(Categoria.class, 22L);
        id22.setNome("ATUALIZANDO");
        dao.atualizar(id22);
        entityManager.getTransaction().commit();
        entityManager.close();


    }
}
