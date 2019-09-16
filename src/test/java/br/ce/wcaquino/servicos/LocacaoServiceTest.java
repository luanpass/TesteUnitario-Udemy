package br.ce.wcaquino.servicos;

import br.ce.wcaquino.entidades.Filme;
import br.ce.wcaquino.entidades.Locacao;
import br.ce.wcaquino.entidades.Usuario;
import br.ce.wcaquino.exceptions.FilmeSemEstoqueException;
import br.ce.wcaquino.exceptions.LocacaoException;
import org.junit.*;
import org.junit.rules.ExpectedException;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static br.ce.wcaquino.utils.DataUtils.adicionarDias;
import static br.ce.wcaquino.utils.DataUtils.isMesmaData;
import static org.hamcrest.CoreMatchers.is;

public class LocacaoServiceTest {

    @Rule
    public ExpectedException expectedException = ExpectedException.none();

    private Usuario usuario;
    private List <Filme> filmes;
    private LocacaoService service;
    private Locacao locacao;

    private static Integer integer = new Integer(0);
    //cenario
    @BeforeClass
    public static void setUpClass(){

        System.out.println("Before Class");
    }
    @AfterClass
    public static void afterClass(){
        System.out.println("After Class");
    }

    @Before
    public void setUp() throws Exception {
        filmes = Arrays.asList(new Filme("Logan",new Integer(20),25.0));
        usuario = new Usuario("Luan");
        service = new LocacaoService();
        locacao = new Locacao();
        integer ++;
        System.out.println(integer);
    }

    @Test
    public void alugarFilmesTestNotNull() throws Exception {

        //ação
        locacao = service.alugarFilme(usuario, filmes);
        //verificacao
        Assert.assertTrue(locacao.getFilme().get(0).getNome() != null);
        Assert.assertTrue(isMesmaData(locacao.getDataRetorno(),adicionarDias(locacao.getDataLocacao(),1)));

    }


    @Test(expected = FilmeSemEstoqueException.class)
    public void testElegant() throws FilmeSemEstoqueException, LocacaoException{
        //ação
        filmes.get(0).setEstoque(0);
        locacao = service.alugarFilme(usuario, filmes);
        //verificacao
        Assert.assertTrue(locacao.getFilme().get(0).getNome() != null);
        Assert.assertTrue(isMesmaData(locacao.getDataRetorno(),adicionarDias(locacao.getDataLocacao(),1)));
    }

    @Test
    public void testRubusto() throws Exception{
        //ação
        filmes.get(0).setEstoque(0);

        try {
            locacao = service.alugarFilme(usuario, filmes);
            Assert.fail("Não deveria funcionar");
        } catch (Exception e) {
            Assert.assertThat(e.getMessage(),is("Estoque cannot be zero"));
        }
    }

    @Test
    public void testNovo() throws Exception{

        expectedException.expect(Exception.class);
        expectedException.expectMessage("Estoque cannot be zero");
        //ação
        filmes.get(0).setEstoque(0);


        locacao = service.alugarFilme(usuario, filmes);
    }

    @Test(expected = FilmeSemEstoqueException.class)
    public void filmeSemEstoqueExceptionTestElegant() throws FilmeSemEstoqueException, LocacaoException {

        //ação
        filmes.get(0).setEstoque(0);

        locacao = service.alugarFilme(usuario, filmes);
        //verificacao
        Assert.assertTrue(locacao.getFilme().get(0).getNome() != null);
        Assert.assertTrue(isMesmaData(locacao.getDataRetorno(),adicionarDias(locacao.getDataLocacao(),1)));
    }

    @Test
    public void filmePromocaoEscalonavel() throws FilmeSemEstoqueException, LocacaoException{

        for(int i=0; i<=6; i++){
            filmes = Arrays.asList(new Filme("Logan",new Integer(20),25.0));
            locacao = service.alugarFilme(usuario,filmes);
            Integer tamanho = filmes.size();

            switch (tamanho.intValue()){
                case 1:
                    Assert.assertEquals(25.0,locacao.getValor(),0.1);
                    break;
                case 2:
                    Assert.assertEquals(50.0,locacao.getValor(),0.1);
                    break;
                case 3:
                    Assert.assertEquals(68.75,locacao.getValor(),0.1);
                    break;
                case 4:
                    Assert.assertEquals(81.25,locacao.getValor(),0.1);
                    break;
                case 5:
                    Assert.assertEquals(87.5,locacao.getValor(),0.1);
                    break;
                case 6:
                    Assert.assertEquals(87.5,locacao.getValor(),0.1);
            }

        }


    }

    @Test
    public void naoAceitaFilmeNoDomingo(){

    }
}
