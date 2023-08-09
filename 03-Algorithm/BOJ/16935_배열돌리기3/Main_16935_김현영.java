import java.io.*;
import java.util.*;

public class Main_16935_������ {
	static int[][] arr;	//������ ������ �迭 nxmũ��
	static int n;
	static int m;

	//1�� ���� : �迭 ���� ����
	static void func1() {
		int newN = arr.length;
		int newM = arr[0].length;
		int[][] copy = new int[newN][newM];
		for (int i = 0; i < newN; i++) {
			copy[i] = arr[newN - i - 1];
		}
		arr = copy;
	}

	//2�� ���� : �迭 �¿� ����
	static void func2() {
		int newN = arr.length;
		int newM = arr[0].length;
		int[][] copy = new int[newN][newM];
		for (int i = 0; i < newN; i++) {
			for (int j = 0; j < newM; j++) {
				copy[i][j] = arr[i][newM - j - 1];
			}
		}
		arr = copy;
	}
	
	//3�� ���� : �迭�� ���������� 90�� ȸ��
	//�迭�� ũ�⸦ nxm���� mxn���� ���� �����������
	static void func3() {
		int newN = arr[0].length;
		int newM = arr.length;
		int[][] copy = new int[newN][newM];
		for (int i = 0; i < newN; i++) {
			for (int j = 0; j < newM; j++) {
				copy[i][j] = arr[newM-j-1][i];
			}
		}
		arr = new int[newN][newM];
		arr = copy;
	}
	
	//4�� ���� : �迭�� �������� 90�� ȸ��
	//�迭�� ũ�⸦ nxm���� mxn���� ���� �����������
	static void func4() {
		int newN = arr[0].length;
		int newM = arr.length;
		int[][] copy = new int[newN][newM];
		for (int i = 0; i < newN; i++) {
			for (int j = 0; j < newM; j++) { 
				copy[i][j] = arr[j][newN-i-1];
			}
		}
		arr = new int[newN][newM];
		arr = copy;
	}
	
	//5������ : �迭�� 4���� �ؼ�  ���������� ����
	// 1 2  -> 4 1
	// 4 3     3 2
	static void func5( ) {
		int newN = arr.length;
		int newM = arr[0].length;
		int[][] copy = new int[newN][newM];
		int n2 = newN/2;
		int m2 = newM/2;
		//1���ڽ� (arr4��>1��)
		for (int i = 0; i < n2; i++) {
			for (int j = 0; j < m2; j++) {
				copy[i][j] = arr[i+n2][j];
			}
		}
		//2���ڽ�(arr1��>2��)
		for (int i = 0; i < n2; i++) {
			for (int j = m2; j < m2*2; j++) {
				copy[i][j] = arr[i][j-m2];
			}
		}
		//3���ڽ�(arr2��>3��)
		for (int i = n2; i < n2*2; i++) {
			for (int j = m2; j < m2*2; j++) {
				copy[i][j] = arr[i-n2][j];
			}
		}
		//4���ڽ�(arr3��>4��)
		for (int i = n2; i < n2*2; i++) {
			for (int j = 0; j < m2; j++) {
				copy[i][j] = arr[i][j+m2];
			}
		}
		arr = copy;
	}
	
	// 6������ : �迭�� 4���� �ؼ�  �������� ����
	// 1 2  -> 2 3
	// 4 3     1 4
	static void func6( ) {
		int newN = arr.length;
		int newM = arr[0].length;
		int[][] copy = new int[newN][newM];
		int n2 = newN/2;
		int m2 = newM/2;
		//1���ڽ� (arr2��>1��)
		for (int i = 0; i < n2; i++) {
			for (int j = 0; j < m2; j++) {
				copy[i][j] = arr[i][j+m2];
			}
		}
		//2���ڽ�(arr3��>2��)
		for (int i = 0; i < n2; i++) {
			for (int j = m2; j < m2*2; j++) {
				copy[i][j] = arr[i+n2][j];
			}
		}
		//3���ڽ�(arr4��>3��)
		for (int i = n2; i < n2*2; i++) {
			for (int j = m2; j < m2*2; j++) {
				copy[i][j] = arr[i][j-m2];
			}
		}
		//4���ڽ�(arr1��>4��)
		for (int i = n2; i < n2*2; i++) {
			for (int j = 0; j < m2; j++) {
				copy[i][j] = arr[i-n2][j];
			}
		}
		arr = copy;
	}
	
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		int r = Integer.parseInt(st.nextToken());	//����Ƚ��

		//�迭 �Է�
		arr = new int[n][m];
		for (int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < m; j++) {
				arr[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		//r���� ������ �ް� �ش� ��ȣ�� �ش��ϴ� �Լ� ȣ��
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < r; i++) {
			int op = Integer.parseInt(st.nextToken());
			switch (op) {
			case 6:
				func6();
				break;
			case 5:
				func5();	
				break;
			case 4:
				func4();
				break;
			case 3:
				func3();
				break;
			case 2:
				func2();
				break;
			case 1:
				func1();
				break;
			}
		} 
		
		// ���
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < arr.length; i++) {
			for (int j = 0; j < arr[0].length; j++) {
				sb.append(arr[i][j]).append(" ");
			}
			sb.append("\n");
		}
		System.out.println(sb.toString());
	}

}
