export class FilaCircular<T> {

    private elementos: T[] = [];
    private tamanho: number = 0;
    private frente: number = 0;
    private tras: number = 0;
    private objetoAtual: number = 0;

    constructor() { }

    // Adiciona um elemento no final da fila
    push(value: T): void {
        if (this.tamanho === this.elementos.length) {
            this.aumentarCapacidade();
        }

        this.elementos[this.tras] = value;
        this.tras = (this.tras + 1) % this.elementos.length;
        this.tamanho++;
    }

    // Remove o elemento do início da fila
    pop(): T | null {
        if (this.isEmpty()) {
            return null;
        }

        const value = this.elementos[this.frente];
        this.frente = (this.frente + 1) % this.elementos.length;
        this.tamanho--;

        return value;
    }

    // Retorna o elemento do início da fila sem removê-lo
    peek(): T | null {
        return this.isEmpty() ? null : this.elementos[this.frente];
    }

    // Retorna o objeto atual da fila
    getObjetoAtual(): T | null {
        return this.isEmpty() ? null : this.elementos[this.objetoAtual];
    }

    get(index: number): T | null {
        if (!this.isEmpty()) {
            return this.elementos[index];
        }

        return null;
    }

    // Verifica se a fila está vazia
    isEmpty(): boolean {
        return this.tamanho === 0;
    }

    // Retorna o tamanho da fila
    getSize(): number {
        return this.tamanho;
    }

    map<R>(callback: (value: T, index: number) => R): R[] {
        return this.elementos.map(callback);
    }

    private aumentarCapacidade(): void {
        const novaCapacidade = this.elementos.length === 0 ? 1 : this.elementos.length * 2;
        const novoArray: T[] = new Array(novaCapacidade);

        for (let i = 0; i < this.tamanho; i++) {
            novoArray[i] = this.elementos[(this.frente + i) % this.elementos.length];
        }

        this.elementos = novoArray;
        this.frente = 0;
        this.tras = this.tamanho;
    }
}

