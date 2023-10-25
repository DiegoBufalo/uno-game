import { No } from "./No";

export class ListaEncadeada<T> {
    head: No<T> | null = null;
    tail: No<T> | null = null;
    size: number = 0;

    constructor() { }

    // Adiciona um elemento no início da lista
    push(value: T): void {
        const no = new No<T>(value);

        if (this.head === null) {
            this.head = no;
            this.tail = no;
        } else {
            no.next = this.head;
            this.head = no;
        }

        this.size++;
    }

    // Remove o elemento do início da lista
    pop(): T | null {
        if (this.head === null) {
            return null;
        }

        const value = this.head.value;
        this.head = this.head.next;

        if (this.head === null) {
            this.tail = null;
        }

        this.size--;
        return value;
    }

    // Retorna o elemento do início da lista sem removê-lo
    peek(): T | null {
        return this.head === null ? null : this.head.value;
    }

    // Verifica se a lista está vazia
    isEmpty(): boolean {
        return this.head === null;
    }

    // Retorna o tamanho da lista
    getSize(): number {
        return this.size;
    }

    // Retorna o valor do elemento na posição especificada
    get(indice: number): T | null {
        if (indice < 0 || indice >= this.size) {
            return null;
        }

        let no = this.head;
        for (let i = 0; i < indice; i++) {
            no = no!.next;
        }

        return no!.value;
    }

    // Modifica o valor do elemento na posição especificada
    set(indice: number, valor: T): void {
        if (indice < 0 || indice >= this.size) {
            return;
        }

        let no = this.head;
        for (let i = 0; i < indice; i++) {
            no = no!.next;
        }

        no!.value = valor;
    }

    // Ordenação por seleção
    ordenarLista(): ListaEncadeada<T> {
        const tamanho = this.size;

        for (let i = 0; i < tamanho - 1; i++) {
            // Assume que o primeiro elemento é o menor
            let menor = i;

            // Percorre a lista a partir do elemento i + 1
            for (let j = i + 1; j < tamanho; j++) {
                // Se encontrar um elemento menor que o menor encontrado até o momento
                if (this.get(j)! < this.get(menor)!) {
                    menor = j;
                }
            }

            // Se o menor encontrado não for o primeiro elemento, troca as posições
            if (menor !== i) {
                const aux = this.get(menor);
                this.set(menor, this.get(i)!);
                this.set(i, aux!);
            }
        }

        return this;
    }
}
