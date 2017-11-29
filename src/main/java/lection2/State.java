package lection2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

class Answer{

}

abstract class State{
    protected final String ESC="\033[";
    protected Answer answer;
    private int choice;
    private BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
    protected int history;
    private int[] transitions;

    public void setTransitions(int[] transitions) {
        this.transitions= transitions;
    }


    public void read(){
        answer = new Answer();
    }

    public boolean correct(){
        if(answer==null)
            return false;
        else
            return true;
    }

    public void process(){

    }

    public void message(){

    }

    abstract public void display();

    public void makeChoice() throws IOException {
        String s = bf.readLine();
        choice = Integer.valueOf(s);
        //Не будем делать защиту от неправильного выбора
        //Будем полагать, что пользователь вводит только те числа,
        //что указаны на экране в качестве вариантов перехода
        //Эта проблема решается за счет использования словарей,
        //исключающих недопустимый выбор
    }

    public void execute(int prevState) throws IOException{
        history = prevState;
        boolean res;
        display();
        do {
            read();
            res = correct();
            if (!res) message();
        }while(!res);
        process();
        makeChoice();
    }

    public int getChoice(){
        return choice;
    }
}

class Register extends State{
    public void display(){
        System.out.print(ESC+"2J");
        System.out.println("Система резервирования билетов");
        System.out.println();System.out.println();
        System.out.println("логин    ХХХХХХХХХ");
        System.out.println("пароль   ХХХХХХХХХ");
        System.out.println();System.out.println();
        System.out.println();System.out.println();
        System.out.println("7 Помощь   2 Выбор рейса  5 Подтвержение 0 Выход");
    }
}

class Help extends State{
    public void display(){
        System.out.print(ESC+"2J");
        System.out.println("HELP how to use the system");
        System.out.println();System.out.println();
        System.out.println("ХХХХХХХХХХХХХХХХXXXXXXXXXXXXXX");
        System.out.println("ХХХХХХХХХХХХХХХХXXXXXXXXXXXXXX");
        System.out.println("ХХХХХХХХХХХХХХХХXXXXXXXXXXXXXX");
        System.out.println("ХХХХХХХХХХХХХХХХXXXXXXXXXXXXXX");
        System.out.println("ХХХХХХХХХХХХХХХХXXXXXXXXXXXXXX");
        System.out.println("ХХХХХХХХХХХХХХХХXXXXXXXXXXXXXX");
        System.out.println();System.out.println();
        System.out.println("Любое число - возврат к предыдущему состоянию");
    }

    @Override
    public int getChoice() {
        return history;
    }
}

class RegisterHelp extends Help{
    public void display(){
        System.out.print(ESC+"2J");
        System.out.println("HELP how to use the system");
        System.out.println();System.out.println();
        System.out.println("ХХХХХХХХХХХХХХХХXXXXXXXXXXXXXX");
        System.out.println("ХХХХХХХ REGISTER HELP XXXXXXXX");
        System.out.println("ХХХХХХХХХХХХХХХХXXXXXXXXXXXXXX");
        System.out.println();System.out.println();
        System.out.println("Любое число - возврат к предыдущему состоянию");
    }

    @Override
    public int getChoice() {
        return history;
    }
}

class Flights extends State{
    public void display(){
        System.out.print(ESC+"2J");
        System.out.println("Из ХХХХХХХХ   В ХХХХХХХХ");
        System.out.println();System.out.println();
        System.out.println("Дата вылета с ХХ.ХХ.ХХ   по ХХ.ХХ.ХХ");
        System.out.println();System.out.println();
        System.out.println("№ рейса Дата и время вылета Дата и время прибытия");
        System.out.println("1 ХХХХХ    ХХ.ХХ.ХХ ХХ:ХХ       ХХ.ХХ.ХХ ХХ:ХХ");
        System.out.println("2 ХХХХХ    ХХ.ХХ.ХХ ХХ:ХХ       ХХ.ХХ.ХХ ХХ:ХХ");
        System.out.println("3 ХХХХХ    ХХ.ХХ.ХХ ХХ:ХХ       ХХ.ХХ.ХХ ХХ:ХХ");
        System.out.println("4 ХХХХХ    ХХ.ХХ.ХХ ХХ:ХХ       ХХ.ХХ.ХХ ХХ:ХХ");
        System.out.println();System.out.println();
        System.out.println("6 Помощь   3 Выбор мест  1 Начальный экран 0 Выход");
    }
}

class Seats extends State{
    public void display(){
        System.out.print(ESC+"2J");
        System.out.println("Ваш рейс");
        System.out.println("№ рейса Дата и время вылета Дата и время прибытия");
        System.out.println(" ХХХХХ    ХХ.ХХ.ХХ ХХ:ХХ       ХХ.ХХ.ХХ ХХ:ХХ");
        System.out.println();System.out.println();
        System.out.println(" Класс    Цена   В наличии");
        System.out.println(" Эконом   $XXXX      XX");
        System.out.println(" Бизнес   $XXXX      XX");
        System.out.println();System.out.println();
        System.out.println("6 Помощь   4 Резервирование  2 Выбор рейса");
    }
}

class Reserve extends State{
    public void display(){
        System.out.print(ESC+"2J");
        System.out.println("Ваш рейс");
        System.out.println("№ рейса Дата и время вылета Дата и время прибытия");
        System.out.println(" ХХХХХ    ХХ.ХХ.ХХ ХХ:ХХ       ХХ.ХХ.ХХ ХХ:ХХ");
        System.out.println();System.out.println();
        System.out.println(" Класс       Резервирование");
        System.out.println(" Эконом           ХХ");
        System.out.println(" Бизнес           ХХ");
        System.out.println();System.out.println();
        System.out.println("6 Помощь   3 Выбор мест  5 подтверждение");
    }
}

class Confirm extends State{
    public void display(){
        System.out.print(ESC+"2J");
        System.out.println("Ваш рейс");
        System.out.println("№ рейса Дата и время вылета Дата и время прибытия");
        System.out.println(" ХХХХХ    ХХ.ХХ.ХХ ХХ:ХХ       ХХ.ХХ.ХХ ХХ:ХХ");
        System.out.println();System.out.println();
        System.out.println(" Класс       Резервирование");
        System.out.println(" Эконом           ХХ");
        System.out.println(" Бизнес           ХХ");
        System.out.println("Сумма к оплате  $XXX.XX");
        System.out.println();System.out.println();
        System.out.println("Номер карты для оплаты   ХХXXXXX");
        System.out.println("подтверждение оплаты   ХХX");
        System.out.println("6 Помощь   4 Резервирование  1 Начальный экран");
    }
}
