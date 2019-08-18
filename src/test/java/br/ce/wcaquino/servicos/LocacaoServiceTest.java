package br.ce.wcaquino.servicos;

import br.ce.wcaquino.entidades.Filme;
import br.ce.wcaquino.entidades.Locacao;
import br.ce.wcaquino.entidades.Usuario;
import org.junit.*;
import org.junit.rules.ExpectedException;

import static br.ce.wcaquino.utils.DataUtils.adicionarDias;
import static br.ce.wcaquino.utils.DataUtils.isMesmaData;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

public class LocacaoServiceTest {

    @Rule
    public ExpectedException expectedException = ExpectedException.none();

    private Usuario usuario;
    private Filme filme;
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
        usuario = new Usuario("Luan");
        filme = new Filme("Star Wars 4",new Integer(1),new Double(25.0));
        service = new LocacaoService();
        locacao = new Locacao();
        integer ++;
        System.out.println(integer);
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
            Assert.fail("Não deveria funcionar");
        } catch (Exception e) {
            Assert.assertThat(e.getMessage(),is("Filme sem estoque :c"));
        }
    }

    @Test
    public void testNovo() throws Exception{
        //ação
        filme.setEstoque(0);

        expectedException.expect(Exception.class);
        expectedException.expectMessage("Filme sem estoque :c");

        locacao = service.alugarFilme(usuario,filme);
    }
}
