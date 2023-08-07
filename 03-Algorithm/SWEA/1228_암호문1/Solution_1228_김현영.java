import java.io.*;
import java.util.*;

public class Solution_1228_김현영 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		for (int t = 1; t <= 10; t++) {
			st = new StringTokenizer(br.readLine());
			int n = Integer.parseInt(st.nextToken()); //원본 암호문의 길이 n 입력

			//원본 암호문 입력
			List<String> pw = new ArrayList<>();	
			st = new StringTokenizer(br.readLine());
			for (int i = 0; i < n; i++) {
				pw.add(st.nextToken());
			}
			
			//암호문을 수정할 명령어 개수
			int k = Integer.parseInt(br.readLine());
			
			st = new StringTokenizer(br.readLine());
			
			//암호문 수정 I x, y, s : x 위치, y개 암호 삽입 , s 삽입할 암호문
			for (int i = 0; i < k; i++) {
				if(st.nextToken().equals("I")) {
					int x = Integer.parseInt(st.nextToken());
					int y = Integer.parseInt(st.nextToken());
					//암호문 수정  x위치부터 y개만큼 인덱스를 늘려가면서 s암호문 삽입
					for (int j = x; j < x+y; j++) {
						pw.add(j, st.nextToken());
					}
				}
				
			}  
			
			//수정된 암호문 출력
			StringBuilder sb = new StringBuilder();
			sb.append("#").append(t).append(" ");
			for (int i = 0; i < 10; i++) {
				sb.append(pw.get(i)).append(" ");
			}
			System.out.println(sb.toString());
			
		}
	}
}
