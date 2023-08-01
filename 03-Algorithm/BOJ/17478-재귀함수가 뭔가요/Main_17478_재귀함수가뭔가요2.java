import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * BOJ 17478 재귀 함수가 뭔가요?
 * 풀이 방법 2. 재귀 호출로 짜기.
 * @author KimDaol
 * S - 14880KB/ 152ms
 *
 */
public class Main_17478_재귀함수가뭔가요2 {
	
	static int N;//재귀 반복 횟수를 저장할 변수
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		
		System.out.println("어느 한 컴퓨터공학과 학생이 유명한 교수님을 찾아가 물었다.");
		
		chatBot(0);
	}
	
	/**
	 * 0번째부터 N번째 호출까지 반복해서 호출할 메서드
	 * - 문장 앞에 추가될 ____를 호출 횟수 만큼 StringBuilder에 쌓기
	 * - 기저 조건에 해당하는 횟수 도달 전까지 반복 문장 출력.
	 * - 기저 조건에 도달하면 마무리 문장 출력.
	 * - 반복 호출된 문장이 종료 되면서 마무리 문장 출력
	 * @param count : 현재 반복 횟수
	 */
	private static void chatBot(int count) {
		StringBuilder prefixSb = new StringBuilder();
		
		for(int i = 0; i<count; i++) {
			prefixSb.append("____");//count 만큼 ____추가
		}
		String prefix = prefixSb.toString(); //버퍼에 쌓아둔 _____를 사용하기 위해서 String 객체로 만듦
		
		System.out.println(prefix + "\"재귀함수가 뭔가요?\"");
		if(count == N) { //반복 횟수 N에 도달하면 출력 할 문장
			System.out.println(prefix + "\"재귀함수는 자기 자신을 호출하는 함수라네\"");
		}else {	//반복횟수 N에 도달하지 않았으면 출력 할 문장
		System.out.println(prefix + "\"잘 들어보게. 옛날옛날 한 산 꼭대기에 이세상 모든 지식을 통달한 선인이 있었어.");
		System.out.println(prefix + "마을 사람들은 모두 그 선인에게 수많은 질문을 했고, 모두 지혜롭게 대답해 주었지.");
		System.out.println(prefix + "그의 답은 대부분 옳았다고 하네. 그런데 어느 날, 그 선인에게 한 선비가 찾아와서 물었어.\"");
		chatBot(count+1); //횟수 추가해서 또 호출 -> N될 때 까지
		}
		//N번의 호출이 끝나면 return 되면서 출력될 마무리 문장
		System.out.println(prefix + "라고 답변하였지.");
	}

}
