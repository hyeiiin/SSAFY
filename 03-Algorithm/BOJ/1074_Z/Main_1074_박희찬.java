import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {  // 1074
	static StringTokenizer st;
	static StringBuilder sb;
	static int N, row, col;  // 배열 크기, 찾을 행과 열
	static int cnt = 0; // 넘버링할 변수

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		row = Integer.parseInt(st.nextToken());
		col = Integer.parseInt(st.nextToken());

		// 한 변의 크기
		int size = (int) Math.pow(2, N);
//		System.out.println("Start size: " + size);
		
		// (0, 0)에서 출발
		divideConquer(0, 0, size);

		System.out.println(sb);

	} // Main

	/**
	 * 분할 정복으로 찾고자하는 좌표의 값을 구하는 함수
	 * @param x : 현재 보고 있는 x 좌표
	 * @param y : 현재 보고 있는 y 좌표
	 * @param size : 현재 (분할된) 한 변의 길이
	 */
	private static void divideConquer(int x, int y, int size) {
		/*
		 * (x, y)가 구하려는 (row, col)보다 앞 쪽 등분에 있거나,
		 * x, y에 다음 등분의 길이(한 변의 길이)를 더해도 찾고자하는 위치에
		 * 못 미친다면 -> 전자의 경우는 버려도 되고, 후자의 경우는
		 * 현재 등분의 전체 값을(칸의 개수) 가져가기 위해 cnt에 합산.
		 */
		if (x > row  || y > col || x + size <= row || y + size <= col) {
			cnt += Math.pow(size, 2);
			return;
		}

		// 2 * 2 사이즈가 남을 때까지 4등분으로 분할
		if (size > 2) {
		int mid = size / 2;
		divideConquer(x, y, mid); // 2
		divideConquer(x, y + mid, mid); // 1
		divideConquer(x + mid, y, mid); // 3
		divideConquer(x + mid, y + mid, mid); // 4
		} else {
			/*
			 * 위의 IF 2개를 통과했다면,
			 * 1. 찾고자 하는 좌표와 같은 등분상에 존재함.
			 * 2. 그 등분이 최소 크기인 2*2의 같은 등분에 있다는 뜻.
			 * 
			 * 따라서, 현재 좌표(x, y)가 (row, col)과 일치 할 때까지
			 * 1칸씩 수를 세어줌.
			 */
			for (int r = x; r < x + size; r++) {
				for (int c = y; c < y + size; c++) {
					// END : 찾았다면 남은 반복 필요없이 반환.
					if (r == row && c == col) {
						sb = new StringBuilder();
						sb.append(cnt);
						break;
					} else {
						cnt++;
					}
				}
			}
		}  // else
	
	}
}
