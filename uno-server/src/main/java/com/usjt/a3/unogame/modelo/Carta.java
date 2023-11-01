package com.usjt.a3.unogame.modelo;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Carta {
    private String id;
    private String cor;
    private String valor;
    private String imagem;
    private Runnable runnable;

    public Carta(String id, String cor, String valor) {
        this.id = id;
        this.cor = cor;
        this.valor = valor;
        this.imagem = String.format("src/assets/%s/%s.png", cor, valor);
    }
}