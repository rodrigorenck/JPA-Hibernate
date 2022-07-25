package com.rodrigorenck.loja.model;

import javax.persistence.Entity;
import java.math.BigDecimal;

@Entity
public class Informatica extends Produto {

    private String modelo;
    private String marca;

    public Informatica(String nome, String descricao, BigDecimal preco, Categoria categoria, String modelo, String marca) {
        super(nome, descricao, preco, categoria);
        this.modelo = modelo;
        this.marca = marca;
    }

    public Informatica(String modelo, String marca) {
        this.modelo = modelo;
        this.marca = marca;
    }

    public Informatica(){}

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }
}
