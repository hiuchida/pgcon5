import java.util.Scanner;

class Main {
    static Scanner in = new Scanner(System.in);

    int n, h, w;
    String[][] display;

    void solve() {
        init(24, 80);
        int n = in.nextInt();
        in.nextLine();
        for (int i = 0; i < n; i++) {
            String line = in.nextLine();
            if (line.charAt(5) == '*')
                continue;
            String str = line.substring(44).trim();
            if (str.length() == 0)
                continue;
            if (str.startsWith("'")) {
                int y = Integer.parseInt(line.substring(38, 41).trim()) - 1;
                int x = Integer.parseInt(line.substring(41, 44).trim()) - 1;
                display[y][x] = str.substring(1, str.length() - 1);

            } else if (str.indexOf("DSPSIZ") > -1) {
                setDisplaySize(str.substring(7, str.length() - 1));

            } else if (str.indexOf("DSPATR") > -1) {
                int size = Integer.parseInt(line.substring(29, 34).trim());
                int y = Integer.parseInt(line.substring(38, 41).trim()) - 1;
                int x = Integer.parseInt(line.substring(41, 44).trim()) - 1;
                display[y][x] = createDSPATR(str.substring(7, str.length() - 1), size);
            }
        }
        show();
    }

    void init(int y, int x) {
        h = y;
        w = x;
        display = new String[h][w];
        for (int i = 0; i < h; i++) {
            for (int j = 0; j < w; j++) {
                display[i][j] = " ";
            }
        }
    }

    void setDisplaySize(String str) {
        String[] ss = str.split(" ");
        h = Integer.parseInt(ss[0].trim());
        w = Integer.parseInt(ss[1].trim());
        init(h, w);
    }

    String createDSPATR(String type, int size) {
        StringBuilder sb = new StringBuilder();
        char ch = '\0';
        if (type.equals("CS")) {
            ch = '.';
        }else if (type.equals("UL")) {
            ch = '_';
        }

        for (int i = 0; i < size; i++) {
            sb.append(ch);
        }

        return sb.toString();
    }

    void show() {
        for (int i = 0; i < h; i++) {
            for (int j = 0; j < w; j++) {
                System.out.print(display[i][j]);
                j += display[i][j].length() - 1;
            }
            System.out.println();
        }
    }

    public static void main(String[] args) {
        new Main().solve();
    }
}