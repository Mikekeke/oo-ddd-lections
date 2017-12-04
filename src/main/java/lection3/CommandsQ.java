package lection3;

import java.util.ArrayList;

class CommandsQ extends ArrayList<Command> {
    int cap;
    int cursor = -1;

    public CommandsQ(int cap) {
        this.cap = cap;
    }

    public void addC(Command command) {
        clearRight();
        if (this.size() < cap) {
            cursor++;
        } else {
            this.remove(0);
        }

        super.add(command);
    }

    private void clearRight(){
        for(int i = this.size() - 1; i > cursor ; i--){

            System.out.println("CLEARED " + i);
            this.remove(i);
        }
    }
    private boolean is_first() {
        return cursor == -1;
    }

    private boolean is_last() {
        return cursor == this.size() - 1;
    }

    public void toPrev() {
        cursor--;
    }

    public void toNext() throws NoComandException {
        cursor++;
    }

    public Command getC() throws NoComandException {
        try {
            return super.get(cursor);
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new NoComandException("Error getting command");
        }
    }

    public Command getToUndo() throws NoComandException {
        if (is_first()) throw new NoComandException("Nothing to undo");
        Command c = getC();
        toPrev();
        return c;
    }

    public Command getToRedo() throws NoComandException {
        if (is_last()) throw new NoComandException("No commands to redo");
        toNext();
        return getC();
    }
}