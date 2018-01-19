import java.io.DataInputStream;
import java.io.BufferedInputStream;

public class Main{
    public static void main(String[] args)throws Exception{
        DataInputStream di = new DataInputStream(new BufferedInputStream(System.in));
        
        byte b;
        String num = "";
        while(di.available() > 0){
            String in = String.format("%02x", di.readByte());
            if(in.equals("0a")){
                continue;
            }else if(in.endsWith("d") || in.endsWith("c")){
                num += in.charAt(0);
                if(num.charAt(0) == '0'){
                    num = num.substring(1);
                }
                if(in.endsWith("d")){
                    num = "-" + num;
                }
                System.out.println(num);
                num = "";
            }else{
                num += in;
            }
        }
    }
}