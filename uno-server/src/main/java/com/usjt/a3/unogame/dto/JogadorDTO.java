package com.usjt.a3.unogame.dto;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.usjt.a3.unogame.estrutura.ListaCircular;
import com.usjt.a3.unogame.modelo.Jogador;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class JogadorDTO {
    private Long id;
    private String nome;
    private Boolean isBot;
    private List<CartaDTO> mao = new ArrayList<>();

    public static JogadorDTO fromJogador(Jogador jogador) {
        JogadorDTO dto = new JogadorDTO();
        dto.id = jogador.getId();
        dto.nome = jogador.getNome();
        dto.isBot = jogador.isBot();
        dto.mao = CartaDTO.fromCarta(jogador.getMao());
        return dto;
    }

    public static List<JogadorDTO> fromJogador(ListaCircular<Jogador> jogadores) {
        List<Jogador> jgs = jogadores.toList();
        return jgs.stream().map(JogadorDTO::fromJogador).collect(Collectors.toList());
    }
}
