
import java.io.IOException;
import java.util.Scanner;

/**
 * S - 28,172 kb/164 ms
 *
 */
//이차원 배열, 규칙을 이용해서 처리하는게 관건
public class Solution_2805_농작물수확하기 {
	
	private static int map[][], N,center; //농장을 만들어줄 배열, 농장 크기를 받을 N, 반절 값 저장 center
	public static void main(String[] args) throws NumberFormatException, IOException {

		Scanner in = new Scanner(System.in);
		int T = in.nextInt();
		
		for(int t=1; t<=T; ++t) {
			N = in.nextInt(); //농장의 크기
			map = new int[N][N]; //배열 만들어주고.
			center = N/2; // 농장 크기의 절반을 center 변수 저장 --> 판단의 기준
			char[] temp = null; 
			//농장에 각 칸별로 수익을 저장
			for(int i=0; i<N; ++i) {
				temp = in.next().toCharArray(); //1줄의 1차원배열로 받아오고, 문자의 형태로 받아옴.
				for(int j=0; j<N; ++j) {
					map[i][j] = temp[j]-'0'; //문자의 형태이기 때문에, 아스키코드값을 이용해서 정수 숫자로
					// 만들어주기 위해서 문자-'0' --> 정수형 숫자값을 받을 수 있음
				}
			}
			//------INPUT END-------------------------
			System.out.println("#"+t+" "+getBenefit2());
		}
		in.close();
	}
	private static int getBenefit() { //마름모꼴로 되어있는 농장의 수익을  구하는 메서드
		int benefit = 0,begin,end,gap=0; // 수익을 구하는 benefit
		// 각 가로줄별로 수익을 구하는 시작/끝 위치를 저장 변수, gap --> center를기준으로 진행될때마다 늘어나는 양옆 칸수
		for(int i=0; i<N; ++i) { 
			begin = center - gap; //gap = 0 시작은 시작 위치만 더하게 됨
			end = center + gap; 
			for(int j=begin; j<=end; ++j) { // 늘린 칸의 수익률 모두 더하게 됨.
				benefit += map[i][j];
			}
			if(i<center) { // 행의 반까지는 양쪽에 한칸씩 칸을 늘리는 형태로 값이 추가됨
				gap++;
			}else { // 행의 반을 넘으면, 반대로 양쪽에 한칸씩 줄어드는 형태로 변화
				gap--;
			}
		}
		return benefit;
	}
	
	private static int getBenefit2() {
		int benefit=0;
		for (int i = 0; i < N; i++) {
			int gap=Math.abs(i-center);
			for (int j = gap; j < N-gap; j++) {
				benefit+=map[i][j];
				//System.out.printf("(%d,%d)",i,j);
			}
			//System.out.println();
		}
		return benefit;
	}

	
}
