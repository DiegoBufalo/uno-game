package com.usjt.a3.unogame.estrutura;

import java.util.ArrayList;
import java.util.List;

// Lista que é usada para guardar os jogadores da partida.
public class ListaCircular<T> {
    private NodeDuplo<T> atual;

    public ListaCircular() {
        // Inicialize a lista vazia com 'atual' como null.
        atual = null;
    }

    public T getPosicaoAtual() {
        if (atual == null) {
            throw new IllegalArgumentException("elemento atual é nulo");
        }
        return atual.getData();
    }

    public void add(T data) {
        NodeDuplo<T> novoNode = new NodeDuplo<>(data);
        if (atual == null) {
            // Se a lista estiver vazia, insira o primeiro elemento.
            atual = novoNode;
            atual.setNext(atual);
            atual.setPrev(atual);
        } else {
            // Insira o novo elemento após o elemento atual.
            NodeDuplo<T> proximo = atual.getNext();
            atual.setNext(novoNode);
            novoNode.setPrev(atual);
            novoNode.setNext(proximo);
            proximo.setPrev(novoNode);
            atual = novoNode;
        }
    }

    public void moveNext() {
        if (atual != null) {
            atual = atual.getNext();
        }
    }

    public void movePrev() {
        if (atual != null) {
            atual = atual.getPrev();
        }
    }

    // metodo usado apenas para montar o DTO e mapear para JSON
    public List<T> toList() {
        List<T> lista = new ArrayList<>();
        if (atual == null) {
            // A lista está vazia, retorne uma lista vazia.
            return lista;
        }

        NodeDuplo<T> temp = atual;
        do {
            lista.add(temp.getData());
            temp = temp.getNext();
        } while (temp != atual);

        return lista;
    }
}
