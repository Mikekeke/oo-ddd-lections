package lection5_2;

public class ListOne<E> {
    private Linkable<E> first;
    private Linkable<E> last;
    private int count;

    private int cursor;
    private int beforeFirst;
    private int afterLast;
    private Linkable<E> currentItem;

    public ListOne() {
        last = new Linkable<E>(first, null, null);
        first = new Linkable<E>(null, null, last);
        count = 0;

        beforeFirst = 0;
        afterLast = 1;
        cursor = 0;
        currentItem = null;
    }

    public void addItem(E item) throws Exception {
        Linkable<E> tmp = new Linkable<>(null, null, null);
        tmp.setLeft(last);
        last.setItem(item);
        last.setRight(tmp);
        last = tmp;
        count++;
        afterLast = count + 1;
    }

    public int getCount() {
        return count;
    }

    public boolean isEmpty() {
        return (count == 0);
    }


    public void setCursor(int i) throws Exception {
        if ((i < 0) || (i > count + 1))
            throw new Exception("индекс за пределами диапазона");/*return null;*///Предусловие
        cursor = i;
        currentItem = first;
        for (int j = 0; j < cursor; j++) {
            currentItem = currentItem.getRight();
        }
    }

    public boolean isValid() {
        return ((cursor != beforeFirst) && (cursor != afterLast));
    }

    public void goForth() throws Exception {
        if (cursor == afterLast) {
            throw new Exception("Дальше вправо некуда");
        }
        cursor++;
        currentItem = currentItem.getRight();
    }

    public void goBack() throws Exception {
        if (cursor == beforeFirst) {
            throw new Exception("Дальше влево некуда");
        }
        cursor--;
        currentItem = currentItem.getLeft();
    }

    public E getItem() throws Exception {
        if (!isValid()) throw new Exception("Недействительная позиция");
        return currentItem.getItem();
    }

    public void setItem(E item) throws Exception {
        if (!isValid()) throw new Exception("Недействительная позиция");
        currentItem.setItem(item);
    }


    public boolean insertItem(int i, E item) throws Exception {
        if ((i < 1) || (i > count)) throw new Exception("индекс за пределами диапазона");/*return false;*///Предусловие
        Linkable<E> e = new Linkable<E>(currentItem.getLeft(), item, currentItem);
        currentItem.setLeft(e);
        currentItem = e;
        count++;
        afterLast = count + 1;
        return true;
    }

    public void removeItem() throws Exception {
        if (!isValid()) throw new Exception("Недействительная позиция");
        currentItem = currentItem.getRight();
        count--;
        afterLast = count - 1;
    }

    public int getFirstOccurence(E item) throws Exception {
        if (count == 0) return -1;
        int cursor = 1;
        Linkable<E> e = first.getRight();
        while (!(e.getItem().equals(item))) {
            e = e.getRight();
            cursor++;
            if (cursor == count + 1) break;
        }
        if (cursor == beforeFirst || cursor == afterLast) {
            return -1;
        } else {
            return cursor;

        }

    }
}
