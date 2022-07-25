package com.rodrigorenck.loja.testes.curso1;

import com.rodrigorenck.loja.dao.CategoriaDao;
import com.rodrigorenck.loja.dao.ProdutoDao;
import com.rodrigorenck.loja.model.Categoria;
import com.rodrigorenck.loja.model.Produto;
import com.rodrigorenck.loja.util.JPAUtil;

import javax.persistence.EntityManager;
import java.math.BigDecimal;
import java.util.List;

public class ConsultandoBanco {

    public static void main(String[] args) {

        EntityManager entityManager = JPAUtil.createEntityManager();

        CategoriaDao categoriaDao = new CategoriaDao(entityManager);
        ProdutoDao produtoDao = new ProdutoDao(entityManager);

        entityManager.getTransaction().begin();
        //buscar por id
        Categoria categoria = categoriaDao.buscarPorId(1L);
        System.out.println(categoria.getNome());

        //buscar todas
        List<Categoria> todasCategorias = categoriaDao.buscarTodos();
        todasCategorias.forEach(c -> System.out.println(c.getNome()));

        //buscar todas com um id maior que x
        List<Categoria> categoriasIdMaior10 = categoriaDao.buscaIdMaiorQue(0L);
        System.out.println(categoriasIdMaior10.size());

        //buscar por nome
        Categoria celulares = categoriaDao.buscarPorNome("CELULARES");
        System.out.println(celulares.getId());

        //buscar uma lista de produtos de mesmo nome categoria
        List<Produto> livros = produtoDao.buscaProdutosPorNomeCategoria("LIVROS");
        livros.forEach(l -> System.out.println(l.getNome()));

        //buscar uma lista de precos de produtos de mesma categoria
        Categoria categoria1 = categoriaDao.buscarPorNome("LIVROS");
        List<BigDecimal> precosLivros = produtoDao.buscaPrecosPorCategoria(categoria1);
        precosLivros.forEach(preco -> System.out.println(preco));

        //busca preco por nome do produto
        BigDecimal precoIphoneX = produtoDao.buscaPrecoPorNomeProduto("Iphone X");
        System.out.println(precoIphoneX);

        entityManager.close();


    }
}
