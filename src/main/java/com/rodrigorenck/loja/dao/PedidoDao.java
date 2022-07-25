package com.rodrigorenck.loja.dao;

import com.rodrigorenck.loja.model.Pedido;
import com.rodrigorenck.loja.vo.RelatorioDeVendasVo;

import javax.persistence.EntityManager;
import java.math.BigDecimal;
import java.util.List;

public class PedidoDao {

    private EntityManager entityManager;

    public PedidoDao(EntityManager entityManager){
        this.entityManager = entityManager;
    }

    public void cadastrar(Pedido pedido){
        entityManager.persist(pedido);
    }

    public Pedido buscarPeloId(Long id){
        return  entityManager.find(Pedido.class, id);
    }

    //Query planejada!
    //vai fazer o JOIN com a tabela Cliente - como se fosse Eager
    public Pedido buscarPedidoComCliente(Long id){
        String jpql = "SELECT p FROM Pedido p JOIN FETCH p.cliente WHERE p.id = :id";
        return  entityManager.createQuery(jpql, Pedido.class).setParameter("id", id).getSingleResult();
    }

    public BigDecimal valorTotalVendido(){
        String jpql = "SELECT SUM(p.valorTotal) FROM Pedido p";
        return entityManager.createQuery(jpql, BigDecimal.class).getSingleResult();
    }

    public Double valorMedioDosPedidos(){
        String jpql = "SELECT AVG(p.valorTotal) FROM Pedido p";
        return entityManager.createQuery(jpql, Double.class).getSingleResult();
    }

    //relatorio - nome produto | quantidade vendida | data da ultima venda
    //cada linha deve ser um produto distinto - group by produto.nome!
    //precisa passar o FQN da classe no SELECT new!
    public List<RelatorioDeVendasVo> relatorio(){
        String jpql = "SELECT new com.rodrigorenck.loja.vo.RelatorioDeVendasVo(" +
                "produto.nome, " +
                "SUM(item.quantidade), " +
                "MAX(pedido.data)) " +
                "FROM Pedido pedido " +
                "JOIN pedido.itens item " +
                "JOIN item.produto produto " +
                "GROUP BY produto.nome " +
                "ORDER BY SUM(item.quantidade) DESC";
        return entityManager.createQuery(jpql, RelatorioDeVendasVo.class).getResultList();
    }
    


    //como eu faco para conseguir o pedido de maior valor?
        //esse metodo da errado pois devolve um BigDecimal (o maior valor) porem eu queria receber um pedido buscando pelo maior valor
    public Pedido pedidoDeMaiorValor(){
        String jpql = "SELECT MAX(p.valorTotal) FROM Pedido p";
        return entityManager.createQuery(jpql, Pedido.class).getSingleResult();
    }

//
//    public Pedido buscaPedidoMaisCaro(){
//        String jpql = "SELECT p from Pedido p WHERE "
//    }



}
