package sdf;

import java.awt.image.MultiPixelPackedSampleModel;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;
import java.util.Map.Entry;

import javax.print.attribute.HashAttributeSet;

class Main_2252_정준원 {

	static int n;
	static int m;

	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		StringTokenizer st = new StringTokenizer(br.readLine());
		StringBuilder sb = new StringBuilder();

		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		ArrayList<Integer>[] list;
		Queue<Integer> q = new LinkedList<>();

		list = new ArrayList[n + 1];

		int cnt[] = new int[n + 1];

		for (int i = 1; i <= n; i++) {
			list[i] = new ArrayList<>();
		}

		for (int i = 0; i < m; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			list[a].add(b);
			cnt[b]++;
		}

		for (int i = 1; i <= n; i++) {
			if (cnt[i] == 0)
				q.add(i);
		}

		while (!q.isEmpty()) {

			int v = q.poll();

			sb.append(v + " ");

			int size = list[v].size();

			for (int i = 0; i < size; i++) {
				int v2 = list[v].get(i);
				cnt[v2]--;

				if (cnt[v2] == 0)
					q.add(v2);
			}

		}

		System.out.print(sb);
	}

}
