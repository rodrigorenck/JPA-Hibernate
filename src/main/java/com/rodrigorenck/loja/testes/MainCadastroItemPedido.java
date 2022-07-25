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

public class MainCadastroItemPedido {

    public static void main(String[] args) {
        //Cadastramos produto e categoria no banco
        cadastrando();

        //recuperar um produto do banco
        EntityManager entityManager = JPAUtil.createEntityManager();
        ProdutoDao produtoDao = new ProdutoDao(entityManager);
        Produto produtoId1 = produtoDao.buscaPorId(1L);
        Produto produtoId2 = produtoDao.buscaPorId(2L);

        entityManager.getTransaction().begin();
        //antes de cadastrarmos nosso pedido eh preciso cadastrar o cliente, se nao teremos uma Transient exception
            //que nem tinha acontecido quando tentamos cadastrar um produto antes de cadastrar uma categoria
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

        PedidoDao pedidoDao = new PedidoDao(entityManager);

        pedido1.adicionarItem(item1Pedido1);
        pedido1.adicionarItem(item2Pedido1);
        pedidoDao.cadastrar(pedido1);

        pedido2.adicionarItem(item1Pedido2);
        pedidoDao.cadastrar(pedido2);

        entityManager.getTransaction().commit();

        BigDecimal valorTotalVendido = pedidoDao.valorTotalVendido();
        System.out.println("Valor total vendido: " + valorTotalVendido);

        Double valorMedioPedidos = pedidoDao.valorMedioDosPedidos();
        System.out.println("Valor medio dos pedidos: " + valorMedioPedidos);

//        Pedido pedidoDeMaiorValor = pedidoDao.pedidoDeMaiorValor();
//        System.out.println("Cliente que fez o pedido mais caro: " + pedidoDeMaiorValor.getCliente().getNome() +
//                "\n" + "Valor do pedido: " + pedidoDeMaiorValor.getValorTotal());
        List<RelatorioDeVendasVo> relatorio = pedidoDao.relatorio();
        relatorio.forEach(System.out::println);

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
