package sdf;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Deque;
import java.util.List;
import java.util.StringTokenizer;

public class Main_11659_정준원 {

	static int n;
	static int m;
	static int w;
	static int v;
	static int[] arr;

	public static void main(String args[]) throws NumberFormatException, IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		StringTokenizer st;
		st = new StringTokenizer(br.readLine(), " ");

		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());

		arr = new int[n + 1];

		st = new StringTokenizer(br.readLine(), " ");

		arr[0] = 0;

		for (int i = 1; i <= n; i++) {
			arr[i] = Integer.parseInt(st.nextToken()) + arr[i - 1]; //dp 느낌으로 arr 에 0부터 해당 인덱스까지의 값을 넣ㄴ
		}

		for (int i = 0; i < m; i++) {
			st = new StringTokenizer(br.readLine(), " ");

			v = Integer.parseInt(st.nextToken());
			w = Integer.parseInt(st.nextToken());

			System.out.println(arr[w] - arr[v - 1]);
		}

	}

}