package lection5_2;

import java.util.Date;

public class LinkOneTests {
    public static void main(String[] args) throws Exception {
        ListOne<Person> l = new PersonList();

        l.addItem(new Person("11", "A", "B", new Date(1000)));
        System.out.println("Added 1");
        l.addItem(new Person("12", "A", "B", new Date(1000)));
        System.out.println("Added 2");
        l.addItem(new Person("12", "A", "B", new Date(1000)));
        System.out.println("Added 3");

    }
}
