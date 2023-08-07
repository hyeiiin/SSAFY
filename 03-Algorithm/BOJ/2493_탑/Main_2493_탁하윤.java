package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;
import java.util.StringTokenizer;

public class Main_2493_탁하윤 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		int N = Integer.parseInt(br.readLine());	// 탑의 개수
		Stack<Integer> height = new Stack<>();	// 높이들이 들어있는 스택
		Stack<Integer> index = new Stack<>();	// 높이들의 인덱스 번호가 들어가 있는 스택

		StringTokenizer st = new StringTokenizer(br.readLine());
		for(int i=0; i<N; i++) {
			int building = Integer.parseInt(st.nextToken());	// 입력으로 들어온 탑의 높이
			while(true) {
				if(!height.isEmpty()) {	// height 스택이 비어있지 않다면
					int top = height.peek();	// 지금까지 들어온 높이 스택들 중 제일 최상위 데이터를 top에 대입
					if(top>=building) {	// 만약 top이 현재 들어온 탑의 높이보다 크거나 같다면
						sb.append(index.peek()+" ");	// 인덱스의 최상위 데이터 번호를 출력하고
						height.push(building);	// 현재 높이와 인덱스 번호를 스택에 push 한다.
						index.push(i+1);
						break;
					} else {	// 만약 top보다 크다면 앞의 데이터는 필요없기 때문에 pop한다.
						height.pop();
						index.pop();
					}
				} else {
					sb.append("0 ");	// 비어있다면 0 출력하고
					height.push(building);	// 스택에 현재 값 push 한다.
					index.push(i+1);
					break;
				}				
			}
		}
		System.out.println(sb);
	}

}
