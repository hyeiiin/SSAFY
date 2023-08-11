

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution_5215_햄버거다이어트 {
	static int N,L; 
	static int maxPrefer;	
	static int[][] ingredient;	
	
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub

//		System.setIn(new FileInputStream("input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int T = Integer.parseInt(br.readLine());
		
		for(int tc=1;tc<=T;tc++) {
			
			maxPrefer=0;	//가장 높은 햄버거 점수
			
			StringTokenizer st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());//재료수	 
			L = Integer.parseInt(st.nextToken());//제한 칼로리	
			
			ingredient = new int[N][2]; // 점수, 칼로리 저장 
			
			
			for(int i=0;i<N;i++) {
				st = new StringTokenizer(br.readLine());
				for(int j=0;j<2;j++) {
					ingredient[i][j] = Integer.parseInt(st.nextToken()); //재료 정보 입력
				}
			}
			
			// 조합 메소드
			pickOne(0,0,0);
			
			System.out.println("#"+tc+" "+maxPrefer);
		}
	}
	
	
/**
 * @param cnt :재료의 수, 재료의 순서
 * @param prefer : 누적점수
 * @param calorie : 누적칼로리
 */
	private static void pickOne(int cnt, int prefer ,int calorie  ) {
		if(calorie>L)return; //조합 만들가는 중간에 칼리로 넘으면 다른 조합 만들러가기
		if(cnt==N) { //재료의 개수가 N개에 도달했을때
			
			if(calorie<=L) { // 칼로리를 넘기지 않았을 때(혹시나...체크)
				maxPrefer = Math.max(maxPrefer, prefer);
			}
			return;
		}
		
		//재료를 선택했을 때
		pickOne(cnt+1,  prefer+ingredient[cnt][0],  calorie+ingredient[cnt][1] );
		// 재료를 선택하지 않았을 때
		pickOne(cnt+1, prefer, calorie);
		
	}
	
}
