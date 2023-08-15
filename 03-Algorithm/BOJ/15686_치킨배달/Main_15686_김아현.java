package ssafy.Boj;

import java.io.*;
import java.util.*;

class Node {
	int x;
	int y;

	public Node(int x, int y) {
		super();
		this.x = x;
		this.y = y;
	}
}

// 치킨배달
public class Main_15686_김아현 {

	static ArrayList<Node> chicken;
	static ArrayList<Node> house;
	static boolean[] visitedChicken;
	static int n, m;
	static int minDisatance = Integer.MAX_VALUE;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());

		chicken = new ArrayList<Node>();
		house = new ArrayList<Node>();

		int[][] map = new int[n][n];
		for (int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < n; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				if (map[i][j] == 1) {
					house.add(new Node(i, j));
				} else if (map[i][j] == 2) {
					chicken.add(new Node(i, j));
				}
			}
		}

		visitedChicken = new boolean[chicken.size()];
		choose(0, 0);
		System.out.println(minDisatance);
	}

	static void choose(int count, int start) {
		if (count == m) {
			int distance = 0;
			for (int i = 0; i < house.size(); i++) {
				int sum = Integer.MAX_VALUE;

				for (int j = 0; j < chicken.size(); j++) {
					if (!visitedChicken[j])
						continue;

					int temp = Math.abs(house.get(i).x - chicken.get(j).x)
							+ Math.abs(house.get(i).y - chicken.get(j).y);
					sum = Math.min(sum, temp);
				}

				distance += sum;
			}

			minDisatance = Math.min(minDisatance, distance);
			return;
		}

		for (int i = start; i < chicken.size(); i++) {
			if (visitedChicken[i])
				continue;
			visitedChicken[i] = true;
			choose(count + 1, i + 1);
			visitedChicken[i] = false;
		}
	}

}