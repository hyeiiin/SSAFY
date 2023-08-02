import java.io.IOException;
import java.util.Scanner;

/**
 * SWEA 1208 Flatten
 * S - 22,240 kb/ 150 ms
 */
public class Solution_1208_Flatten2 {
	static int dumpCnt, min, max, height, boxCount[];

	public static void main(String[] args) throws NumberFormatException, IOException {

		Scanner sc = new Scanner(System.in);
		int TC = 10;
		for (int t = 1; t <= TC; ++t) {
			dumpCnt = sc.nextInt();
			boxCount = new int[101];// 모든 위치에서 상자의 높이는 1이상 100이하로 주어진다.
			min = Integer.MAX_VALUE;
			max = Integer.MIN_VALUE;
			for (int i = 0; i < 100; ++i) {// 가로 길이는 항상 100으로 주어진다.
				height = sc.nextInt();
				boxCount[height]++;//높이에 해당하는 인덱스에 박스의 개수 카운팅
				if (height < min)
					min = height;//가장 낮은 높이
				if (height > max)
					max = height; //가장 높은 높이 
			}

			System.out.println("#" + t + " " + getFlatten());
		}
		sc.close();
	}

	private static int getFlatten() {
		for (int d = 1; d <= dumpCnt; ++d) { //주어진 dump 횟수 만큼 반복
			if (max - min <= 1)
				break; // 평탄화 작업 불가(만약,1이라면 계속 도돌이표의 상황이 되므로)
			boxCount[min + 1]++;// 제일 낮은 곳에 상자 하나 쌓고 쌓은 상자 높이의 카운트 증가시키기
			boxCount[max - 1]++;// 제일 높은 곳에 상자 하나 치우고 치운 상자 높이의 카운트 감소시키기
			if (--boxCount[min] == 0)
				min++; // 제일 낮았던 곳 카운트 감소시키고 0이되면 최소값 변경
			if (--boxCount[max] == 0)
				max--; // 제일 높았던 곳 카운트 감소시키고 0이되면 최대값 변경
		}
		return max - min; // 평탄화 후의 차이값 리턴
	}
}
