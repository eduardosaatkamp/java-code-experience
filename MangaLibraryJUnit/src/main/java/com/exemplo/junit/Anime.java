package com.exemplo.junit;

import java.time.LocalDate;

public class Anime {

    private String titulo;

    private String autor;

    private int anoLancamentoManga;

    private LocalDate dataLancamentoAnime;

    public Anime(String titulo, String autor, int anoLancamentoManga, LocalDate dataLancamentoAnime){
        super();

        if(anoLancamentoManga > dataLancamentoAnime.getYear()){
            throw new IllegalArgumentException("Ano de lançamento do mangá não pode ser maior que o ano de lançamento do anime.");
        }
        this.titulo = titulo;
        this.autor = autor;
        this.anoLancamentoManga = anoLancamentoManga;
        this.dataLancamentoAnime = dataLancamentoAnime;
    }

    public String getTitulo() {
        return titulo;
    }

    public String getAutor() {
        return autor;
    }

    public int getAnoLancamentoManga() {
        return anoLancamentoManga;
    }

    public LocalDate getDataLancamentoAnime() {
        return dataLancamentoAnime;
    }
}

