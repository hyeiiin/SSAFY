package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;



public class Main_16435_전상혁 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int N = Integer.parseInt(st.nextToken()); //과일의 개수
		int L = Integer.parseInt(st.nextToken()); //스네이크 버드 초기 길이
		
		//스네이크버드는 자신 길이보다 작거나 같은 높이에 있는 과일을 먹을 수 있음
		int[] high = new int[N];
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			high[i] = Integer.parseInt(st.nextToken());
		}
		
		Arrays.sort(high);
		
		for (int i = 0; i < N; i++) {
			//자신보다 작거나 같은 높이
			if (L>=high[i]) {
				//과일 하나 먹으면 길이 +1
				L++;
			}
		}
		
		System.out.println(L);
		
	}
}
