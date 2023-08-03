
import java.io.IOException;
import java.util.Scanner;


/**
 * BOJ 11695 구간합 구하기 4 
 * 표준 입출력 사용. (입력도 최악 10만번, 출력도 최악 10만번)
 * s- 258192kb/2492ms
 *
 */
public class Main_11659_구간합구하기4 {

	public static void main(String[] args) throws IOException {

		Scanner sc = new Scanner(System.in);

		int N = sc.nextInt(); // N개의 숫자
		int M = sc.nextInt(); // M개의 구간 정보
		int num[] = new int[N + 1];

		for (int i = 1; i <= N; i++) {
			num[i] = num[i - 1] + sc.nextInt(); // 이전 값 + 현재 입력 값으로 누적값을 저장
		}

		int a = 0, b = 0;
		for (int i = 1; i <= M; i++) {// M개의 구간합 구하기
			a = sc.nextInt(); // 시작값
			b = sc.nextInt(); // 종료값
			// 종료값까지의 누적합 - 시작값 이전까지의 누적합 = a~b까지의 합
			System.out.println(num[b] - num[a - 1]);

		}

	}

}
