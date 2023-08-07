import java.io.*;
import java.util.*;

public class Main_1158_김현영 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int n = Integer.parseInt(st.nextToken());
		int k = Integer.parseInt(st.nextToken());
		
		//n명의 원 그리기
		Stack<Integer> circle = new Stack<>();
		for (int i = 1; i <= n; i++) {
			circle.push(i);
		}
		
		//요세푸스 순열을 저장할 스택
		Stack<Integer> Josephus = new Stack<>();
		int index = k;
		//원이 없어질 때까지 반복
		while(!circle.isEmpty()) {
			//circle 스택에서 remove 할 인덱스 설정
			index = index%circle.size() -1 ;
			if(index < 0)
				index = circle.size()-1 ;
			Josephus.push(circle.remove(index) );
			index+=k;
		}
		
		//요세푸스 순열 출력
		StringBuilder sb = new StringBuilder();
		sb.append("<");
		
		for (Integer i : Josephus) {
			sb.append(i).append(", ");
		}
		sb.deleteCharAt(sb.length()-1);
		sb.deleteCharAt(sb.length()-1);
		sb.append(">");
		System.out.println(sb.toString());
		
	}

}
