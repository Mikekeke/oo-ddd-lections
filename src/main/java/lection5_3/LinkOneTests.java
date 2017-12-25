package lection5_3;

import java.util.Date;

public class LinkOneTests {
    public static void main(String[] args) throws Exception {
        ListOne<Person> l = new ListOne<>();
        try {
            System.out.println("Should throw IllegalStateException:");
            l.addItem(new Person("11", "A", "B", new Date(1000)));
        } catch (IllegalStateException e) {
            System.out.println(e.toString());
        }

        Person.setList(l);

        l.addItem(new Person("11", "A", "B", new Date(1000)));
        System.out.println("Added 1");
        l.addItem(new Person("12", "A", "B", new Date(1000)));
        System.out.println("Added 2");
        try {
            System.out.println("Should throw IllegalAccessException:");
            l.addItem(new Person("11", "A", "B", new Date(1000)));
        } catch (IllegalAccessException e) {
            System.out.println(e.toString());
        }
        System.out.println("Done");
    }
}
