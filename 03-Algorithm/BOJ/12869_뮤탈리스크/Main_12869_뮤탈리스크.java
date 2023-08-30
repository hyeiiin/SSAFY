import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

//BOJ_12869_뮤탈리스크-DP
public class Main_12869_뮤탈리스크 {
	static int N; //scv 개수
	static int[] scv = {0,0,0}; //3개의 scv 체력(1~3개 있을 수 있음)
	static int[][][] HP; 
	static int result = Integer.MAX_VALUE;
	
	static int[][] damage= {{9,3,1},{9,1,3},{3,9,1},{3,1,9},{1,9,3},{1,3,9}};
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		StringTokenizer st = new StringTokenizer(br.readLine().trim());
		
		for(int i=0;i<N;i++) {
			scv[i]=Integer.parseInt(st.nextToken());
		}
		//----------Input End-----------------
		//SCV 체력이 각각 hp1, hp2, hp3 남았을 때 공격 횟수의 최솟값
		HP = new int[61][61][61]; //만들어 질 수 있는 최력의 최대
		attack(scv[0], scv[1],scv[2],0);
		
		System.out.println(result);
	}
	

	/**
	 * 
	 * @param a scv[0]의 체력
	 * @param b scv[1]의 체력
	 * @param c scv[2]의 체력
	 * @param cnt 공격한 횟수
	 */
	static void attack(int a, int b, int c, int cnt) {
		//음수 값이 나와도 체력은 0
		if(a<0) a=0; 
		if(b<0) b=0;
		if(c<0) c=0;
		//전달 받은 값보다 더 작은 값은 값이 들어가 있거나, 이미 값이 있으면 다른 어택 시도
		if(HP[a][b][c] <= cnt && HP[a][b][c] !=0) {
			return;
		}else {
			//현재 공격 횟수 저장해두기
			HP[a][b][c]=cnt;
		}
		//모든 scv의 체력이 0이되면 
		if(a==0&&b==0&&c==0) { 
			result = Math.min(result, cnt);
			return;
		}
		//3개의 scv를 공격하는 경우의 수 = 6가지
		for(int i=0; i<6; i++) {
			attack(a-damage[i][0],b-damage[i][1],c-damage[i][2], cnt+1);
		}
		
		return;
		
	}

}