import { NoDuplo } from "./No";

export class FilaCircularDuplamenteEncadeada<T> {
    head: NoDuplo<T> | null = null;
    tail: NoDuplo<T> | null = null;
    size: number = 0;

    constructor() { }

    // Adiciona um elemento no final da fila
    push(value: T): void {
        const no = new NoDuplo(value);

        if (this.head === null) {
            this.head = no;
            this.tail = no;
            no.prev = no;
            no.next = no;
        } else {
            this.tail!.next = no;
            no.prev = this.tail;
            no.next = this.head;
            this.head.prev = no;
            this.tail = no;
        }

        this.size++;
    }

    // Remove o elemento do início da fila
    pop(): T | null {
        if (this.head === null) {
            return null;
        }

        const value = this.head.value;
        this.head = this.head.next;
        this.head!.prev = this.tail;
        this.tail!.next = this.head;

        this.size--;
        return value;
    }

    // Retorna o elemento do início da fila sem removê-lo
    peek(): T | null {
        return this.head === null ? null : this.head.value;
    }

    // Verifica se a fila está vazia
    isEmpty(): boolean {
        return this.head === null;
    }

    // Retorna o tamanho da fila
    getSize(): number {
        return this.size;
    }
}
