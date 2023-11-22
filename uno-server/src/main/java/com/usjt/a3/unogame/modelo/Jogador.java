package com.usjt.a3.unogame.modelo;

import com.usjt.a3.unogame.estrutura.ListaEncadeada;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Jogador {
    private Long id;
    private String nome;
    private boolean isBot = false;
    private ListaEncadeada<Carta> mao = new ListaEncadeada<>();

    public Jogador(Long id, String nome, Boolean isBot) {
        this.id = id;
        this.nome = nome;
        this.isBot = isBot;
    }
}
