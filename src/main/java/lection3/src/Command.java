package lection3.src;

abstract class Command {
    protected static Lines lines;

    public static void setLines(Lines exlines) {
        lines = exlines;
    }

    abstract public void execute(String line, int n, int m);

    abstract public void undo();
}

class InsertLine extends Command {
    String insLine;
    int idx = -1;

    @Override
    public void execute(String line, int n, int m) {
        insLine = insLine == null ? line : insLine;
        idx = idx == -1 ? n : idx;
        lines.insert(insLine, idx);
    }

    @Override
    public void undo() {
        if (idx >= 0) {
            lines.delete(idx);
        }
    }
}

class AppendLine extends Command {
    String appLine;

    @Override
    public void execute(String line, int n, int m) {
        appLine = appLine == null ? line : appLine;
        lines.append(appLine);
    }

    @Override
    public void undo() {
        lines.delete(lines.lastLine());
    }
}

class ReplaceLine extends Command {
    String oldLine, newLine;
    int idx = -1;

    @Override
    public void execute(String line, int n, int m) {
        idx = idx == -1 ? n : idx;
        oldLine = oldLine == null ? lines.lines[idx] : oldLine;
        newLine = newLine == null ? line : newLine;
        lines.replace(newLine, idx);
    }

    @Override
    public void undo() {
        lines.replace(oldLine, idx);
    }
}

class DeleteLine extends Command {
    private String delLine;
    private int idx = -1;

    @Override
    public void execute(String line, int n, int m) {
        delLine = delLine == null ? lines.lines[n] : delLine;
        idx = idx == -1 ? n : idx;
        lines.delete(idx);
    }

    @Override
    public void undo() {
        if (idx == lines.cl) {
            lines.append(delLine);
        } else {
            lines.insert(delLine, idx);
        }
    }
}