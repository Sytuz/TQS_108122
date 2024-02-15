package tqs.lab1;

import java.util.LinkedList;

public class TqsStack<T> {
    private LinkedList<T> collection;
    private int maxSize;

    public TqsStack() {
        this.collection = new LinkedList<T>();
    }

    public TqsStack(int maxSize) {
        this.collection = new LinkedList<T>();
        this.maxSize = maxSize;
    }

    public T pop() {
        T item = collection.removeLast();
        return item;
    }

    public int size() {
        return collection.size();
    }

    public T peek() {
        return collection.getLast();
    }

    public void push(T item) {
        if (maxSize > 0 && collection.size() == maxSize) {
            throw new IllegalStateException("Stack is full");
        }
        else {
            collection.add(item);
        }
    }

    public boolean isEmpty() {
        return collection.isEmpty();
    }
}
