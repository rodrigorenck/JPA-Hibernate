package com.rodrigorenck.loja.testes.curso1;

import com.rodrigorenck.loja.dao.CategoriaDao;
import com.rodrigorenck.loja.dao.ProdutoDao;
import com.rodrigorenck.loja.model.Categoria;
import com.rodrigorenck.loja.model.Produto;
import com.rodrigorenck.loja.util.JPAUtil;

import javax.persistence.EntityManager;
import java.math.BigDecimal;

public class Testando {


    public static void main(String[] args) {
        EntityManager entityManager = JPAUtil.createEntityManager();

        Categoria category = new Categoria("SMART WATCHS");

        Produto product1 = new Produto("Iphone 11", "Use anywhere", new BigDecimal("1200"), category);

        ProdutoDao productDao = new ProdutoDao(entityManager);
        CategoriaDao categoryDao = new CategoriaDao(entityManager);



        categoryDao.cadastrar(category);
        productDao.cadastrar(product1);


    }


}
