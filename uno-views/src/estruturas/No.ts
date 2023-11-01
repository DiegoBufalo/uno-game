export class No<T> {
    value: T;
    next: No<T> | null = null;

    constructor(value: T, next?: No<T> | null) {
        if (next === undefined) next = null;
        this.value = value;
        this.next = next;
    }
}

export class NoDuplo<T> {
    value: T;
    next: NoDuplo<T> | null = null;
    prev: NoDuplo<T> | null = null;

    constructor(value: T, next?: NoDuplo<T> | null, prev?: NoDuplo<T> | null) {
        if (next === undefined) next = null;
        if (prev === undefined) prev = null;

        this.value = value;
        this.next = next;
        this.prev = prev;
    }
}