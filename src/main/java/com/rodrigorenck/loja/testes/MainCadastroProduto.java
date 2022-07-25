package com.rodrigorenck.loja.testes;

import com.rodrigorenck.loja.dao.CategoriaDao;
import com.rodrigorenck.loja.dao.ProdutoDao;
import com.rodrigorenck.loja.model.Categoria;
import com.rodrigorenck.loja.model.Produto;
import com.rodrigorenck.loja.util.JPAUtil;

import javax.persistence.EntityManager;
import java.math.BigDecimal;

public class MainCadastroProduto {

    public static void main(String[] args) {
        cadastrando();


    }

    private static void cadastrando() {
        Categoria categoria = new Categoria("CASA");
        Produto produto = new Produto("Almofadas", "Fofinhas", new BigDecimal("30"), categoria);


        EntityManager entityManager = JPAUtil.createEntityManager();

        CategoriaDao categoriaDao = new CategoriaDao(entityManager);
        ProdutoDao produtoDao = new ProdutoDao(entityManager);

        entityManager.getTransaction().begin();

        categoriaDao.cadastrar(categoria);
        produtoDao.cadastrar(produto);

        entityManager.getTransaction().commit();
        entityManager.close();
    }
}
