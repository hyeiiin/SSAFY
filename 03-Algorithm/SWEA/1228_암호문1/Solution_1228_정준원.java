package sdf;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StreamCorruptedException;
import java.util.*;

public class Solution_1228_정준원 {

	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//		Scanner sc = new Scanner(System.in);

		for (int tc = 1; tc <= 10; tc++) {
//			int n = sc.nextInt();
			int n = Integer.parseInt(br.readLine());

			List<String> str = new ArrayList<>();
			Stack<String> command = new Stack<>();

//			String[] = new String[n];
			StringTokenizer st = new StringTokenizer(br.readLine());

			for (int i = 0; i < n; i++) {
//				str.add(sc.next());
				str.add(st.nextToken());
//				System.out.println(" str.get " + i + " " + str.get(i));
			}

//			int amholen = sc.nextInt();
			int amholen = Integer.parseInt(br.readLine());
			st = new StringTokenizer(br.readLine());

			for (int i = 0; i < amholen; i++) {
//				System.out.println();
//				int x = sc.nextInt();
				String input = st.nextToken();
//				System.out.println("input" + input);
				int x = Integer.parseInt(st.nextToken());
				int y = Integer.parseInt(st.nextToken());
//				System.out.println("Y" + y);

//				int y = sc.nextInt();
//				int s = sc.nextInt();

				for (int j = 0; j < y; j++) {
//					command.add(sc.next());
					command.add(st.nextToken());
//					System.out.println("size" + command.size());
				}

				while (!command.isEmpty()) {
					str.add(x, command.pop());
//					System.out.println("str size" + str.size());
				}

			}

			int size = 0;
			System.out.print("#" + tc + " ");

			while (size < 10) {
				System.out.print(str.get(size) + " ");
				size++;
			}

			System.out.println();
		}

	}

}
