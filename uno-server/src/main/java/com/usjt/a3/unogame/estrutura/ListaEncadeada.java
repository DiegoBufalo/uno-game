package com.usjt.a3.unogame.estrutura;

import java.util.ArrayList;
import java.util.List;

import com.usjt.a3.unogame.modelo.Carta;

// Lista usada para guardar as cartas na mao de cada jogador
public class ListaEncadeada<T> {
    private Node<T> head;


    public boolean isEmpty() {
        return head == null;
    }

    // adiciona um novo elemento à lista, caso head seja nulo adiciona no head, caso contrário 
    // é inserido no next do ultimo elemento da lista
    public void add(T data) {
        Node<T> newNode = new Node<>(data);
        if (head == null) {
            head = newNode;
        } else {
            Node<T> current = head;
            while (current.getNext() != null) {
                current = current.getNext();
            }
            current.setNext(newNode);
        }
    }

    public void remove(T data) {
        if (head == null) {
            // A lista está vazia, não há nada para remover.
            return;
        }

        if (head.getData().equals(data)) {
            head = head.getNext();
            return;
        }

        Node<T> current = head;
        while (current.getNext() != null) {
            if (current.getNext().getData().equals(data)) {
                current.setNext(current.getNext().getNext());
                return;
            }
            current = current.getNext();
        }
    }

    public Carta findCartaById(String id) {
        Node<Carta> current = (Node<Carta>) head;
        while (current != null) {
            if (current.getData().getId().equals(id)) {
                return current.getData();
            }
            current = current.getNext();
        }
        return null; // Retorna null se o elemento não for encontrado.
    }

    public Carta buscaCartaParaDescartar(String color, String value) {
        Node<Carta> current = (Node<Carta>) head;
        while (current != null) {
            if (current.getData().getCor().equals(color) ||
                    current.getData().getValor().equals(value) ||
                    current.getData().getCor().equals("wild")) {

                return current.getData();
            }
            current = current.getNext();
        }
        return null; // Retorna null se o elemento não for encontrado.
    }

    public Carta findFirstColorNotLike(String color) {
        Node<Carta> current = (Node<Carta>) head;
        while (current != null) {
            if (!current.getData().getCor().equals(color)) {
                return current.getData();
            }
            current = current.getNext();
        }
        return null; // Retorna null se o elemento não for encontrado.
    }

    // metodo usado apenas para montar o DTO e mapear para JSON
    public List<T> toList() {
        List<T> lista = new ArrayList<>();
        Node<T> current = head;
        while (current != null) {

            lista.add(current.getData());
            current = current.getNext();
        }
        return lista;
    }
}