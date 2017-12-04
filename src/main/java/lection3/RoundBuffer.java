package lection3;

import java.lang.reflect.Array;


public class RoundBuffer<T> {

    private T[] buffer;
    private int begin, end, cursor = 0;
    public final int size;

    public RoundBuffer(Class<T> cls, int size) {
        buffer = (T[]) Array.newInstance(cls, size);
        this.size = size;
    }

    public int getCursor() {
        return cursor;
    }

    public void cPrev() {
        if (isFirst()) throw new IllegalStateException("No prev");
        if (cursor > 0) {
            cursor--;
        } else {
            cursor = size - 1;
        }
    }

    public void cNext() {
        if (isLast()) throw new IllegalStateException("No next");
        if (cursor < size) {
            cursor++;
        } else {
            cursor = 0;
        }
    }

    public void put(T val) {
        end++;
        if (end == size) {
            end = 0;
        }
        cursor++;
        if (cursor == size) {
            cursor = 0;
        }
        if (end == begin) {
            begin++;
            if (begin == size) {
                begin = 0;
            }
        }
        buffer[cursor] = val;
    }

    public T get() {
        if (isEmpty()) throw new IllegalStateException("Empty");
        return buffer[cursor];
    }

    public boolean isEmpty() {
        return begin == end && cursor == begin;
    }

    public boolean isFirst() {
        return cursor == begin;
    }

    public boolean isLast() {
        return cursor == end;
    }

    public void print() {
        for (int i = 0; i < size; i++) {
            System.out.println(i + "=Val(" + buffer[i] + ")");
        }
        System.out.println("begin=" + begin);
        System.out.println("end=" + end);
        System.out.println("cursor at " + cursor);
    }
}

class NoCommandException extends Exception {
    public NoCommandException(String message) {
        super(message);
    }
}

class CommandQueue extends RoundBuffer<Command> {

    public CommandQueue(int size) {
        super(Command.class, size);
    }

    public Command getUndo() throws NoCommandException {
        if (isFirst()) throw new NoCommandException("Nothing to undo");
        Command c = get();
        cPrev();
        return c;
    }

    public Command getRedo() throws NoCommandException {
        if (isLast()) throw new NoCommandException("No commands to redo");
        cNext();
        return get();
    }
}

class Tests {
    public static void main(String[] args) {
        RoundBuffer<Integer> buf = new RoundBuffer<>(Integer.class, 4);
        int n = 15;
        for (int i = 0; i < n; i++) {
            buf.put(i);
        }
        System.out.println(buf.get());
        buf.print();
        buf.cPrev();
        buf.cPrev();
        System.out.println("c at " + buf.getCursor());
        System.out.println(buf.get());
        buf.cNext();
        System.out.println("c at " + buf.getCursor());
        System.out.println(buf.get());
    }
}