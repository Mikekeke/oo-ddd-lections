package lection5_2;

class Person {
    private String snils; //UNIQUE !!!
    private String fam;
    private String nam;
    private java.util.Date birthday;

    public Person(String snils, String fam, String nam, java.util.Date birthday) throws Exception {
        this.snils = snils;
        this.fam = fam;
        this.nam = nam;
        this.birthday = birthday;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }

        if (obj == null) {
            return false;
        }

        if (obj instanceof Person) {
            return this.snils.equals(((Person) obj).snils);
        }

        return false;
    }
}

public class PersonList extends ListOne<Person> {

    public boolean hasPerson(Person other) throws Exception {
        if (getFirstOccurence(other) == -1) {
            return false;
        } else {
            return true;
        }
    }

    @Override
    public void addItem(Person item) throws Exception {
        if (!hasPerson(item)) {
            super.addItem(item);
        } else {
            throw new Exception("СНИЛС уже есть");
        }
    }

    @Override
    public void setItem(Person item) throws Exception {
        super.setItem(item);
    }

    @Override
    public boolean insertItem(int i, Person item) throws Exception {
        return super.insertItem(i, item);
    }
}
