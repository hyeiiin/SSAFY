
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Solution_1208_문예은 {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		int diff = 0; // 최고점과 최저점 높이차
		for (int t = 0; t < 10; t++) { // 테스트케이스 10회 반복
			int[] boxHeight = new int[100]; // 박스들의 가로 길이는 항상 100
			int dumpCount = Integer.parseInt(br.readLine()); // 덤프 횟수 (1~1000 범위)
			st = new StringTokenizer(br.readLine());
			for(int i = 0; i < 100; i++) { 
				boxHeight[i] = Integer.parseInt(st.nextToken()); // 상자 높이 입력
			}
			Arrays.sort(boxHeight); // 상자높이 오름차순 정렬
			diff = boxHeight[99]-boxHeight[0]; // 초기 상태의 최고점과 최저점 높이차
			for(int c = 0; c < dumpCount; c++) { // 주어진 덤프 횟수만큼 반복
				if(diff == 1 || diff == 0) break;
				else {
					boxHeight[0] += 1; // 가장 낮은 상자에 하나 쌓기
					boxHeight[99] -= 1; // 가장 높은 상자에 하나 빼기
					Arrays.sort(boxHeight); 
					diff = boxHeight[99]-boxHeight[0]; // 덤프 완료 후의 높이차 구하기
				}
			}
			System.out.printf("#%d %d%n", t+1, diff);
		}
	}
}
