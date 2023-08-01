import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// N과 M(1)
public class Main_15649_문예은 {
	static int N,M; // 1부터 N까지 자연수 중에서 중복 없이 M개를 고른 수열
	static int[] numbers; // 결과저장배열
	static boolean[] isSelected; // 방문여부 체크배열
	static StringBuilder sb = new StringBuilder();
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken()); // 1부터 N까지
		M = Integer.parseInt(st.nextToken()); // M개 고르는 순열
		
		isSelected = new boolean[N+1]; // 1부터 N까지의 방문처리 배열, 0은 임시
		numbers = new int[M]; // M개만큼 골라 출력할 배열
		
		permutation(0);
		
		System.out.println(sb); // StringBuilder에 쌓아 한번에 결과 출력
	}

	private static void permutation(int cnt) {
		if(cnt == M) { // 뽑을 횟수만큼 뽑았으면
			for(int i = 0; i < M; i++) { // 순열 완성
				sb.append(numbers[i]+" ");
			}
			sb.append("\n");
			return;
		}
		for (int i = 1; i <= N; i++) { // 1부터 N까지 자연수 반복
			if(!isSelected[i]) { // 이미 뽑힌 숫자면 i 값 증가
				// 뽑힌 수가 아니라면? 해당 숫자 i 넣어주기
				numbers[cnt]= i;
				isSelected[i]= true; // 해당 숫자 인덱스에 방문처리
				// 다음 자리 순열 뽑기
				permutation(cnt+1); 
				isSelected[i]= false; // 재귀 호출 후 방문처리 해제
			}
		}
	}
}
