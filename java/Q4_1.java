import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Scanner;

class Main {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);

        int n = in.nextInt(); in.nextLine();
        String[] tr = new String[n];
        for (int i = 0; i < n; i++) {
            tr[i] = in.nextLine();
        }
        HashMap<String, Master> map = new HashMap<>();
        String read;
        while (in.hasNextLine()) {
            read = in.nextLine();
            String[] record = read.split(" ");
            map.put(record[0], new Master(record[0], record[1], Integer.parseInt(record[2])));
        }
        ArrayList<String> error = new ArrayList<>();
        for (String str : tr) {
            String[] a = str.split(" ");
            switch (a[0]) {
            case "I":
                if (map.containsKey(a[1])) {
                    error.add(str);
                } else {
                    map.put(a[1], new Master(a[1], a[2], Integer.parseInt(a[3])));
                }
                break;
            case "U":
                if (!map.containsKey(a[1])) {
                    error.add(str);
                } else {
                    map.put(a[1], new Master(a[1], a[2], Integer.parseInt(a[3])));
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
        String[] keyset = new String[map.size()];
        int i = 0;
        for (String key : map.keySet()) {
            keyset[i++] = key;
        }
        Arrays.sort(keyset);
        for (String key : keyset) {
            Master master = map.get(key);
            System.out.println(master.key + " " + master.name + " " + master.value);
        }
        for (String e : error) {
            System.out.println(e);
        }
    }
}

class Master {
    String key;
    String name;
    int value;

    public Master(String k, String n, int v) {
        key = k;
        name = n;
        value = v;
    }
}