package ssafyPractice;

import java.io.*;
import java.util.*;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Solution_1228_이세은 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		for (int test_case = 1; test_case <= 10; test_case++) {
			int n = Integer.parseInt(br.readLine()); // 원본 암호문 길이
			StringTokenizer origin = new StringTokenizer(br.readLine());
			List<String> rslt = new ArrayList<>();
			
			for(int i=0; i<n; i++) {
				rslt.add(origin.nextToken());
			}
			int q = Integer.parseInt(br.readLine()); // 명령어 개수
			
			StringTokenizer st = new StringTokenizer(br.readLine(), "I");
			for(int i=0; i<q; i++) {
				StringTokenizer st1 = new StringTokenizer(st.nextToken());
				int idx = Integer.parseInt(st1.nextToken()); //idx번째에
				int cnt = Integer.parseInt(st1.nextToken()); //cnt개 삽입
				for(int j=0; j<cnt; j++)
					rslt.add(idx++, st1.nextToken());	
			}
			StringBuilder sb = new StringBuilder();
			sb.append("#"+test_case+" ");
			for (int i=0; i<10; i++) {
				sb.append(rslt.get(i) + " ");
			}
			System.out.println(sb.toString());
		}

	}

}
