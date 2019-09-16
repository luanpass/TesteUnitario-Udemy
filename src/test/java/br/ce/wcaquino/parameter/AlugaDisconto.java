package br.ce.wcaquino.parameter;

import br.ce.wcaquino.entidades.Filme;
import br.ce.wcaquino.entidades.Locacao;
import br.ce.wcaquino.entidades.Usuario;
import br.ce.wcaquino.exceptions.FilmeSemEstoqueException;
import br.ce.wcaquino.exceptions.LocacaoException;
import br.ce.wcaquino.servicos.LocacaoService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameter;
import org.junit.runners.Parameterized.Parameters;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

//Classe Parametrizada.
//Os values nas annotations sao cada para do Arrays.asList.
// Esses values sao usados como variaveis.

@RunWith(Parameterized.class)
public class AlugaDisconto {

    private LocacaoService service;
    @Parameter
    public List<Filme> filmes;

    @Parameter(value = 1)
    public Double valorLocacao;

    @Parameter(value =2)
    public String nome;
    @Before
    public void setUp(){
        service = new LocacaoService();
    }

    private static Filme filme1 = new Filme ("Filme1",new Integer(5),25.0);
    private static Filme filme2 = new Filme ("Filme2",new Integer(5),25.0);
    private static Filme filme3 = new Filme ("Filme3",new Integer(5),25.0);
    private static Filme filme4 = new Filme ("Filme4",new Integer(5),25.0);
    private static Filme filme5 = new Filme ("Filme5",new Integer(5),25.0);
    private static Filme filme6 = new Filme ("Filme6",new Integer(5),25.0);
    private static Filme filme7 = new Filme ("Filme7",new Integer(5),25.0);


    @Parameters(name ="{2}, Valor = {1}")
    public static Collection<Object[]> getParametros(){
        return Arrays.asList(new Object[][]{
                {Arrays.asList(filme1,filme2),50.0,"2 Filmes, 0%"},
                {Arrays.asList(filme1,filme2,filme3),68.75,"3 Filmes, 25%"},
                {Arrays.asList(filme1,filme2,filme3,filme4),81.25, "4 Filmes, 50%"},
                {Arrays.asList(filme1,filme2,filme3,filme4,filme5),87.5, "5 Filmes, 75%"},
                {Arrays.asList(filme1,filme2,filme3,filme4,filme5,filme6),87.5, "6 Filmes, 100%"},
                {Arrays.asList(filme1,filme2,filme3,filme4,filme5,filme6,filme7),112.5, "7 Filmes, 0%"}
        });
    }
    @Test
    public void calculaDiscontoProgessivoComParamiter() throws FilmeSemEstoqueException, LocacaoException {
        //cenario
        Usuario usuario = new Usuario("Usario1");

        //acao
        Locacao resultado = service.alugarFilme(usuario,filmes);

        //verificacao
        assertThat(resultado.getValor(),is(valorLocacao));

    }


}
