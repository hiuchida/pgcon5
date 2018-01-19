import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Scanner;

class Main {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);

        int n = in.nextInt();
        in.nextLine();
        String[] tr = new String[n];
        for (int i = 0; i < n; i++) {
            tr[i] = in.nextLine();
        }
        HashMap<String, String> map = new HashMap<>();
        String read;
        while (in.hasNextLine()) {
            read = in.nextLine();
            String[] record = read.split(" ");
            map.put(record[0], read);
        }
        ArrayList<String> error = new ArrayList<>();
        for (String str : tr) {
            String[] a = str.split(" ");
            switch (a[0]) {
            case "I":
                if (map.containsKey(a[1])) {
                    error.add(str);
                } else {
                    map.put(a[1], str.substring(2));
                }
                break;
            case "U":
                if (!map.containsKey(a[1])) {
                    error.add(str);
                } else {
                    map.put(a[1], str.substring(2));
                }
                break;
            case "D":
                if (!map.containsKey(a[1])) {
                    error.add(str);
                } else {
                    map.remove(a[1]);
                }
                break;
            }
        }
        map.keySet().stream().sorted().map(k -> map.get(k)).forEach(System.out::println);
        error.stream().forEach(System.out::println);
    }
}