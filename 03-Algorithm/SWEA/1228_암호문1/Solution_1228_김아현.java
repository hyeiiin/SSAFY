package ssafy.Swea;

import java.io.*;
import java.util.*;

// 암호문1
public class _1228_Swea {
	
	private static List<String> list;
	public static void main(String[] args) throws Exception{
	
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = 10;
		
		for (int test = 1; test <= T; test++) {
			list = new LinkedList<>();
			int n = Integer.parseInt(br.readLine()); // 원본 암호문의 길이
			
			// 원본 암호문
			StringTokenizer st = new StringTokenizer(br.readLine());
			for (int i = 0; i < n; i++) {
				list.add(st.nextToken());
			}
			
			// 명령어의 개수
			int num = Integer.parseInt(br.readLine());
			
			// 명령어
			String[] arr = new String[num];
			arr = br.readLine().split("I");
			
			for (int i = 1; i <= num; i++) {
				st = new StringTokenizer(arr[i]);
				int start = Integer.parseInt(st.nextToken());
				int size = Integer.parseInt(st.nextToken());
				
				List<String> temp = new LinkedList<>();
			
				for (int j = 0; j < size; j++) {
					temp.add(st.nextToken());
				}
				
				// start 뒤에 temp값 끼워넣기
				list.addAll(start, temp);	
			}
			
			
			// 10개씩 출력
			StringBuilder sb = new StringBuilder();
			sb.append("#");
			sb.append(test).append(" ");
			for (int i = 0; i < 10; i++) {
				sb.append(list.get(i)).append(" ");
			}
			System.out.println(sb.toString());
		}

		
	}

}
