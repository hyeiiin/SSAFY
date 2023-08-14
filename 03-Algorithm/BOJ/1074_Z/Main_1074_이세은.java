package ssafyPractice;

import java.io.*;
import java.util.*;

public class Main_1074_이세은 {

//	private static int[][] arr;
	private static int r, c, cnt=0;

	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		int n = Integer.parseInt(st.nextToken()); // 제곱수
		r = Integer.parseInt(st.nextToken()); // 찾는 행
		c = Integer.parseInt(st.nextToken()); // 찾는 열

		int len = (int) Math.pow(2, n);
//		System.out.println(len);
//		arr = new int[len][len]; //배열 선언하는 것 자체가 메모리초과
		divide(0, 0, len);
		
	}

	public static void divide(int i, int j, int size) {
		// 좌상단의 처음 요소를 피봇으로 한다
		// 찾았을 경우
		if (i == r && j == c) {
			System.out.println(cnt);
			return;
		}

		// 현재 위치가 r,c를 포함하는 경우 재귀 수행
		if (i + size > r && i <= r && j + size > c && j <= c) {
			//왼쪽 상단의 시작하는 좌표 업데이트 해주고 크기 줄여서 재귀 수행
			// 1사분면
			divide(i, j, size / 2);
			// 2사분면
			divide(i, j+size / 2, size / 2);
			// 3사분면
			divide(i+size / 2, j, size / 2);
			// 4사분면
			divide(i+size / 2, j+size / 2, size / 2);
		}
		//포함하지 않는 경우 다음 사분면으로 넘어감, 넘어가려는 현재 사분면의 크기 더해준다
		// 만약 크기 2*2라면 총 4개의 요소를 넘어가므로 size*size
		else {
			cnt += size*size;
		}

	}

}
