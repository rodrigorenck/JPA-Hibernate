package com.rodrigorenck.loja.model;

import com.rodrigorenck.loja.model.embedded.CategoriaId;

import javax.persistence.*;

@Entity
@Table(name = "categorias")
public class Categoria {

    @EmbeddedId //para chaves compostas
    private CategoriaId id;

    public Categoria(String nome){
        this.id = new CategoriaId(nome, "teste");
    }

    public Categoria(){}

    public String getNome(){
        return id.getNome();
    }
    public void setNome(String nome){
        id.setNome(nome);
    }

    public CategoriaId getId() {
        return id;
    }
}
