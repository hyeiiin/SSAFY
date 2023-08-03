import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_2961_한정수 {
	
	static int N;
	static int[][] input;
	static int min = 10000000;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		input = new int[N+1][2];
		
		for (int i=0 ; i<N; i++) {
			st = new StringTokenizer(br.readLine());
			input[i][0] = Integer.parseInt(st.nextToken());
			input[i][1] = Integer.parseInt(st.nextToken());
		}
		
		
		subSet(0, input[0][0], input[0][1], 1, 0, 0);
		//    cnt     신맛                쓴맛            곱   합
		System.out.println(min);
		
		
		
		
	}
	
	private static void subSet(int cnt, int a, int b, int mult, int plus, int selected_count) {
		if (cnt == N) {
			if(selected_count > 0) {
				//그만. 출력 앞라인은 곱하고 뒷라인은 더하고.
				//각 케이스의 Math.abs(곱 - 합) 의 min 값을 출력.
				if(min > Math.abs(mult-plus)) {
					min = Math.abs(mult-plus);
				}
				return;
				
			}
			else {
				return;
			}
		}
		
		
		//선택함
		subSet(cnt+1, input[cnt+1][0], input[cnt+1][1], mult*input[cnt][0], plus+input[cnt][1], selected_count + 1);
		//선택안함
		subSet(cnt+1, input[cnt+1][0], input[cnt+1][1], mult, plus, selected_count);
		
		
		
	}

}
