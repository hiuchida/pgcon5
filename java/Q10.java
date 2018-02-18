import java.util.Scanner;

public class Main {
    static Scanner in = new Scanner(System.in);

    void solve() {
        Display display = new Display();
        Analyzer analyzer = new Analyzer(display);

        int n = in.nextInt();
        in.nextLine();
        while(n-- > 0) {
            analyzer.analyze(in.nextLine());
        }
        display.show();
    }

    public static void main(String[] args) {
        new Main().solve();
    }
}

class Analyzer {

    Display display;

    public Analyzer(Display display) {
        this.display = display;
    }

    void analyze(String str) {
        if(check(str)) {
            return;
        }

        String field = substring(str, 19, 28).trim();
        int length = 0;
        if(!isBlankString(substring(str, 30, 34))){
            length = parseInt(str, 30, 34);
        }
        String word = substring(str, 45).trim();
        if(word.startsWith("DSPSIZ")) {
            String[] ss = substring(word, 8, word.length() - 1).split(" ", 2);
            int width = parseInt(ss[0]);
            int height = parseInt(ss[1]);
            display.init(width, height);
        } else {
            int y = 0;
            int x = 0;
            y = parseInt(str, 39, 41);
            x = parseInt(str, 42, 44);
            if(word.startsWith("DSPATR")) {
                String type = substring(word, 8, 9);
                String line = createDSPATR(type, length);
                display.setField(line, y, x);
            } else {
                display.setField(substring(word, 2, word.length()-1), y, x);
            }
        }
    }

    boolean check(String str) {
        // 7 comment
        if(charAt(str, 7) == '*') {
            return true;
        }

        // 1 - 5 brank
        if(!isBlankString(substring(str, 1, 5))){
            throw new RuntimeException("The 1st ~ 5th digits must be grank.");
        }
        // 6 'A'
        if(charAt(str, 6) != 'A') {
            throw new RuntimeException("The 6th digit must be 'A'.");
        }
        // 7 ' '
        if(charAt(str, 7) != ' '){
            throw new RuntimeException("The 6th digit must be '*' or ' '.");
        }
        // 8 - 18 brank
        if(!isBlankString(substring(str, 8, 18))){            
            throw new RuntimeException("The 8th ~ 18th digits must be grank.");
        }
        // 19 - 28 field name
        String field = substring(str, 19, 28);
        if(!isBlankString(field) && field.startsWith(" ")){
            throw new RuntimeException("The filed name must start with 19th digit.");
        }
        // 29 breank
        if(charAt(str, 29) != ' '){
            throw new RuntimeException("The 29th digit must be grank.");
        }
        //30 - 34 field length
        String strLen = substring(str, 30, 34);
        if(field.trim().length() > 0) {
            if(!isBlankString(strLen) && strLen.endsWith(" ")){
                throw new RuntimeException("The field length must be written right-justfield.");
            }
            if(!isNumber(strLen.trim())){
                throw new RuntimeException("The field length must be numeric.");
            }
        }
        // 35 - 38 brank
        if(!isBlankString(substring(str, 35, 38))){            
            throw new RuntimeException("The 35th ~ 38th digits must be grank.");
        }
        // 39 - 41, 42 - 44 position
        // 45 - word
        String posY = substring(str, 39, 41);
        String posX = substring(str, 42, 44);
        String word = substring(str, 45);
        String tmp = word.trim();
        if(!word.startsWith("DSPSIZ")) {
            if(isBlankString(posY) || isBlankString(posX)){
                throw new RuntimeException("The position must be designation.");
            }
            if(posY.endsWith(" ") || posX.endsWith(" ")) {
                throw new RuntimeException("the position must be written right-justfield.");
            }
            if(tmp.length() > 0 && !tmp.startsWith("DSPATR") && (!tmp.startsWith("'") || !tmp.endsWith("'"))){
                throw new RuntimeException("The string must be enclosed in single quotes.");
            }
        } else {
            String[] ss = substring(tmp, 8, tmp.length() - 1).split(" ", 2);
            String widthStr = ss[0].trim();
            String heightStr = ss[1].trim();
            if(!isNumber(widthStr) || !isNumber(heightStr)){
                throw new RuntimeException("The screen size must be numeric.");
            }
        }
        return false;
    }

    char charAt(String str, int idx){
        return str.charAt(idx - 1);
    }

    String substring(String str, int startIdx, int endIdx) {
        return str.substring(startIdx - 1, endIdx);
    }

    String substring(String str, int startIdx){
        return str.substring(startIdx - 1);
    }

    Integer parseInt(String str, int startIdx, int endIdx){
        return Integer.parseInt(substring(str, startIdx, endIdx).trim());
    }

    Integer parseInt(String str) {
        return Integer.parseInt(str.trim());
    }

    boolean isBlankString(String str) {
        for(char c: str.toCharArray()){
            if(c != ' ') {
                return false;
            }
        }
        return true;
    }

    boolean isNumber(String str) {
        if(str.length() == 0){
            return false;
        }
        for(char c : str.toCharArray()) {
            if(c < '0' && '9' < c) {
                return false;
            }
        }
        return true;
    }

    String createDSPATR(String type, int size) {
        StringBuilder sb = new StringBuilder();
        char ch;
        switch(type) {
            case "CS":
                ch = '.';
                break;
            case "UL":
                ch = '_';
                break;
            default:
                throw new RuntimeException("In DPSATR(). The type must be reserved word.(CS or UL)");
        }

        for (int i = 0; i < size; i++) {
            sb.append(ch);
        }

        return sb.toString();
    }
}

class Display {
    int height, width;
    char field[][];

    public Display(){
        this.init(24, 80);
    }

    void init(int h, int w){
        this.height = h;
        this.width = w;
        field = new char[height][width];
    }

    void setField(String str, int y, int x){
        for(int i = 0; i < str.length(); i++) {
            field[y - 1][(x + i) - 1] = str.charAt(i);
        }
    }

    void show() {
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                System.out.print(field[y][x] == '\0' ? ' ' : field[y][x]);
            }
            System.out.println();
        }
    }
}