package stack_queue;

import java.util.ArrayList;
import java.util.List;

class Stack<T> {
    private List<T> inner = new ArrayList<>();

    public void push(T element){
        inner.add(element);
    }

    public T pop(){
        return inner.remove(inner.size() - 1);
    }
}

class Queue<T> {
    private List<T> inner = new ArrayList<>();

    public void put(T element){
        inner.add(element);
    }

    public T get(){
        return inner.remove(0);
    }
}

class TestStackQueue {
    public static void main(String[] args) {
        Stack<String> sStack = new Stack<>();
        sStack.push("One");
        sStack.push("Two");
        System.out.println("Stack: " + sStack.pop());
        Queue<String> qQueue = new Queue<>();
        qQueue.put("One");
        qQueue.put("Two");
        System.out.println("Queue: " + qQueue.get());

    }
}