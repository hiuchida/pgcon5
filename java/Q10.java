import java.util.ArrayList;
import java.util.Scanner;

public class Main implements Handler{

    Display display;
    Analyzer analyzer;
    ArrayList<Field> fields;

    private void solve() {
        Scanner in = new Scanner(System.in);

        display = new Display();
        analyzer = new Analyzer(this);
        fields = new ArrayList<>();

        int n = in.nextInt();
        in.nextLine();
        while(n-- > 0) {
            analyzer.analyze(in.nextLine());
        }
        display.attach(fields);
        display.show();
    }

    public void init(int h, int w){
        display.init(h, w);
    }

    public void setText(String str, int y, int x){
        display.setText(str, y, x);
    }

    public void setField(String name, String type, int length, int y, int x){
        fields.add(new Field(name, type, length, y, x));
    }

    public static void main(String[] args) {
        new Main().solve();
    }
}

interface Handler {
    void init(int h, int w);
    void setText(String str, int y, int x);
    void setField(String name, String type, int length, int y, int x);
}

class Analyzer {

    Handler handler;

    public Analyzer(Handler handler) {
        this.handler = handler;
    }

    public void analyze(String str) {
        if(check(str)) {
            return;
        }

        String word = substring(str, 45).trim();
        if(word.startsWith("DSPSIZ")) {
            String[] ss = substring(word, 8, word.length() - 1).split(" ", 2);
            int width = parseInt(ss[0]);
            int height = parseInt(ss[1]);
            handler.init(width, height);
        } else {
            int y = parseInt(str, 39, 41);
            int x = parseInt(str, 42, 44);
            if(word.startsWith("DSPATR")) {
                String field = substring(str, 19, 28).trim();
                int length = parseInt(str, 30, 34);
                String type = substring(word, 8, 9);
                handler.setField(field, type, length, y, x);
            } else {
                handler.setText(substring(word, 2, word.length()-1), y, x);
            }
        }
    }

    private boolean check(String str) {
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

    private char charAt(String str, int idx){
        return str.charAt(idx - 1);
    }

    private String substring(String str, int startIdx, int endIdx) {
        return str.substring(startIdx - 1, endIdx);
    }

    private String substring(String str, int startIdx){
        return str.substring(startIdx - 1);
    }

    private Integer parseInt(String str, int startIdx, int endIdx){
        return Integer.parseInt(substring(str, startIdx, endIdx).trim());
    }

    private Integer parseInt(String str) {
        return Integer.parseInt(str.trim());
    }

    private boolean isBlankString(String str) {
        for(char c: str.toCharArray()){
            if(c != ' ') {
                return false;
            }
        }
        return true;
    }

    private boolean isNumber(String str) {
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
}

class Display {
    int height, width;
    char text[][];

    public Display(){
        this.init(24, 80);
    }

    public void init(int h, int w){
        this.height = h;
        this.width = w;
        text = new char[height][width];
        for(int y = 0; y < height; y++){
            for(int x = 0; x < width; x++){
                text[y][x] = ' ';
            }
        }
    }

    private void setChar(char ch, int y, int x){
        text[y - 1][x - 1] = ch;
    }

    public void setText(String str, int y, int x){
        for(int i = 0; i < str.length(); i++) {
            setChar(str.charAt(i), y, x + i);
        }
    }

    public void attach(ArrayList<Field> fields){
        for(Field f: fields){
            for(int i = 0; i < f.length; i++) {
                setChar(f.getChar(i), f.posY, f.posX + i);
            }
        }
    }

    public void show() {
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                System.out.print(text[y][x]);
            }
            System.out.println();
        }
    }
}

class Field {
    String name;
    String value;
    char line;
    int length;
    int posY;
    int posX;

    public Field(String name, String type, int length, int y, int x) {
        this.name = name;
        this.value = "";
        this.line = getLineChar(type);
        this.length = length;
        this.posY = y;
        this.posX = x;
    }

    private char getLineChar(String type) {
        switch(type) {
            case "CS":
                return '.';
            case "UL":
                return '_';
            default:
                throw new RuntimeException("In DPSATR(). The type must be reserved word.(CS or UL)");
        }
    }

    public char getChar(int idx){
        return idx < value.length() ? value.charAt(idx) : line;
    }
}