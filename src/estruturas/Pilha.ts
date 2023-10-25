export class Pilha<T> {
    private topo: T | null = null;
    private tamanho: number = 0;

    constructor() { }

    // Adiciona um elemento no topo da pilha
    push(carta: T): void {
        this.topo = carta;
        this.tamanho++;
    }

    // Remove o elemento do topo da pilha
    pop(): T | null {
        if (this.tamanho === 0) {
            return null;
        }

        const carta = this.topo;
        this.topo = null;
        this.tamanho--;
        return carta;
    }

    // Retorna o elemento do topo da pilha sem removê-lo
    peek(): T | null {
        return this.topo;
    }

    // Verifica se a pilha está vazia
    isEmpty(): boolean {
        return this.tamanho === 0;
    }

    // Retorna o tamanho da pilha
    size(): number {
        return this.tamanho;
    }
}
