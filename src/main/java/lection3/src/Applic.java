package lection3.src;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Applic {
    Lines lines;
    private BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));

    public Applic(int N){
        lines = new Lines(N);
    }

    public void exec() throws IOException {
        String[] args = null;
        Command cmd=null;
        Command.setLines(lines);
        String ln="";
        int n=0;
        int m=0;
        do {
            System.out.println("====================================================");
            for(String s:lines.lines){
                System.out.println(s);
            }
            System.out.println("Commands:\nInsert Line IL:s:n, Append Line AL:s:, Replace Line RL:s:n, Delete Line DL::n\n"
                    +"Insert Word IW:w:n:m, Append Word AW:w:n:, Replace Word RW:w:n:m, Delete Word DW::n:m\n"
                    + "undo U, redo R, quit Q");
            String command = bf.readLine();
            args = command.split(":");
            n=0;
            m=0;
            ln="";
            try {
                ln = args[1];
                n = Integer.valueOf(args[2]);
                m = Integer.valueOf(args[3]);
            }
            catch(Exception e){

            }

            //Единственная точка выбора в проекте - if'ы здесь допустимы
            if (args[0].compareTo("IL") == 0) {
                cmd = new InsertLine();
                cmd.execute(ln, n, m);
                continue;
            }

            if (args[0].compareTo("AL") == 0) {
                cmd = new AppendLine();
                cmd.execute(ln, n, m);
                continue;
            }

            if (args[0].compareTo("RL") == 0) {
                cmd = new ReplaceLine();
                cmd.execute(ln, n, m);
                continue;
            }

            if (args[0].compareTo("DL") == 0) {
                cmd = new DeleteLine();
                cmd.execute(ln, n, m);
                continue;
            }

            if (args[0].compareTo("U") == 0) {
                if(cmd!=null) {
                    cmd.undo();
                }

                continue;
            }

            if (args[0].compareTo("R") == 0) {
                if(cmd!=null)
                    cmd.execute(ln, n, m);
                continue;
            }

            if (args[0].compareTo("Q") == 0) {
                break;
            }
            System.out.println("Неверная команда");
        } while(true);
    }

    public static void main(String[] args) throws IOException {
        // TODO code application logic here
        Applic m3 = new Applic(8);
        m3.exec();
    }
}
