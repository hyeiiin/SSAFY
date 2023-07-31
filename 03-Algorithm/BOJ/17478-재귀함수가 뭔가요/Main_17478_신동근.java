package 백준;

import java.util.*;
import java.io.*;

public class Main_17478_신동근 {

	static int N;
	static StringBuilder sb;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());	// 재귀 몇번 돌릴지 입력받기
		sb = new StringBuilder();
		System.out.println("어느 한 컴퓨터공학과 학생이 유명한 교수님을 찾아가 물었다.");
		
		printRecursion(0);	// 0번부터 시작
		System.out.print(sb);

	}
	
	// print로 재귀함수 돌리는 메서드
	public static void printRecursion(int idx) {
		String str = "";
		for(int i=0; i<idx; i++) {
			str += "____";
		}
		sb.append(str + "\"재귀함수가 뭔가요?\"").append("\n");
		// 해당 재귀 번째가 도달하면
		if(idx == N) {
			sb.append(str + "\"재귀함수는 자기 자신을 호출하는 함수라네\"").append("\n");
			sb.append(str + "라고 답변하였지.").append("\n");
			return;	// 메서드 종료해줌 (무한 메서드 호출 방지)
		}
		sb.append(str + "\"잘 들어보게. 옛날옛날 한 산 꼭대기에 이세상 모든 지식을 통달한 선인이 있었어.").append("\n");
		sb.append(str + "마을 사람들은 모두 그 선인에게 수많은 질문을 했고, 모두 지혜롭게 대답해 주었지.").append("\n");
		sb.append(str + "그의 답은 대부분 옳았다고 하네. 그런데 어느 날, 그 선인에게 한 선비가 찾아와서 물었어.\"").append("\n");
		printRecursion(idx + 1);	// 재귀 호출 증가
		sb.append(str + "라고 답변하였지.").append("\n");
	}

}
