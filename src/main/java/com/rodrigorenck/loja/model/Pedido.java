package com.rodrigorenck.loja.model;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Entity
@Table(name = "pedidos")
public class Pedido {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private LocalDate data = LocalDate.now();
    @ManyToOne(fetch = FetchType.LAZY) //so vou carregar a tabela do cliente se eu fizer o acesso no cliente - boa pratica
    private Cliente cliente;
    private BigDecimal valorTotal = BigDecimal.ZERO;
    @OneToMany(mappedBy = "pedido", cascade = CascadeType.ALL) //relacionamento bidirecional, cascade - tudo o que eu fizer com Pedido faça com ItemPedido
    private List<ItemPedido> itens = new ArrayList<>(); //sempre que for usar lista eh bom ja instancia-la

    public Pedido(Cliente cliente) {
        this.cliente = cliente;
        for (ItemPedido item :
                itens) {
            valorTotal = valorTotal.add(item.getPrecoUnitario());
        }
    }

    public Pedido(){}

    //relacionamento bidirecional -> por meio desse metodo garantimos que ItemPedido conhece Pedido e Pedido conhece ItemPedido
    //setar os dois lados do relacionamento
    public void adicionarItem(ItemPedido item){
        item.setPedido(this);
        this.itens.add(item);
        valorTotal = valorTotal.add(item.getValor());
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDate getData() {
        return data;
    }

    public void setData(LocalDate data) {
        this.data = data;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public BigDecimal getValorTotal() {
        return valorTotal;
    }

    public void setValorTotal(BigDecimal valorTotal) {
        this.valorTotal = valorTotal;
    }

    public List<ItemPedido> getItens() {
        return Collections.unmodifiableList(itens);
    }
}
