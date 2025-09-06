package com.exemplo.junit;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

public class AnimeTest {
    @Test
    void testExcecaoAoCriarAnimeComAnoDeLancamentoInvalido() {
        Assertions.assertThrows(IllegalArgumentException.class, () ->
                new Anime("Naruto", "Masashi Kishimoto", 2022, LocalDate.of(2002, 10, 3))
        );
    }
}

