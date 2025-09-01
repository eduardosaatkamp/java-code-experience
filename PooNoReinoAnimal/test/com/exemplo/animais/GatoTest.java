package com.exemplo.animais;

import org.junit.Assert;
import org.junit.Test;

public class GatoTest {

    @Test
    public void testEmitirSom() {
        Felino gato = new Gato("Garfield");
        gato.emitirSom();

        Assert.assertEquals("Garfield está miando!",
                gato.getNome() + " está miando!");
    }
}
