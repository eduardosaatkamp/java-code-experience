package com.exemplo.animais;

public abstract class Canino implements Animal {
    protected String nome;

    public Canino(String nome) {
        this.nome = nome;
    }

    public String getNome() {
        return nome;
    }

    // Método comum a todos os caninos
    public void uivar() {
        System.out.println(getNome() + " está uivando!");
    }
}