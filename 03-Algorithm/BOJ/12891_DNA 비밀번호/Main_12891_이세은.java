package practice;

import java.io.*;
import java.util.*;

public class Main_12891_이세은 {
	private static int aCnt, cCnt, gCnt, tCnt;
	private static int a = 0, c = 0, g = 0, t = 0;

	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

		StringTokenizer st = new StringTokenizer(br.readLine());
		int s = Integer.parseInt(st.nextToken()); // DNA문자열 길이
		int p = Integer.parseInt(st.nextToken()); // 부분문자열 길이

		String dnaStr = br.readLine(); // dna문자열

		st = new StringTokenizer(br.readLine()); // 각 문자 최소 사용 개수
		aCnt = Integer.parseInt(st.nextToken());
		cCnt = Integer.parseInt(st.nextToken());
		gCnt = Integer.parseInt(st.nextToken());
		tCnt = Integer.parseInt(st.nextToken());

		int cnt = 0; // 가능한 문자열 수
		for (int i = 0; i < p; i++) { // 먼저 p까지는 다 add
			add(dnaStr.charAt(i));
//			System.out.println(dnaStr.charAt(i)+"더하기");
		}
		
		if (check()) {
//			System.out.println("체크");
			cnt++;
		}
		for (int i = 0; i < s - p; i++) {
			add(dnaStr.charAt(i + p));
			delete(dnaStr.charAt(i));
//			System.out.println(dnaStr.charAt(i + p)+"더하기");
//			System.out.println(dnaStr.charAt(i)+"빼기");
			if (check()) {
//				System.out.println("체크");
				cnt++;
			}
		}

		bw.write(String.valueOf(cnt));
		bw.flush();
		bw.close();
		br.close();
	}

	// 카운트 맞는지 확인
	public static boolean check() {

		if (a >= aCnt && c >= cCnt && g >= gCnt && t >= tCnt)
			return true;
		return false;
	}

	// 오른쪽에 추가된 문자 카운트 더하기
	public static void add(char ch) {
		switch (ch) {
		case 'A':
			a++;
			break;
		case 'C':
			c++;
			break;
		case 'G':
			g++;
			break;
		case 'T':
			t++;
			break;
		}
	}

	// 왼쪽에 빠지는 문자 카운트 빼주기
	public static void delete(char ch) {
		switch (ch) {
		case 'A':
			a--;
			break;
		case 'C':
			c--;
			break;
		case 'G':
			g--;
			break;
		case 'T':
			t--;
			break;
		}
	}
}
