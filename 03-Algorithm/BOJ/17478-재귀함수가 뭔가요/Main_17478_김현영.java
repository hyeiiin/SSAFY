import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_17478_김현영 {

	public static void func(int i, int n) {
		String s = "";
		for (int j = 0; j < i; j++) {
			s += "____";
		}

		System.out.println(s + "\"재귀함수가 뭔가요?\"");
		if (i < n) {
			System.out.println(s + "\"잘 들어보게. 옛날옛날 한 산 꼭대기에 이세상 모든 지식을 통달한 선인이 있었어.");
			System.out.println(s+ "마을 사람들은 모두 그 선인에게 수많은 질문을 했고, 모두 지혜롭게 대답해 주었지.");
			System.out.println(s+ "그의 답은 대부분 옳았다고 하네. 그런데 어느 날, 그 선인에게 한 선비가 찾아와서 물었어.\"");
			func(i + 1, n);
		}
		if (i == n) {
			System.out.println(s + "\"재귀함수는 자기 자신을 호출하는 함수라네\"");
//			return;
		}
		System.out.println(s + "라고 답변하였지.");

	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		int n = Integer.parseInt(st.nextToken());
		System.out.println("어느 한 컴퓨터공학과 학생이 유명한 교수님을 찾아가 물었다.");

		func(0, n);

	}

}
