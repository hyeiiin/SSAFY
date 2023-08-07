
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

public class Solution_1218_괄호짝짓기 {
 //통과하면 안되는 코드>>그런데 통과 됨 >>테케에 문제가 있음 확인됨.
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T=10;
        boolean flag = true;
        for(int test_case = 1; test_case <= T; test_case++)
        {
            int N =Integer.parseInt(br.readLine()); 
            String line = br.readLine(); //괄호 String 받아오기
            int size = line.length();
            // ( : 0, [ :1, {:2, <:3 
            int sign[] = new int[4];
           
            for(int i=0; i<size; i++) {
            	char ch = line.charAt(i); 
            	if(ch == '(') sign[0]++; //열리는 괄호 들어오면  +1
            	else if(ch == ')') sign[0]--; //닫히는 괄호 들어오면 -1
            	
            	if(ch == '[') sign[1]++;
            	else if(ch == ']') sign[1]--;
            	
            	if(ch == '{') sign[2]++;
            	else if(ch == '}') sign[2]--;
            	
            	if(ch == '<') sign[3]++;
            	else if(ch == '>') sign[3]--;
            }
            
            for(int i = 0; i<sign.length;i++)
            {
            	if(sign[i]!=0) { // 값이 남아있으면  유효하지 않음
            		flag = false;
                    System.out.println("#"+test_case+" "+0);
                    break;
            	}else if(sign[i]==0) {
            		flag = true;
            	}
            }
            if(flag == true) { //값이 남아있지 않으면 (모두 0) 이면 유효함
            	System.out.println("#"+test_case+" "+1);
            }
            	
            	
            }
        }
       
}