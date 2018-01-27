import java.util.ArrayList;
import java.util.Arrays;
import java.util.TreeMap;
import java.util.Scanner;

public class Q4_2 {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);

        int n = in.nextInt();
        in.nextLine();
        String[] tr = new String[n];
        for (int i = 0; i < n; i++) {
            tr[i] = in.nextLine();
        }
        TreeMap<String, String> map = new TreeMap<>();
        String read;
        while (in.hasNextLine()) {
            read = in.nextLine();
            map.put(read.substring(0, 9), read);
        }
        ArrayList<String> error = new ArrayList<>();
        for (String str : tr) {
            char cmd = str.charAt(0);
            String id = str.substring(2, 11);
            switch (cmd) {
            case 'I':
                if (map.containsKey(id)) {
                    error.add(str);
                } else {
                    map.put(id, str.substring(2));
                }
                break;
            case 'U':
                if (!map.containsKey(id)) {
                    error.add(str);
                } else {
                    map.put(id, str.substring(2));
                }
                break;
            case 'D':
                if (!map.containsKey(id)) {
                    error.add(str);
                } else {
                    map.remove(id);
                }
                break;
            }
        }
        map.keySet().stream().map(k -> map.get(k)).forEach(System.out::println);
        error.forEach(System.out::println);
    }
}