package br.ce.wcaquino.servicos;

import br.ce.wcaquino.entidades.Filme;
import br.ce.wcaquino.entidades.Usuario;
import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.*;
import static org.junit.Assert.assertSame;

public class Asserts {
    @Test
    public void Tests(){

        assertNotNull("",new Object());
        assertEquals(5.0,5.01,0.1);

        Usuario u1= new Usuario("Luan");
        Usuario u2 = new Usuario("Luan");
        Filme f1 = new Filme();
        assertEquals(u1,u2);
        assertSame(u1,u1);
        assertThat();
    }
}
