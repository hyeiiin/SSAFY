import java.io.*;
import java.util.*;

public class Main_1074_김현영 {

	static int n;

	// 2 1
	// 3 4
	// 배열을 위와 같이 4분면으로 나눠 재귀로 함수 호출
	static int findLoc(int len, int r, int c) {
		// 배열의 크기가 2x2일 때 현재 위치의 값 반환
		// 0 1
		// 2 3
		if (len == 2) {
			int now = r * 2 + c % 2;
			return now;
		}

		// 좌표가 1, 2사분면일 때
		if (r < len / 2) { 
			// 좌표가 2사분면일 때
			if (c < len / 2)
				return findLoc(len / 2, r, c);
			// 좌표가 1사분면일 때
			else 
				return len / 2 * len / 2 + findLoc(len / 2, r, c - len / 2);

		}
		// 좌표가 3, 4사분면일 때
		else { // r >= len/2
			// 좌표가 3사분면일 때
			if (c < len / 2) 
				return (len / 2 * len) + findLoc(len / 2, r - len / 2, c);
			// 좌표가 4사분면일 때
			else 
				return (len / 2 * len) + (len / 2 * len / 2) + findLoc(len / 2, r - len / 2, c - len / 2);
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		StringTokenizer st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken()); // 배열의 크기에 사용. 배열의 크기 = 2^n
		int r = Integer.parseInt(st.nextToken()); // 찾으려는 좌표의 행
		int c = Integer.parseInt(st.nextToken()); // 찾으려는 좌표의 열

		int len = (int) Math.pow(2, n); // 배열의 길이 구하기

		// 배열의 길이와 찾으려는 좌표의 값을 넘겨줘서 방문 순서 찾기
		System.out.println(findLoc(len, r, c));

	}

}
