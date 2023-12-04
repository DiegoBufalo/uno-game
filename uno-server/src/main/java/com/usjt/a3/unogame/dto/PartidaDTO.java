package com.usjt.a3.unogame.dto;

import java.util.ArrayList;
import java.util.List;

import com.usjt.a3.unogame.modelo.Partida;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PartidaDTO {
    private List<JogadorDTO> jogadores = new ArrayList<>();
    private List<CartaDTO> monte = new ArrayList<>();
    private List<CartaDTO> descarte = new ArrayList<>();
    private String direcao;
    private Long idJogadorAtual;
    private Boolean jogadorAtualIsBot;
    private String corAtual;
    private Boolean jogoFinalizado;
    private String jogadorVencedor;

    public static PartidaDTO fromPartida(Partida partida) {
        PartidaDTO dto = new PartidaDTO();
        dto.setJogadores(JogadorDTO.fromJogador(partida.getJogadores()));
        dto.setMonte(CartaDTO.fromCarta(partida.getMonte()));
        dto.setDescarte(CartaDTO.fromCarta(partida.getDescarte()));
        dto.setDirecao(partida.getDirecao().name());
        dto.setIdJogadorAtual(partida.getJogadores().getPosicaoAtual().getId());
        dto.setJogadorAtualIsBot(partida.getJogadores().getPosicaoAtual().isBot());
        dto.setCorAtual(partida.getCorAtual());
        dto.setJogadorVencedor(partida.getJogadorVencedor());
        dto.setJogoFinalizado(partida.getJogoFinalizado());

        return dto;
    }
}
