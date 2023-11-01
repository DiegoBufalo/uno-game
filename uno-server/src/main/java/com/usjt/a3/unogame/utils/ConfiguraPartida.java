package com.usjt.a3.unogame.utils;

import java.util.List;

import com.usjt.a3.unogame.modelo.Carta;
import com.usjt.a3.unogame.modelo.Direcao;
import com.usjt.a3.unogame.modelo.Jogador;
import com.usjt.a3.unogame.modelo.Partida;

public class ConfiguraPartida {

    public static Partida criaPartida(String nomeJogador) {
        Partida partida = new Partida();
        ConfiguraPartida.loadConfig(partida, nomeJogador);
        return partida;
    }

    private static void loadConfig(Partida partida, String nomeJogador) {
        String[] coresCarta = new String[] { "red", "green", "yellow", "blue" };
        String[] valoresCarta = new String[] { "0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "draw", "reverse",
                "skip" };

        // Coloca duas +4 e duas troca de cor
        partida.getMonte().push(new Carta("wild", "draw", () -> ConfiguraPartida.draw4Method(partida)));
        partida.getMonte().push(new Carta("wild", "draw", () -> ConfiguraPartida.draw4Method(partida)));
        partida.getMonte().push(new Carta("wild", "change", () -> ConfiguraPartida.changeColor(partida)));
        partida.getMonte().push(new Carta("wild", "change", () -> ConfiguraPartida.changeColor(partida)));

        for (int i = 0; i < coresCarta.length; i++) {
            for (int y = 0; y < valoresCarta.length; y++) {
                Carta carta = new Carta(coresCarta[i], valoresCarta[y]);

                switch (valoresCarta[y]) {
                    case "draw":
                        carta.setRunnable(() -> ConfiguraPartida.draw2Method(partida));
                        break;
                    case "reverse":
                        carta.setRunnable(() -> ConfiguraPartida.reverseMethod(partida));
                        break;
                    case "skip":
                        carta.setRunnable(() -> ConfiguraPartida.skipMethod(partida));
                        break;
                    default:
                        break;
                }

                partida.getMonte().push(carta);
            }
        }

        partida.getMonte().shuffle();

        NameGenerator nameGenerator = new NameGenerator();

        Jogador jogador1 = new Jogador(1L, nameGenerator.getNomeRandom(), true);
        List<Carta> cartas1 = partida.getMonte().popX(5);
        cartas1.forEach(c -> jogador1.getMao().add(c));

        Jogador jogador2 = new Jogador(2L, nomeJogador, false);
        List<Carta> cartas2 = partida.getMonte().popX(5);
        cartas2.forEach(c -> jogador2.getMao().add(c));

        Jogador jogador3 = new Jogador(3L, nameGenerator.getNomeRandom(), true);
        List<Carta> cartas3 = partida.getMonte().popX(5);
        cartas3.forEach(c -> jogador3.getMao().add(c));

        Jogador jogador4 = new Jogador(4L, nameGenerator.getNomeRandom(), true);
        List<Carta> cartas4 = partida.getMonte().popX(5);
        cartas4.forEach(c -> jogador4.getMao().add(c));

        partida.getJogadores().add(jogador1);
        partida.getJogadores().add(jogador3);
        partida.getJogadores().add(jogador2);
        partida.getJogadores().add(jogador4);
        partida.getJogadores().moveNext();

        partida.getDescarte().push(partida.getMonte().pop());
    }

    public static void reverseMethod(Partida partida) {
        if (partida.getDirecao().equals(Direcao.DIREITA))
            partida.setDirecao(Direcao.ESQUERDA);
        else
            partida.setDirecao(Direcao.DIREITA);
    }

    public static void skipMethod(Partida partida) {
        partida.setBloqueado(true);
    }

    public static void changeColor(Partida partida) {
        partida.setEscolheCor(true);
    }

    public static void draw2Method(Partida partida) {
        partida.setComprasObrigatorias(2);
    }

    public static void draw4Method(Partida partida) {
        partida.setComprasObrigatorias(4);
        partida.setEscolheCor(true);
    }
}
