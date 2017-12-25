package lection5_3;

class Person {
    private String snils; //UNIQUE !!!
    private String fam;
    private String nam;
    private java.util.Date birthday;

    private static ListOne<Person> mList;

    static void setList(ListOne<Person> list) {
        mList = list;
    }

    public Person(String snils, String fam, String nam, java.util.Date birthday) throws Exception {
        if (mList == null) {
            throw new IllegalStateException("List need to be set fits");
        }


        this.snils = snils;
        this.fam = fam;
        this.nam = nam;
        this.birthday = birthday;

        if (mList.hasItem(this)) {
            throw new IllegalAccessException("Person with same SNILS already in list");
        }
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

//public class PersonList extends ListOne<Person> {
//
//    @Override
//    public void addItem(Person item) throws Exception {
//        if (!hasPerson(item)) {
//            super.addItem(item);
//        } else {
//            throw new Exception("СНИЛС уже есть");
//        }
//    }
//
//    @Override
//    public void setItem(Person item) throws Exception {
//        super.setItem(item);
//    }
//
//    @Override
//    public boolean insertItem(int i, Person item) throws Exception {
//        return super.insertItem(i, item);
//    }
//}
