package com.usjt.a3.unogame.modelo;

import java.util.Random;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Carta {
    private String id;
    private String cor;
    private String valor;
    private String imagem;

    @JsonIgnore
    private Runnable runnable;

    public Carta(String cor, String valor) {
        Random random = new Random();

        this.id = String.format("%s-%s-%d", cor, valor, random.nextInt());
        this.cor = cor;
        this.valor = valor;
        this.imagem = String.format("src/assets/%s/%s.png", cor, valor);
    }

    public Carta(String cor, String valor, Runnable runnable) {
        super();
        this.runnable = runnable;
    }
}