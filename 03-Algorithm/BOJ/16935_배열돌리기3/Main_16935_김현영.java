import java.io.*;
import java.util.*;

public class Main_16935_김현영 {
	static int[][] arr;	//연산을 실행할 배열 nxm크기
	static int n;
	static int m;

	//1번 연산 : 배열 상하 반전
	static void func1() {
		int newN = arr.length;
		int newM = arr[0].length;
		int[][] copy = new int[newN][newM];
		for (int i = 0; i < newN; i++) {
			copy[i] = arr[newN - i - 1];
		}
		arr = copy;
	}

	//2번 연산 : 배열 좌우 반전
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
	
	//3번 연산 : 배열을 오른쪽으로 90도 회전
	//배열의 크기를 nxm에서 mxn으로 새로 지정해줘야함
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
	
	//4번 연산 : 배열을 왼쪽으로 90도 회전
	//배열의 크기를 nxm에서 mxn으로 새로 지정해줘야함
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
	
	//5번연산 : 배열을 4분할 해서  오른쪽으로 돌림
	// 1 2  -> 4 1
	// 4 3     3 2
	static void func5( ) {
		int newN = arr.length;
		int newM = arr[0].length;
		int[][] copy = new int[newN][newM];
		int n2 = newN/2;
		int m2 = newM/2;
		//1번박스 (arr4번>1번)
		for (int i = 0; i < n2; i++) {
			for (int j = 0; j < m2; j++) {
				copy[i][j] = arr[i+n2][j];
			}
		}
		//2번박스(arr1번>2번)
		for (int i = 0; i < n2; i++) {
			for (int j = m2; j < m2*2; j++) {
				copy[i][j] = arr[i][j-m2];
			}
		}
		//3번박스(arr2번>3번)
		for (int i = n2; i < n2*2; i++) {
			for (int j = m2; j < m2*2; j++) {
				copy[i][j] = arr[i-n2][j];
			}
		}
		//4번박스(arr3번>4번)
		for (int i = n2; i < n2*2; i++) {
			for (int j = 0; j < m2; j++) {
				copy[i][j] = arr[i][j+m2];
			}
		}
		arr = copy;
	}
	
	// 6번연산 : 배열을 4분할 해서  왼쪽으로 돌림
	// 1 2  -> 2 3
	// 4 3     1 4
	static void func6( ) {
		int newN = arr.length;
		int newM = arr[0].length;
		int[][] copy = new int[newN][newM];
		int n2 = newN/2;
		int m2 = newM/2;
		//1번박스 (arr2번>1번)
		for (int i = 0; i < n2; i++) {
			for (int j = 0; j < m2; j++) {
				copy[i][j] = arr[i][j+m2];
			}
		}
		//2번박스(arr3번>2번)
		for (int i = 0; i < n2; i++) {
			for (int j = m2; j < m2*2; j++) {
				copy[i][j] = arr[i+n2][j];
			}
		}
		//3번박스(arr4번>3번)
		for (int i = n2; i < n2*2; i++) {
			for (int j = m2; j < m2*2; j++) {
				copy[i][j] = arr[i][j-m2];
			}
		}
		//4번박스(arr1번>4번)
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
		int r = Integer.parseInt(st.nextToken());	//연산횟수

		//배열 입력
		arr = new int[n][m];
		for (int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < m; j++) {
				arr[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		//r개의 연산을 받고 해당 번호에 해당하는 함수 호출
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
		
		// 출력
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
