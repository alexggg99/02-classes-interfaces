package de.fhro.inf.prg3.a02;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * @author Peter Kurfer
 * Created on 10/6/17.
 */
public class SimpleListImpl implements SimpleList, Iterable {

    // TODO: Implement the required methods.

    private static final int INITSIZEDEFAULT = 4;
    private Element[] arr;
    private int size = 0;
    private int arraySize;

    public SimpleListImpl() {
        this.arraySize = INITSIZEDEFAULT;
        this.arr = new Element[INITSIZEDEFAULT];
    }

    public SimpleListImpl(int initSize) {
        this.arraySize = initSize;
        this.arr = new Element[initSize];
    }

    @Override
    public void add(Object o) {
        if (size > this.arr.length - 1) {
            this.restructArray();
        }
        Element el = new Element(o);
        this.arr[size++] = el;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public SimpleList filter(SimpleFilter filter) {
        SimpleList result = new SimpleListImpl(this.arraySize);
        for (int i = 0; i < size; i++) {
            Element elem = arr[i];
            if (filter.include(elem.item)) {
                result.add(elem.item);
            }
        }
        return result;
    }

    private void restructArray() {
        this.arraySize *= 2;
        Element[] tmp = new Element[this.arraySize];
        System.arraycopy(arr,0,tmp,0,arr.length);
        arr = tmp;
    }

    @Override
    public java.util.Iterator iterator() {
        return new SimpleIteratorImpl(0);
    }

    public class Element {
        private Object item;
        private Element next;

        public Element(Object item) {
            this.item = item;
        }

        public Object getItem() {
            return item;
        }
    }

    private class SimpleIteratorImpl implements Iterator {
        int cursor = 0;

        public SimpleIteratorImpl(int index) {
            this.cursor = index;
        }

        @Override
        public boolean hasNext() {
            return cursor != size();
        }

        @Override
        public Object next() {
            try {
            int i = cursor;
            Object next = arr[i];
            cursor = i + 1;
            return next;
            } catch (IndexOutOfBoundsException ex) {
                throw new NoSuchElementException();
            }
        }
    }

}
