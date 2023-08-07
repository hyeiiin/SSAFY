package sdf;

import java.util.*;

public class Main_1158_정준원 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		int n, k;

		Scanner sc = new Scanner(System.in);
		n = sc.nextInt();
		k = sc.nextInt();

		Queue<Integer> q = new LinkedList<>();
		Queue<Integer> res = new LinkedList<>();
		for (int i = 1; i <= n; i++)
			q.add(i);

		int cnt = 1;
		while (!q.isEmpty()) {
			cnt %= k;

			if (cnt == 0)
				res.add(q.poll());

			else {

				int v = q.poll();
				q.add(v);

			}

			cnt++;
		}
		int i = 0;

		if (res.size() == 1)
			System.out.print("<" + res.poll() + ">");

		while (!res.isEmpty()) {
			if (res.size() == 1)
				System.out.print(res.poll() + ">");
			else if (i == 0)
				System.out.print("<" + res.poll() + ", ");

			else {
				System.out.print(res.poll() + ", ");

			}
			i++;
		}

	}

}
