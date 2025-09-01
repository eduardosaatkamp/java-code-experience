package com.exemplo.animais;

import org.junit.Assert;
import org.junit.Test;

public class LeaoTest {

    @Test
    public void testEmitirSom() {
        Felino leao = new Leao("Simba");
        leao.emitirSom();

        Assert.assertEquals("Simba está rugindo!",
                leao.getNome() + " está rugindo!");
    }
}
