package com.rodrigorenck.loja.testes.curso1;

import com.rodrigorenck.loja.dao.CategoriaDao;
import com.rodrigorenck.loja.model.Categoria;
import com.rodrigorenck.loja.util.JPAUtil;

import javax.persistence.EntityManager;

public class AtualizandoDadoNaTabela2 {

    public static void main(String[] args) {

        EntityManager entityManager = JPAUtil.createEntityManager();

        entityManager.getTransaction().begin();
        Categoria categoriaLivros = entityManager.find(Categoria.class, 2L);
        categoriaLivros.setNome("LIVROS");
        entityManager.getTransaction().commit();
        entityManager.close();


    }
}
