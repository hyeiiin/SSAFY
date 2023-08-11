package baekjun;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main_16435_김도현 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken()); // 과일 수
		int L = Integer.parseInt(st.nextToken()); // 새의 초기 길이
		int [] len = new int[N];
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			len[i] = Integer.parseInt(st.nextToken());
		}
		
		Arrays.sort(len);
		
		for (int i = 0; i < len.length; i++) {
			if(L>=len[i]) {
				L+=1;
			}
		}
		System.out.println(L);
	}

}
