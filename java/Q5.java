import java.util.ArrayList;
import java.util.Comparator;
import java.util.Scanner;

public class Q5 {

    static class Pair {
        String phrase;
        int value;
    
        public Pair(String s, int v) {
            phrase = s;
            value = v;
        }
    }

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);

        ArrayList<Pair> list = new ArrayList<>();
        for (String s : in.nextLine().split(",")) {
            String[] pv = s.split("\\|");
            list.add(new Pair(pv[0], Integer.parseInt(pv[1])));
        }
        
        list.sort(new Comparator<Pair>() {
            public int compare(Pair p1, Pair p2) {
                if (p1.value == p2.value) {
                    return p1.phrase.length() > p2.phrase.length() ? 1 : -1;
                }
                return p1.value < p2.value ? 1 : -1;
            }
        });

        String str = in.nextLine();
        int score = 0;
        for (Pair p : list) {
            int pos = str.indexOf(p.phrase);
            while (pos != -1) {
                    score += p.value;
                    str = str.substring(0, pos) + str.substring(pos + p.phrase.length());
                pos = str.indexOf(p.phrase);
            }
        }
        System.out.println(score);
    }
}