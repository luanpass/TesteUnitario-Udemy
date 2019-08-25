package br.ce.wcaquino.servicos;

import br.ce.wcaquino.entidades.Calculadora;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class CalculadoraTest {

    private Integer init;
    private Integer aux;
    private Integer result;
    private Calculadora calculadora;

    @Before
    public void setUp(){
        init = new Integer(0);
        aux = new Integer(0);
        result = new Integer(0);
        calculadora = new Calculadora();
    }

    @Test
    public void somaTest(){
        init = 5;
        aux = 3;

        result = calculadora.soma(init,aux);
        assertThat(result.intValue(), is(8));
    }

    @Test
    public void subtracaoTest(){

        init = 5;
        aux = 3;

        result = calculadora.subtracao(init,aux);
        assertThat(result.intValue(),is(2));
    }

    @Test
    public void divisaoTest(){

        init = 6;
        aux = 2;

        result = calculadora.divisao(init,aux);
        assertThat(result.intValue(),is(3));
    }

    @Test
    public void multiplicacaoTest(){

        init= 5 ;
        aux = 5;
        result = calculadora.multiplicacao(init,aux);
        assertThat(result.intValue(),is(25));
    }
}
