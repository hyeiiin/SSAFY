
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class Solution_4012_요리사 {
	static int N;
	static int[][] map;
	static int min;
	static boolean[] used;
	public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        int testNum = Integer.parseInt(br.readLine());
        for(int test=1; test<=testNum; test++) {
			// N 입력 받기
			N = Integer.parseInt(br.readLine());
			map = new int[N][N];

			// 식재료 간의 시너지 입력 받기
			for(int i=0; i<N; i++){
				String[] temp = br.readLine().split(" ");
				for(int j=0; j<N; j++){
					map[i][j] = Integer.parseInt(temp[j]);
				}
			}
			//--------------INPUT END--------------------------
			min = Integer.MAX_VALUE; //결과저장 변수 초기화
			used = new boolean[N]; //사용여부 체크 배열 초기화
			
			combination(0,N,N/2);
			System.out.println("#" + test + " " + min);	
        }
        			  
		br.close();
	}
	//식재료 조합 만들기.
	/**
	 *@param start : 재료 선택 시작 indx
	 *@param N : 전체 재료 수
	 *@param r : 남은 선택 재료의 수
	 */
	public static void combination(int start, int N, int r){
		if(r==0){ //남은 선택 재료의 수 X-->조합 완성
			int ret = cook(); //조합한 재료로 A,B 요리 만들기
			min = min > ret ? ret : min;
			return;
		}
		
		//A음식, B음식 2가지 경우가 있음
		//원래 조합에 뽑히는 애들을 넣어주는데, 리스트에 뽑힌 애들을 넣어주면 
		//나중에 뽑히지 않은 애들을 찾아줘야해서
		//어떤 재료가 뽑혔는지를 마킹하기 위해서 boolean 배열을 사용
		// (순열이라서 방문 여부 확인하려고 사용하는게 아님!!)
		for(int i=start; i<N; i++){
			used[i] = true;
			combination(i+1, N, r-1); //다음 재료 선택
			used[i] = false;
		}
	}


	/**
	 * A음식, B음식 시너지의 합 구하기.
	 * @return 맛의 차이 반환
	 */
	public static int cook(){
		int a=0;	// A 음식의 시너지의 합
		int b=0;	// B 음식의 시너지의 합

		for(int i=0;i<N-1;i++) {
            for(int j=i+1;j<N;j++) {
                if(used[i] && used[j]) { //뽑은 재료는 A음식
                    a+=map[i][j]+map[j][i];
                }
                else if(!used[i] && !used[j]) { //안뽑힌 재료는 B음식
                    b+=map[i][j]+map[j][i];
                }
            }
        }

		return Math.abs(a-b);
	}
}