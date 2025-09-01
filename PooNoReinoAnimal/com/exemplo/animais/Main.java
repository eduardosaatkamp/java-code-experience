package com.exemplo.animais;

public class Main {
    public static void main(String[] args) {

        Canino cachorro = new Cachorro("Kripto");
        Canino fantasma = new Lobo("Fantasma");
        Canino ventoCinzento = new Lobo("Vento Cinzento");
        Canino nymeria = new Lobo("Nymeria");
        Felino gato = new Gato("Garfield");
        Felino leao = new Leao("Simba");

        cachorro.emitirSom();
        gato.emitirSom();
        leao.emitirSom();
        fantasma.emitirSom();
        ventoCinzento.emitirSom();
        nymeria.emitirSom();

        cachorro.executarAcao();
        gato.executarAcao();
        leao.executarAcao();
        fantasma.executarAcao();
        ventoCinzento.executarAcao();
        nymeria.executarAcao();
    }
}