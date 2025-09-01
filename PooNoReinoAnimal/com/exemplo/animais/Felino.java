package com.exemplo.animais;

public abstract class Felino implements Animal {
    protected String nome;

    public Felino(String nome) {
        this.nome = nome;
    }

    public String getNome() {
        return nome;
    }

    // Método comum a todos os felinos
    public void arranhar() {
        System.out.println(getNome() + " está arranhando!");
    }
}