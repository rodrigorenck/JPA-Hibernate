package com.rodrigorenck.loja.dao;

import com.rodrigorenck.loja.model.Cliente;

import javax.persistence.EntityManager;

public class ClienteDao {

    EntityManager entityManager;

    public ClienteDao(EntityManager entityManager){
        this.entityManager = entityManager;
    }

    public void cadastrar(Cliente cliente){
        this.entityManager.persist(cliente);
    }
}
