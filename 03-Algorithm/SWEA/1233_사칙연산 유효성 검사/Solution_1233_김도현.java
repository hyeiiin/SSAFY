import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution_1233_김도현 {
	
StringTokenizer st;
	public static void main(String[] args) throws NumberFormatException, IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		
		for (int test_case = 1; test_case <= 10; test_case++) {
			int N = Integer.parseInt(br.readLine());
			String [][] arr = new String[N][4];
			
			for (int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine());
				if(st.hasMoreTokens()) arr[i][0] = st.nextToken();
				if(st.hasMoreTokens()) arr[i][1] = st.nextToken();
				if(st.hasMoreTokens()) arr[i][2] = st.nextToken();
				if(st.hasMoreTokens()) arr[i][3] = st.nextToken();
			}
			
			boolean flag = false;
			for (int i = 0; i < arr.length; i++) {
				if(arr[i][2]== null && arr[i][3]==null) {
					if(check(arr[i][1])) {
						flag = true;
						break;
					}
				}else {
					if(!check(arr[i][1])) {
						flag = true;
						break;
					}
					else if(arr[i][2]==null || arr[i][3]==null) {
						flag = true;
						break;
					}
				}
			}
			if(!flag) {
				System.out.println("#"+test_case+" 1");
			}else {
				System.out.println("#"+test_case+" 0");
			}
		}
	}
	
	
	public static boolean check(String str) { // 2번 째가 연산자인지 아닌지
		if(str.equals("+") || str.equals("-") || str.equals("/") || str.equals("*")) {
			return true;
		}
		return false;
	}
}
