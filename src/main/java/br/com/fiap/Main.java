package br.com.fiap;

import br.com.fiap.model.Categoria;
import br.com.fiap.model.Cliente;
import br.com.fiap.model.Pedido;
import br.com.fiap.model.Produto;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

import java.time.LocalDateTime;

public class Main {
    public static void main(String[] args) {

        EntityManagerFactory factory = Persistence.createEntityManagerFactory("maria-db");
        EntityManager manager = factory.createEntityManager();

        salvar(manager);

        Pedido pedido = manager.find(Pedido.class, 1L);

        System.out.println(pedido);

    }

    private static void salvar(EntityManager manager) {
        var mobile = new Categoria();
        mobile.setNome("Mobile");

        var tablet = new Categoria();
        tablet.setNome("Tablet");

        var ipad = new Produto();
        ipad.setNome("Ipade");
        ipad.addCategoria(mobile).addCategoria(tablet);

        var benezinho = new Cliente();
        benezinho.setNome("Benefrancis");
        benezinho.setEmail("benefrancis@gmail.com");


        var pedido = new Pedido();
        pedido.addProduto(ipad).setCliente(benezinho).setData(LocalDateTime.now());

        manager.getTransaction().begin();
        manager.persist(pedido);
        manager.getTransaction().commit();
    }
}