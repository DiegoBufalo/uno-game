export class ListaEncadeadaSimples<T> {
    private elementos: T[] = [];

    constructor() { }

    // Adiciona um elemento no início da lista
    push(value: T): void {
        this.elementos.unshift(value);
    }

    // Remove o elemento do início da lista
    pop(): T | null {
        if (this.isEmpty()) {
            return null;
        }
        return this.elementos.shift() || null;
    }

    // Remove a primeira ocorrência do elemento na lista
    remove(value: T): void {
        const index = this.elementos.indexOf(value);
        if (index !== -1) {
            this.elementos.splice(index, 1);
        }
    }

    // Retorna o elemento do início da lista sem removê-lo
    peek(): T | null {
        return this.isEmpty() ? null : this.elementos[0];
    }

    // Verifica se a lista está vazia
    isEmpty(): boolean {
        return this.elementos.length === 0;
    }

    // Retorna o tamanho da lista
    getSize(): number {
        return this.elementos.length;
    }

    // Retorna o valor do elemento na posição especificada
    get(indice: number): T | null {
        if (indice < 0 || indice >= this.getSize()) {
            return null;
        }
        return this.elementos[indice];
    }

    // Modifica o valor do elemento na posição especificada
    set(indice: number, valor: T): void {
        if (indice >= 0 && indice < this.getSize()) {
            this.elementos[indice] = valor;
        }
    }

    // Ordenação por seleção
    ordenarLista(): ListaEncadeadaSimples<T> {
        this.elementos.sort((a, b) => {
            if (a < b) return -1;
            if (a > b) return 1;
            return 0;
        });
        return this;
    }

    // Mapeia os elementos da lista sem remover os itens
    map<R>(callback: (value: T, index: number) => R): R[] {
        return this.elementos.map(callback);
    }
}
