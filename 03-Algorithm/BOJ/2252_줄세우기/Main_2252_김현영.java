import java.io.*;
import java.util.*; 

public class Main_2252_������ {

	static int n, m;
	static Node[] adjList; // ���� ����Ʈ
	static int[] inDegree; // ��������
	static Deque<Integer> q = new ArrayDeque<>();

	// ��������Ʈ�� ����� ���
	static class Node {
		int vertex;
		Node next;

		public Node(int vertex, Node next) {
			this.vertex = vertex;
			this.next = next;
		}
	}

	public static List<Integer> topologySort() {
		// ���������� �� �л��� ������ ����Ʈ
		List<Integer> list = new ArrayList<>();

		// ���������� 0�� ��� ��� ť�� �߰�
		for (int i = 0; i < n; i++) {
			if (inDegree[i] == 0) {
				q.offer(i);
			}
		}
		while (!q.isEmpty()) {
			int now = q.poll(); // ť���� ��� ������
			list.add(now + 1); // ����Ʈ�� �ε��� ����� ���� �Է¹��� ���� -1 ���־��� ������ ����Ʈ�� ���� �� +1 ó��
			// ���� ����� ��������Ʈ ��� Ž��
			for (Node temp = adjList[now]; temp != null; temp = temp.next) {
				// ��������Ʈ�� ������ 1�̶�� ť�� �߰�
				if (--inDegree[temp.vertex] == 0)
					q.offer(temp.vertex);
			}

		}

		return list;
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		StringBuilder sb = new StringBuilder();

		n = Integer.parseInt(st.nextToken()); // �л� ��
		m = Integer.parseInt(st.nextToken()); // Ű�� ���� Ƚ��

		adjList = new Node[n]; // �л� ����ŭ ��������Ʈ ����
		inDegree = new int[n]; // �л� ����ŭ �������� �迭 ����

		for (int i = 0; i < m; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken()) - 1;
			int b = Integer.parseInt(st.nextToken()) - 1;

			adjList[a] = new Node(b, adjList[a]); // ���� ����Ʈ �߰�
			inDegree[b]++; // ���� ���� ����
		}
 
		// ���� ����
		List<Integer> list = topologySort();

		// �������ĵ� ����Ʈ ���
		for (int i : list) {
			sb.append(i).append(" ");
		}
		System.out.println(sb);
	}
}
