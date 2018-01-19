import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);

        String s = in.nextLine();
        System.out.println(new StringBuilder()
                .append(s.substring(1, s.length() / 2))
                .append(s.charAt(0))
                .append(s.length() % 2 == 0 ? "" : s.charAt(s.length() / 2))
                .append(s.charAt(s.length() - 1))
                .append(s.substring(s.length() / 2 + s.length() % 2, s.length() - 1))
                .toString());
    }
}