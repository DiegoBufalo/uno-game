import { FilaCircular } from "estruturas/FilaCircular"
import { Pilha } from "../estruturas/Pilha"
import { GameInfoState } from "../interfaces"
import { Carta } from "../modelos/Carta"
import { Jogador } from "../modelos/Jogador"
import { NameGenerator } from "./NameGenerator"


export function iniciadorGame(oldState: GameInfoState): GameInfoState {
    const reverseMethod = (state: GameInfoState) => {
        if (state.direcao === 'DIREITA') state.direcao = "ESQUERDA"
        else state.direcao = 'DIREITA'
    };

    const skipMethod = (state: GameInfoState) => {
        state.bloqueado = true;
    };

    const draw2Method = (state: GameInfoState) => {
        state.compraObrigatoria = 2;
    };

    const draw4Method = (state: GameInfoState) => {
        state.compraObrigatoria = 4;
        state.escolheCor = true;
    };

    const changeColor = (state: GameInfoState) => {
        state.escolheCor = true;
    };

    const cartas = (): Pilha<Carta> => {
        const coresCarta = ['red', 'green', 'yellow', 'blue']
        const valoresCarta = ['0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'draw', 'reverse', 'skip'];
        const cartas = new Pilha<Carta>();

        for (let index = 1; index <= 1; index++) {
            // coloca duas +4 e duas troca de cor por baralho
            cartas.push(new Carta('wild', 'draw', draw4Method));
            cartas.push(new Carta('wild', 'draw', draw4Method));
            cartas.push(new Carta('wild', 'change', changeColor));
            cartas.push(new Carta('wild', 'change', changeColor));

            // adiciona uma carta de cada numero e cor por baralho
            coresCarta.map(cor => {
                return valoresCarta.map(valor => {
                    if (valor === 'draw') {
                        cartas.push(new Carta(cor, valor, draw2Method))
                    } else if (valor === 'reverse') {
                        cartas.push(new Carta(cor, valor, reverseMethod))
                    } else if (valor === 'skip') {
                        cartas.push(new Carta(cor, valor, skipMethod))
                    } else {
                        cartas.push(new Carta(cor, valor))
                    }
                });
            });
        }

        // embaralha as cartas
        cartas.shuffle();

        // retorna a pilha embaralhada
        return cartas;
    }

    const jogadores = (): FilaCircular<Jogador> => {
        const nameGenerator = new NameGenerator();

        // cria a fila de jogadores
        const fila = new FilaCircular<Jogador>();

        const jogador1 = new Jogador(1, nameGenerator.getNomeRandom(), true);
        jogador1.mao = cartas().popX(5);

        const jogador2 = new Jogador(2, nameGenerator.getNomeRandom(), false);
        jogador2.mao = cartas().popX(5);

        const jogador3 = new Jogador(3, nameGenerator.getNomeRandom(), true);
        jogador3.mao = cartas().popX(5);

        const jogador4 = new Jogador(4, nameGenerator.getNomeRandom(), true);
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
        ...oldState,
        jogadores: jogadores(),
        monte: cartas(),
        descarte: descarte,
    }
}
