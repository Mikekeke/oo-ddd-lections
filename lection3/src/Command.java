import java.util.Optional;

abstract class Command{
    protected static Lines lines;
    public static void setLines(Lines exlines){
        lines = exlines;
    }
    abstract public void execute(String line, int n, int m);
    abstract public void undo();
}

class InsertLine extends Command{
    Optional idx = Optional.empty();

    @Override
    public void execute(String line, int n, int m){

        lines.insert(line, n);
    }

    @Override
    public void undo() {

    }
}

class AppendLine extends Command{

    @Override
    public void execute(String line, int n, int m){

        lines.append(line);
    }
    @Override
    public void undo() {

    }
}

class ReplaceLine extends Command{

    @Override
    public void execute(String line, int n, int m){

        lines.replace(line,n);
    }
    @Override
    public void undo() {

    }
}

class DeleteLine extends Command{

    @Override
    public void execute(String line, int n, int m){

        lines.delete(n);
    }
    @Override
    public void undo() {

    }
}