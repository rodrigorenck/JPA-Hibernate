package com.rodrigorenck.loja.testes.curso1;

import com.rodrigorenck.loja.dao.CategoriaDao;
import com.rodrigorenck.loja.dao.ProdutoDao;
import com.rodrigorenck.loja.model.Categoria;
import com.rodrigorenck.loja.model.Produto;
import com.rodrigorenck.loja.util.JPAUtil;

import javax.persistence.EntityManager;
import java.math.BigDecimal;

public class AtualizandoDadoNaTabela {

    public static void main(String[] args) {

        EntityManager entityManager = JPAUtil.createEntityManager();

        CategoriaDao dao = new CategoriaDao(entityManager);

        entityManager.getTransaction().begin();
        Categoria categoriaParaAtualizar = entityManager.find(Categoria.class, 1L);
        categoriaParaAtualizar.setNome("CELULARES");
        Categoria id1 = entityManager.merge(categoriaParaAtualizar);
        entityManager.getTransaction().commit();
        entityManager.close();


    }
}
