package br.ce.wcaquino.servicos;

import static br.ce.wcaquino.utils.DataUtils.adicionarDias;

import java.util.Date;
import java.util.List;

import br.ce.wcaquino.exceptions.FilmeSemEstoqueException;
import br.ce.wcaquino.entidades.Filme;
import br.ce.wcaquino.entidades.Locacao;
import br.ce.wcaquino.entidades.Usuario;
import br.ce.wcaquino.exceptions.LocacaoException;

public class LocacaoService {
	
	public Locacao alugarFilme(Usuario usuario, List<Filme> filmes) throws FilmeSemEstoqueException, LocacaoException {


		if(usuario == null){
			throw new LocacaoException("Usuario cannot be null");
		}

		if(filmes == null){
			throw new  LocacaoException("Filme cannot be null");
		}

        for (Filme filme : filmes) {
            if (filme.getEstoque() == 0) {
                throw new FilmeSemEstoqueException("Estoque cannot be zero");
            }
        }

        Locacao locacao = new Locacao();
		locacao.setFilme(filmes);
		locacao.setUsuario(usuario);
		locacao.setDataLocacao(new Date());

        filmes.forEach(filme -> {
            locacao.setValor(filme.getPrecoLocacao());
        });

		//Entrega no dia seguinte
		Date dataEntrega = new Date();
		dataEntrega = adicionarDias(dataEntrega, 1);
		locacao.setDataRetorno(dataEntrega);
		
		//Salvando a locacao...	
		//TODO adicionar método para salvar
		
		return locacao;
	}


}