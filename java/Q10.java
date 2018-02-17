import java.util.Scanner;

public class Main {
    static Scanner in = new Scanner(System.in);

    void solve() {
        Display display = new Display();
        Analyzer analyzer = new Analyzer(display);

        int n = in.nextInt();
        in.nextLine();
        for (int i = 0; i < n; i++) {
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
        // 7 comment
        if(charAt(str, 7) == '*') {
            return;
        }
        
        checkFixedString(str);

        // 19 - 28 field name
        String field = substring(str, 19, 28);
        if(!isBlankString(field) && field.startsWith(" ")){
            throw new RuntimeException("The filed name must start with 19th digit.");
        }
        field = field.trim();
        //30 - 34 field length
        String strLen = substring(str, 30, 34);
        if(field.length() > 0) {
            if(!isBlankString(strLen) && strLen.endsWith(" ")){
                throw new RuntimeException("The field length must be written right-justfield.");
            }
            if(!isNumber(strLen.trim())){
                throw new RuntimeException("The field length must be numeric.");
            }
        }
        int length = 0;
        if(!isBlankString(strLen)){
            length = Integer.parseInt(strLen.trim());
        }

        // 39 - 41, 42 - 44 position
        // 45 - word
        String posY = substring(str, 39, 41), posX = substring(str, 42, 44);
        String word = substring(str, 45);
        int y = 0, x = 0;
        if(!word.startsWith("DSPSIZ")) {
            if(isBlankString(posY) || isBlankString(posX)){
                throw new RuntimeException("The position must be designation.");
            }
            if(posY.endsWith(" ") || posX.endsWith(" ")) {
                throw new RuntimeException("the position must be written right-justfield.");
            }
            y = Integer.parseInt(posY.trim());
            x = Integer.parseInt(posX.trim());
        }
        
        if(word.startsWith("DSPSIZ")) {
            setDisplaySize(word.trim());
        } else if(word.startsWith("DSPATR")) {
            String type = substring(word, 8, 9);
            String line = createDSPATR(type, length);
            display.setField(y, x, line);
        } else {
            String tmp = word.trim();
            if(tmp.length() > 0 && (!tmp.startsWith("'") || !tmp.endsWith("'"))){
                throw new RuntimeException("The string must be enclosed in single quotes.");
            }
            display.setField(y, x, substring(tmp.trim(), 2, tmp.length()-1));
        }
    }

    void  checkFixedString(String str) {
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
        // 29 breank
        if(charAt(str, 29) != ' '){
            throw new RuntimeException("The 29th digit must be grank.");
        }
        // 35 - 38 brank
        if(!isBlankString(substring(str, 35, 38))){            
            throw new RuntimeException("The 35th ~ 38th digits must be grank.");
        }
    }

    char charAt(String str, int idx){
        return str.charAt(idx - 1);
    }

    String substring(String str, int startIdx, int endIdx) {
        return str.substring(startIdx - 1, endIdx);
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

    String substring(String str, int startIdx){
        return str.substring(startIdx - 1);
    }

    void setDisplaySize(String str) {
        String[] ss = substring(str, 8, str.length() - 1).split(" ", 2);
        String posY = ss[0].trim(), posX = ss[1].trim();
        if(!isNumber(posY) || !isNumber(posX)){
            throw new RuntimeException("The screen size must be numeric.");
        }
        int y = Integer.parseInt(posY), x = Integer.parseInt(posX);
        display.init(y, x);
    }

    String createDSPATR(String type, int size) {
        StringBuilder sb = new StringBuilder();
        char ch = '\0';
        switch(type) {
            case "CS":
                ch = '.';
                break;
            case "UL":
                ch = '_';
                break;
            default:
                throw new RuntimeException();
        }

        for (int i = 0; i < size; i++) {
            sb.append(ch);
        }

        return sb.toString();
    }
}

class Display {
    int height, width;
    String view[][];

    public Display(){
        this.init(24, 80);
    }

    void init(int h, int w){
        this.height = h;
        this.width = w;
        view = new String[height][width];
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                view[y][x] = " ";
            }
        }
    }

    void setField(int y, int x, String str){
        this.view[y - 1][x - 1] = str;
    }

    void show() {
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                System.out.print(view[y][x]);
                x += view[y][x].length() - 1;
            }
            System.out.println();
        }
    }
}