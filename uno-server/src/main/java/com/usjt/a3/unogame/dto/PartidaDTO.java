package com.usjt.a3.unogame.dto;

import java.util.ArrayList;
import java.util.List;

import com.usjt.a3.unogame.modelo.Carta;
import com.usjt.a3.unogame.modelo.Jogador;
import com.usjt.a3.unogame.modelo.Partida;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PartidaDTO {
    private List<Jogador> jogadores = new ArrayList<Jogador>();
    private List<Carta> monte = new ArrayList<Carta>();
    private List<Carta> descarte = new ArrayList<Carta>();
    private String direcao;
    private Boolean bloqueado;
    private Integer comprasObrigatorias;
    private Boolean escolheCor;

    public static PartidaDTO fromPartida(Partida partida) {
        PartidaDTO dto = new PartidaDTO();
        dto.setJogadores(partida.getJogadores().toList());
        dto.setMonte(partida.getMonte().toList());
        dto.setDescarte(partida.getDescarte().toList());
        dto.setDirecao(partida.getDirecao().name());
        dto.setBloqueado(partida.getBloqueado());
        dto.setComprasObrigatorias(partida.getComprasObrigatorias());
        dto.setEscolheCor(partida.getEscolheCor());

        return dto;
    }
}
