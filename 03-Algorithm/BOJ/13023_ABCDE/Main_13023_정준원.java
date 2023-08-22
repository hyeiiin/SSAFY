 

import java.util.ArrayList;
import java.util.Scanner;

public class Main {
	static int N, M;
	static boolean[] visit;
	static boolean flag;
	static ArrayList<Integer>[] list;

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		Scanner sc = new Scanner(System.in);

		N = sc.nextInt();
		M = sc.nextInt();

		visit = new boolean[N];
		list = new ArrayList[N];

		for (int i = 0; i < N; i++)
			list[i] = new ArrayList<>();

		int a = 0, b;

		
		for (int i = 0; i < M; i++) {

			a = sc.nextInt();
			b = sc.nextInt();

			list[a].add(b);
			list[b].add(a);

		}

		for (int i = 0; i < N; i++) {
			if (flag)
				break;

			dfs(1, i);

		}

		if (flag == false)
			System.out.println(0);
		else
			System.out.println(1);

	}

	static void dfs(int d, int idx) {

		if (d == 5 || flag) {
			flag = true;
			return;
		}

		visit[idx] = true;

		for (int i : list[idx]) {

			if (visit[i])
				continue;

			dfs(d + 1, i);

		}

		visit[idx] = false;

	}

}
