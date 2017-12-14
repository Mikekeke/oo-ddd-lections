package lection5;

public class ListOne <E> {
    private Linkable<E> first;
    private Linkable<E> last;
    private int count;

    private int cursor;
    private int beforeFirst;
    private int afterLast;
    private Linkable<E> currentItem;
    private Linkable<E> beforeItem; //список односвязный

    public ListOne() {
        last = new Linkable<E>(null, null, null);
        first = new Linkable<E>(null, null, last);
        count = 0;

        beforeFirst = 0;
        afterLast = 1;
        cursor = 0;
        currentItem = null;
        beforeItem = null;
    }

    public void addItem(E item) {
        Linkable<E> tmp = new Linkable<>(null,null);
        last.setItem(item);
        last.setRight(tmp);
        last = tmp;
        count++;
        afterLast=count+1;
    }

    public int getCount() {
        return count;
    }

    public boolean isEmpty() {
        return (count == 0);
    }


    public void setCursor(int i) throws Exception {
        if ((i < 0) || (i > count+1)) throw new Exception("индекс за пределами диапазона");/*return null;*///Предусловие
        cursor = i;
        currentItem  = first;
        beforeItem = null;
        for (int j = 0; j < cursor; j++){
            beforeItem = currentItem;
            currentItem = currentItem.getRight();
        }
    }

    public boolean isValid(){
        return ((cursor!=beforeFirst)&&(cursor!=afterLast));
    }

    public void goForth() throws Exception {
        if(cursor==afterLast){
            throw new Exception("Дальше вправо некуда");
        }
        cursor++;
        beforeItem = beforeItem.getRight();
        currentItem = beforeItem.getRight();
    }

    public void goBack() throws Exception {
        if(cursor==beforeFirst){
            throw new Exception("Дальше влевво некуда");
        }
        cursor--;
        currentItem  = first;
        beforeItem = null;
        for (int j = 0; j < cursor; j++){ //список односвязный и поэтому дублирование
            beforeItem = currentItem;
            currentItem = currentItem.getRight();
        }
    }

    public E getItem() throws Exception {
        if(!isValid()) throw new Exception("Недействительная позиция");
        return currentItem.getItem();
    }

    public void setItem(E item) throws Exception {
        if(!isValid()) throw new Exception("Недействительная позиция");
        currentItem.setItem(item);
    }


    public boolean insertItem(int i, E item) throws Exception {
        if ((i < 1) || (i > count)) throw new Exception("индекс за пределами диапазона");/*return false;*///Предусловие
        Linkable<E> e = new Linkable<E>(item,currentItem);
        beforeItem.setRight(e);
        currentItem = e;
        count++;
        afterLast=count+1;
        return true;
    }

    public void removeItem() throws Exception {
        if(!isValid()) throw new Exception("Недействительная позиция");
        currentItem=currentItem.getRight();
        beforeItem.setRight(currentItem);
        count--;
        afterLast=count-1;
    }

    public void getFirstOccurence(E item) throws Exception {
        if (count == 0) throw new Exception("список пуст"); /*return 0;*/
        int cursor = 1;
        Linkable<E> e = first.getRight();
        while (!(e.getItem().equals(item))) {
            e = e.getRight();
            cursor++;
            if (cursor == count+1) break;
        }
    }
}
