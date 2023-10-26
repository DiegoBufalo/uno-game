import { FilaCircularDuplamenteEncadeada } from "../estruturas/FilaCircular"
import { Pilha } from "../estruturas/Pilha"
import { GameInfoState } from "../interfaces"
import { Carta } from "../modelos/Carta"
import { Jogador } from "../modelos/Jogador"


export function iniciadorGame(quantidadeBaralho: number = 2, quantidadeJogador: number = 4): GameInfoState {
    const cartas = (): Pilha<Carta> => {
        const coresCarta = ['red', 'green', 'yellow', 'blue']
        const valoresCarta = ['0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'draw', 'reverse', 'skip'];
        const cartas = new Pilha<Carta>();

        for (let index = 1; index <= quantidadeBaralho; index++) {
            // coloca duas +4 e duas troca de cor por baralho
            cartas.push(new Carta('wild', 'draw'));
            cartas.push(new Carta('wild', 'draw'));
            cartas.push(new Carta('wild', 'change'));
            cartas.push(new Carta('wild', 'change'));

            // adiciona uma carta de cada numero e cor por baralho
            coresCarta.map(cor => {
                return valoresCarta.map(valor => {
                    cartas.push(new Carta(cor, valor))
                });
            });
        }

        // embaralha as cartas
        cartas.shuffle();

        // retorna a pilha embaralhada
        return cartas;
    }

    const jogadores = (): FilaCircularDuplamenteEncadeada<Jogador> => {
        // cria a fila de jogadores
        const fila = new FilaCircularDuplamenteEncadeada<Jogador>();

        // instancia cada um deles, 3 bots e um jogador
        for (let index = 1; index <= quantidadeJogador; index++) {
            // regra para colocar o jogador humano como o jogador 2, pq é o que fica na parte de baixo da tela
            const isBot = index !== 2;

            // instancia um jogador
            const jogador = new Jogador(index, `jogador${index}`, isBot);

            // coloca 5 cartas do monte na mão do jogador
            jogador.mao = cartas().popX(5);

            // insere o jogador na fila de jogo
            fila.push(jogador);
        }

        // retorna a fila
        return fila;
    }


    // depois de distribuidas as cartas, vira a primeira carta da pilha para que sirva como primeira referencia
    const descarte = new Pilha<Carta>();
    descarte.push(cartas().pop()!);

    return {
        jogadores: jogadores(),
        monte: cartas(),
        descarte: descarte,
        direcao: 'DIREITA',
        jogadorTurno: jogadores().peek()?.id || 1,
        bloqueado: false,
        compraObrigatoria: 0,
    }
}
