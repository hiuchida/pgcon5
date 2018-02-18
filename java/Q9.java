import java.util.ArrayList;
import java.util.Comparator;
import java.util.Scanner;

public class Main {

    void solve(){
        Scanner in = new Scanner(System.in);

        String[] cond = in.nextLine().split(" ");
        ArrayList<Data> list = new ArrayList<>();
        while (in.hasNextLine()) {
            String s = in.nextLine();
            list.add(new Data(s));
        }

        list.sort(new MyComparator(cond));
        list.forEach(System.out::println);
    }
    
    public static void main(String[] args) {
        new Main().solve();
    }
}

class Data{
    String line;
    String[] cols;

    public Data(String s){
        this.line = s;
        this.cols = s.split(" ");
    }

    @Override
    public String toString(){
        return line;
    }
}

class MyComparator implements Comparator<Data> {

    class Condition{
        int idx;
        int sign;
        public Condition(String str){
            idx = (str.charAt(0) - '0') - 1;
            sign = str.charAt(1) == 'A' ? 1 : -1;
        }
    }
    Condition[] cond;

    public MyComparator(String[] ss){
        super();
        cond = new Condition[ss.length];
        for(int i = 0; i < ss.length; i++){
            cond[i] = new Condition(ss[i]);
        }
    }

    @Override
    public int compare(Data d1, Data d2){
        for(Condition ct : cond) {
            int result = d1.cols[ct.idx].compareTo(d2.cols[ct.idx]);
            if(result != 0){
                return ct.sign * result;
            }
        }
        return 0;
    }
}