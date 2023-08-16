import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {  // BOJ_16435
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder sb = new StringBuilder();
	static StringTokenizer st;
	static int[] lst;
	static int N, L;  // 과일의 개수, 초기 길이
	static int maxLength = 0;
	
	public static void main(String[] args) throws IOException {
		st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		L = Integer.parseInt(st.nextToken());
		
        // 과일 초기화
		lst = new int[N];
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			lst[i] = Integer.parseInt(st.nextToken());
		}
		
        // 선 정렬
		Arrays.sort(lst);
        // 앞에서부터 먹을 수 있을 때까지 반복.
		for (int i = 0; i < N; i++) {
			if (lst[i] <= L) {
				L++;
			} else break;
		}
		
		sb.append(L);
		System.out.println(sb);
		
	}  // Main
	
}
