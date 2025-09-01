package com.exemplo.animais;

public class Leao extends Felino {
    public Leao(String nome) {
        super(nome);
    }

    @Override
    public void emitirSom() {
        System.out.println(getNome() + " está rugindo!");
    }

    @Override
    public void executarAcao() {
        System.out.println(getNome() + " está caçando!");
    }
}