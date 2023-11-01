package com.usjt.a3.unogame;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class UnoGameApplication {

	public static void main(String[] args) {
		SpringApplication.run(UnoGameApplication.class, args);
	}

	// @Bean(name = "teste")
	// @Scope("singleton")
	// public Teste createGame() {
	// return new Teste("A");
	// }
}
