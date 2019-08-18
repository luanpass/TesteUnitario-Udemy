package br.ce.wcaquino.servicos;


import br.ce.wcaquino.entidades.Filme;
import br.ce.wcaquino.entidades.Locacao;
import br.ce.wcaquino.entidades.Usuario;
import br.ce.wcaquino.exceptions.FilmeSemEstoqueException;
import br.ce.wcaquino.exceptions.LocacaoException;
import org.junit.*;
import org.junit.rules.ExpectedException;

import static br.ce.wcaquino.utils.DataUtils.adicionarDias;
import static br.ce.wcaquino.utils.DataUtils.isMesmaData;
import static org.hamcrest.CoreMatchers.is;

public class TestsExceptions {
    @Rule
    public ExpectedException expectedException = ExpectedException.none();

    private Usuario usuario;
    private Filme filme;
    private LocacaoService service;
    private Locacao locacao;
    private Filme filmeNull;
    private Usuario usuarioNull;

    //Método roda antes da classe ser instaciada.
    //Método tem que ser static para o JUnit conseugir instaciar o método.
    @BeforeClass
    public static void setUpClass(){

        System.out.println("Before Class");
    }

    //Método roda depois da classe ser destruída.
    //Método tem que ser static para o JUnit conseugir instaciar o método.
    @AfterClass
    public static void tearDownClass(){
        System.out.println("After Class");
    }

    //Método roda antes de cada método Teste
    @Before
    public void setUp() throws Exception {
        System.out.println("Before Method");
        usuario = new Usuario("Luan");
        filme = new Filme("Star Wars 4",new Integer(1),new Double(25.0));
        service = new LocacaoService();
        locacao = new Locacao();
    }
    //Método roda depois de cada método Teste
    @After
    public void tearDown(){
        System.out.println("After Method");
    }

    @Test
    public void alugarFilmesTestNotNull() throws Exception {

        //ação
        locacao = service.alugarFilme(usuario,filme);
        //verificacao
        Assert.assertTrue(locacao.getFilme().getNome() != null);
        Assert.assertTrue(isMesmaData(locacao.getDataRetorno(),adicionarDias(locacao.getDataLocacao(),1)));

    }

    @Test(expected = Exception.class)
    public void testElegant() throws Exception{
        //ação
        filme.setEstoque(0);
        locacao = service.alugarFilme(usuario,filme);
        //verificacao
        Assert.assertTrue(locacao.getFilme().getNome() != null);
        Assert.assertTrue(isMesmaData(locacao.getDataRetorno(),adicionarDias(locacao.getDataLocacao(),1)));
    }

    @Test
    public void testRubusto() throws Exception{
        //ação
        filme.setEstoque(0);

        try {
            locacao = service.alugarFilme(usuario,filme);
            Assert.fail();
        } catch (Exception e) {
            Assert.assertThat(e.getMessage(),is("Estoque cannot be zero"));
        }
    }

    @Test
    public void testNovo() throws Exception{
        //ação
        filme.setEstoque(0);

        expectedException.expect(Exception.class);
        expectedException.expectMessage("Estoque cannot be zero");

        locacao = service.alugarFilme(usuario,filme);
    }

//    Filme com estoque zerado
//    Usuario Populado.
//    Estrutura usada para tratar a Exception: "TesteRobusto"
    @Test(expected = FilmeSemEstoqueException.class)
    public void filmeSemEstoqueExceptionTestElegant() throws FilmeSemEstoqueException, LocacaoException {

        //ação
        filme.setEstoque(0);
        locacao = service.alugarFilme(usuario,filme);
        //verificacao
        Assert.assertTrue(locacao.getFilme().getNome() != null);
        Assert.assertTrue(isMesmaData(locacao.getDataRetorno(),adicionarDias(locacao.getDataLocacao(),1)));
    }

//    Filme Null
//    Usuario Populado.
//    Estrutura usada para tratar a Exception: "TesteRobusto"
    @Test
    public void filmeNullTestRobusto() throws FilmeSemEstoqueException {
        //ação
        try {
            locacao = service.alugarFilme(usuario,filmeNull);
            Assert.fail();
        } catch (LocacaoException e) {
            Assert.assertThat(e.getMessage(),is("Filme cannot be null"));
        }
    }


//    Usuario Null
//    Filme Populado.
//    Estrutura usada para tratar a Exception: "TesteNova"
    @Test
    public void usuarioNullTestNovo() throws FilmeSemEstoqueException, LocacaoException {

        expectedException.expect(LocacaoException.class);
        expectedException.expectMessage("Usuario não pode ser vazio");

        locacao= service.alugarFilme(usuarioNull,filme);
    }
}

