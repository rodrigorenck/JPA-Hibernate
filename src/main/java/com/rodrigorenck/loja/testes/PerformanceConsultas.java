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
import java.time.LocalDate;
import java.util.List;

public class PerformanceConsultas {

    public static void main(String[] args) {
        populandoBanco();
        EntityManager entityManager = JPAUtil.createEntityManager();

        PedidoDao pedidoDao = new PedidoDao(entityManager);

        Pedido pedido = pedidoDao.buscarPedidoComCliente(1L);

        entityManager.close();
        System.out.println(pedido.getCliente().getNome());

    }

    private static void populandoBanco() {
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

        Produto produtoId1 = produtoDao.buscaPorId(1L);
        Produto produtoId2 = produtoDao.buscaPorId(2L);


        Cliente cliente1 = new Cliente("Rodrigo", "1234567");
        Cliente cliente2 = new Cliente("Joao", "9876543");
        ClienteDao clienteDao = new ClienteDao(entityManager);

        clienteDao.cadastrar(cliente1);
        clienteDao.cadastrar(cliente2);

        Pedido pedido1 = new Pedido(cliente1);
        Pedido pedido2 = new Pedido(cliente2);
        ItemPedido item1Pedido1 = new ItemPedido(5, produtoId1, pedido1);
        ItemPedido item2Pedido1 = new ItemPedido(3, produtoId2, pedido1);
        ItemPedido item1Pedido2 = new ItemPedido(20, produtoId2, pedido2);

        clienteDao.cadastrar(cliente1);
        clienteDao.cadastrar(cliente2);

        PedidoDao pedidoDao = new PedidoDao(entityManager);

        pedido1.adicionarItem(item1Pedido1);
        pedido1.adicionarItem(item2Pedido1);
        pedidoDao.cadastrar(pedido1);

        pedido2.adicionarItem(item1Pedido2);
        pedidoDao.cadastrar(pedido2);

        entityManager.getTransaction().commit();
        entityManager.close();
    }
}
