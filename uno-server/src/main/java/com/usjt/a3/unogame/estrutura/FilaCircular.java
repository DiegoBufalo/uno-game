package com.usjt.a3.unogame.estrutura;

public class FilaCircular<T> {
    private Node<T> current;
    private int size;

    private class Node<T> {
        private T data;
        private Node<T> next;
        private Node<T> prev;

        public Node(T data) {
            this.data = data;
            this.next = null;
            this.prev = null;
        }
    }

    public FilaCircular() {
        current = null;
        size = 0;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public int size() {
        return size;
    }

    public T getCurrent() {
        if (isEmpty()) {
            throw new IllegalStateException("A fila está vazia.");
        }
        return current.data;
    }

    public void add(T data) {
        Node<T> newNode = new Node<>(data);
        if (isEmpty()) {
            current = newNode;
            current.next = current;
            current.prev = current;
        } else {
            newNode.next = current.next;
            newNode.prev = current;
            current.next.prev = newNode;
            current.next = newNode;
            current = newNode;
        }
        size++;
    }

    public void moveNext() {
        if (isEmpty()) {
            throw new IllegalStateException("A fila está vazia.");
        }
        current = current.next;
    }

    public void movePrev() {
        if (isEmpty()) {
            throw new IllegalStateException("A fila está vazia.");
        }
        current = current.prev;
    }

    public void print() {
        if (isEmpty()) {
            System.out.println("Fila vazia.");
            return;
        }
        Node<T> temp = current;
        do {
            System.out.print(temp.data + " ");
            temp = temp.next;
        } while (temp != current);
        System.out.println();
    }
}
