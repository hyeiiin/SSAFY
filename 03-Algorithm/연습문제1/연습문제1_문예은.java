import java.util.Arrays;
import java.util.Scanner;
/* 노      파
 * 1   1   2 1
 * 1*2 1*1 3 2 
 * 2*2 1*1 5 3
 * 3*2 2*1 8 4
 * 5*2 3*1 13
 * 8*2 5*1 21
 * 
 * 노+파*2 노*1
 */
public class 연습문제1_문예은 { // 아파트 색칠하기
	static long yellow[], blue[];
	
	private static long apart(int n) { // 피보나치 n향 구하기
		if(n==1) return yellow[n]+blue[n];
		if (n==2) {
			yellow[n] = 1;
			blue[n] = 1; 
		}
		if(yellow[n]==-1 && blue[n]==-1) { // 메모 되어있는지 확인
			apart(n-1);
			yellow[n] = yellow[n-1] + blue[n-1]; // 메모 안되어있으면 메모하기 
			blue[n] = yellow[n-1];
		}
		return yellow[n]*2+blue[n]; // 메모 되어있으면  출력하기
		
	}
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int N = sc.nextInt();
		
		yellow = new long[N+1];
		blue = new long[N+1];
		
		Arrays.fill(yellow, -1); // 메모되지 않은 상태로 초기화
		Arrays.fill(blue, -1);
		
		yellow[1]=1; // 미리 기저조건 값 메모하기 
		blue[1]=1;
		System.out.println(apart(N));

	}
}

