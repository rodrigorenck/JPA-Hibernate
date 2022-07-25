package com.rodrigorenck.loja.testes.curso1;

import com.rodrigorenck.loja.dao.CategoriaDao;
import com.rodrigorenck.loja.dao.ProdutoDao;
import com.rodrigorenck.loja.model.Categoria;
import com.rodrigorenck.loja.model.Produto;
import com.rodrigorenck.loja.util.JPAUtil;

import javax.persistence.EntityManager;
import java.math.BigDecimal;

public class CadastrandoNovoLivro {

    public static void main(String[] args) {

        EntityManager entityManager = JPAUtil.createEntityManager();

        CategoriaDao categoriaDao = new CategoriaDao(entityManager);
        Categoria livros = categoriaDao.buscarPorNome("LIVROS");

        Produto produto = new Produto("Do Mil ao Milhao", "Por Primo Rico", new BigDecimal("40"), livros);
        ProdutoDao produtoDao = new ProdutoDao(entityManager);
        produtoDao.cadastrar(produto);

    }
}
