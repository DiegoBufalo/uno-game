package com.usjt.a3.unogame.estrutura;

import java.util.List;
import java.util.ArrayList;

public class FilaCircular<T> {
    private NodeDuplo<T> current;
    private int size;

    public FilaCircular() {
        this.current = null;
        this.size = 0;
    }

    public boolean isEmpty() {
        return this.size == 0;
    }

    public int size() {
        return this.size;
    }

    public T getCurrent() {
        if (isEmpty()) {
            throw new IllegalStateException("A fila está vazia.");
        }
        return this.current.getData();
    }

    public void add(T data) {
        NodeDuplo<T> newNode = new NodeDuplo<>(data);
        if (isEmpty()) {
            this.current = newNode;
            this.current.setNext(current);
            this.current.setNext(current);
        } else {
            newNode.setNext(current.getNext());
            newNode.setPrev(current);
            this.current.getNext().setPrev(newNode);
            this.current.setNext(newNode);
            this.current = newNode;
        }
        size++;
    }

    public void moveNext() {
        if (isEmpty()) {
            throw new IllegalStateException("A fila está vazia.");
        }
        this.current = this.current.getNext();
    }

    public void movePrev() {
        if (isEmpty()) {
            throw new IllegalStateException("A fila está vazia.");
        }
        this.current = this.current.getPrev();
    }

    public void print() {
        if (isEmpty()) {
            System.out.println("Fila vazia.");
            return;
        }
        NodeDuplo<T> temp = this.current;
        do {
            System.out.print(temp.getData() + " ");
            temp = temp.getNext();
        } while (temp != this.current);
        System.out.println();
    }

    public List<T> toList() {
        List<T> list = new ArrayList<>();
        if (isEmpty()) {
            return list;
        }

        NodeDuplo<T> temp = this.current;
        do {
            list.add(temp.getData());
            temp = temp.getNext();
        } while (temp != this.current);

        return list;
    }
}
