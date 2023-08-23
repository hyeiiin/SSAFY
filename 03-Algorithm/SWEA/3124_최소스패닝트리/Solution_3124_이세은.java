import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;
import java.util.*;

public class Solution_3124_�̼��� {

	static Edge[] edgeList;
	static int[] parents;

	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int T = Integer.parseInt(br.readLine());

		for (int test_case = 1; test_case <= T; test_case++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int v = Integer.parseInt(st.nextToken()); // ����
			int e = Integer.parseInt(st.nextToken()); // ����

			edgeList = new Edge[e];
			for (int i = 0; i < e; i++) {
				st = new StringTokenizer(br.readLine());
				int from = Integer.parseInt(st.nextToken());
				int to = Integer.parseInt(st.nextToken());
				int weight = Integer.parseInt(st.nextToken());
				// ������ ���� ���� ����Ʈ�� ����
				edgeList[i] = new Edge(from, to, weight);
			}

			// �ּڰ� ã�� ���� ����ġ �������� ����
			Arrays.sort(edgeList);

			// make�ܰ�, �ּ� ���� �����
			parents = new int[v + 1]; // �� ������ ���ϴ� ������ ��ǥ��
			for (int i = 1; i <= v; i++) { // ������ŭ ������ �ϳ��� ������ �ּ����� ����
				parents[i] = i;
			}

			long rslt = 0; // ���
			int cnt = 0; // ���� ���� ����
			for (Edge edge : edgeList) {
				if (union(edge.from, edge.to)) {
					rslt += edge.weight; // ���� ����ġ ���ϱ�
					if (++cnt == v - 1) {
						break;
					}
				}
			}

			System.out.println("#" + test_case + " " + rslt);

		}
	}

	private static boolean union(int from, int to) {
		int fromRoot = find(from);
		int toRoot = find(to);

		if (fromRoot == toRoot)
			return false;

		parents[toRoot] = fromRoot;
		return true;
	}

	private static int find(int from) {
		if (parents[from] == from)
			return from;
		else return parents[from] = find(parents[from]);
	}

	static class Edge implements Comparable<Edge> {
		int from, to, weight;

		public Edge(int from, int to, int weight) {
			super();
			this.from = from;
			this.to = to;
			this.weight = weight;
		}

		@Override
		public int compareTo(Edge o) {
			// TODO Auto-generated method stub
			return Integer.compare(this.weight, o.weight);
		}

	}

}
