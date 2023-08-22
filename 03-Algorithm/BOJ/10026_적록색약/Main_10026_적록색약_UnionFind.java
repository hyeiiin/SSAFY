
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.StringTokenizer;

public class Main_10026_적록색약_UnionFind {
	static int N;
	static char[][] map;
	static int[] parents;
	static int[] dy = { -1, 0, 1, 0 };
	static int[] dx = { 0, 1, 0, -1 };

	public static void main(String[] args) throws Exception {

		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(in.readLine());
		N = Integer.parseInt(st.nextToken());
		map = new char[N][N];
		int no = 0; //적록색약이 아닐 때, 구역의 수
		int yes = 0; //적록색약일 때, 구역의 수
		boolean flag = false; //적록색약 여부(초기 셋팅 false)

		for (int i = 0; i < N; i++) {
			map[i] = in.readLine().toCharArray();
		}

		no = find(flag); //적록색약  여부에 따라서 구역의 개수를 카운팅 
		yes = find(!flag); 

		System.out.printf("%d %d%n", no, yes);

	}


	private static void make() {
		int num = N * N;
		parents = new int[num];

		for (int i = 0; i < num; i++) {
			parents[i] = i;
		}
	}

	private static int find(int a) {
		if (parents[a] == a) {
			return a;
		}
		return parents[a] = find(parents[a]);
	}

	private static void union(int a, int b) {
		int aParents = find(a);
		int bParents = find(b);

		parents[aParents] = bParents;
	}
	//적록색약 여부에 따라 집단 만들어서 수 세기
	private static int find(boolean flag) {
		//최소 단위 만들
		make();
		int idx = 0;
		for (int i = 0; i < N; i++) { //2차원 배열 돌면서 구역나누기
			for (int j = 0; j < N; j++) {
				for (int now = 0; now < 4; now++) { //사방 탐색
					int ni = i + dy[now];
					int nj = j + dx[now];
					if (ni >= 0 && ni < N && nj >= 0 && nj < N) { //경계를 벗어나지 않았는지 
						// 현재 위치와 탐색 위치가 같은 색인 or 다른색인데, 조합이 R,G인지 여부 확인 + 적록색약 여부
						if (map[i][j] == map[ni][nj]|| 
								((map[i][j] == 'R' && map[ni][nj] == 'G' || map[i][j] == 'G' && map[ni][nj] == 'R')&& flag)) {

							switch (now) { //탐색 뱡향 같은 집단으로 묶어주기
							case 0:
								union(idx, idx - N);
								break;
							case 1:
								union(idx, idx + 1);
								break;
							case 2:
								union(idx, idx + N);
								break;
							case 3:
								union(idx, idx - 1);
								break;
							}
						}
					} else
						continue; //조건에 맞지 않으면 다른 방향 탐색
				}
				idx++;
			}
		}

		HashSet<Integer> check = new HashSet<>(); //중복값 허용X
		for (int i = 0; i < N * N; i++) { 
			check.add(find(i));
		}
		return check.size();
	}
}
