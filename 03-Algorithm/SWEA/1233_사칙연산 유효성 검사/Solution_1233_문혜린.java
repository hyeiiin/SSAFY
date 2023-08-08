package algorithm.swea;

import java.util.*;
import java.io.*;

//사칙연산 유효성 검사
public class Solution_1233_문혜린 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		boolean isCal; //계산이 가능한지 여부 flag
		
		for(int t=1; t<=10; t++) {
			isCal = true; //계산이 가능한지 여부 flag
			
			int N = Integer.parseInt(br.readLine()); //노드 개수
			
			//노드가 짝수이면 계산 불가
			if(N%2 == 0) {
				isCal = false;
			}
			for(int i=1; i<=N; i++) {
				String str = br.readLine();
				//계산이 가능한 상태이고 (이미 계산 불가면 더 안 돌리도록) 부모 노드일 경우
				if(isCal && i <= N/2) {
					char c = str.split(" ")[1].charAt(0); //노드 정점 값
					//부모 노드일 경우 연산자로만 이루어져야 함
					if(c == '+' || c == '-' || c == '*' || c == '/') {}
					else { //연산자가 아니라면
						isCal = false;
					}
				}
				//계산이 가능한 상태이고 (이미 계산 불가면 더 안 돌리도록) 리프 노드일 경우
				else if(isCal && i > N/2) {
					char c = str.split(" ")[1].charAt(0); //노드 정점 값
					//리프 노드일 경우 양의 정수로만 이루어져야 함
					if(c == '+' || c == '-' || c == '*' || c == '/') { //양의 정수가 아니라면
						isCal = false;
					}
				}
			}
			if(isCal) { //계산이 가능하면
				sb.append("#"+t+" "+1+"\n");
			}
			else if(!isCal){ //계산이 불가능하면
				sb.append("#"+t+" "+0+"\n");
			}
		}
		System.out.println(sb);
	}

}
