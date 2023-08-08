import java.io.BufferedReader;
import java.util.*;
import java.io.*;

public class Solution_1233_김현영 {
	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st; 
		for (int t = 1; t <= 10; t++) {
			st = new StringTokenizer(br.readLine());
			int n =Integer.parseInt(st.nextToken());	//노드 정점의 개수
			
			int result = 1; 
			
			for (int i = 1; i <= n; i++) {
				st = new StringTokenizer(br.readLine());
				int nodeNum = Integer.parseInt(st.nextToken());	//노드 번호
				char opOrNum = st.nextToken().charAt(0);		//노드 내용 : 연산자 or 숫자
				
				//노드가 숫자라면 자식이 없어야함
				if( '0'<= opOrNum && opOrNum <='9' ) {
					if(st.hasMoreTokens())
						result = 0;
				} 
				//노드가 연산자라면 자식이 있어야함
				else {
					if(!st.hasMoreTokens()) {
						result = 0;
					}
				}
				
			}
			
			StringBuilder sb = new StringBuilder();
			sb.append("#").append(t).append(" ").append(result);
			System.out.println(sb.toString());
			 
		} 
	}
}
