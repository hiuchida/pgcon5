import java.util.HashMap;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        
        HashMap<Integer, Integer> map = new HashMap<>();
        int n = in.nextInt();
        int max = 0;
        for (int i = 0; i < n; i++) {
            int a = in.nextInt();
            for (int k = a - 1; k <= a + 1; k++) {
                if (map.containsKey(k)) {
                    map.put(k, map.get(k) + 1);
                } else {
                    map.put(k, 1);
                }
            }
        }
        for (int a : map.keySet()) {
            max = Math.max(max, map.get(a));
        }
        System.out.println(max);
    }
}