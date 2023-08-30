import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_1010_이세은 {

	static int n, m;
	static int[][] bridge;

	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		StringBuilder sb = new StringBuilder();

		for (int test_case = 1; test_case <= T; test_case++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			n = Integer.parseInt(st.nextToken());
			m = Integer.parseInt(st.nextToken());
			bridge = new int[30][30]; //n과 m 최댓값
			
			// m개의 사이트 중 n개 뽑기
			sb.append(setB(m,n)).append("\n");//dp실행 위한 메서드 실행

		}
		System.out.print(sb.toString());
	}

	public static int setB(int i, int j) {
	
		if(bridge[i][j] != 0) { //값이 있는 경우 그대로 리턴
			return bridge[i][j];
		}
		//메모이제이션
		//n과 m이 같을 경우 경우의 수는 1개 밖에 없음
		if( j==0 || i == j) {
			return bridge[i][j] = 1;
		}
		
		return bridge[i][j] = setB(i-1, j) + setB(i-1, j-1); //선택, 비선택
	}

}
