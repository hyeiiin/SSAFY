import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;


public class Main_2252_줄세우기 {
	static int N, M;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		StringBuilder sb = new StringBuilder();
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		int[] indegree = new int[N + 1];
		ArrayList<Integer>[] list = new ArrayList[N + 1]; // 각 학생 뒤에 와야하는 애들을 넣어 줄 예정
		for (int i = 0; i < list.length; i++) {
			list[i] = new ArrayList<Integer>();// 배열안에 리스트 만들어주기
		}

		for (int m = 0; m < M; m++) {
			st = new StringTokenizer(br.readLine());
			int front = Integer.parseInt(st.nextToken());// 앞에 오는 학생 번호
			int back = Integer.parseInt(st.nextToken()); // 뒤에 오는 학생 번호
			list[front].add(back); // 앞에 오는 학생 인덱스의 리스트에 뒤에 위치해야 하는 학생 번호 넣기
			indegree[back]++;// 누군가의 뒤로 들어가야한다고 명시된 인덱스 카운팅(나를 가르키는 화살표의 개수)
		}
			// ------------INPUT END----------------

			Queue<Integer> queue = new LinkedList<Integer>();
			// indegree가 0인 index 넣기(화살표가 가리키고 있는 곳X)
			for (int i = 1; i <= N; i++) {
				if (indegree[i] == 0) {
					queue.add(i);
				}
			}

			// 큐가 빌때까지 확인하기
			while (!queue.isEmpty()) {
				int num = queue.poll();// 큐에서 indegree가 0인 번호 빼오기
				sb.append(num).append(" "); // indegree가 0이란건? 아무도 내가 뒤에 온다고 안한것 = 앞에 위치한 사람

				// 현재 뽑은 번호의 뒤에 오는 번호들 확인하기
				for (int i = 0; i < list[num].size(); i++) {
					int next = list[num].get(i);

					indegree[next]--;// 현재 번호가 가리키는 횟수 하나 줄여주기(현재 번호는 위치 확정)
					if (indegree[next] == 0) { // 내가 가리키는 횟수를 뺐더니 더이상 가리키는 횟수가 없다면? 앞에 와야함.
						queue.add(next);// 큐에 넣어주기
					}

				}
			}
			System.out.println(sb);
		
	}

}
