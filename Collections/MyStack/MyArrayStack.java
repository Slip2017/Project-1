package   com.brainacad.Collections.MyStack;

import java.util.Arrays;
import java.util.EmptyStackException;

/**
 * @author  Shynkarenko Eduard
 *
 */
public class MyArrayStack<E> implements Stack<E> {

    private int capacity;
    private int newCapacity;
    private E[] array;
    private E[] newArray;
    private static int size;
    private static int index;

    public MyArrayStack(int cap) {
        this.capacity = cap;
        this.array = (E[]) new Object[cap];
        this.newCapacity = cap * 2;
    }


    private void resize() {
        newArray = (E[]) new Object[capacity];
        System.arraycopy(array, 0, newArray, 0, array.length);
        array = (E[]) new Object[newCapacity];
        System.arraycopy(newArray, 0, array, 0, newArray.length);
        capacity = newCapacity;
        newCapacity = newCapacity * 2;
    }


    @Override
    public void push(E element) {
        if (size == 0) {
            array[index++] = element;
        } else if (size < array.length) {
            array[index++] = element;
        } else {
            resize();
            array[index++] = element;
        }
        size++;
    }

    @Override
    public E pop() {
        if (size == 0) {
            throw new EmptyStackException();
        }

        E elem = array[index-1];
        array[--index] = null;
        size--;
        return elem;
    }

    public E peek() {
        if (size == 0) {
            throw new EmptyStackException();
        }

        E elem = array[index-1];
        return elem;
    }

    public int search(Object obj) {

        for (int i = index-1; i >= 0; i--) {
            if (array[i].equals(obj)) {
                return i;
            }
        }

        return -1;

    }

    public boolean empty() {
        return size == 0;
    }

    public int getSize() {
        return size;
    }

    public int getArrayLength() {
        return array.length;
    }

    public int getIndex(){
        return index;
    }

    public E[] getArray(){
        return array;
    }

}

class TestArrayStack{
    public static void main(String[] args) {
        MyArrayStack<String> ms = new MyArrayStack<>(10);
        ms.push("a");
        ms.push("b");
        ms.push("c");

        System.out.println(ms.search("a"));
        Object[] arr1 = ms.getArray();
        System.out.println(Arrays.toString(arr1));
        //System.out.println(ms.getSize());
        System.out.println(ms.pop());
        System.out.println(ms.pop());
        System.out.println(ms.pop());
       // System.out.println(ms.getIndex());
       // System.out.println(ms.getSize());
        Object[] arr2 = ms.getArray();
        System.out.println(Arrays.toString(arr2));
    }
}