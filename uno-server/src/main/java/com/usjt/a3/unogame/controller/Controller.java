package com.usjt.a3.unogame.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.web.bind.annotation.*;

import com.usjt.a3.unogame.dto.PartidaDTO;
import com.usjt.a3.unogame.modelo.Partida;

@RestController
@RequestMapping("api")
public class Controller {

    private final ApplicationContext applicationContext;

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
        partida.setCorAtual(partidaConfigurada.getCorAtual());
        partida.setJogadorVencedor("");
        partida.setJogoFinalizado(false);

        return PartidaDTO.fromPartida(partida);
    }

    @PutMapping("/compra-carta")
    public PartidaDTO compraCarta() {
        Partida partida = applicationContext.getBean(Partida.class);
        if (!partida.getJogadores().getPosicaoAtual().isBot()) {
            partida.compraCarta();
        }
        return PartidaDTO.fromPartida(partida);
    }

    @PutMapping("/descarta-carta/{cartaId}")
    public PartidaDTO descartaCarta(@PathVariable String cartaId) {
        Partida partida = applicationContext.getBean(Partida.class);
        if (!partida.getJogadores().getPosicaoAtual().isBot()) {
            partida.descartaCarta(cartaId);
        }
        return PartidaDTO.fromPartida(partida);
    }

    @PutMapping("/definir-cor/{cor}")
    public PartidaDTO definirCorWild(@PathVariable String cor) {
        Partida partida = applicationContext.getBean(Partida.class);
        if (!partida.getJogadores().getPosicaoAtual().isBot()) {
            partida.defineCor(cor);
        }
        return PartidaDTO.fromPartida(partida);
    }

    @PutMapping("/jogada-bot")
    public PartidaDTO jogadaBot() {
        Partida partida = applicationContext.getBean(Partida.class);
        if (partida.getJogadores().getPosicaoAtual().isBot()) {
            partida.jogadaBot();
        }
        return PartidaDTO.fromPartida(partida);
    }

    @DeleteMapping("/reinicia-partida")
    public void reiniciaPartida() {
        this.init("Jogador");
    }
}
