package lection5_1;

public class LinkOneTests {
    public static void main(String[] args) throws Exception {
        ListOne<String> l = new ListOne<>();
        l.addItem("a");
        l.addItem("b");
        l.addItem("c");
        int c = l.getFirstOccurence("c");
        l.setCursor(c);
        System.out.println(l.getItem());
//        l.getFirstOccurence(55);
//        System.out.println(l.getItem());
    }
}
