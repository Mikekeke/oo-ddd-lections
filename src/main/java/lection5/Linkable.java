package lection5;

public class Linkable<E> {
    private E item;
    private Linkable<E> left, right;
    public Linkable(Linkable<E> left, E item, Linkable<E> right){
        this.item = item;
        this.right = right;
    }

    public void setItem(E item) {
        this.item  = item;
    }

    public E getItem() {
        return item;
    }

    public Linkable<E> getRight() {
        return right;
    }

    public void setRight(Linkable<E> right) {
        this.right = right;
    }

    public Linkable<E> getLeft() {
        return left;
    }

    public void setLeft(Linkable<E> left) {
        this.left = left;
    }
}
