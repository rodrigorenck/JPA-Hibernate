package com.rodrigorenck.loja.model.embedded;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

@Embeddable //Classes Embeddable devem implementar Serializable
public class DadosPessoais implements Serializable {

    @Column(length = 70)
    private String nome;
    private String cpf;

    public DadosPessoais(){}

    public DadosPessoais(String nome, String cpf) {
        this.nome = nome;
        this.cpf = cpf;
    }

    public String getNome() {
        return nome;
    }

    public String getCpf() {
        return cpf;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }
}
