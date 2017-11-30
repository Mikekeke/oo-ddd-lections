import java.util.Optional;

abstract class Command {
    protected static Lines lines;

    public static void setLines(Lines exlines) {
        lines = exlines;
    }

    abstract public void execute(String line, int n, int m);

    abstract public void undo();
}

class InsertLine extends Command {
    String _line;
    int idx = -1;

    @Override
    public void execute(String line, int n, int m) {
        String toInsert = line.isEmpty() ? _line : line;
        lines.insert(toInsert, n);
        idx = n;
        _line = line;
    }

    @Override
    public void undo() {
        if (idx >= 0) {
            lines.delete(idx);
        }

    }
}

class AppendLine extends Command {

    @Override
    public void execute(String line, int n, int m) {

        lines.append(line);
    }

    @Override
    public void undo() {
        lines.delete(lines.lastLine());
    }
}

class ReplaceLine extends Command {

    @Override
    public void execute(String line, int n, int m) {

        lines.replace(line, n);
    }

    @Override
    public void undo() {

    }
}

class DeleteLine extends Command {
    private String _line;
    private int idx;

    @Override
    public void execute(String line, int n, int m) {
        _line = lines.lines[n];
        idx = n;

        lines.delete(n);
    }

    @Override
    public void undo() {
        if (lines.cl == 0) {
            lines.append(_line);
        } else {
            lines.insert(_line, idx);
        }

    }
}