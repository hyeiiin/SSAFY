package sdf;

import java.io.BufferedReader;
import java.io.IOError;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main_2493_정준원 {

	static StringBuilder sb;
	// 이 문제는 현재 탑보다 왼쪽에 있는 탑중에서 자신보다 높은 탑중 가장 가까운탑이 몇번째에 위치하는지 를 알려주어야 하는 문제이다.

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		int N;
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		sb = new StringBuilder();

		N = Integer.parseInt(br.readLine()); // 탑들의 개수 이다.

		StringTokenizer st = new StringTokenizer(br.readLine());

		int[] arr = new int[N];
		Stack<int[]> sta = new Stack<>(); // 탑들의 높이를 받기위한 스택.

		sta.add(new int[] { Integer.parseInt(st.nextToken()), 1 }); // 탑들의 높이를 받고 스택에 넣는다. 예제 입력 1 로 따지면 (6,1) 이 된다. 이
																	// 작업은 두번째 탑 입장에서 첫번쨰 탑의 높이를 알기 위해
//		수행한다.

		sb.append(0 + " "); // 첫번째 탑은 당연히 본인의 앞에 탑이 없기 때문에 0을 출력해야한다.

		for (int i = 1; i < N; i++) { // i 가 0인경우는 본인 앞에 탑이 없기때문에 0을 sb 에 넣어주고 , 1부터 시작한다.
			int v = Integer.parseInt(st.nextToken());
			sb.append(find(v, sta) + " "); // find 함수를 통해 자신보다 높은 탑중 가장 가까운탑이 몇번째에 위치하는지를 찾는다.
			sta.add(new int[] { v, i + 1 }); // 스택에 현재 탑의 높이와 현재 인덱스를 넣는다. 예제 입력 1 로 따지면 (9,2) 가 된다. 이 작업을 함으로써 각 탑 의 본인
												// 앞에 있는 탑의 값을 알수가 있다.
		}

		System.out.print(sb.toString());

	}

	static int find(int n, Stack<int[]> sta) {

		while (!sta.isEmpty()) {

			if (n < sta.peek()[0]) { // 현재 스택의 최근 값이 높이보다 더 높은 탑을 발견한 경우
				return sta.peek()[1]; // 해당 탑의 인덱스 값 출력.
			}

			else // 현재 스택의 최근 값이 높이보다 더 높은 탑이 아닌경우 더 왼쪽 탑을 탐색하기위해 pop 을 해준다. 이 경우 팝을 해주면 다음의 탑
					// 입장에서 본인보다 큰 탑일수도 있는데, 현재 탑의 입장에서만 생각하는게 아니냐고 할수
//				있는데, 현재 탑이 더 크니까 pop 을 해도 가장 가까운탑이 현재 탑이 되기 때문에 , 현재 탑의 인덱스 값을 출력하면 된다.
				sta.pop();
		}

		return 0;

	}

}
