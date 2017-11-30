package lection3.src;

import java.util.TreeMap;

class XFactory<K,E>{
    public static final String None = "None";
    private TreeMap<K,E> map;
    public XFactory(TreeMap<K,E> map){
        this.map = map;
    }
    public E getOrNull(K key) {
        E e = map.get(key);
        if(e==null) return null;
        try {
            return (E) e.getClass().newInstance();
        } catch (InstantiationException | IllegalAccessException e1) {
            return null;
        }
    }
}
