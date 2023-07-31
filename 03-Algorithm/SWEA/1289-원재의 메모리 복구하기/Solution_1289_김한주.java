import java.util.Scanner;
import java.io.FileInputStream;
 

class Solution
{
    static int T;
    public static void main(String[] args) throws Exception{
        Scanner sc = new Scanner(System.in);
        T = sc.nextInt();
        for(int t=0; t<T; t++){
            String data = sc.next(); 
            int count = 0; 
            char prev = '0';
            for(int i=0; i<data.length(); i++){
                if(data.charAt(i)==prev){
 
                }else{
                    prev = data.charAt(i); 
                    count++;
                }
            }System.out.println("#"+(t+1)+" "+count);
        }
        // 0000 
        // 0011
            //세번째 비트 1로 바꿈 
        // 100
            //첫번째 비트 1로 바꿈 
            //두번째 비트 0으로 바꿈 
 
    }
}
