import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_1010_�̼��� {

	static int n, m;
	static int[][] bridge;

	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		StringBuilder sb = new StringBuilder();

		for (int test_case = 1; test_case <= T; test_case++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			n = Integer.parseInt(st.nextToken());
			m = Integer.parseInt(st.nextToken());
			bridge = new int[30][30]; //n�� m �ִ�
			
			// m���� ����Ʈ �� n�� �̱�
			sb.append(setB(m,n)).append("\n");//dp���� ���� �޼��� ����

		}
		System.out.print(sb.toString());
	}

	public static int setB(int i, int j) {
	
		if(bridge[i][j] != 0) { //���� �ִ� ��� �״�� ����
			return bridge[i][j];
		}
		//�޸������̼�
		//n�� m�� ���� ��� ����� ���� 1�� �ۿ� ����
		if( j==0 || i == j) {
			return bridge[i][j] = 1;
		}
		
		return bridge[i][j] = setB(i-1, j) + setB(i-1, j-1); //����, ����
	}

}
