import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Solution_1208_조은서 {
	public static int tc;
	public static int dump;
	public static int[] boxHeight;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		for(tc = 1; tc <= 10; tc++) {
			dump = Integer.parseInt(br.readLine()); // 덤프수
			String[] str = br.readLine().split(" "); // 공백을 기준으로 값을 배열에 저장
			boxHeight = new int[str.length];
			for(int i=0; i<str.length; i++) {
				boxHeight[i] = Integer.parseInt(str[i]);
			}
			
			flatten(1);
		}
	}
	
	// 평탄화
	// 상자 높이 정렬 후, 제일 높은 곳의 높이 -1, 제일 낮은 곳의 높이 + 1를 반복
	// 그리고 count를 늘리면서 덤프수와 같아지면, 상자 높이 정렬 후 가장 높은 곳과 낮은 곳의 차이를 출력
	private static void flatten(int cnt) {
		int len = boxHeight.length; 
		if(cnt == dump+1) { // *덤프가 완료된 후 출력!
			Arrays.sort(boxHeight);
			System.out.println("#" + tc + " " +(boxHeight[len - 1] - boxHeight[0]));
		}
		else {
			Arrays.sort(boxHeight); // 상자 높이 정렬
			boxHeight[len-1] -= 1;
			boxHeight[0] += 1;
			flatten(cnt + 1);
		}
	}
}
