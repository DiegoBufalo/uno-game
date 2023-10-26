import { ListaEncadeadaSimples } from "../estruturas/ListaEncadeadaSimple";
import { Carta } from "./Carta"

export class Jogador {
    id: number;
    nome: string;
    isBot: boolean;
    mao: ListaEncadeadaSimples<Carta> = new ListaEncadeadaSimples();

    constructor(id: number, nome: string, isBot: boolean) {
        this.id = id;
        this.nome = nome;
        this.isBot = isBot;
        this.mao = new ListaEncadeadaSimples<Carta>();
    }
}
