package com.usjt.a3.unogame.modelo;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.usjt.a3.unogame.estrutura.FilaCircular;
import com.usjt.a3.unogame.estrutura.ListaEncadeada;
import com.usjt.a3.unogame.estrutura.Pilha;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Component
@Scope("singleton")
public class Partida {

    private FilaCircular<Jogador> jogadores = new FilaCircular<Jogador>();
    private Pilha<Carta> monte = new Pilha<Carta>();
    private Pilha<Carta> descarte = new Pilha<Carta>();
    private Direcao direcao = Direcao.DIREITA;
    private Boolean bloqueado = false;
    private Integer comprasObrigatorias = 0;
    private Boolean escolheCor = false;

    public Partida() {
        super();
    }

    public void compraCarta() {
        Jogador jogadorAtual = jogadores.getCurrent();
        ListaEncadeada<Carta> maoJogadorAtual = jogadorAtual.getMao();

        Carta cartaComprada = monte.pop();
        maoJogadorAtual.add(cartaComprada);
    }

}
