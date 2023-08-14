import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main_1074_한정수 {
	static int N;
	static int N2;
	static int R;
	static int C;
	static int[][] arr;
	static int start;
	static int answer;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		N2 = (int)Math.pow(2, N);
//		arr = new int[N2][N2]; //배열을 만들면 메모리 터짐. 그럼 배열을 만들지말고 r,c값만 판단해서 돌리라는 뜻.
		start = 0;
		answer = -1;
		
		//전체 배열을 1,2,3,4 사분면으로 나누고 r,c가 어디에 속하는지 찾아야함.
		//그 사분면을 찍어서 다시 4분할 시키고 4분할시키고....
		//각 사분면의 시작(11시) 점의 값은 (이전사분면 시작값 + (현재 사이즈(==가로길이)/2) * 사분면.)
		
		
		solution(N2, 0, 0, 0, R, C);


		System.out.println(answer);
	}

	public static void solution(int size, int start_x, int start_y, int value, int r, int c) {

		if (size == 1) {
//			System.out.println("size =1  끝");
			answer = value;
			return;
		}
		else {
			int half = (int)size / (int)2;
//			int start1_x = start_x;
//			int start1_y = start_y;
//			
//			int start2_x = start_x;
//			int start2_y = start_y+half;
//			
//			int start3_x = start_x + half;
//			int start3_y = start_y + half;
//			
//			int start4_x = start_x + half;
//			int start4_y = start_y;
			
			//굳이 다 만들지말고, 딱 절반 절반만 보자.
			int half_x = start_x + half;
			int half_y = start_y + half;

			if(r >= half_x) {
				//3,4사분면
				if(c < half_y) {
					//4사분면
//					System.out.println("4사분면");
					solution(half, start_x+half, start_y, value + half*half*2, r, c);
				}
				else {
					//3사분면
//					System.out.println("3사분면");
					solution(half, start_x+half, start_y+half, value + half*half*3, r, c);
				}
			}
			else {
				//1,2사분면
				if(c >= half_y) {
					//2사분면
//					System.out.println("2사분면");
					solution(half, start_x, start_y+half, value + half*half*1, r, c);
				}
				else {
					//1사분면
//					System.out.println("1사분면");
					solution(half, start_x, start_y, value, r, c);
				}
			}
			
			
		}
	}

}
