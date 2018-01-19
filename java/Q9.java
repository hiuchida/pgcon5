import java.util.ArrayList;
import java.util.Comparator;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);

        String[] sort = in.nextLine().split(" ");
        ArrayList<String> list = new ArrayList<>();

        while (in.hasNextLine()) {
            list.add(in.nextLine());
        }

        list.sort(new Comparator<String>() {
            public int compare(String s1, String s2) {
                String[] ss1 = s1.split(" "), ss2 = s2.split(" ");
                for (String st : sort) {
                    int idx = st.charAt(0) - '0' - 1;
                    if (ss1[idx].equals(ss2[idx])) {
                        continue;
                    }
                    return ss1[idx].compareTo(ss2[idx]) * (st.charAt(1) == 'A' ? 1 : -1);
                }
                return 0;
            }
        });

        list.forEach(System.out::println);
    }
}