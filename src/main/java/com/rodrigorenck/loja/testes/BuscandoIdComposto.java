package com.rodrigorenck.loja.testes;

import com.rodrigorenck.loja.model.Categoria;
import com.rodrigorenck.loja.model.embedded.CategoriaId;
import com.rodrigorenck.loja.util.JPAUtil;

import javax.persistence.EntityManager;

public class BuscandoIdComposto {

    public static void main(String[] args) {
        EntityManager em = JPAUtil.createEntityManager();

        Categoria categoria = em.find(Categoria.class, new CategoriaId("CASA", "teste"));
        System.out.println(categoria);
    }
}
