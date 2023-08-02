import java.util.Scanner;
import java.util.*;

public class Main_15650_김현영 {
	static List<Integer> result = new ArrayList<>();
	static int n, m;
	
	
	public static void func(int start, int count) {
		result.add(start);
			
		if(count == m) {
			StringBuilder sb = new StringBuilder();
			for (Integer i : result) {
				sb.append(i).append(" ");
			}
			System.out.println(sb.toString());
		}
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
//			System.out.println(func(i, 1));
		}

		
		

		
		

		
		
		
	}
}
