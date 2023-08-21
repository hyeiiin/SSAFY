package sdf;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class Main_1697_정준원 {

	public static void main(String[] args) {

		int N; // 수빈이 위치
		int k; // 동생 위치
		int[] time = new int[100001];

		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();
		k = sc.nextInt();
		bfs(k, time, N);
		System.out.println(time[k]);
	}

	static void bfs(int k, int time[], int N) {
		Queue<Integer> q = new LinkedList<>();

		q.offer(N);

		while (!q.isEmpty()) {

			N = q.poll();

			if (N == k) {
				break;
			}

			if (N - 1 >= 0 && time[N - 1] == 0) {
				q.offer(N - 1);
				time[N - 1] = time[N] + 1;
			}

			if (N + 1 <= 100000 && time[N + 1] == 0) {
				q.offer(N + 1);
				time[N + 1] = time[N] + 1;
			}

			if (N * 2 <= 100000 && time[N * 2] == 0) {
				q.offer(N * 2);
				time[N * 2] = time[N] + 1;
			}
		}

	}

}