import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);

        int n = Integer.parseInt(in.nextLine());
        String[] cs = in.nextLine().split(" ");
        int[] a = new int[26];

        int count = 1;
        for (String s : cs) {
            int idx = s.charAt(0) - 'A';
            a[idx]++;

            int fst = 0, sec = 0;
            for (int i : a) {
                sec = Math.max(sec, i);
                if (fst < sec) {
                    int tmp = fst;
                    fst = sec;
                    sec = tmp;
                }
            }
            if (fst - sec > n - count) {
                for (int i = 0; i < 26; i++) {
                    if (a[i] == fst) {
                        System.out.println((char) ('A' + i) + " " + count);
                        return;
                    }
                }
            }
            count++;
        }
        System.out.println("TIE");
    }
}