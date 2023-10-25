import { ListaEncadeada } from "../estruturas/ListaEncadeada"
import { Carta } from "./Carta"
import { getRandomInt } from "../utils/utils"

export class Jogador {
    id: number;
    nome: string;
    isBot: boolean;
    mao: ListaEncadeada<Carta> = new ListaEncadeada;

    constructor(nome: string, isBot: boolean) {
        this.id = getRandomInt();
        this.nome = nome;
        this.isBot = isBot;
    }
}