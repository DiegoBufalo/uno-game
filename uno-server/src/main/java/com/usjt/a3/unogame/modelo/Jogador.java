package com.usjt.a3.unogame.modelo;

import com.usjt.a3.unogame.estrutura.ListaEncadeada;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Jogador {
    private Long id;
    private String nome;
    private Boolean isBot = false;
    private ListaEncadeada<Carta> mao = new ListaEncadeada<Carta>();

    public Jogador(Long id, String nome) {
        this.id = id;
        this.nome = nome;
    }
}
