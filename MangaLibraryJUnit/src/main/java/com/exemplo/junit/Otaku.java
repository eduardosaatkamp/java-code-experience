package com.exemplo.junit;

import java.util.ArrayList;
import java.util.List;

public class Otaku {
    private String nomeDoOtaku;
    private List<Anime> coletaneaDoOtaku;

    public Otaku (String nomeDoOtaku){
        this.nomeDoOtaku = nomeDoOtaku;
        coletaneaDoOtaku = new ArrayList<Anime>();
    }

    public String getnomeDoOtaku() {
        return nomeDoOtaku;
    }

    public List<Anime> getcoletaneaDoOtaku() {
        return coletaneaDoOtaku;
    }

    public void adicionarAnime(Anime anime){
        coletaneaDoOtaku.add(anime);
    }

    public int obterAnoDoAnimeMaisAntigo(){
        int maisAntigo = Integer.MAX_VALUE;

        for(Anime anime: coletaneaDoOtaku){
            if(anime.getAnoLancamentoManga() < maisAntigo){
                maisAntigo = anime.getAnoLancamentoManga();
            }
        }

        return maisAntigo;
    }
}
