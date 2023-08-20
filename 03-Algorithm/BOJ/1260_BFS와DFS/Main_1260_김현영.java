import java.io.*;
import java.util.*;

public class Main_1260_������ { 
	static void bfs(List<Set<Integer>> graph, int n, int v) {
		StringBuilder sb = new StringBuilder();

		List<Integer> visited = new ArrayList<>(); // �湮�� ��� ����
		Deque<Integer> q = new ArrayDeque<>(); // �������� Ȯ���� ��� ����
		q.add(v);    // ť�� ó�� �湮�� ��� ����
		while (!q.isEmpty()) { //���̻� �湮�� ��尡 ���� ������ �ݺ�
			sb.append(q.peek()).append(" ");
		    //���� ��尡 ������ �湮�� �� �ִ� ������ ����Ʈ�� �������� ����
			List<Integer> next = new ArrayList<>(graph.get(q.peek() - 1));
			Collections.sort(next); 
			visited.add(q.poll());    //�湮ó��
		    //next�� �ϳ��� Ȯ���ϸ� �湮������ �ʾҰ� �湮�ҿ����� �ƴ� ���� ��带 ��� ã�Ƽ� ť�� ����
			for (Integer i : next) {
				if (!visited.contains(i) && !q.contains(i))
					q.add(i);
			} 
		}
		System.out.println(sb.toString());
	}

	public static void dfs(List<Set<Integer>> graph, List<Integer> visited, Deque<Integer> q) {
		if (q.isEmpty()) //���̻� �湮 ������ ��尡 ���ٸ� Ž�� ����
			return;

		System.out.print(q.peek() + " ");

		//���� ��尡 ������ �湮�� �� �ִ� ������ ����Ʈ�� �������� ����
		List<Integer> next = new ArrayList<>(graph.get(q.peek() - 1));
		Collections.sort(next); 
		visited.add(q.poll());	//�湮ó��
		
		//next�� �ϳ��� Ȯ���ϸ� �湮������ �ʾҰ� �湮�ҿ����� �ƴ� ���� ��带 1�� ã�Ƽ� ť�� ����
		for (int i = 0; i < next.size(); i++) {
			if (!visited.contains(next.get(i)) && !q.contains(next.get(i))) {
				q.add(next.get(i)); 
                dfs(graph, visited, q);	//�����忡�� �湮�� ���� ��忡�� �湮�� ��带 ã�ư�
			}
		} 
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		int n = Integer.parseInt(st.nextToken()); // ����� ��
		int m = Integer.parseInt(st.nextToken()); // ������ ��
		int v = Integer.parseInt(st.nextToken()); // �湮�� ������ ���

		// �׷����� �����ϴ� ����Ʈ
		// ����Ʈ�� �ε��� = ������ȣ. ����Ʈ�� �� ��Ҵ� �ش� �ε����� ����Ǿ� �ִ� ��ȣ���� ����Ʈ
		List<Set<Integer>> graph = new ArrayList<>();
		for (int i = 0; i < n; i++) {
			Set<Integer> init = new HashSet<>();
			graph.add(init);
		}
		//������ �� ��ŭ ����Ǿ��ִ� ��� 2���� �Է¹ް� �׷����� ����
		for (int i = 0; i < m; i++) {
			st = new StringTokenizer(br.readLine());
			int e1 = Integer.parseInt(st.nextToken());
			int e2 = Integer.parseInt(st.nextToken());

			graph.get(e1 - 1).add(e2);
			graph.get(e2 - 1).add(e1);
		} 
        
		// dfs
		List<Integer> visited = new ArrayList<>(); // �湮�� ��� ����
		Deque<Integer> q = new ArrayDeque<>(); // �������� Ȯ���� ��� ����
		q.add(v);    //ť�� ó�� �湮�� ��� ����
		dfs(graph, visited, q);
		
		System.out.println();

		// bfs
		bfs(graph, n, v);

	}
}