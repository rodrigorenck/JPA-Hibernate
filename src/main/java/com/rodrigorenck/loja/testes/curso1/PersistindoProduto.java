package com.rodrigorenck.loja.testes.curso1;

import com.rodrigorenck.loja.model.Produto;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.math.BigDecimal;

public class PersistindoProduto {

    public static void main(String[] args) {
        Produto produto = new Produto();
        produto.setNome("Macbook Pro");
        produto.setDescricao("Bom para programar");
        produto.setPreco(new BigDecimal("10000"));

        EntityManagerFactory factory = Persistence.createEntityManagerFactory("loja");
        EntityManager entityManager = factory.createEntityManager();
        entityManager.getTransaction().begin();
        entityManager.persist(produto); //criar uma classe DAO que encapsula esse comportamento
        entityManager.getTransaction().commit();
        entityManager.close();


    }
}
