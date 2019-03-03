package  com.brainacad.Collections;

import java.util.*;

/**
 * @author  Shynkarenko Eduard
 *
 */
public class MyLinkedList  {

    Entry header = new Entry(null, null, null);

    /* index elements equal size*/
    private static int size;

    private class Entry {
        Object element;
        Entry next;
        Entry prev;

        Entry(Object element, Entry next, Entry prev) {
            this.element = element;
            this.next = next;
            this.prev = prev;
        }
    }

    /**
     * Appends the specified element to the end of this list.
     *
     * @param o element to be appended to this list
     * @return {@code true}
     */
    public boolean add(Object o) {

        Entry newEntry = new Entry(o, header, null);

        if (header.prev == null) {
            header.next = newEntry;
            header.prev = newEntry;
            newEntry.prev = header;
        } else {
            newEntry.prev = header.prev;
            header.prev.next = newEntry;
            header.prev = newEntry;
        }
        ++size;
        return true;
    }

    /**
     * Inserts the specified element at the beginning of this list.
     *
     * @param o the element to add
     */
    public void addFirst(Object o) {
        Entry newEntry = new Entry(o, null, header);
        if (header.next != null) {
            newEntry.next = header.next;
            header.next.prev = newEntry;
            header.next = newEntry;
            ++size;
        } else {
            add(o);
        }

    }

    /**
     * Appends the specified element to the end of this list.
     *
     * @param o the element to add
     */
    public void addLast(Object o) {
        add(o);
    }

    /**
     * Inserts the specified element at the specified position in this list.
     * Shifts the element currently at that position (if any) and any
     * subsequent elements to the right (adds one to their indices). Indexing starts with 1
     *
     * @param index index at which the specified element is to be inserted
     * @param o element to be inserted
     * @throws IndexOutOfBoundsException
     */
    public void add(int index, Object o) throws IndexOutOfBoundsException {
        if (index < 1 || index > size) {
            throw new IndexOutOfBoundsException("Wrong index");
        } else if (index == 1) {
            addFirst(o);
        } else {
            Entry newEntry = new Entry(o, null, null);
            Entry obj = searchElement(index);
            newEntry.next = obj;
            newEntry.prev = obj.prev;
            obj.prev.next = newEntry;
            obj.prev = newEntry;
            size++;
        }
    }

    /**
     *
     * Searches for an element with specified index
     */
    private Entry searchElement(int index) {
        if (index < (size / 2)) {
            Entry obj = header;
            for (int i = 0; i < index; i++)
                obj = obj.next;
            return obj;
        } else {
            Entry obj = header;
            for (int i = size; i >= index; i--)
                obj = obj.prev;
            return obj;
        }
    }

    /**
     * Returns the number of elements in this list.
     *
     * @return the number of elements in this list
     */
    public int size() {
        return size;
    }

    /**
     * Returns the element at the specified position in this list. Indexing starts with 1
     *
     * @param index index of the element to return
     * @return the element at the specified position in this list
     * @throws IndexOutOfBoundsException
     */
    public Object get(int index) throws IndexOutOfBoundsException {
        if (index < 1 || index > size) {
            throw new IndexOutOfBoundsException("Wrong index");
        } else {
            return searchElement(index).element;
        }
    }

    /**
     * Retrieves, but does not remove, the head (first element) of this list.
     *
     * @return the head of this list
     * @throws NoSuchElementException if this list is empty
     */
    public Object getFirst() {
        Object obj = searchElement(1).element;
        if(obj == null){
            throw new NoSuchElementException();
        }
        return obj;
    }

    /**
     * Returns the last element in this list.
     *
     * @return the last element in this list
     * @throws NoSuchElementException if this list is empty
     */
    public Object getLast() {
        Object obj =  searchElement(size).element;
        if(obj == null){
            throw new NoSuchElementException();
        }
        return obj;
    }

    /**
     * Removes the first occurrence of the specified element from this list,
     * if it is present.  If this list does not contain the element, it is
     * unchanged.  More formally, removes the element with the lowest index
     * {@code i} such that
     * <tt>(o==null&nbsp;?&nbsp;get(i)==null&nbsp;:&nbsp;o.equals(get(i)))</tt>
     * (if such an element exists).  Returns {@code true} if this list
     * contained the specified element (or equivalently, if this list
     * changed as a result of the call).
     *
     * @param o element to be removed from this list, if present
     * @return {@code true} if this list contained the specified element
     */
    public boolean remove(Object o) {
        if (o == null) {
            if (size == 1 & header.next.element == null) {
                header.next.next = null;
                header.prev.prev = null;
                header.next = null;
                header.prev = null;
                size--;
                return true;
            } else {
                Entry obj = header;
                for (int i = 0; i < size; i++) {
                    obj = obj.next;
                    if (obj.element == null) {
                        obj.prev.next = obj.next;
                        obj.next.prev = obj.prev;
                        obj.prev = obj.next = null;
                        size--;
                        return true;
                    }
                }
            }
        } else {
            if (size == 1 & header.next.element.equals(o)) {
                header.next.next = null;
                header.prev.prev = null;
                header.next.element = null;
                header.next = null;
                header.prev = null;
                size--;
                return true;
            } else {
                Entry obj = header;
                for (int i = 0; i < size; i++) {
                    obj = obj.next;
                    if (obj.element.equals(o)) {
                        obj.prev.next = obj.next;
                        obj.next.prev = obj.prev;
                        obj.prev = obj.next = null;
                        obj.element = null;
                        size--;
                        return true;
                    }
                }
            }
        }
        return false;
    }

    /**
     * Removes and returns the first element from this list.
     *
     * @return the first element from this list
     * @throws NoSuchElementException if this list is empty
     */
    public Object removeFirst() {
        Object oldObj = searchElement(1).element;
        Entry e = searchElement(1);
        if(e == null){
            throw new NoSuchElementException();
        }
        if (size == 1) {
            e.next.next = null;
            e.prev.prev = null;
            e.next = null;
            e.prev = null;
            e.element = null;
            size--;
        } else {
            e.prev.next = e.next;
            e.next.prev = e.prev;
            e.prev = e.next = null;
            e.element = null;
            size--;
        }

        return oldObj;
    }

    /**
     * Removes and returns the last element from this list.
     *
     * @return the last element from this list
     * @throws NoSuchElementException if this list is empty
     */
    public Object removeLast() {
        Object oldObj = searchElement(size).element;
        Entry e = searchElement(size);
        if(e == null){
            throw new NoSuchElementException();
        }
        if (size == 1) {
            e.next = null;
            e.prev = null;
            e.next.next = null;
            e.prev.prev = null;
            e.element = null;
            size--;
        } else {
            e.prev.next = e.next;
            e.prev.prev = e.prev;
            e.next.prev = e.prev;
            e.prev = e.next = null;
            e.element = null;
            size--;
        }

        return oldObj;

    }

    /**
     * Replaces the element at the specified position in this list with the
     * specified element. Indexing starts with 1
     *
     * @param index index of the element to replace
     * @param o element to be stored at the specified position
     * @return the element previously at the specified position
     * @throws IndexOutOfBoundsException
     */
    public Object set(int index, Object o) {
        if(index < 1 | index > size()){
            throw new IndexOutOfBoundsException();
        }
        Object oldObj = searchElement(index).element;
        searchElement(index).element = o;
        return oldObj;
    }

    /**
     * Returns the index of the first occurrence of the specified element
     * in this list, or -1 if this list does not contain the element.
     * More formally, returns the lowest index {@code i} such that
     * <tt>(o==null&nbsp;?&nbsp;get(i)==null&nbsp;:&nbsp;o.equals(get(i)))</tt>,
     * or -1 if there is no such index.
     *
     * @param o element to search for
     * @return the index of the first occurrence of the specified element in
     *         this list, or -1 if this list does not contain the element
     */
    public int indexOf(Object o) {
        if (o == null) {
            Entry obj = header;
            for (int i = 0; i < size; i++) {
                obj = obj.next;
                if (obj.element == null) {
                    return i + 1;
                }
            }
        } else {
            Entry obj = header;
            for (int i = 0; i < size; i++) {
                obj = obj.next;
                if (obj.element.equals(o)) {
                    return i + 1;
                }
            }
        }
        return -1;
    }

    /**
     * Returns the index of the last occurrence of the specified element
     * in this list, or -1 if this list does not contain the element.
     * More formally, returns the highest index {@code i} such that
     * <tt>(o==null&nbsp;?&nbsp;get(i)==null&nbsp;:&nbsp;o.equals(get(i)))</tt>,
     * or -1 if there is no such index.
     *
     * @param o element to search for
     * @return the index of the last occurrence of the specified element in
     *         this list, or -1 if this list does not contain the element
     */
    public int lastIndexOf(Object o) {
        if (o == null) {
            Entry obj = header;
            for (int i = size; i > 0; i--) {
                obj = obj.prev;
                if (obj.element == null) {
                    return i;
                }
            }
        } else {
            Entry obj = header;
            for (int i = size; i > 0; i--) {
                obj = obj.prev;
                if (obj.element.equals(o)) {
                    return i;
                }
            }
        }
        return -1;
    }

    /**
     * Returns an array containing all of the elements in this list
     * in proper sequence (from first to last element).
     *
     * <p>The returned array will be "safe" in that no references to it are
     * maintained by this list.  (In other words, this method must allocate
     * a new array).  The caller is thus free to modify the returned array.
     *
     * @return an array containing all of the elements in this list
     *         in proper sequence
     */
    public Object[] toArray() {
        Object[] array = new Object[size];
        Entry obj = header;
        for (int i = 0; i < size; i++) {
            obj = obj.next;
            array[i] = obj.element;
        }

        return array;
    }

    /**
     * Retrieves, but does not remove, the head (first element) of this list.
     *
     * @return the head of this list
     * @throws NoSuchElementException if this list is empty
     */
    public Object element()throws NullPointerException{
        if(header.next == null){
            throw new NullPointerException("There is no elements");
        }

        return header.next.element;
    }

    /**
     * Returns {@code true} if this list contains the specified element.
     * More formally, returns {@code true} if and only if this list contains
     * at least one element {@code e} such that
     * <tt>(o==null&nbsp;?&nbsp;e==null&nbsp;:&nbsp;o.equals(e))</tt>.
     *
     * @param o element whose presence in this list is to be tested
     * @return {@code true} if this list contains the specified element
     */
    public boolean contains(Object o){
        if (o == null) {
            Entry obj = header;
            for (int i = 0; i <size; i++) {
                obj = obj.next;
                if (obj.element == null) {
                    return true;
                }
            }
        } else {
            Entry obj = header;
            for (int i = 0; i<size; i++) {
                obj = obj.next;
                if (obj.element.equals(o)) {
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * Removes all of the elements from this list.
     * The list will be empty after this call returns.
     */
    public void clear(){
        header.next.prev=null;
        header.next=null;
        header.prev.next=null;
        header.prev = null;
        size=0;
    }

}
