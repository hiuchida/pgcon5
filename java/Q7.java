import java.util.Scanner;

public class Main{
    public static void main(String[] args){
        Scanner in = new Scanner(System.in);
        
        int n = in.nextInt();
        int[] p = new int[n], q = new int[n];
        for(int i = 0; i < n; i++) {
            p[i] = in.nextInt();
        }
        for(int i = 0; i < n; i++) {
            q[i] = in.nextInt();
        }
        double sum = 0;
        for(int i = 0; i < n; i++) {
            sum += Math.pow(Math.abs(p[i] - q[i]), 2);
        }
        System.out.printf("%.2f\n", Math.sqrt(sum));
    }
}