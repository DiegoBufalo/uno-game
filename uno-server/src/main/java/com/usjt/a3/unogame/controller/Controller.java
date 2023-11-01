package com.usjt.a3.unogame.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.usjt.a3.unogame.dto.PartidaDTO;
import com.usjt.a3.unogame.modelo.Partida;
import com.usjt.a3.unogame.utils.ConfiguraPartida;

@RestController
@RequestMapping("api")
public class Controller {

    private ApplicationContext applicationContext;

    @Autowired
    public Controller(ApplicationContext applicationContext) {
        this.applicationContext = applicationContext;
    }

    @GetMapping("/init")
    public void init(@RequestParam(defaultValue = "Jogador", required = false) String nomeJogador) {
        Partida partida = applicationContext.getBean(Partida.class);
        Partida partidaConfigurada = ConfiguraPartida.criaPartida(nomeJogador);
        partida.setJogadores(partidaConfigurada.getJogadores());
        partida.setMonte(partidaConfigurada.getMonte());
        partida.setDescarte(partidaConfigurada.getDescarte());
        partida.setBloqueado(partidaConfigurada.getBloqueado());
        partida.setComprasObrigatorias(partidaConfigurada.getComprasObrigatorias());
        partida.setDirecao(partidaConfigurada.getDirecao());
        partida.setEscolheCor(partidaConfigurada.getEscolheCor());
    }

    @GetMapping("/load")
    public PartidaDTO load() {
        Partida partida = applicationContext.getBean(Partida.class);
        return PartidaDTO.fromPartida(partida);
    }
}
