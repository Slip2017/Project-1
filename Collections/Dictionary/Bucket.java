package com.brainacad.Collections.Dictionary;


/**
 * @author  Shynkarenko Eduard
 *
 */
public class Bucket {

    private Node<String, String> head = new Node<>();

     class Node <T, K> {
        private K key;
        private T[] value;
        private Node next;

        public Node() {
            this.next = this;
        }

        public Node(K key, T[] value) {
            this.key = key;
            this.value = value;
        }

        public Node(K key, T[] value, Node next) {
            this.key = key;
            this.value = value;
            this.next = next;
        }

        public void addValues(T[] value) {
            T[] temp = this.value;
            this.value = (T[]) new Object[temp.length + value.length];
            System.arraycopy(temp, 0, this.value, 0, temp.length);
            System.arraycopy(value, 0, this.value, temp.length, value.length);
        }

        public K getKey() {
            return key;
        }

        public void setKey(K key) {
            this.key = key;
        }

        public T[] getValue() {
            return value;
        }

        public void setValue(T[] value) {
            this.value = value;
        }

        public Node<String, String> getNext() {
            return next;
        }

        public void setNext(Node next) {
            this.next = next;
        }
    }


    Bucket(String key, String... value) {
        Node<String, String> node = new Node<>(key, value);
        head.setNext(node);
    }

    public void add(String key, String[] value) {
        Node<String, String> node = new Node<>(key, value, getFirstNode());
        head.setNext(node);

    }

    public void addValues(String key, String[] values) {
        Node<String, String> node = getNodeByKey(key);
        node.addValues(values);
    }

    public Node<String, String> getFirstNode() {
        return head.getNext();
    }

    public Node<String, String> getHeader() {
        return head;
    }

    public String[] getValueByKey(String key) {
        Node<String, String> node = getNodeByKey(key);
        return node == null ? null : node.getValue();
    }

    public Node<String, String> getNodeByKey(String key) {
        Node<String, String> node = head.getNext();
        while (node != null) {
            if (key.equals(node.getKey())) {
                return node;
            }
            node = node.getNext();
        }
        return null;
    }


}
