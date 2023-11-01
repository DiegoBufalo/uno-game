package com.usjt.a3.unogame;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Scope;

import com.usjt.a3.unogame.modelo.Partida;
import com.usjt.a3.unogame.utils.ConfiguraPartida;

@SpringBootApplication
public class UnoGameApplication {

	public static void main(String[] args) {
		SpringApplication.run(UnoGameApplication.class, args);
	}

	@Bean(name = "partida")
	@Scope("singleton")
	public Partida iniciaPartida() {
		return ConfiguraPartida.criaPartida();
	}
}
