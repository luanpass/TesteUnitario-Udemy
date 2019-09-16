package br.ce.wcaquino.main;

import br.ce.wcaquino.entidades.Filme;
import br.ce.wcaquino.entidades.Locacao;
import br.ce.wcaquino.entidades.Usuario;
import br.ce.wcaquino.exceptions.FilmeSemEstoqueException;
import br.ce.wcaquino.exceptions.LocacaoException;
import br.ce.wcaquino.servicos.LocacaoService;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main (String args[]) throws FilmeSemEstoqueException, LocacaoException {
        LocacaoService service = new LocacaoService();
        List<Filme> filmes = new ArrayList<>();
        Locacao locacao = new Locacao();
        Usuario usuario = new Usuario();
        usuario.setNome("Luan");

        Filme logan = new Filme("Logan",new Integer(10),25.0);
        filmes.add(logan);
        locacao = service.alugarFilme(usuario,filmes);
        System.out.println(locacao.getValor());

        Filme starWars7 = new Filme("Star Wars 7", new Integer(5),25.0);
        filmes.add(starWars7);
        locacao = service.alugarFilme(usuario,filmes);
        System.out.println(locacao.getValor());

        Filme deadPool = new Filme("Deadpool",new Integer(7),25.0);
        filmes.add(deadPool);
        locacao = service.alugarFilme(usuario,filmes);
        System.out.println(locacao.getValor());

        Filme vingadores = new Filme("Vingadores",new Integer(7),25.0);
        filmes.add(vingadores);
        locacao = service.alugarFilme(usuario,filmes);
        System.out.println(locacao.getValor());

        Filme curtindoAVida = new Filme("Curtindo a Vida Adoidado",new Integer(7),25.0);
        filmes.add(curtindoAVida);
        locacao = service.alugarFilme(usuario,filmes);
        System.out.println(locacao.getValor());

        Filme joker = new Filme("Joker",new Integer(7),25.0);
        filmes.add(joker);
        locacao = service.alugarFilme(usuario,filmes);
        System.out.println(locacao.getValor());

    }
}
