package com.rodrigorenck.loja.testes.curso1;

import com.rodrigorenck.loja.dao.CategoriaDao;
import com.rodrigorenck.loja.dao.ProdutoDao;
import com.rodrigorenck.loja.model.Categoria;
import com.rodrigorenck.loja.model.Produto;
import com.rodrigorenck.loja.util.JPAUtil;

import javax.persistence.EntityManager;
import java.math.BigDecimal;

public class PersistindoProdutoAtualizado2 {

    public static void main(String[] args) {
        Categoria categoria = new Categoria("churipa");
        Produto produto = new Produto("12 Regras Para Vida", "Por Jordan Peterson", new BigDecimal("50"), categoria);

        EntityManager entityManager = JPAUtil.createEntityManager();


        entityManager.getTransaction().begin();

        entityManager.persist(categoria);
        categoria.setNome("kkkk");
        entityManager.flush();

        entityManager.clear();

        //colocamos de volta no estado managed
        Categoria livros = entityManager.find(Categoria.class, 17L);
        entityManager.remove(livros);
        entityManager.getTransaction().commit();
        entityManager.close();

    }
}
