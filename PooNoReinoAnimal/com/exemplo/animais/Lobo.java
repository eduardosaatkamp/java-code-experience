package com.exemplo.animais;

public class Lobo extends Canino {
    public Lobo(String nome) {
        super(nome);
    }

    @Override
    public void emitirSom() {
        System.out.println(getNome() + " está rosnando!");
    }

    @Override
    public void executarAcao() {
        System.out.println(getNome() + " está caçando!");
    }
}