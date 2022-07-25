package com.rodrigorenck.loja.testes.curso1;

import com.rodrigorenck.loja.dao.CategoriaDao;
import com.rodrigorenck.loja.dao.ProdutoDao;
import com.rodrigorenck.loja.model.Categoria;
import com.rodrigorenck.loja.model.Produto;
import com.rodrigorenck.loja.util.JPAUtil;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.math.BigDecimal;

public class PersistindoProdutoAtualizado {

    public static void main(String[] args) {
        Categoria categoria = new Categoria("livros");
        Produto produto = new Produto("12 Regras Para Vida", "Por Jordan Peterson", new BigDecimal("50"), categoria);

        EntityManager entityManager = JPAUtil.createEntityManager();

        ProdutoDao produtoDao = new ProdutoDao(entityManager);
        CategoriaDao categoriaDao = new CategoriaDao(entityManager);

        entityManager.getTransaction().begin();


//        categoriaDao.cadastrar(categoria);
//        produtoDao.cadastrar(produto);

        entityManager.persist(categoria);
        categoria.setNome("FFF");

        entityManager.clear();

        Categoria categoriaManaged = entityManager.merge(categoria);
        categoriaManaged.setNome("1234");
        entityManager.flush();
        entityManager.getTransaction().commit();

    }
}
