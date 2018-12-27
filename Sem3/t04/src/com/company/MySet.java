package com.company;

public class MySet<T> {
    private Elem<T> head, tail;
    private int sizeList;

    public MySet() {

        head = null;
        tail = null;
        sizeList = 0;
    }

    private class Elem<T> {

        T value;
        Elem<T> next;

        public Elem(T value) {

            this.value = value;
            next = null;
        }
    }

    public boolean add(T elem) {

        if (head == null && tail == null) {
            synchronized (this) {
                head = new Elem<>(elem);
                tail = head;
                sizeList++;
            }
        } else {
            Elem<T> newElem = new Elem<>(elem);
            synchronized (this) {
                tail.next = newElem;
                tail = newElem;
                sizeList++;
            }
        }

        return true;
    }

    public boolean contains(T elem) {

        Elem<T> tmp = head;
        while (tmp != null) {
            if (elem.equals(tmp.value)) {
                return true;
            }
            tmp = tmp.next;
        }

        return false;
    }

    public boolean isEmpty() {

        return head == null;
    }

    public T getFirstEl() {

        T value = head.value;
        synchronized (this) {
            head = head.next;
            if (head == null) {
                tail = null;
            }
            sizeList--;
        }

        return value;
    }
}
