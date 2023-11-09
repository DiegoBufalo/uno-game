package com.usjt.a3.unogame.estrutura;

import java.util.List;
import java.util.Random;
import java.util.LinkedList;

// Pilha usada para empilhar as cartas de descarte e compra
public class Pilha<T> {
    private Node<T> top;

    public void push(T data) {
        Node<T> newNode = new Node<>(data);
        newNode.setNext(top);
        top = newNode;
    }

    public T pop() {
        if (isEmpty()) {
            throw new IllegalStateException("A pilha está vazia.");
        }
        T data = top.getData();
        top = top.getNext();
        return data;
    }

    public T peek() {
        if (isEmpty()) {
            throw new IllegalStateException("A pilha está vazia.");
        }
        return top.getData();
    }

    public void shuffle() {
        Node<T> current = top;
        int size = 0;

        // Contar o número de elementos na pilha
        while (current != null) {
            size++;
            current = current.getNext();
        }

        // Criar um array para armazenar os elementos da pilha
        Object[] elements = new Object[size];

        // Preencher o array com os elementos da pilha
        current = top;
        for (int i = 0; i < size; i++) {
            elements[i] = current.getData();
            current = current.getNext();
        }

        // Embaralhar o array usando o algoritmo Fisher-Yates
        Random rand = new Random();
        for (int i = size - 1; i > 0; i--) {
            int j = rand.nextInt(i + 1);
            Object temp = elements[i];
            elements[i] = elements[j];
            elements[j] = temp;
        }

        // Reconstruir a pilha com os elementos embaralhados
        top = null;
        for (int i = 0; i < size; i++) {
            @SuppressWarnings("unchecked")
            T data = (T) elements[i];
            push(data);
        }
    }

    public boolean isEmpty() {
        return top == null;
    }

    // metodo usado apenas para montar o DTO e mapear para JSON
    public List<T> toList() {
        List<T> lista = new LinkedList<>();
        Node<T> current = top;
        while (current != null) {
            lista.add(current.getData());
            current = current.getNext();
        }
        return lista;
    }
}
