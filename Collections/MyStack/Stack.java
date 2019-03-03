package com.brainacad.Collections.MyStack;

/**
 * @author  Shynkarenko Eduard
 *
 */
public interface Stack<E> {


    static class Node <E>{
        Node next;
        E element;


        public Node() {
            this.next = this;
        }

        public Node(Node next, E element) {
            this.next = next;
            this.element = element;
        }

        public Node getNext() {
            return next;
        }

        public E getElement() {
            return element;
        }

        public void setNext(Node next) {
            this.next = next;
        }
    }

    void push(E element);
    E pop();
}
