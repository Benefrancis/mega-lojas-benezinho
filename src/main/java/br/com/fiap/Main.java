package br.com.fiap;

import br.com.fiap.funcionario.model.Funcionario;
import br.com.fiap.model.Categoria;
import br.com.fiap.model.Cliente;
import br.com.fiap.model.Pedido;
import br.com.fiap.model.Produto;
import br.com.fiap.unidade.model.Unidade;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

import java.time.LocalDateTime;
import java.util.Arrays;

public class Main {
    public static void main(String[] args) {

        EntityManagerFactory factory = Persistence.createEntityManagerFactory("maria-db");
        EntityManager manager = factory.createEntityManager();

        salvar(manager);

        Pedido pedido = manager.find(Pedido.class, 1L);

        System.out.println(pedido);

        Unidade matriz = new Unidade();
        matriz.setNome("Mega Loja Brasil");
        matriz.setMatriz(null);

        Unidade sp = new Unidade();
        sp.setNome("São Paulo");
        sp.setMatriz(matriz);

        Unidade vilaMariana = new Unidade();
        vilaMariana.setNome("Vila Mariana");
        vilaMariana.setMatriz(sp);

        Unidade imirim = new Unidade();
        imirim.setNome("Imirim");
        imirim.setMatriz(sp);

        Unidade rj = new Unidade();
        rj.setNome("Rio de Janeiro");
        rj.setMatriz(matriz);

        Unidade copa = new Unidade();
        copa.setNome("Copacabana");
        copa.setMatriz(rj);


        Funcionario prof = new Funcionario();
        prof.setMatricula("23117");
        prof.setEmail("benefrancis@gmail.com");
        prof.addUnidade(matriz);
        prof.addUnidade(sp);

        Funcionario c = new Funcionario();
        c.setMatricula("11111");
        c.setEmail("claudioty@gmail.com");
        c.addUnidade(matriz);
        c.addUnidade(copa);

        Funcionario claudio = new Funcionario();
        claudio.setMatricula("12345");
        claudio.setEmail("oliveirclau@hotmail.com");
        claudio.addUnidade(matriz);
        claudio.addUnidade(copa);

        Funcionario gustavo = new Funcionario();
        gustavo.setEmail("gmnp98@gmail.com");
        gustavo.setMatricula("097853");
        gustavo.addUnidade(sp);
        gustavo.addUnidade(vilaMariana);

        manager.getTransaction().begin();
        Arrays.asList(matriz, sp, rj, vilaMariana, imirim, copa).forEach(manager::persist);
        Arrays.asList(prof, c, claudio, gustavo).forEach(manager::persist);

        matriz.setChefe(prof);
        sp.setChefe(gustavo);
        rj.setChefe(claudio);
        copa.setChefe(c);
        imirim.setChefe(gustavo);
        vilaMariana.setChefe(claudio);
        manager.getTransaction().commit();

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