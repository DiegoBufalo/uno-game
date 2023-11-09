package com.usjt.a3.unogame.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.usjt.a3.unogame.dto.PartidaDTO;
import com.usjt.a3.unogame.modelo.Partida;

@RestController
@RequestMapping("api")
public class Controller {

    private ApplicationContext applicationContext;

    @Autowired
    public Controller(ApplicationContext applicationContext) {
        this.applicationContext = applicationContext;
    }

    @PostMapping("/init")
    public PartidaDTO init(@RequestParam(defaultValue = "Jogador", required = false) String nomeJogador) {
        Partida partida = applicationContext.getBean(Partida.class);
        Partida partidaConfigurada = new Partida(nomeJogador);
        partida.setJogadores(partidaConfigurada.getJogadores());
        partida.setMonte(partidaConfigurada.getMonte());
        partida.setDescarte(partidaConfigurada.getDescarte());
        partida.setBloqueado(partidaConfigurada.getBloqueado());
        partida.setComprasObrigatorias(partidaConfigurada.getComprasObrigatorias());
        partida.setDirecao(partidaConfigurada.getDirecao());
        partida.setEscolheCor(partidaConfigurada.getEscolheCor());
        return PartidaDTO.fromPartida(partida);
    }

    @PutMapping("/compra-carta")
    public PartidaDTO compraCarta() {
        Partida partida = applicationContext.getBean(Partida.class);
        partida.compraCarta();
        return PartidaDTO.fromPartida(partida);
    }

    @PutMapping("/descarta-carta/{cartaId}")
    public PartidaDTO descartaCarta(@PathVariable String cartaId) {
        Partida partida = applicationContext.getBean(Partida.class);
        partida.descartaCarta(cartaId);
        return PartidaDTO.fromPartida(partida);
    }
}
