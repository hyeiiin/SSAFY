import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

/**
 * BOJ 17478 재귀함수가 뭔가요?
 * 풀이 방법1. 반복문으로 짜기 (for문)
 * @author SSAFY
 *
 */
public class Main_17478_재귀함수가뭔가요 {
	static int N; //반복 횟수를 저장할 변수
	public static void main(String[] args) throws NumberFormatException, IOException {	
		//1. 입력 받기
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		
		System.out.println("어느 한 컴퓨터공학과 학생이 유명한 교수님을 찾아가 물었다.");
		
		chatBot(N);
	}
	/**
	 * 반복 문장 출력을 위한 메소드
	 * 1. 첫번째 for문 N번만큼 반복하면서
	 * 	- "재귀함수가 뭔가요? ~~~~잘들어보게~~~" 출력
	 * 	- 마지막 횟수에선 "재귀함수는 자기 자신을 ~~~함수라네"출력
	 * 	- 횟수가 늘어날때마다 ____추가
	 * 2. 두번째 for문 N번만큼 반복하면서
	 * 	- 문장 앞의 ____의 수를 줄여가면서, "라고 답변하였지" 출력
	 * @param count : 반복할 횟수 입력 받기
	 */
	private static void chatBot(int count) {
		StringBuilder prefixSb = new StringBuilder();
		//첫번째 for문
		for (int i = 0; i <= count; i++) {
			if(i==0) {
				System.out.println("\"재귀함수가 뭔가요?\"");
				System.out.println("\"잘 들어보게. 옛날옛날 한 산 꼭대기에 이세상 모든 지식을 통달한 선인이 있었어.");
				System.out.println("마을 사람들은 모두 그 선인에게 수많은 질문을 했고, 모두 지혜롭게 대답해 주었지.");
				System.out.println("그의 답은 대부분 옳았다고 하네. 그런데 어느 날, 그 선인에게 한 선비가 찾아와서 물었어.\"");
			}else {
				prefixSb.append("____"); //count수만큼 ____추가
				String prefix = prefixSb.toString(); //버퍼에 쌓아둔 ____만큼 출력 됨
				
				System.out.println(prefix + "\"재귀함수가 뭔가요?\"");
				if(i == count) {
					System.out.println(prefix +"\"재귀함수는 자기 자신을 호출하는 함수라네\"");
				}else {
				System.out.println(prefix +"\"잘 들어보게. 옛날옛날 한 산 꼭대기에 이세상 모든 지식을 통달한 선인이 있었어.");
				System.out.println(prefix +"마을 사람들은 모두 그 선인에게 수많은 질문을 했고, 모두 지혜롭게 대답해 주었지.");
				System.out.println(prefix +"그의 답은 대부분 옳았다고 하네. 그런데 어느 날, 그 선인에게 한 선비가 찾아와서 물었어.\"");
				}
			}
		}
		
		//두번째 for문. 문장 마무리
		for (int i = 0; i < count; i++) {
			String prefix = prefixSb.toString(); //쌓아둔 ____를 꺼내오기.
			System.out.println(prefix+"라고 답변하였지.");
			
			prefixSb.delete(prefixSb.length()-4, prefixSb.length());
		}
		System.out.println("라고 답변하였지.");
	}

}
