import { FilaCircular } from "estruturas/FilaCircular";
import { Pilha } from "../estruturas/Pilha";
import { Carta } from "../modelos/Carta";
import { Jogador } from "../modelos/Jogador";


export interface GameInfoState {
    jogadores: FilaCircular<Jogador>;
    monte: Pilha<Carta>;
    descarte: Pilha<Carta>;
    direcao: 'ESQUERDA' | 'DIREITA';
    bloqueado: boolean;
    compraObrigatoria: number;
}
