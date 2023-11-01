package com.usjt.a3.unogame.dto;

import java.util.List;
import java.util.stream.Collectors;

import com.usjt.a3.unogame.estrutura.ListaEncadeada;
import com.usjt.a3.unogame.estrutura.Pilha;
import com.usjt.a3.unogame.modelo.Carta;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CartaDTO {
    private String id;
    private String cor;
    private String valor;
    private String imagem;

    public static CartaDTO fromCarta(Carta carta) {
        CartaDTO dto = new CartaDTO();
        dto.id = carta.getId();
        dto.cor = carta.getCor();
        dto.valor = carta.getValor();
        dto.imagem = carta.getImagem();

        return dto;
    }

    public static List<CartaDTO> fromCarta(Pilha<Carta> cartas) {
        List<Carta> crt = cartas.toList();
        return crt.stream().map(CartaDTO::fromCarta).collect(Collectors.toList());
    }

    public static List<CartaDTO> fromCarta(ListaEncadeada<Carta> cartas) {
        List<Carta> crt = cartas.toList();
        return crt.stream().map(CartaDTO::fromCarta).collect(Collectors.toList());
    }
}
