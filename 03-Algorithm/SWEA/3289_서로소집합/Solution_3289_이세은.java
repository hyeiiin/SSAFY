import java.io.*;
import java.util.*;

public class Solution_3289_�̼��� {

	private static int n;
	private static int parents[]; // ��ǥ�� �迭

	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		// �׽�Ʈ���̽� ��
		int T = Integer.parseInt(br.readLine());

		for (int test_case = 1; test_case <= T; test_case++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			n = Integer.parseInt(st.nextToken()); // n���� ����
			int m = Integer.parseInt(st.nextToken()); // ������ ����
			StringBuilder sb = new StringBuilder(); // ���� ���� sb

			// �� ���Ҹ� �ּҷ� ������ ���� ����
			makeSet();

			for (int i = 0; i < m; i++) {
				st = new StringTokenizer(br.readLine());
				// �ɼǰ� �μ� �Է¹ޱ�
				int op = Integer.parseInt(st.nextToken());
				int a = Integer.parseInt(st.nextToken());
				int b = Integer.parseInt(st.nextToken());

				// ������ ����
				if (op == 0) {
					// a�� b�� ���Ե� ������ ���� ��ģ��.
					// �� ������ ��ǥ�� ã��
					unionSet(a, b);
				}
				// ���� ���տ� ���ԵǴ��� ���� ����
				else if (op == 1) {
					// �� ������ ��ǥ�ڰ� ������ Ȯ��
					if (findSet(a) == findSet(b))
						sb.append("1");
					else
						sb.append("0");

				}
			}

			System.out.println("#" + test_case + " " + sb.toString());
		}

	}

	// Make-Set, n���� ���ҿ� ���� �� ���Ҹ� ��ǥ�ڷ� �ϴ� ���� n�� ���� �޼���
	public static void makeSet() {
		parents = new int[n + 1]; // n���� ���� ����, �� ������ ��ǥ�� �迭 �ʱ�ȭ
		for (int i = 1; i <= n; i++) {
			parents[i] = i; // �� ���Ҹ� �ּҷ� ������ ����, ��ǥ�ڴ� �ش� ����
		}
	}

	// find-Set, ���� n�� ���Ե� ������ ��ǥ�� ���� �޼���
	public static int findSet(int num) {
		if (parents[num] == num)
			return num; // ��� ��ǥ�� ã�ٰ� ��Ʈ ��� �����ٸ� ����
		return parents[num] = findSet(parents[num]); // ��ǥ�� ���� ��ã�� ��Ȳ������ ��������� ��� ��ǥ�� ã�ư���

	}

	// union-Set, ���� a�� ���� b�� ���Ե� ������ ��ġ�� �޼���
	public static void unionSet(int a, int b) {

		if (a == b) // �μ��� ���ٸ� ����X
			return;

		// �� ������ ��ǥ�� ���� ã��
		int aRoot = findSet(a);
		int bRoot = findSet(b);

		// ���� ���� �����̸� ����X
		if (aRoot == bRoot)
			return;
		// ���� �ٸ� �����̸� union ����
		else {
			parents[bRoot] = aRoot; // ������ �ϳ��� ������ ��ǥ�ڿ� �ٸ� ������ ��ǥ�ڸ� �̾� ���δ�
		}
	}

}
