package com.exemplo.junit;

import org.junit.Test;

import java.time.LocalDate;

public class AnimeTest {
    @Test(expected = IllegalArgumentException.class)
    public void testExcecaoAoCriarAnimeComAnoDeLancamentoInvalido() {
        new Anime("Naruto", "Masashi Kishimoto", 2022, LocalDate.of(2002, 10, 3));
    }
}

