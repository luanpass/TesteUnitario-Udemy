package br.ce.wcaquino.servicos;

import br.ce.wcaquino.entidades.Filme;
import br.ce.wcaquino.entidades.Usuario;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;
import static org.junit.Assert.assertSame;

public class Asserts {

    private Usuario u1;
    private Usuario u2;

    @Before
    public void setUp(){
        u1= new Usuario("Luan");
        u2 = new Usuario("Luan");
    }

    @Test
    public void assertNotNullTest(){
        assertNotNull("",new Object());

    }

    //delta == margem de erro
    @Test
    public void assertEqualsFloatTest(){
        assertEquals(5.0,5.01,0.1);
    }

    // Utiliza a função equals() do objeto
    @Test
    public void assertEqualsTest(){
        assertEquals(u1,u2);
    }
    // Verifica se é o mesmo objeto
    @Test
    public void assertSameTest(){
        assertSame(u1,u1);
    }
    //
    @Test
    public void assertThatTest(){
        assertThat(u1,is(u2));
    }
}
