import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main_15683_�̼��� {

	private static int n, m, min = Integer.MAX_VALUE;
	private static int[][] room;
	// �����¿� �̵�
	private static int[] moveR = { -1, 1, 0, 0 };
	private static int[] moveC = { 0, 0, -1, 1 };

	// �� cctv���� ȸ������ �� �� �� �ִ� �ݰ� ���س���, cctv��ȣ�� �ε���
	// �����¿� 0,1,2,3
	private static int[][][] types = { {}, { { 0 }, { 1 }, { 2 }, { 3 } }, { { 2, 3 }, { 0, 1 } },
			{ { 0, 3 }, { 3, 1 }, { 1, 2 }, { 2, 0 } }, { { 0, 2, 3 }, { 0, 1, 3 }, { 1, 2, 3 }, { 0, 1, 2 } },
			{ { 0, 1, 2, 3 } } };

	private static List<CCTV> cctvList;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		StringTokenizer st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken()); // �繫�� ����
		m = Integer.parseInt(st.nextToken()); // �繫�� ����

		room = new int[n][m];

		// �繫�� ���� �ޱ�
		// ���� cctv�ݰ� ���� Ȯ���ϱ� ���� cctv ������ ���� ��ǥ �����صд�.
		cctvList = new ArrayList<>();
		for (int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < m; j++) {
				room[i][j] = Integer.parseInt(st.nextToken());
				if (room[i][j] >= 1 && room[i][j] <= 5) { // cctv�� ��� ���� ����
					cctvList.add(new CCTV(room[i][j], i, j));
				}
			}
		}
		search(0, room);
		System.out.println(min);
	}

	// �ݰ� ����Ǽ� Ȯ�� �޼���, �б� cnt�� ���� list�� �� cctv�� Ž�� �� room�� ����
	public static void check(CCTV cctv, int[][] room, int t) {
	
		for (int j = 0; j < types[cctv.no][t].length; j++) { // �� cctv�� ����� ���� �ε�����ŭ �����ֱ�
			int newR = cctv.i, newC = cctv.j; //cctv�� ��ġ�ϴ� ������ �ݰ��� �����ϱ� ���� ��ġ ����
			int dir = types[cctv.no][t][j]; //cctv��ȣ�� ���� ������ �б� t���� ȸ��
			// ���� ��ȿ�ϸ鼭 ���� �ƴ� ��� cnt�б�� Ž�� ����ϱ�, Ž�� �����ٸ� ���� cctv�� �Ѿ�� ���� �޼��� ����
			while (true) {
				newR += moveR[dir];
				newC += moveC[dir];
				//���� ��ȿ�ϰ� �� �ƴѰ�� ���� ����
				if (newR >= 0 && newC >= 0 && newR < n && newC < m && room[newR][newC] != 6) {
					if (room[newR][newC] == 0) { // ��ĭ
						room[newR][newC] = -1; // �ݰ� ǥ��
					}
					//���� �ٸ� cctv�����ٸ� �׳� �Ѿ�� �����Ͽ� �̵�
				} else {
					break;
				}
			}
		}
	}

	// ������ �ݰ����� �� cctvŽ�� �� �簢���� ī��Ʈ �ϴ� �޼���
	public static void search(int cnt, int[][] room) {
		if (cnt == cctvList.size()) { // ��� cctvŽ�� �Ϸ�
			int cnt0 = 0;
			for (int i = 0; i < n; i++) {
				for (int j = 0; j < m; j++) {
					if (room[i][j] == 0) { // �簢���� ī��Ʈ
						cnt0++;
					}
				}
			}
			min = Math.min(min, cnt0); // �簢���� �ּ��� �� ã��
			return;
		}

		CCTV searchFor = cctvList.get(cnt);
		for (int t = 0; t < types[searchFor.no].length; t++) { // �繫�ǿ� �����ϴ� cctv ��� �ݰ� Ȯ��
			// �� �б⸶���� ���ÿ��� ���������� Ȯ���ϱ� ���� ���� �繫�� �迭 ����, ����� �迭�� �ݰ� Ȯ��
			int[][] tmpRoom = new int[n][m];
			for (int i = 0; i < n; i++) {
				for (int j = 0; j < m; j++) {
					tmpRoom[i][j] = room[i][j];
				}
			}
			check(searchFor, tmpRoom, t); // cctv���� ȸ����Ű�� �ݰ� Ž���ؼ� ���� ���� ó��
			search(cnt + 1, tmpRoom); // cnt ���� �б� ����
		}
	}

	// cctv ���� ���� Ŭ����
	static class CCTV {
		int no;
		int i;
		int j;

		public CCTV(int no, int i, int j) {
			this.no = no;
			this.i = i;
			this.j = j;
		}
	}

}