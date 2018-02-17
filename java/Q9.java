import java.util.ArrayList;
import java.util.Comparator;
import java.util.Scanner;

public class Main {

    void solve(){
        Scanner in = new Scanner(System.in);

        String[] sort = in.nextLine().split(" ");
        ArrayList<Data> list = new ArrayList<>();

        while (in.hasNextLine()) {
            String s = in.nextLine();
            list.add(new Data(s, s.split(" ")));
        }

        list.sort(new MyComparator(sort));

        list.forEach(System.out::println);
    }
    
    public static void main(String[] args) {
        new Main().solve();
    }
}

class Data{
    String line;
    String[] cols;

    public Data(String s, String[] ss){
        this.line = s;
        this.cols = ss;
    }

    @Override
    public String toString(){
        return line;
    }
}

class MyComparator implements Comparator<Data> {

    String[] sort;

    public MyComparator(String[] ss){
        super();
        this.sort = ss;
    }
    @Override
    public int compare(Data d1, Data d2){
        for(String st : sort) {
            int idx = st.charAt(0) - '0' -1;
            if(d1.cols[idx].equals(d2.cols[idx])){
                continue;
            }
            return d1.cols[idx].compareTo(d2.cols[idx]) * (st.charAt(1) == 'A' ? 1 : -1);
        }
        return 0;
    }
}