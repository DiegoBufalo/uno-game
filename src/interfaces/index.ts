import { FilaCircularDuplamenteEncadeada } from "../estruturas/FilaCircular";
import { Pilha } from "../estruturas/Pilha";
import { Carta } from "../modelos/Carta";
import { Jogador } from "../modelos/Jogador";


export interface GameInfoState {
    jogadores: FilaCircularDuplamenteEncadeada<Jogador>;
    monte: Pilha<Carta>;
    descarte: Pilha<Carta>;
    direcao: 'ESQUERDA' | 'DIREITA';
    jogadorTurno: number;
    bloqueado: boolean;
    compraObrigatoria: number;
}
