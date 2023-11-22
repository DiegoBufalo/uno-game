package com.usjt.a3.unogame.utils;

import com.usjt.a3.unogame.estrutura.Pilha;
import com.usjt.a3.unogame.modelo.Carta;
import com.usjt.a3.unogame.modelo.Jogador;
import com.usjt.a3.unogame.modelo.Partida;

public class ConfiguraPartida {

    private ConfiguraPartida() {
    }

    public static void initConfig(Partida partida, String nomeJogador) {
        String[] coresCarta = new String[] { "red", "green", "yellow", "blue" };
        String[] valoresCarta = new String[] { "0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "draw", "reverse", "skip" };

        // Coloca duas +4 e duas troca de cor
        partida.getMonte().push(new Carta("wild", "draw", partida::draw4Method));
        partida.getMonte().push(new Carta("wild", "draw", partida::draw4Method));
        partida.getMonte().push(new Carta("wild", "change", partida::changeColor));
        partida.getMonte().push(new Carta("wild", "change", partida::changeColor));

        for (String string : coresCarta) {
            for (String s : valoresCarta) {
                Carta carta = new Carta(string, s);

                switch (s) {
                    case "draw":
                        carta.setAcaoCarta(partida::draw2Method);
                        break;
                    case "reverse":
                        carta.setAcaoCarta(partida::reverseMethod);
                        break;
                    case "skip":
                        carta.setAcaoCarta(partida::skipMethod);
                        break;
                    default:
                        carta.setAcaoCarta(partida::proximoJogador);
                        break;
                }

                if (carta.getCor() != null && carta.getValor() != null)
                    partida.getMonte().push(carta);
            }
        }

        partida.getMonte().shuffle();

        NameGenerator nameGenerator = new NameGenerator();

        Jogador jogador1 = new Jogador(1L, nameGenerator.getNomeRandom(), true);
        compraMaoInicial(partida.getMonte(), jogador1);

        Jogador jogador2 = new Jogador(2L, nomeJogador, false);
        compraMaoInicial(partida.getMonte(), jogador2);

        Jogador jogador3 = new Jogador(3L, nameGenerator.getNomeRandom(), true);
        compraMaoInicial(partida.getMonte(), jogador3);

        Jogador jogador4 = new Jogador(4L, nameGenerator.getNomeRandom(), true);
        compraMaoInicial(partida.getMonte(), jogador4);

        partida.getJogadores().add(jogador1);
        partida.getJogadores().add(jogador3);
        partida.getJogadores().add(jogador2);
        partida.getJogadores().add(jogador4);
        partida.getJogadores().moveNext();

        Carta descarte;
        do {
            descarte = partida.getMonte().pop();
            partida.getDescarte().push(descarte);
            partida.setCorAtual(descarte.getCor());
        } while (descarte.getCor().equals("wild"));

    }

    private static void compraMaoInicial(Pilha<Carta> monte, Jogador jogador) {
        for (int i = 0; i < 5; i++) {
            jogador.getMao().add(monte.pop());
        }
    }
}
