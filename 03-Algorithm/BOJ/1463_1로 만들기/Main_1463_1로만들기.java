
import java.util.*;

/**
 * 1. X가 3으로 나누어 떨어지면, 3으로 나눈다.
 * 2. X가 2로 나누어 떨어지면, 2로 나눈다.
 * 3. 1을 뺀다.
 *
 */
public class Main_1463_1로만들기{
    public static int[] d;//N을 1로 만드는 최소 횟수
    
    public static int go(int n) {
        if (n == 1) { //1에 도달하면 종료
            return 0;
        }
        
        if (d[n] > 0) { //메모제이션 
            return d[n];
        }
        
        d[n] = go(n-1) + 1; //n-1 
        
        if (n%2 == 0) { //2로 나누어 떨어지면 
        	//n을 2로 나누었을때 나온 숫자를 1로 만드는 최소 횟수 +1 = 현재 최소 횟수
            int temp = go(n/2)+1;
            //횟수 갱신
            if (d[n] > temp) {
                d[n] = temp;
            }
        }
        
        if (n%3 == 0) { //3으로 나누어 떨어지면
        	//n을 3으로 나누었을때 나온 숫자를 1로 만드는 최소 횟수 +1 = 현재 최소 횟수
            int temp = go(n/3)+1;
            //횟수 갱신
            if (d[n] > temp) {
                d[n] = temp;
            }
        }
        return d[n];
    }
    
    public static void main(String args[]) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        d = new int[n+1];
        System.out.println(go(n));
    }
}