package com.rodrigorenck.loja.model;

import com.rodrigorenck.loja.model.embedded.DadosPessoais;

import javax.persistence.*;

@Entity
@Table(name = "clientes")
public class Cliente {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Embedded
    private DadosPessoais dadosPessoais;
    public Cliente(String nome, String cpf){
        this.dadosPessoais = new DadosPessoais(nome, cpf);
    }
    public Cliente(){}


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    //metodos que delegam uma funcao para outra classe!
    public String getNome() {
        return dadosPessoais.getNome();
    }
    public String getCpf() {
        return dadosPessoais.getCpf();
    }

}
