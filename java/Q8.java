import java.io.DataInputStream;
import java.io.BufferedInputStream;

public class Main{
    public static void main(String[] args)throws Exception{
        DataInputStream di = new DataInputStream(new BufferedInputStream(System.in));
        
        byte b;
        String num = "";
        while(di.available() > 0){
            b =  di.readByte();

            if(b == 0x0a){
                continue;
            }

            if((b & 0x0f) == 0x0d || (b & 0x0f) == 0x0c) {
                num += String.format("%x", b>>4 & 0x0f);
                if(num.charAt(0) == '0'){
                    num = num.substring(1);
                }
                if((b & 0x0f) == 0x0d){
                    num = "-" + num;
                }
                System.out.println(num);
                num = "";
            } else {
                num += String.format("%02x", b);
                
            }
        }
    }
}