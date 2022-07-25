package com.rodrigorenck.loja.testes;

import com.rodrigorenck.loja.dao.CategoriaDao;
import com.rodrigorenck.loja.dao.ClienteDao;
import com.rodrigorenck.loja.dao.PedidoDao;
import com.rodrigorenck.loja.dao.ProdutoDao;
import com.rodrigorenck.loja.model.*;
import com.rodrigorenck.loja.util.JPAUtil;
import com.rodrigorenck.loja.vo.RelatorioDeVendasVo;

import javax.persistence.EntityManager;
import java.math.BigDecimal;
import java.util.List;

public class MainBuscarProduto {

    public static void main(String[] args) {
        //Cadastramos produto e categoria no banco
        cadastrando();

        //recuperar um produto do banco
        EntityManager entityManager = JPAUtil.createEntityManager();
        ProdutoDao produtoDao = new ProdutoDao(entityManager);

        //testando Named Query buscaProdutosPorNomeCategoria
        List<Produto> casa = produtoDao.buscaProdutosPorNomeCategoria("CASA");
        casa.forEach(p -> System.out.println(p.getNome()));

        entityManager.close();

    }

    private static void cadastrando() {
        Categoria categoria = new Categoria("CASA");
        Produto produto = new Produto("Almofadas", "Fofinhas", new BigDecimal("30"), categoria);
        Produto produto2 = new Produto("Cadeiras com encosto", "De madeira", new BigDecimal("25"), categoria);


        EntityManager entityManager = JPAUtil.createEntityManager();

        CategoriaDao categoriaDao = new CategoriaDao(entityManager);
        ProdutoDao produtoDao = new ProdutoDao(entityManager);

        entityManager.getTransaction().begin();

        categoriaDao.cadastrar(categoria);
        produtoDao.cadastrar(produto);
        produtoDao.cadastrar(produto2);

        entityManager.getTransaction().commit();
        entityManager.close();
    }
}
