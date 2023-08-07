import java.io.*;
import java.util.*;

public class Main_2493_김현영 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();
		int n = Integer.parseInt(br.readLine());
		st = new StringTokenizer(br.readLine());
		// top 입력
		Stack<Integer> top = new Stack<>(); //탑의 높이
		Stack<Integer> index = new Stack<>();//탑의 인덱스
		
		//레이저를 받는 탑의 인덱스 저장
		int[] laser = new int[n];
		for (int i = 1; i <= n; i++) {
			int nowNum = Integer.parseInt(st.nextToken()); //현재 탑의 높이
			//탑 스택이 비어 있으면 현재 높이와 인덱스 push. laser에 0 저장
			if(top.isEmpty()) {
				top.push(nowNum);
				index.push(i);
				laser[i-1] = 0;
				continue;
			}
			//현재 입력받은 탑의 바로 왼쪽(스택 최상단)이 현재보다 높다면 인덱스 lase에 저장 후 스택에 현재 탑 push 
			if(top.peek() > nowNum) {
				laser[i-1] = index.peek();
				index.push(i);
				top.push(nowNum);
			}
			//스택 최상단이 현재보다 높지않다면 스택pop하며 높은 탑 찾기
			else {
				while(true) {
					if(top.isEmpty()) {
						laser[i-1] = 0;
						index.push(i);
						top.push(nowNum);	
						break;
					}
					if(top.peek()>nowNum) {
						laser[i-1] = index.peek();
						index.push(i);
						top.push(nowNum);
						break;
					}
					else {
						index.pop();
						top.pop();						
					}
				}
			}
			  
		}
		 
		 
		
		
		//출력
		for (Integer i : laser) {
			sb.append(i).append(" ");
		}
		System.out.println(sb.toString());

	}

}
