package com.usjt.a3.unogame.modelo;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.usjt.a3.unogame.estrutura.ListaCircular;
import com.usjt.a3.unogame.estrutura.ListaEncadeada;
import com.usjt.a3.unogame.estrutura.Pilha;
import com.usjt.a3.unogame.utils.ConfiguraPartida;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Component
@Scope("singleton")
public class Partida {

    private ListaCircular<Jogador> jogadores = new ListaCircular<Jogador>();
    private Pilha<Carta> monte = new Pilha<Carta>();
    private Pilha<Carta> descarte = new Pilha<Carta>();
    private Direcao direcao = Direcao.DIREITA;
    private Boolean bloqueado = false;
    private Integer comprasObrigatorias = 0;
    private Boolean escolheCor = false;

    public Partida() {
        super();
    }

    public Partida(String nomeJogador) {
        super();
        ConfiguraPartida.initConfig(this, nomeJogador);
    }

    public void compraCarta() {
        Jogador jogadorAtual = jogadores.getPosicaoAtual();
        ListaEncadeada<Carta> maoJogadorAtual = jogadorAtual.getMao();

        Carta cartaComprada = monte.pop();
        maoJogadorAtual.add(cartaComprada);
    }

    public void descartaCarta(String idCarta) {
        Jogador jogadorAtual = jogadores.getPosicaoAtual();
        ListaEncadeada<Carta> cartasJogador = jogadorAtual.getMao();
        Carta topo = descarte.peek();

        Carta cartaEncontrada = cartasJogador.findCarta(idCarta);
        if (cartaEncontrada == null)
            return;

        if (cartaEncontrada.getCor().equals("wild") ||
                topo.getCor().equals(cartaEncontrada.getCor()) ||
                topo.getValor().equals(cartaEncontrada.getValor())) {
            cartasJogador.remove(cartaEncontrada);
            cartaEncontrada.getAcaoCarta().run();
            descarte.push(cartaEncontrada);
        }
    }

    public void reverseMethod() {
        if (this.direcao.equals(Direcao.DIREITA))
            this.direcao = Direcao.ESQUERDA;
        else
            this.direcao = Direcao.DIREITA;
        proximoJogador();
    }

    public void skipMethod() {
        this.bloqueado = true;
        proximoJogador();
    }

    public void changeColor() {
        this.escolheCor = true;
        proximoJogador();
    }

    public void draw2Method() {
        this.comprasObrigatorias = (2);
        proximoJogador();
    }

    public void draw4Method() {
        this.comprasObrigatorias = 4;
        this.escolheCor = true;
        proximoJogador();
    }

    public void proximoJogador() {
        if (this.direcao.equals(Direcao.DIREITA))
            this.jogadores.moveNext();
        else
            this.jogadores.movePrev();
    }
}
