package br.ce.wcaquino.servicos;

import br.ce.wcaquino.entidades.Filme;
import br.ce.wcaquino.entidades.Locacao;
import br.ce.wcaquino.entidades.Usuario;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import static br.ce.wcaquino.utils.DataUtils.adicionarDias;
import static br.ce.wcaquino.utils.DataUtils.isMesmaData;
import static org.junit.Assert.*;

public class LocacaoServiceTest {

    private Usuario usuario;
    private Filme filme;
    private LocacaoService service;
    private Locacao locacao;

    //cenario
    @Before
    public void setUp() throws Exception {
        usuario = new Usuario("Luan");
        filme = new Filme("Star Wars 4",new Integer(15),new Double(25.0));
        service = new LocacaoService();
        locacao = new Locacao();
    }

    @Test
    public void alugarFilmesTestNotNull() {
        //ação
        locacao = service.alugarFilme(usuario,filme);
        //verificacao
        Assert.assertTrue(locacao.getFilme().getNome() != null);
        Assert.assertTrue(isMesmaData(locacao.getDataRetorno(),adicionarDias(locacao.getDataLocacao(),1)));

    }
}