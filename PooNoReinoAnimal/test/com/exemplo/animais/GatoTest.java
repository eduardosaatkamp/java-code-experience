package test.com.exemplo.animais;

import com.exemplo.animais.Felino;
import com.exemplo.animais.Gato;
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