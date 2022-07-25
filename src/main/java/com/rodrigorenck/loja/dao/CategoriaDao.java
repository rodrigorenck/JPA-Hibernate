package com.rodrigorenck.loja.dao;

import com.rodrigorenck.loja.model.Categoria;

import javax.persistence.EntityManager;
import java.util.List;

public class CategoriaDao {
    private EntityManager entityManager;

    public CategoriaDao(EntityManager entityManager){
        this.entityManager = entityManager;
    }

    public void cadastrar(Categoria categoria){
        entityManager.persist(categoria);
    }

    //forcamos a categoria a estar no estado MANAGED caso ela esteja no estado DETACHED
    public void atualizar(Categoria categoria){
        categoria = this.entityManager.merge(categoria);
    }

    public void remover(Categoria categoria){
        categoria = entityManager.merge(categoria);
        entityManager.remove(categoria);
    }

    public Categoria buscarPorId(Long id){
        return entityManager.find(Categoria.class, id);
    }

    public List<Categoria> buscarTodos(){
//        String jpql = "SELECT * FROM categorias"; -> caso fosse no SQL
        String jpql = "SELECT c FROM Categoria c";
        return entityManager.createQuery(jpql, Categoria.class).getResultList();
    }

    public List<Categoria> buscaIdMaiorQue(Long numero){
        String query = String.format("SELECT c FROM Categoria c WHERE c.id>%d", numero);
        return entityManager.createQuery(query, Categoria.class).getResultList();
    }

    public Categoria buscarPorNome(String nome){
        String jpql = "SELECT c FROM Categoria c WHERE c.nome = :nome";
        return entityManager.createQuery(jpql, Categoria.class)
                .setParameter("nome", nome).getSingleResult();
    }

}
