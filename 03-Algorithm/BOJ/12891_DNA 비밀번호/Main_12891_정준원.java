package sdf;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_12891_정준원 {
	static int[] cnt = new int[4];

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub

		long num;
		long size;

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		StringTokenizer st = new StringTokenizer(br.readLine());

		num = Integer.parseInt(st.nextToken());
		size = Integer.parseInt(st.nextToken());

		char[] ch = new char[(int) num];

		ch = br.readLine().toCharArray(); // 예시 CCTGGATTG

		int[] arr = new int[4];

		st = new StringTokenizer(br.readLine());

		for (int i = 0; i < 4; i++) {
			arr[i] = Integer.parseInt(st.nextToken()); // 에시 2 0 1 1
		}

		int start = 0;
		long end = size - 1;

		for (int i = start; i <= end; i++)
			put(ch[i]); // ch 는 초기문자열 담은 배열

		int res = 0;

		while (true) {

			if (available(arr)) {
				res++;
			}

			remove(ch[start]);
			start++;
			end++;

			if (end > ch.length - 1)
				break;

			put(ch[(int) end]);

		}

		System.out.print(res);
	}

	static boolean available(int[] arr) {
		for (int i = 0; i < 4; i++) {
			if (arr[i] > cnt[i]) // 최소 포함개수 arr , cnt는 새로만든배열 . 슬라이딩 윈도우 수행을 위해서 생성.
				return false;
		}

		return true;
	}

	static void put(char ch) {
		if (ch == 'A') {
			cnt[0]++;
		}
		if (ch == 'C') {
			cnt[1]++;
		}
		if (ch == 'G') {
			cnt[2]++;
		}
		if (ch == 'T') {
			cnt[3]++;
		}
	}

	static void remove(char ch) {
		if (ch == 'A') {
			cnt[0]--;
		}
		if (ch == 'C') {
			cnt[1]--;
		}
		if (ch == 'G') {
			cnt[2]--;
		}
		if (ch == 'T') {
			cnt[3]--;
		}
	}
}
