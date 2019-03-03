package com.brainacad.Collections;

import java.util.*;

/**
 * @author  Shynkarenko Eduard
 * Created  on 26.01.2016.
 */
public class MyArrayList extends AbstractList<Object> implements List<Object> {

    private int capacity = 10;
    private int maxCapacity = Integer.MAX_VALUE;
    private int newCapacity;
    private static int size = 0;
    private Object[] array;
    private Object[] newArray;

    /**
     * Constructs an empty list with  initial capacity 10.
     *
     */
    public MyArrayList() {
        this.array = new Object[capacity];
        this.newCapacity = ((capacity * 3) / 2) + 1;
    }

    /**
     * Constructs an empty list with the specified initial capacity.
     *
     * @param  cap  the initial capacity of the list
     * @throws IllegalArgumentException if the specified initial capacity
     *         is negative
     */
    public MyArrayList(int cap) throws IllegalArgumentException {
        if (cap > 0 & cap < Integer.MAX_VALUE) {
            this.array = new Object[cap];
            this.capacity = cap;
            this.newCapacity = ((cap * 3) / 2) + 1;
        } else if (cap == 0) {
            this.array = new Object[0];
            this.capacity = 0;
        } else {
            throw new IllegalArgumentException("Illegal Capacity: " + cap);
        }
    }

    private void resize() {
        newArray = new Object[capacity];
        System.arraycopy(array, 0, newArray, 0, array.length);
        array = new Object[newCapacity];
        System.arraycopy(newArray, 0, array, 0, newArray.length);
        capacity = newCapacity;
        newCapacity = ((capacity * 3) / 2) + 1;
    }

    @Override
    /**
     * Returns <tt>true</tt> if this list contains no elements.
     *
     * @return <tt>true</tt> if this list contains no elements
     */
    public boolean isEmpty() {
        return size == 0;

    }

    @Override
    /**
     * Returns the number of elements in this list.
     *
     * @return the number of elements in this list
     */
    public int size() {
        return size;
    }


    @Override
    /**
     * Returns the element at the specified position in this list.
     *
     * @param  index index of the element to return
     * @return the element at the specified position in this list
     * @throws IndexOutOfBoundsException
     */
    public Object get(int index) throws IndexOutOfBoundsException {
        if (index < 0 || index > size - 1) {
            throw new IndexOutOfBoundsException("Wrong index");
        } else {
            return array[index];
        }
    }

    @Override
    /**
     * Appends the specified element to the end of this list.
     *
     * @param o element to be appended to this list
     * @return <tt>true</tt>
     */
    public boolean add(Object o) {
        if (size < array.length) {
            array[size++] = o;
        } else {
            resize();
            array[size++] = o;
        }
        return true;
    }

    @Override
    /**
     * Inserts the specified element at the specified position in this
     * list. Shifts the element currently at that position (if any) and
     * any subsequent elements to the right (adds one to their indices).
     *
     * @param index index at which the specified element is to be inserted
     * @param o element to be inserted
     * @throws IndexOutOfBoundsException
     */
    public void add(int index, Object o) throws IndexOutOfBoundsException {
        if (index < 0 || index > size - 1) {
            throw new IndexOutOfBoundsException("Wrong index");
        } else if (size < array.length) {
            System.arraycopy(array, index, array, index + 1, size - index);
            array[index] = o;
            size++;
        } else {
            resize();
            System.arraycopy(array, index, array, index + 1, size - index);
            array[index] = o;
            size++;
        }

    }

    @Override
    /**
     * Removes the element at the specified position in this list.
     * Shifts any subsequent elements to the left (subtracts one from their
     * indices).
     *
     * @param index the index of the element to be removed
     * @return the element that was removed from the list
     * @throws IndexOutOfBoundsException
     */
    public Object remove(int index) throws IndexOutOfBoundsException {
        Object obj;
        if (index < 0 || index > size - 1) {
            throw new IndexOutOfBoundsException("Wrong index");
        } else {
            obj = array[index];
            System.arraycopy(array, index + 1, array, index, size - index - 1);
            array[size - 1] = null;
            size--;
        }
        return obj;
    }

    @Override
    /**
     * Removes the first occurrence of the specified element from this list,
     * if it is present.  If the list does not contain the element, it is
     * unchanged.  More formally, removes the element with the lowest index
     * <tt>i</tt> such that
     * <tt>(o==null&nbsp;?&nbsp;get(i)==null&nbsp;:&nbsp;o.equals(get(i)))</tt>
     * (if such an element exists).  Returns <tt>true</tt> if this list
     * contained the specified element (or equivalently, if this list
     * changed as a result of the call).
     *
     * @param o element to be removed from this list, if present
     * @return <tt>true</tt> if this list contained the specified element
     */
    public boolean remove(Object o) {
        if (o == null) {
            for (int i = 0; i < size; i++) {
                if (array[i] == null) {
                    System.arraycopy(array, i + 1, array, i, size - i - 1);
                    array[size - 1] = null;
                    size--;
                    return true;
                }
            }
        } else {
            for (int i = 0; i < size; i++) {
                if (array[i].equals(o)) {
                    System.arraycopy(array, i + 1, array, i, size - i - 1);
                    array[size - 1] = null;
                    size--;
                    return true;
                }
            }
        }
        return false;

    }

    @Override
    /**
     * Removes all of the elements from this list.  The list will
     * be empty after this call returns.
     */
    public void clear() {
        Arrays.fill(array, null);
        size = 0;
    }

    @Override
    /**
     * Replaces the element at the specified position in this list with
    * the specified element.
    *
    * @param index index of the element to replace
    * @param o element to be stored at the specified position
    * @return the element previously at the specified position
    * @throws IndexOutOfBoundsException
    */
    public Object set(int index, Object o) throws IndexOutOfBoundsException {
        Object obj;
        if (index < 0 || index > size - 1) {
            throw new IndexOutOfBoundsException("Wrong index");
        }
        obj = array[index];
        array[index] = o;
        return obj;
    }

    @Override
    /**
     * Returns the index of the first occurrence of the specified element
     * in this list, or -1 if this list does not contain the element.
     * More formally, returns the lowest index <tt>i</tt> such that
     * <tt>(o==null&nbsp;?&nbsp;get(i)==null&nbsp;:&nbsp;o.equals(get(i)))</tt>,
     * or -1 if there is no such index.
     * @param o element to search for
     * @return the index of the first occurrence of the specified element in this list, or -1 if this list does not contain the element
     */
    public int indexOf(Object o) {
        if (o == null) {
            for (int i = 0; i < size; i++) {
                if (array[i] == null) {
                    return i;
                }
            }
        } else {
            for (int i = 0; i < size; i++) {
                if (array[i].equals(o)) {
                    return i;
                }
            }
        }
        return -1;
    }

    @Override
    /**
     * Returns the index of the last occurrence of the specified element
     * in this list, or -1 if this list does not contain the element.
     * More formally, returns the highest index <tt>i</tt> such that
     * <tt>(o==null&nbsp;?&nbsp;get(i)==null&nbsp;:&nbsp;o.equals(get(i)))</tt>,
     * or -1 if there is no such index.
     * @param o element to search for
     * @return the index of the first occurrence of the specified element in this list, or -1 if this list does not contain the element
     */
    public int lastIndexOf(Object o) {
        if (o == null) {
            for (int i = size - 1; i >= 0; i--) {
                if (array[i] == null) {
                    return i;
                }
            }
        } else {
            for (int i = size - 1; i >= 0; i--) {
                if (array[i].equals(o)) {
                    return i;
                }
            }
        }
        return -1;
    }

    @Override
    /**
     * Returns <tt>true</tt> if this list contains the specified element.
     * More formally, returns <tt>true</tt> if and only if this list contains
     * at least one element <tt>e</tt> such that
     * <tt>(o==null&nbsp;?&nbsp;e==null&nbsp;:&nbsp;o.equals(e))</tt>.
     *
     * @param o element whose presence in this list is to be tested
     * @return <tt>true</tt> if this list contains the specified element
     */
    public boolean contains(Object o) {
        if (o == null) {
            for (int i = 0; i < size; i++) {
                if (array[i] == null) {
                    return true;
                }
            }
        } else {
            for (int i = 0; i < size; i++) {
                if (array[i].equals(o)) {
                    return true;
                }
            }
        }
        return false;
    }

    @Override
    /**
     * Returns an array containing all of the elements in this list
     * in proper sequence (from first to last element).
     *
     * <p>The returned array will be "safe" in that no references to it are
     * maintained by this list.  (In other words, this method must allocate
     * a new array).  The caller is thus free to modify the returned array.
     *
     * @return an array containing all of the elements in this list in
     *         proper sequence
     */
    public Object[] toArray() {
        Object[] copyArray = new Object[capacity];
        System.arraycopy(array, 0, copyArray, 0, size - 1);
        return copyArray;
    }

    private int length() {
        return array.length;
    }
}


























