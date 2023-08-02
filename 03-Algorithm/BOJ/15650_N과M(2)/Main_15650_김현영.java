import java.util.Scanner;
import java.util.*;

public class Main_15650_김현영 {
	
	//수열을 저장할 리스트
	static List<Integer> result = new ArrayList<>();
	static int n, m;
	
	
	public static void func(int start, int count) {
		result.add(start);
			
		//수열의 개수가 m이라면 출력
		if(count == m) {
			StringBuilder sb = new StringBuilder();
			for (Integer i : result) {
				sb.append(i).append(" ");
			}
			System.out.println(sb.toString());
		}
		//수열의 개수가 m이 아니라면 다음 요소를 찾아감
		else {
			for (int i = start+1; i <= n; i++) {
				func(i, count+1);
				result.remove(result.size()-1);
			}
		}
	}
	
	
	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		n = sc.nextInt();
		m = sc.nextInt();
		
		for (int i = 1; i <= n-m+1; i++) {
			result.clear();
			func(i,1);
		} 
	}
}
