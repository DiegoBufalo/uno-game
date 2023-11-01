import { FilaCircular } from "estruturas/FilaCircular";
import { Pilha } from "../estruturas/Pilha";
import { Carta } from "../modelos/Carta";
import { Jogador } from "../modelos/Jogador";


export class GameInfoState {
    jogadores: FilaCircular<Jogador>;
    monte: Pilha<Carta>;
    descarte: Pilha<Carta>;
    direcao: 'ESQUERDA' | 'DIREITA';
    bloqueado: boolean;
    compraObrigatoria: number;
    escolheCor: boolean;

    constructor() {
        this.jogadores = new FilaCircular<Jogador>();
        this.monte = new Pilha<Carta>();
        this.descarte = new Pilha<Carta>();
        this.direcao = 'DIREITA';
        this.bloqueado = false;
        this.compraObrigatoria = 0;
        this.escolheCor = false;
    }
}
