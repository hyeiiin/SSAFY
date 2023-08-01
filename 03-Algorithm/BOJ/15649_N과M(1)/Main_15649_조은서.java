import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

// 1부터 N까지 자연수 중에서 중복 없이 M개를 고른 수열 => 순열
public class Main_15649_조은서 {
	public static int n;
	public static int m;
	public static boolean[] isSelected;
	public static int[] numbers;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		String[] str = br.readLine().split(" ");
		n = Integer.parseInt(str[0]);
		m = Integer.parseInt(str[1]);
		
		isSelected = new boolean[n]; // 중복 없이 고르기 위해 선택된 숫자인지 기록하기 위한 배열
		numbers = new int[m]; // 수열을 저장하기 위한 배열
		
		perm(0, isSelected, numbers);
		
	}
	
	
	public static void perm(int cnt, boolean[] isSelected, int[] numbers ) {	
		if (cnt == m) {
			for(int j=0; j<m; j++) {
				System.out.print(numbers[j]+1 + " "); // 1부터 출력하기 위해 numbers[] + 1
			}
			System.out.println();
			return;
		}
		else {
			for(int i=0; i<n; i++) {
				if (isSelected[i] == true) { // 이미 선택됐던 숫자면 넘어가기
					continue;
				}
				numbers[cnt] = i; 
				isSelected[i] = true;
				perm(cnt+1, isSelected, numbers);

				isSelected[i] = false;

			}
		}
	}
}
