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

    class Pair{
        int idx;
        int asc;
        public Pair(String str){
            idx = (str.charAt(0) - '0') - 1;
            asc = str.charAt(1) == 'A' ? 1 : -1;
        }
    }
    Pair[] cond;

    public MyComparator(String[] ss){
        super();
        cond = new Pair[ss.length];
        for(int i = 0; i < ss.length; i++){
            cond[i] = new Pair(ss[i]);
        }
    }

    @Override
    public int compare(Data d1, Data d2){
        for(Pair st : cond) {
            int result = d1.cols[st.idx].compareTo(d2.cols[st.idx]);
            if(result == 0){
                continue;
            }
            return result * st.asc;
        }
        return 0;
    }
}