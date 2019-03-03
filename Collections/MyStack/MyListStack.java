package com.brainacad.Collections.MyStack;


import  com.brainacad.Collections.MyStack.Stack;

import java.util.EmptyStackException;

/**
 * @author  Shynkarenko Eduard
 *
 */
public class MyListStack<E> implements Stack<E> {

    private int capacity;
    private static int size;
    Stack.Node<E> header = new Stack.Node<>();

    public MyListStack(int cap) {
        this.capacity = cap;
    }

    @Override
    public void push(E element) {
        if (size == 0) {
            Stack.Node<E> node = new Stack.Node<>(null, element);
            header.setNext(node);
        } else if(size < capacity){
            Stack.Node<E> node = new Stack.Node<>(header.getNext(), element);
            header.setNext(node);
        }else{
            incrCapacity();
            Stack.Node<E> node = new Stack.Node<>(header.getNext(), element);
            header.setNext(node);
        }

        size++;
    }

    @Override
    public E pop() {
        if (size == 0) {
            throw new EmptyStackException();
        }

        Stack.Node<E> node = header.getNext();
        E elem = node.getElement();
        header.setNext(node.getNext());
        --size;
        return elem;
    }


    public E peek() {
        if (size == 0) {
            throw new EmptyStackException();
        }

        Stack.Node<E> node = header.getNext();
        E elem = node.getElement();
        return elem;
    }

    public boolean empty() {
        return size == 0;
    }

    public int search(Object obj) {

        Stack.Node<E> node = header.getNext();
        for (int i = 1; i <= size; i++) {
            if (node.getElement().equals(obj)) {
                return i;
            }
            node = node.getNext();
        }

        return -1;

    }

    public int getSize() {
        return size;
    }

    public void incrCapacity() {
        capacity = capacity * 2;
    }

}

