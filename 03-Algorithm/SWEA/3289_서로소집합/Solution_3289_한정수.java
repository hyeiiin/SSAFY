import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution_3289_한정수 {
	static int T;
	static int N;
	static int M;
	static int[] parents;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		T = Integer.parseInt(st.nextToken());
		StringBuilder sb = new StringBuilder();
		for (int test_case=1 ; test_case<=T ; test_case++) {
			sb.append("#").append(test_case).append(" ");
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());
			
			parents = new int[N+1];
			//parents 자기 자신의 부모는 자기 자신 일단 세팅
			for (int i=0; i<N+1 ; i++) {
				parents[i] = i;
			}
			
			for (int i=0; i<M ; i++) {
				int mode;
				int a;
				int b;
				int aRoot;
				int bRoot;
				st = new StringTokenizer(br.readLine());
				mode = Integer.parseInt(st.nextToken());
				a = Integer.parseInt(st.nextToken());
				b = Integer.parseInt(st.nextToken());
				
				//0이면 union.
				if(mode == 0) {
					union(a,b);
				}
				else {
					//1이면 서로 같은 소속인지 보고 같으면 1, 아니면 0
					aRoot = find(a);
					bRoot = find(b);
					if(aRoot == bRoot) {
						sb.append(1);
					}
					else {
						sb.append(0);
					}
				}
			}
			sb.append("\n");
		}
		System.out.println(sb);
	}
	public static int find(int a) {
		if (parents[a] == a) {
			return a;
		}
		return parents[a] = find(parents[a]);
	}
	public static void union(int a, int b) {
		a = find(a);
		b = find(b);
		
		if(a != b) {
			if(a>b) parents[a] = b;
			else parents[b] = a;
		}
	}

}
