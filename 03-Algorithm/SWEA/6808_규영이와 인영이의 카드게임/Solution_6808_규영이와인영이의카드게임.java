
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

// 단순 재귀로 순열 작성 해결
public class Solution_6808_규영이와인영이의카드게임 {

	static boolean[] check;	//입력 받았을때 인영이의 카드를 알아내기 위한 배열
	static boolean[] isSelected; // 인영이의 카드 중 선택되 카드 체크
	
	static int[] gyu;//규영이 카드
	static int[] in; //인영이 카드
	
	static int winG, winI; //규영이, 인영이의 승패를 체크하는 변수
	
	public static void main(String[] args) throws IOException {

		//System.setIn(new FileInputStream("input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st = null;
		
		int T = Integer.parseInt(br.readLine());
		
		for(int tc=1; tc<=T; tc++) {
			check = new boolean[19]; 
			isSelected = new boolean[9]; 
			gyu = new int[9];
			in = new int[9];
			winG = 0; //승패를 체크하는 변수
			winI = 0;
			
			st = new StringTokenizer(br.readLine());
			for(int i=0;i<9;i++) {
				int num = Integer.parseInt(st.nextToken()); 
				gyu[i] = num;	//규영이의 카드 입력
				check[num] = true; // 해당 인덱스는 true 바꾸어 체크
			}
			
			int idx = 0;
			for(int i=1;i<=18;i++) {
				if(!check[i]) { //false 카드를 인영이한테 주기
					in[idx] = i;	
					idx++;
				}
			}
			
			permutation(0, 0, 0);
			
			sb.append("#"+tc +" "+winG+" "+winI+"\n");
		}
		System.out.println(sb);
	}


	/**
	 * 주어진 카드로 게임 진행하기(인영이 카드 순서 뽑기)
	 * @param r : 진행되는 라운드
	 * @param scoreG : 규영이의 점수
	 * @param scoreI : 인영이의 점수
	 */
	private static void permutation(int r,int scoreG,int scoreI) {

		if(r==9) { //9라운드가 되면, 
			
			if(scoreG>scoreI) winG++;
			else if(scoreG < scoreI) winI++;
			
			return;
		}
		
		// 9라운드 진행
		for(int i=0;i<9;i++) {
			//이미 선택된 카드라면, 
			if(isSelected[i]) continue;
			// 카드 선택 처리
			isSelected[i] = true;
			// 해당 라운드의 카드 합 ==> 이기는 사람이 가져갈 예정
			int sum = gyu[r] + in[i]; 
			//규영이가 이긴경우
			if(gyu[r] > in[i]) {
				permutation(r+1, scoreG+sum, scoreI);}
			else {//인영이가 이긴경우
				permutation(r+1, scoreG, scoreI+sum);
			}
			//카드 선택 해제 처리.
			isSelected[i] = false;
		}
		
	}

}
