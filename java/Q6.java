import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);

        int n = in.nextInt();
        in.nextLine();
        String str = in.nextLine();

        while (str.length() > 0) {
            int f = 0;
            while (f < str.length() && str.charAt(f) == '0') {
                f++;
            }
            if (f == str.length()) {
                System.out.println(0);
                return;
            }
            str = str.substring(f);
            if (str.length() <= n) {
                System.out.println(str);
                return;
            }
            int idx = 0;
            long value = 0;
            for (int i = 0; i < str.length() - n + 1; i++) {
                if (str.charAt(i) == '0') {
                    continue;
                }
                long v = Long.parseLong(str.substring(i, i + n));
                if (value < v) {
                    value = v;
                    idx = i;
                }
            }
            System.out.println(value);
            str = str.substring(0, idx) + str.substring(idx + n);
        }
    }
}