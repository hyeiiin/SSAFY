import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_12891_������ {

	static int[] check; // �ʿ��� A, C, G, T�� �ּ� ����
	static int[] charCnt; // �Է� ���� ���ڿ����� A, C, G, T�� ���� counting
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int s = Integer.parseInt(st.nextToken()); // ���ڿ� ����
		int p = Integer.parseInt(st.nextToken()); // �κ� ���ڿ� ����
		
		char[] str = br.readLine().toCharArray(); // DNA ���ڿ�
		
		check = new int[4];
		st = new StringTokenizer(br.readLine());
		for(int i=0;i<4;i++) {
			check[i] = Integer.parseInt(st.nextToken()); // A, C, G, T ������� �ʿ��� �ּ� ���� ����
		}
		
		charCnt = new int[4];
		for(int i=0; i<p; i++) {
			if(str[i]=='A') charCnt[0]++;
			else if(str[i]=='C') charCnt[1]++;
			else if(str[i]=='G') charCnt[2]++;
			else if(str[i]=='T') charCnt[3]++;
		}
		
		int answer =0;
		if(verifyPassword()) {
			answer++;
		}
		
		
		//  �κ� ���ڿ� �����
		int i = -1;
		
		for (int j=p; j<s; j++) {
			i = j - p; // ���� �κ� ���ڿ��� ���� ��ġ
			
			// ���� �κ� ���ڿ� ���� ���� counting ����
			if(str[i]=='A') charCnt[0]--;
			else if(str[i]=='C') charCnt[1]--;
			else if(str[i]=='G') charCnt[2]--;
			else if(str[i]=='T') charCnt[3]--;
			
			// ���� �κ� ���ڿ� ������ ���� counting
			if(str[j]=='A') charCnt[0]++;
			else if(str[j]=='C') charCnt[1]++;
			else if(str[j]=='G') charCnt[2]++;
			else if(str[j]=='T') charCnt[3]++;
			
			if(verifyPassword()) {
				answer++;
			}
			
		}
		System.out.println(answer);
		
		
	}

	private static boolean verifyPassword() {
		for(int i=0; i<4; i++) {
			if(charCnt[i] < check[i]) { // ��й�ȣ ������ �����ߴ��� Ȯ��
				return false;
			}
		}
		return true;
	}
	
}
