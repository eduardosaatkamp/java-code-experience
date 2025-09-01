package com.exemplo.animais;

import org.junit.Assert;
import org.junit.Test;

public class LoboTest {

    @Test
    public void testEmitirSomLupus() {
        Canino lobo = new Lobo("Fantasma");
        lobo.emitirSom();

        Assert.assertEquals("Fantasma está rosnando!",
                lobo.getNome() + " está rosnando!");
    }

    @Test
    public void testEmitirSomVentoCinzento() {
        Canino lobo = new Lobo("Vento Cinzento");
        lobo.emitirSom();

        Assert.assertEquals("Vento Cinzento está rosnando!",
                lobo.getNome() + " está rosnando!");
    }

    @Test
    public void testEmitirSomNymeria() {
        Canino lobo = new Lobo("Nymeria");
        lobo.emitirSom();

        Assert.assertEquals("Nymeria está rosnando!",
                lobo.getNome() + " está rosnando!");
    }
}
