package lection3;

public class Lines {
    protected String[] lines;
    protected int nLines;
    protected int cl;

    public int lastLine() {
        return cl - 1;
    }

    public Lines(int nLines) {
        this.nLines = nLines;
        lines = new String[nLines];
        for(int i=0;i<nLines;i++){
            lines[i] = "";
        }
        cl=0;
    }

    public void insert(String line, int n){
        if(cl > n){
            for(int j=nLines-1;j>n;j--){
                lines[j]=lines[j-1];
            }
            lines[n]=line;
            if(cl<nLines-1)cl++;
        }
        else System.out.println("n must be between 0 and "+(cl-1));
    }

    public void append(String line){
        if(cl < nLines){
            lines[cl]=line;
            cl++;
        }
        else System.out.println("There is not enough room");
    }

    public void replace(String line, int n){
        if(cl > n){
            lines[n]=line;
        }
        else System.out.println("n must be between 0 and "+(cl-1));
    }

    public void delete(int n){
        if(cl > n){
            for(int j=n; j<nLines-1;j++){
                lines[j]=lines[j+1];
            }
            cl--;
            lines[cl]="";
        }
        else System.out.println("n must be between 0 and "+(cl-1));
    }
}