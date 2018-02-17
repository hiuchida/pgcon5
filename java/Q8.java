import java.io.DataInputStream;
import java.io.BufferedInputStream;

public class Main{
    public static void main(String[] args)throws Exception{
        BufferedInputStream in = new BufferedInputStream(System.in);
        
        byte b;
        StringBuilder num = new StringBuilder();
        while((b = (byte)in.read()) != -1){
            if(b == 0x0a){
                continue;
            }
            
            byte upper4bit = (byte)((b>>4) & 0x0f); // upper 4 bit of b
            byte lower4bit = (byte)(b & 0x0f);      // lower 4 bit of b
            if(lower4bit == 0x0d || lower4bit == 0x0c) {
                num.append(String.format("%x", upper4bit));
                if(num.charAt(0) == '0'){
                    num.deleteCharAt(0);
                }
                if(lower4bit == 0x0d){
                    num.insert(0, "-");
                }
                System.out.println(num);
                num = new StringBuilder();
            } else {
                num.append(String.format("%02x", b));
            }
        }
    }
}