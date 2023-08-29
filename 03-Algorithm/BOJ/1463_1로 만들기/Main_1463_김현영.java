import java.io.*;
import java.util.*;

public class Main_1463_김현영 {

	static boolean[] isVisited;
	static Deque<Integer> q, cnt;

	static void func(int n, int c) {
		//현재 숫자가 3 이하라면 연산 종료
		if (n <= 3) {
			return;
		}

		// 현재 숫자가 3으로 나뉘어지고 이전에 나온 결과가 아니라면 큐에 저장
		if (n % 3 == 0 && !isVisited[n / 3]) {
			q.add(n / 3);
			cnt.add(c + 1);
		}
		// 현재 숫자가 2으로 나뉘어지고 이전에 나온 결과가 아니라면 큐에 저장
		if (n % 2 == 0 && !isVisited[n / 2]) {
			q.add(n / 2);
			cnt.add(c + 1);
		}

		// 현재 숫자 -1이 이전에 나온 결과가 아니라면 큐에 저장
		if (!isVisited[n - 1]) {
			q.add(n - 1);
			cnt.add(c + 1);
		}

		//큐에서 숫자를 하나 뽑아서 연산 진행
		func(q.poll(), cnt.poll());

	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		int n = Integer.parseInt(st.nextToken()); // 연산을 적용할 정수

		// 정수가 1이라면 연산을 할 필요가 없으므로 0 출력 후 종료
		if (n == 1) {
			System.out.println(0);
			return;
		}

		// 정수가 2,3이라면 연산 한 번만 하면 되므로 1 출력 후 종료
		if (n <= 3) {
			System.out.println(1);
			return;
		}

		q = new ArrayDeque<>(); // 연산 후 결과값을 저장할 큐
		cnt = new ArrayDeque<>(); // 해당 연산이 몇번째인지 저장할 큐
		isVisited = new boolean[1000001]; // n이 될 수 있는 최댓값인 10^6으로 크기 할당

		func(n, 0);

		System.out.println(cnt.poll() + 1); // 연산 횟수 출력

	}

}
