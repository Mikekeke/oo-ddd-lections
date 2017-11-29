package lection2;

public class Application {
    private State[] states; // ассоциативнывй массив состояний
    private int[][] trans;  // таблица переходов между состояниями
    int initial_st;         // начальное состояние
    int final_st;           // конечное состояние (завершение работы)

    public Application(int init, int fin, State st[], int tr[][]) {
        initial_st = init;
        final_st = fin;
        states = st;
        trans = tr;
        setTransitions();
    }

    private void setTransitions() {
        for (int i = 0; i < trans.length; i++) {
            State s = states[i];
            if (s!= null) {
                states[i].setTransitions(trans[i]);
            }
        }

    }

//    public void execute_1() throws Exception{
//        int choice = initial_st;
//        int back;
//        do{
//            State state = states[choice];
//            back = choice;
//            state.execute();
//            choice = state.getChoice();
//            if (state.getChoice() == 6){
//                states[6].execute();
//                choice = back;
//            }
//        } while (choice != final_st);
//    }

    public void execute() throws Exception {
        int choice = initial_st;
        int back = initial_st;
        do {
            State state = states[choice];
            state.execute(back);
            back = choice;
            choice = state.getChoice();
        } while (choice != final_st);
    }

    public static void main(String[] args) throws Exception {
        State[] st = new State[8];
        st[0] = null;
        st[1] = new Register();
        st[2] = new Flights();
        st[3] = new Seats();
        st[4] = new Reserve();
        st[5] = new Confirm();
        st[6] = new Help();
        st[7] = new RegisterHelp();

        int[][] tr = {
                {0},
                {0, 7, 5, 2},
                {0, 6, 1, 3},
                {6, 2, 4},
                {6, 3, 5},
                {6, 4, 1}
        };

        Application app = new Application(1, 0, st, tr);
        app.execute();

    }
}
