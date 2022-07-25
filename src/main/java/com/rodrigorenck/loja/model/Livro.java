package com.rodrigorenck.loja.model;

import javax.persistence.Entity;
import java.math.BigDecimal;

@Entity
public class Livro extends Produto {

    private String nomeAutor;
    private Integer numeroPaginas;


    public Livro(String nome, String descricao, BigDecimal preco, Categoria categoria, String nomeAutor, Integer numeroPaginas) {
        super(nome, descricao, preco, categoria);
        this.nomeAutor = nomeAutor;
        this.numeroPaginas = numeroPaginas;
    }

    public Livro(String nomeAutor, Integer numeroPaginas) {
        this.nomeAutor = nomeAutor;
        this.numeroPaginas = numeroPaginas;
    }

    public Livro(){}

    public String getNomeAutor() {
        return nomeAutor;
    }

    public void setNomeAutor(String nomeAutor) {
        this.nomeAutor = nomeAutor;
    }

    public Integer getNumeroPaginas() {
        return numeroPaginas;
    }

    public void setNumeroPaginas(Integer numeroPaginas) {
        this.numeroPaginas = numeroPaginas;
    }
}
