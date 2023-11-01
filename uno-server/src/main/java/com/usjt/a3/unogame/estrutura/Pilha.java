package com.usjt.a3.unogame.estrutura;

import java.util.List;
import java.util.Random;
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

    public List<T> popX(int numCartas) {
        List<T> cartasRemovidas = new ArrayList<>();
        for (int i = 0; i < numCartas; i++) {
            if (isEmpty()) {
                break;
            }
            cartasRemovidas.add(pop());
        }
        return cartasRemovidas;
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

    public void shuffle() {
        Random rand = new Random();
        for (int i = size - 1; i > 0; i--) {
            int j = rand.nextInt(i + 1);

            // Troca os elementos nas posições i e j
            T temp = getElementAtPosition(i);
            setElementAtPosition(i, getElementAtPosition(j));
            setElementAtPosition(j, temp);
        }
    }

    private T getElementAtPosition(int position) {
        Node<T> current = top;
        for (int i = 0; i < position; i++) {
            current = current.getNext();
        }
        return current.getData();
    }

    private void setElementAtPosition(int position, T data) {
        Node<T> current = top;
        for (int i = 0; i < position; i++) {
            current = current.getNext();
        }
        current.setData(data);
    }
}
