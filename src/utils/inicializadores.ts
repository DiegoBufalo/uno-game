import { FilaCircular } from "estruturas/FilaCircular"
import { Pilha } from "../estruturas/Pilha"
import { GameInfoState } from "../interfaces"
import { Carta } from "../modelos/Carta"
import { Jogador } from "../modelos/Jogador"

export function iniciadorGame(quantidadeBaralho: number = 2): GameInfoState {
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

    const jogadores = (): FilaCircular<Jogador> => {
        // cria a fila de jogadores
        const fila = new FilaCircular<Jogador>();

        const jogador1 = new Jogador(1, `jogador1`, true);
        jogador1.mao = cartas().popX(5);

        const jogador2 = new Jogador(2, `jogador2`, false);
        jogador2.mao = cartas().popX(5);

        const jogador3 = new Jogador(3, `jogador3`, true);
        jogador3.mao = cartas().popX(5);

        const jogador4 = new Jogador(4, `jogador4`, true);
        jogador4.mao = cartas().popX(5);

        fila.push(jogador1);
        fila.push(jogador3);
        fila.push(jogador2);
        fila.push(jogador4);

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
        bloqueado: false,
        compraObrigatoria: 0,
    }
}
