package algorithm.swea;

import java.util.*;
import java.io.*;

//괄호 짝짓기
public class Solution_1218_문혜린 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();
		
		for(int t=1; t<=10; t++) {
			int len = Integer.parseInt(br.readLine()); //테스트케이스 길이
			st = new StringTokenizer(br.readLine());
			String str = st.nextToken(); //괄호 문자열
			Stack<Character> stack = new Stack<>();
			
			int i;
			for(i=0; i<len; i++) {
				char p = str.charAt(i);
				
				//여는 괄호들은 스택에 삽입
				if(p == '(' || p == '[' || p == '{' || p == '<') {
					stack.push(p);
				}
				//닫는 괄호가 있으면 스택에서 pop해서 맞는지 비교
				else {
					char c = stack.pop(); //여는 괄호
					if(p == ')') {
						if(c != '(') {
							break;
						}
					}
					else if(p == ']') {
						if(c != '[') {
							break;
						}
					}
					else if(p == '}') {
						if(c != '{') {
							break;
						}
					}
					else if(p == '>') {
						if(c != '<') {
							break;
						}
					}
				}
			}
			if(i==len) { //for문 다 돌았을 경우 -> 유효
				sb.append("#"+t+" "+1+"\n");
			}
			else { //for문 다 못 돌았을 경우 조건을 충족 못해서 빠져나온 것이므로 -> 유효하지 않음
				sb.append("#"+t+" "+0+"\n");
			}
		}
		System.out.println(sb);
	}

}
