package com.usjt.a3.unogame.estrutura;

import java.util.List;
import java.util.ArrayList;

public class Pilha<T> {
    private Node<T> top;
    private int size;

    public Pilha() {
        top = null;
        size = 0;
    }

    public boolean isEmpty() {
        return top == null;
    }

    public int size() {
        return size;
    }

    public void push(T data) {
        Node<T> newNode = new Node<>(data);
        newNode.setNext(top);
        top = newNode;
        size++;
    }

    public T pop() {
        if (isEmpty()) {
            throw new IllegalStateException("A pilha está vazia.");
        }

        T data = top.getData();
        top = top.getNext();
        size--;
        return data;
    }

    public T peek() {
        if (isEmpty()) {
            throw new IllegalStateException("A pilha está vazia.");
        }

        return top.getData();
    }

    public List<T> toList() {
        List<T> list = new ArrayList<>();
        Node<T> current = this.top;
        while (current != null) {
            list.add(current.getData());
            current = current.getNext();
        }
        return list;
    }
}
