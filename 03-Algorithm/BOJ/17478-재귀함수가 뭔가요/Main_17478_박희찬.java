package BOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	static int N;
	
	public static void chatbot(int M) {
		String under = "";

		for (int i = 0; i < 4 * (N -M); i++) {
			under += "_";
		}
		
		String str1 = "\"재귀함수가 뭔가요?\"";
		String str2 = "\"잘 들어보게. 옛날옛날 한 산 꼭대기에 이세상 모든 지식을 통달한 선인이 있었어.";
		String str3 = "마을 사람들은 모두 그 선인에게 수많은 질문을 했고, 모두 지혜롭게 대답해 주었지.";
		String str4 = "그의 답은 대부분 옳았다고 하네. 그런데 어느 날, 그 선인에게 한 선비가 찾아와서 물었어.\"";
		String str5 = "라고 답변하였지.";
		String end = "\"재귀함수는 자기 자신을 호출하는 함수라네\"";
		
		if (M > 0) {
			System.out.println(under + str1);
			System.out.println(under + str2);
			System.out.println(under + str3);
			System.out.println(under + str4);
			chatbot(M - 1);
			System.out.println(under + str5);
//			return;
		} else {
			System.out.println(under + str1);
			System.out.println(under + end);
			System.out.println(under + str5);
		}
		
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
	
		
		N = Integer.parseInt(st.nextToken());
		
		System.out.println("어느 한 컴퓨터공학과 학생이 유명한 교수님을 찾아가 물었다.");
		chatbot(N);
		
	}
}
