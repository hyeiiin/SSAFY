
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

// N과 M(2)
public class Main_15650_조은서 {
	static int n, m;
	static int[] numbers;
	static boolean[] isSelected; 
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] str = br.readLine().split(" ");
		n = Integer.parseInt(str[0]);
		m = Integer.parseInt(str[1]);
		
		numbers = new int[n];
		isSelected = new boolean[n];
		
		combi(0, 0);

	}
	
	private static void combi(int cnt, int init) {
		if(cnt == m) {
			for(int i =0; i<m; i++) {
				System.out.print(numbers[i] + " ");
			}
			System.out.println();
			return;
		}
		for(int i = init; i<n; i++) {
			if(isSelected[i] == true) {
				continue;
			}
			numbers[cnt] = i+1;
			isSelected[i] = true;
			combi(cnt+1, i+1);
			isSelected[i] = false;
			
		}
	}

}
