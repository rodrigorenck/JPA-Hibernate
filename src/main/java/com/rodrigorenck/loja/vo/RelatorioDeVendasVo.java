package com.rodrigorenck.loja.vo;

import java.time.LocalDate;

public class RelatorioDeVendasVo {
   private String nomeProduto;
   //esse valor precisa ser Long e nao int pois dependendo da quantidade de valores no banco pode estourar o limite do int
    //por isso a JPA obriga a ser Long quando eu faco uma SUM
   private Long quantidadeVendida;
   private LocalDate dataDaUltimaVenda;

    public RelatorioDeVendasVo(String nomeProduto, Long quantidadeVendida, LocalDate dataDaUltimaVenda) {
        this.nomeProduto = nomeProduto;
        this.quantidadeVendida = quantidadeVendida;
        this.dataDaUltimaVenda = dataDaUltimaVenda;
    }

    @Override
    public String toString() {
        return "RelatorioDeVendasVo{" +
                "nomeProduto='" + nomeProduto + '\'' +
                ", quantidadeVendida=" + quantidadeVendida +
                ", dataDaUltimaVenda=" + dataDaUltimaVenda +
                '}';
    }

    public String getNomeProduto() {
        return nomeProduto;
    }

    public void setNomeProduto(String nomeProduto) {
        this.nomeProduto = nomeProduto;
    }

    public Long getQuantidadeVendida() {
        return quantidadeVendida;
    }

    public void setQuantidadeVendida(Long quantidadeVendida) {
        this.quantidadeVendida = quantidadeVendida;
    }

    public LocalDate getDataDaUltimaVenda() {
        return dataDaUltimaVenda;
    }

    public void setDataDaUltimaVenda(LocalDate dataDaUltimaVenda) {
        this.dataDaUltimaVenda = dataDaUltimaVenda;
    }
}
