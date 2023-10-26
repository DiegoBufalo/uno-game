import { ListaEncadeadaSimples } from "./ListaEncadeadaSimple";

export class Pilha<T> {
    private items: T[] = [];

    constructor() { }

    // Adiciona um elemento ao topo da pilha
    push(item: T): Pilha<T> {
        this.items.unshift(item);
        return this;
    }

    // Verifica se a pilha está vazia
    isEmpty(): boolean {
        return this.items.length === 0;
    }

    // Retorna o tamanho da pilha
    size(): number {
        return this.items.length;
    }

    // Remove e retorna o elemento do topo da pilha
    pop(): T | undefined {
        return this.items.shift();
    }

    peek(): T {
        return this.items[0];
    }

    popX(quantity: number): ListaEncadeadaSimples<T> {
        if (quantity > this.size()) quantity = this.size();
        const result: ListaEncadeadaSimples<T> = new ListaEncadeadaSimples();
        for (let index = 0; index < quantity; index++) {
            result.push(this.pop()!)
        }

        return result;
    }

    // Embaralha os elementos da pilha
    shuffle(): void {
        for (let i = this.items.length - 1; i > 0; i--) {
            const j = Math.floor(Math.random() * (i + 1));
            [this.items[i], this.items[j]] = [this.items[j], this.items[i]];
        }
    }
}
