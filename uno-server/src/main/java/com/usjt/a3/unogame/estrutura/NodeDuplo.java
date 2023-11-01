package com.usjt.a3.unogame.estrutura;

public class NodeDuplo<T> {
    private T data;
    private NodeDuplo<T> next;
    private NodeDuplo<T> prev;

    public NodeDuplo(T data) {
        this.data = data;
        this.next = null;
        this.prev = null;
    }

    public T getData() {
        return this.data;
    }

    public NodeDuplo<T> getNext() {
        return this.next;
    }

    public NodeDuplo<T> getPrev() {
        return this.prev;
    }

    public void setNext(NodeDuplo<T> next) {
        this.next = next;
    }

    public void setPrev(NodeDuplo<T> next) {
        this.prev = next;
    }
}