import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.time.Year;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import java.util.StringTokenizer;

public class 연습문제1 {
	// 펠린드롬 만들기
	static int N;
	static int res;
	static int[] arr;
	static int[] dp;
	static int answer = Integer.MAX_VALUE;
	static int yellow[];
	static int blue[];

	public static void main(String[] args) throws Exception {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		StringTokenizer st;
		StringBuilder sb = new StringBuilder();
		String str;

		dp = new int[9];
		yellow = new int[10];
		blue = new int[10];

		yellow[1] = 1;
		blue[1] = 1;

//		 노 파
//		f2  노 파 노

		// f3= 5 노 파 /노 / 노 파

//		노 파/ 노/ 노 파/노 파/ 노 

		// f4=8
		solve();

		System.out.println(yellow[8] + blue[8]);

	}

	static void solve() {

		for (int a = 2; a <= 8; a++) {

			yellow[a] = yellow[a - 1] + blue[a - 1];
			blue[a] = yellow[a - 1];
//			System.out.println("yellow " + yellow[a]);
//			System.out.println("blue " + blue[a]);
		}

	}

}
