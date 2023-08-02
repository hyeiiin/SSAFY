package ssafy.Boj;
import java.util.*;
public class _15650_Boj {

	private static int n;
	private static int m;
	private static int[] numbers;
	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		n = sc.nextInt();
		m = sc.nextInt();
		
		numbers = new int[m];
		makePer(0,0);

		sc.close();
	}
	
	private static void makePer(int count, int start) {
		StringBuilder sb = new StringBuilder();
		if(count == m) {
			for (int i = 0; i < numbers.length; i++) {
				sb.append(numbers[i]);
				sb.append(" ");
			}
			System.out.println(sb);
			return;
		}
		
		for (int i = start; i < n; i++) {
			numbers[count] = i+1;
			makePer(count+1,i+1);
		}
		
	}

}
