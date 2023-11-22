export interface Carta {
    id: string;
    cor: string;
    valor: string;
    imagem: string;
}

export interface Jogador {
    id: number;
    nome: string;
    isBot: boolean;
    mao: Carta[];
}

export interface GameInfoState {
    jogadores: Jogador[];
    monte: Carta[];
    descarte: Carta[];
    direcao: 'ESQUERDA' | 'DIREITA';
    bloqueado: boolean;
    comprasObrigatorias: number;
    idJogadorAtual: number;
    jogadorAtualIsBot: boolean;
    corAtual?: 'red' | 'blue' | 'green' | 'yellow';
    jogoFinalizado: boolean;
    jogadorVencedor: string;
 }
