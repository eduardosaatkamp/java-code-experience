package com.exemplo.animais;

public class Gato extends Felino {
    public Gato(String nome) {
        super(nome);
    }

    @Override
    public void emitirSom() {
        System.out.println(getNome() + " está miando!");
    }

    @Override
    public void executarAcao() {
        System.out.println(getNome() + " está ronronando!");
    }
}