package com.usjt.a3.unogame.dto;

import java.util.ArrayList;
import java.util.List;

import com.usjt.a3.unogame.modelo.Partida;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PartidaDTO {
    private List<JogadorDTO> jogadores = new ArrayList<JogadorDTO>();
    private List<CartaDTO> monte = new ArrayList<CartaDTO>();
    private List<CartaDTO> descarte = new ArrayList<CartaDTO>();
    private String direcao;
    private Boolean bloqueado;
    private Integer comprasObrigatorias;
    private Boolean escolheCor;
    private Long idJogadorAtual;

    public static PartidaDTO fromPartida(Partida partida) {
        PartidaDTO dto = new PartidaDTO();
        dto.setJogadores(JogadorDTO.fromJogador(partida.getJogadores()));
        dto.setMonte(CartaDTO.fromCarta(partida.getMonte()));
        dto.setDescarte(CartaDTO.fromCarta(partida.getDescarte()));
        dto.setDirecao(partida.getDirecao().name());
        dto.setBloqueado(partida.getBloqueado());
        dto.setComprasObrigatorias(partida.getComprasObrigatorias());
        dto.setEscolheCor(partida.getEscolheCor());
        dto.setIdJogadorAtual(partida.getJogadores().getCurrent().getId());

        return dto;
    }
}
