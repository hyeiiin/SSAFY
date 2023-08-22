import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main_13023_�̼��� {

	private static List<Integer>[] arr;
	private static boolean[] visited;
	private static boolean end = false;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		StringTokenizer st = new StringTokenizer(br.readLine());
		int n = Integer.parseInt(st.nextToken()); // ��� ��
		int m = Integer.parseInt(st.nextToken()); // ���� ��
		arr = new ArrayList[n];
		visited = new boolean[n];// 0�� ������� ���̰� 5�� �Ǵ� �κ��ִ��� Ž��

		// n�� ��ŭ ����Ʈ �ʱ�ȭ
		for (int i = 0; i < n; i++) {
			arr[i] = new ArrayList<>();
		}

		for (int i = 0; i < m; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			// ���� ģ�� ���� ������Ʈ
			arr[a].add(b);
			arr[b].add(a);
		}

		for(int i=0 ;i<n; i++) {
			dfs(i, 1);//���� �� �ڽź��� �����ϹǷ� ī��Ʈ 1���� ����
			if(end) {
				System.out.println(1);
				return;
			}
		}
		System.out.println(0);
	}
	
	public static void dfs(int p, int cnt) {
		if(end || cnt == 5) {//ī��Ʈ ��� ������ �������� ��� ��� ������
			end = true;
			return;
		}
		visited[p] = true; //���� ��� �湮ó��
		
		//�̾��� ģ���� Ž��
		for(int i=0; i<arr[p].size(); i++) {
			int r = arr[p].get(i);
			if(!visited[r]) {
				visited[r] = true;
				dfs(r, cnt+1); //���� ģ�� ���� ��� Ž��, ī��Ʈ �� ����� �Ű������� �־��ش�
			}
		}
		//�̾��� ģ���� ��� ���� �� Ž���ߴ� ��� �ٽ� �湮ó�� ���, ���� ���� ����� ��Ϳ��� ģ�� �� �� �ֱ� ������
		visited[p] = false;
	}
}
