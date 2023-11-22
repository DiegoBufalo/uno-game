package com.usjt.a3.unogame.modelo;

import com.usjt.a3.unogame.estrutura.ListaCircular;
import com.usjt.a3.unogame.estrutura.ListaEncadeada;
import com.usjt.a3.unogame.estrutura.Pilha;
import com.usjt.a3.unogame.utils.ConfiguraPartida;
import lombok.Getter;
import lombok.Setter;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Getter
@Setter
@Component
@Scope("singleton")
public class Partida {

    private ListaCircular<Jogador> jogadores = new ListaCircular<>();
    private Pilha<Carta> monte = new Pilha<>();
    private Pilha<Carta> descarte = new Pilha<>();
    private Direcao direcao = Direcao.DIREITA;
    private Boolean bloqueado = false;
    private Integer comprasObrigatorias = 0;
    private String corAtual = "";
    private Boolean jogoFinalizado = false;
    private String jogadorVencedor = "";

    public Partida() {
        super();
    }

    public Partida(String nomeJogador) {
        super();
        ConfiguraPartida.initConfig(this, nomeJogador);
    }

    public Carta compraCarta() {
        if (monte.isEmpty()) {
            reembaralhaDescarte();
        }

        Jogador jogadorAtual = jogadores.getPosicaoAtual();
        ListaEncadeada<Carta> maoJogadorAtual = jogadorAtual.getMao();

        Carta cartaComprada = monte.pop();
        maoJogadorAtual.add(cartaComprada);

        return cartaComprada;
    }

    public void descartaCarta(String idCarta) {
        Jogador jogadorAtual = jogadores.getPosicaoAtual();
        ListaEncadeada<Carta> cartasJogador = jogadorAtual.getMao();
        Carta topo = descarte.peek();

        Carta cartaEncontrada = cartasJogador.findCartaById(idCarta);
        if (cartaEncontrada == null)
            return;

        if (cartaEncontrada.getCor().equals("wild") ||
                corAtual.equals(cartaEncontrada.getCor()) ||
                topo.getValor().equals(cartaEncontrada.getValor())) {
            cartasJogador.remove(cartaEncontrada);
            descarte.push(cartaEncontrada);

            if (cartasJogador.isEmpty()) {
                jogoFinalizado();
                return;
            }

            cartaEncontrada.getAcaoCarta().run();

            if (!cartaEncontrada.getCor().equals("wild")) {
                defineCor(cartaEncontrada.getCor());
            }
        }

    }

    private void jogoFinalizado() {
        this.jogoFinalizado = true;
        this.jogadorVencedor = jogadores.getPosicaoAtual().getNome();
    }

    public void defineCor(String cor) {
        this.corAtual = cor;
    }

    public void jogadaBot() {
        // Pegar a carta do topo atual
        Carta topo = descarte.peek();

        // verificar qual a melhor jogada, primeira ocorrencia que retornar verdade para cor ou valor da carta
        Jogador jogadorAtual = jogadores.getPosicaoAtual();
        ListaEncadeada<Carta> cartasJogador = jogadorAtual.getMao();
        Carta descarteMao = cartasJogador.buscaCartaParaDescartar(corAtual, topo.getValor());

        // se o bot nao possui carta de descarte compramos até possuir
        if (descarteMao == null) {
            do {
                descarteMao = compraCarta();
            } while (!descarteMao.getCor().equals("wild") &&
                    !descarteMao.getCor().equals(corAtual) &&
                    !descarteMao.getValor().equals(topo.getValor()));
        }

        // descartar carta
        descartaCarta(descarteMao.getId());

        // se a carta escolhida for wild definir a cor com base na primeira ocorrencia restante da mao
        if (descarteMao.getCor().equals("wild")) {
            Carta peek = cartasJogador.findFirstColorNotLike("wild");
            defineCor(peek.getCor());
        }
    }

    private void reembaralhaDescarte() {
        Carta topo = descarte.pop();
        descarte.shuffle();

        while (!descarte.isEmpty()) {
            monte.push(descarte.pop());
        }

        descarte.push(topo);
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
        proximoJogador();
    }

    public void changeColor() {
        proximoJogador();
    }

    public void draw2Method() {
        this.comprasObrigatorias = (2);
        proximoJogador();
        compraCarta();
        compraCarta();
        proximoJogador();
    }

    public void draw4Method() {
        this.comprasObrigatorias = 4;
        proximoJogador();
        compraCarta();
        compraCarta();
        compraCarta();
        compraCarta();
        proximoJogador();
    }

    public void proximoJogador() {
        if (this.direcao.equals(Direcao.DIREITA))
            this.jogadores.moveNext();
        else
            this.jogadores.movePrev();
    }
}
