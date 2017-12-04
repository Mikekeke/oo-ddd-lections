package lection3;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.TreeMap;




public class Applic {
    Lines lines;
    private BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
    protected XFactory<String, Command> fact;
    protected CommandQueue cq;

    public Applic(int N) {
        lines = new Lines(N);
        TreeMap<String, Command> cmdMap = new TreeMap<String, Command>();
        cmdMap.put("AL", new AppendLine());
        cmdMap.put("IL", new InsertLine());
        cmdMap.put("DL", new DeleteLine());
        cmdMap.put("RL", new ReplaceLine());
        fact = new XFactory<>(cmdMap);
        cq = new CommandQueue(4);
    }

    public void exec() throws IOException {
        String[] args = null;
        Command cmd = null;
        Command.setLines(lines);
        String ln = "";
        int n = 0;
        int m = 0;
        do {
            System.out.println("====================================================");
            for (String s : lines.lines) {
                System.out.println(s);
            }
            System.out.println("Commands:\nInsert Line IL:s:n, Append Line AL:s:, Replace Line RL:s:n, Delete Line DL::n\n"
                    + "Insert Word IW:w:n:m, Append Word AW:w:n:, Replace Word RW:w:n:m, Delete Word DW::n:m\n"
                    + "undo U, redo R, quit Q");
            String command = bf.readLine();
            args = command.split(":");
            n = 0;
            m = 0;
            ln = "";
            try {
                ln = args[1];
                n = Integer.valueOf(args[2]);
                m = Integer.valueOf(args[3]);
            } catch (Exception e) {

            }

            Command fCmd = fact.getOrNull(args[0]);
            if (fCmd != null) {
                cmd = fCmd;
                cq.put(fCmd);
                cmd.execute(ln, n, m);
                continue;
            }

            if (args[0].compareTo("U") == 0) {
                try {
                    cq.getUndo().undo();
                } catch (NoCommandException e) {
                    System.err.println(e.getMessage());
                }
                continue;
            }

            if (args[0].compareTo("R") == 0) {
                try {
                    cq.getRedo().execute(ln, n, m);
                } catch (NoCommandException e) {
                    System.err.println(e.getMessage());
                }
                continue;
            }

            if (args[0].compareTo("Q") == 0) {
                break;
            }
            System.out.println("Неверная команда");
        } while (true);
    }

    public static void main(String[] args) throws IOException {
        Applic m3 = new Applic(8);
        m3.exec();
    }
}
