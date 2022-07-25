package com.rodrigorenck.loja.dao;

import com.rodrigorenck.loja.model.Categoria;
import com.rodrigorenck.loja.model.Produto;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

public class ProdutoDao {

    private EntityManager entityManager;

    public ProdutoDao(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    public void cadastrar(Produto produto) {
        entityManager.persist(produto);
    }

    public Produto buscaPorId(Long id) {
        return entityManager.find(Produto.class, id);
    }

    //utilizando Named Query
    public List<Produto> buscaProdutosPorNomeCategoria(String nomeCategoria) {
        return entityManager.createNamedQuery("Produto.buscaProdutosPorNomeCategoria", Produto.class)
                .setParameter("nomeCategoria", nomeCategoria).getResultList();
    }

    public List<BigDecimal> buscaPrecosPorCategoria(Categoria categoria) {
        String jpql = "SELECT p.preco FROM Produto p WHERE p.categoria = :categoria";
        return entityManager.createQuery(jpql, BigDecimal.class).setParameter("categoria", categoria).getResultList();
    }

    public BigDecimal buscaPrecoPorNomeProduto(String nome) {
        String jpql = "SELECT p.preco FROM Produto p WHERE p.nome = :nome";
        return entityManager.createQuery(jpql, BigDecimal.class).setParameter("nome", nome).getSingleResult();
    }

    //consultas dinamicas - parametros opcionais
    public List<Produto> buscaPorParametrosOpcionais(String nome, BigDecimal preco, LocalDate dataCadastro) {
        String jpql = "SELECT p FROM Produto p WHERE 1=1 "; //gambiarra para forcar o WHERE funcionar
        if (nome != null && !nome.isEmpty()) {
            jpql += " AND p.nome = :nome";
        }
        if (preco != null) {
            jpql += " AND p.preco = :preco";
        }
        if (dataCadastro != null) {
            jpql += " AND p.dataCadastro = :dataCadastro";
        }
        TypedQuery<Produto> query = entityManager.createQuery(jpql, Produto.class);
        if (nome != null && !nome.isEmpty()) {
            query.setParameter("nome", nome);
        }
        if (preco != null) {
            query.setParameter("preco", preco);
        }
        if (dataCadastro != null) {
            query.setParameter("dataCadastro", dataCadastro);
        }
        return query.getResultList();
    }

    //consultas dinamicas utilizando API Criteria
    public List<Produto> buscaPorParametrosOpcionaisComCriteria(String nome, BigDecimal preco, LocalDate dataCadastro) {
        CriteriaBuilder builder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Produto> query = builder.createQuery(Produto.class);
        Root<Produto> from = query.from(Produto.class);

        Predicate filtros = builder.and();
        if (nome != null && !nome.isEmpty()) {
            filtros = builder.and(filtros, builder.equal(from.get("nome"), nome));
        }
        if (preco != null) {
            filtros = builder.and(filtros, builder.equal(from.get("preco"), preco));
        }if(dataCadastro != null){
            filtros = builder.and(filtros, builder.equal(from.get("dataCadastro"), dataCadastro));
        }
        query.where(filtros);
        return entityManager.createQuery(query).getResultList();
    }
}
