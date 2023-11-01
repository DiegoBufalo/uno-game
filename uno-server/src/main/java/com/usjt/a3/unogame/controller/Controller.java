package com.usjt.a3.unogame.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.usjt.a3.unogame.dto.PartidaDTO;
import com.usjt.a3.unogame.modelo.Partida;

@RestController
public class Controller {

    private ApplicationContext applicationContext;

    @Autowired
    public Controller(ApplicationContext applicationContext) {
        this.applicationContext = applicationContext;
    }

    @GetMapping("/controller/{a}")
    public PartidaDTO teste(@PathVariable String a) {
        Partida partida = (Partida) applicationContext.getBean("partida");
        partida.compraCarta();
        return PartidaDTO.fromPartida(partida);
    }
}
