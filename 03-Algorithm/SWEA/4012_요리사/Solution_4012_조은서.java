package algo_0810;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution_4012_������ {

	static int N;
	static boolean[] isSelected;
	static int[] a;
	static int[] b;
	static int[][] table;
	static int min;
	static int tmpMin;
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		StringBuilder sb = new StringBuilder();
		
		int T = Integer.parseInt(br.readLine());
		for(int tc = 1; tc <= T; tc++) {
			N = Integer.parseInt(br.readLine());
			
			table = new int[N][N];
			for(int i=0; i<N; i++) {
				st = new StringTokenizer(br.readLine());
				for(int j=0; j<N; j++) {
					table[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			isSelected = new boolean[N]; // ���� ���� �� ���� ���� ����
			a = new int[N/2]; // a ���� ���
			b = new int[N/2]; // b ���� ���
			min = Integer.MAX_VALUE;
			
			combi(0,0);
			
			sb.append("#").append(tc).append(" ").append(min).append("\n");
		}
		System.out.println(sb);

	}
	
	private static void combi(int cnt, int init) {
		if(cnt == N/2) {
			int idx = 0;
			for(int i=0; i<N; i++) {
				if(!isSelected[i]) { // a ���� ��� �������� ���õ��� ���� �� = b ���� ��� ����
					b[idx++] = i+1;
				}
			}
			
			int aScore = 0;
			int bScore = 0;
			for(int i=0; i<N/2 -1; i++) { // ���տ��� 2���� ���� �� �ó��� �� ���ϱ�
				for(int j=i+1; j < N/2; j++) {
					aScore += table[a[i]-1][a[j]-1] + table[a[j]-1][a[i]-1]; // i, j�� �������� �ó������� S_ij+S_ji
					bScore += table[b[i]-1][b[j]-1] + table[b[j]-1][b[i]-1];	
				}
			}
			tmpMin = Math.abs(aScore-bScore);
			if (min > tmpMin) {
				min = tmpMin;
			}

			return;
		}
		for(int i = init; i<N ; i++) {
			if(isSelected[i] == true) {
				continue;
			}
			a[cnt] = i+1; // a ���� ��� ����
			isSelected[i] = true;
			combi(cnt+1, i+1);
			isSelected[i] = false;
			
		}
	}

}