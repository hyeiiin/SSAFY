import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main_1786_이도훈 {


	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));


		String T = br.readLine();
		String P = br.readLine();


		// 실패 함수 구하기
		int[] failureFunc = failureFunc(P);

		// KMP 돌리기
		int i = 0; // 텍스트 문자열 인덱스
		int j = 0; // 패턴 문자열 인덱스

		int cnt = 0;
		StringBuilder sb = new StringBuilder();

		while (i < T.length()) {
			// 문자가 같은지 확인
			if (T.charAt(i) == P.charAt(j)) {
				i++;
				j++;
			}

			// 끝까지 도달했다면
			if (j == P.length()) {
				cnt++;
				sb.append(i - j + 1).append(" ");
				j = failureFunc[j - 1];
			} else if(i < T.length() && T.charAt(i) != P.charAt(j)){
				if (j != 0) {
					j = failureFunc[j - 1];
				} else {
					i++;
				}
			}
		}

		System.out.println(cnt);
		System.out.println(sb);

	}

	static int[] failureFunc(String pattern) {
		int[] failureFunc = new int[pattern.length()];

		int len = pattern.length();
		int i = 1;
		int j = 0;

		// 문자열 길이 1은 무조건 0임
		failureFunc[0] = 0;

		while (i < len) {
			// 문자가 동일
			if (pattern.charAt(i) == pattern.charAt(j)) {
				j++;
				failureFunc[i] = j;
				i++;
			} else {
				// j값이 0이 아닌 경우
				if (j != 0) {
					j = failureFunc[j - 1];
				} else {
					failureFunc[i] = 0;
					i++;
				}
			}
		}

		return failureFunc;
	}





}
