package com.brainacad.Collections.Dictionary;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.Map;
import java.util.NoSuchElementException;

/**
 * @author  Shynkarenko Eduard
 *
 */
public class Dictionary {

    private Bucket[] buckets;
    private static int size;
    private static int countBucket = 0;
    private Bucket.Node<String, String> currentNode;
    private Bucket.Node<String, String> firstNode;

    public Dictionary(int size) {
        this.buckets = new Bucket[size];
        Dictionary.size = 0;
    }

    public Dictionary() {
        this.buckets = new Bucket[16];
        Dictionary.size = 0;
    }


    public Dictionary(Map<String, String> map) {
        for (Map.Entry entry : map.entrySet()) {
            String key = (String) entry.getKey();
            String value = (String) entry.getValue();
            add(key, value);
        }
    }

    public void add(String key, String... value) {
        if (size >= buckets.length) {
            Bucket[] tempBuckets = buckets;
            buckets = new Bucket[buckets.length * 2];
            System.arraycopy(tempBuckets, 0, buckets, 0, tempBuckets.length);
        }

        int index = getIndex(key);

        if (buckets[index] == null) {
            buckets[index] = new Bucket(key, value);
            size++;
        } else if (buckets[index].getNodeByKey(key) == null) {
            buckets[index].add(key, value);
        } else {
            buckets[index].addValues(key, value);
        }
    }

    public void addMap(Map<String, String> map) {
        for (Map.Entry entry : map.entrySet()) {
            String key = (String) entry.getKey();
            String value = (String) entry.getValue();
            add(key, value);
        }
    }

    public void addAll(Dictionary src) {
        for (Bucket entry : src.buckets) {
            if(entry== null){
                continue;
            }
            Bucket.Node<String, String> node = entry.getFirstNode();
            while (node != null) {
                add(node.getKey(), node.getValue());
                node = node.getNext();
            }
        }
    }

    public void removeAll(String[] keys) {
        for (int i = 0; i < keys.length; i++) {
            remove(keys[i]);
        }
    }

    public String[] get(String key) {
        String[] oldValue = null;
        if (key == null) {
            Bucket entry = buckets[0];
            oldValue = entry.getValueByKey(key);
        } else {
            int index = getIndex(key);
            Bucket entry = buckets[index];
            oldValue = entry.getValueByKey(key);
        }
        return oldValue;
    }

    public String[] remove(String key) {
        String[] oldValue = null;
        if (key == null) {
            Bucket entry = buckets[0];
            Bucket.Node<String, String> node = entry.getFirstNode();
            Bucket.Node<String, String> prev = entry.getHeader();

            while (node != null) {
                if (key == node.getKey()) {
                    if (prev == entry.getHeader()) {
                        if (node.getNext() == null) {
                            buckets[0] = null;
                            --size;
                        }
                        entry.getHeader().setNext(node.getNext());
                        oldValue = node.getValue();
                        node = entry.getHeader().getNext();
                        continue;
                    } else {
                        prev.setNext(node.getNext());
                        oldValue = node.getValue();
                        node = prev.getNext();
                        continue;
                    }
                }
                prev = prev.getNext();
                node = node.getNext();
            }
        } else {
            int index = getIndex(key);
            Bucket entry = buckets[index];
            Bucket.Node<String, String> node = entry.getFirstNode();
            Bucket.Node<String, String> prev = entry.getHeader();

            while (node != null) {
                if (key.equals(node.getKey())) {
                    if (prev == entry.getHeader()) {
                        if (node.getNext() == null) {
                            buckets[index] = null;
                            --size;
                        }
                        entry.getHeader().setNext(node.getNext());
                        oldValue = node.getValue();
                        node = entry.getHeader().getNext();
                        continue;
                    } else {
                        prev.setNext(node.getNext());
                        oldValue = node.getValue();
                        node = prev.getNext();
                        continue;
                    }
                }
                prev = prev.getNext();
                node = node.getNext();
            }

        }
        return oldValue;
    }

    public boolean equals(Object src) {
        if (!(src instanceof Dictionary)) {
            return false;
        } else if ((size !=((Dictionary)src).getSize()) & (buckets.length != ((Dictionary)src).getBuckets().length)) {
            return false;
        }

        return true;
    }

    public void addValuesByKey(String key, String... values) {
        int index = getIndex(key);
        buckets[index].addValues(key, values);
    }

    public String[] getValue(String key) {
        int index = getIndex(key);
        return buckets[index].getValueByKey(key);
    }

    private int getIndex(String key) {
        int hash = key.hashCode();
        int index = hash % buckets.length;
        return index;
    }

    public Bucket.Node<String, String> next() {
        return currentNode = currentNode.getNext();
    }


    public boolean hasNext() {
        if (firstNode == null) {
            for (int i = 0; i < buckets.length; i++) {
                if (buckets[i] != null) {
                    firstNode = currentNode = buckets[i].getHeader();
                    countBucket = i;
                    return true;
                }
            }
        } else if (currentNode.getNext() != null) {
            return true;
        } else {
            for (int i = countBucket + 1; i < buckets.length; i++) {
                if (buckets[i] != null) {
                    countBucket = i;
                    currentNode = buckets[countBucket].getHeader();
                    return true;
                }
            }
        }

        firstNode = null;
        return false;
    }


    public void remove() {
        if (currentNode == null) {
            throw new IllegalStateException();
        }
        remove(currentNode.getKey());
    }

    public int getSize() {
        return size;
    }

    public Bucket[] getBuckets() {
        return buckets;
    }
}



