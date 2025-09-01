package test.com.exemplo.animais;

import com.exemplo.animais.Cachorro;
import com.exemplo.animais.Canino;
import org.junit.Assert;
import org.junit.Test;

public class CachorroTest {

    @Test
    public void testEmitirSom() {
        Canino cachorro = new Cachorro("Kripto");
        cachorro.emitirSom();

        Assert.assertEquals("Kripto está latindo!",
                cachorro.getNome() + " está latindo!");
    }
}