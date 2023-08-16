import java.io.*; 

import java.util.*; 

//각팀의 승무패를 저장하는 클래스 
class Result{
	int []result;
	
	public Result(int win, int tie, int lose) {
		result = new int[3];
		result[0]= win;
		result[1] = tie; 
		result[2] = lose;
		
	}
	
	public String toString() {
		return Arrays.toString(result);
	}
	
	
}
//각 경기마다 팀1과 팀2의 조합을 저장하는 클래스 
class Match{
	int t1, t2; 
	
	public Match(int t1, int t2) {
		this.t1 = t1;
		this.t2 = t2; 
	}
	
	public String toString() {
		return this.t1+" vs "+this.t2; 
	}
}

class Solution
{   
    //가독성을 위해 Result클래스의 각 인덱스가 의미하는 것을 상수화 해놓음 
	static final int WIN =0;
	static final int TIE =1; 
	static final int LOSE = 2;
	
	static BufferedReader buffer = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer tokens; 
	static Result[][] map = new Result[4][6]; 
	static int result[] = new int[4]; 
	static ArrayList<Match> matchCombination; 
	static boolean[] isUsed; 
	
	//변수 초기화 부 
	static void init() throws IOException{
		for(int match=0; match<4; match++) {
			tokens = new StringTokenizer(buffer.readLine()); 
			for(int nation=0; nation<6; nation++) {
				int win = Integer.valueOf(tokens.nextToken());
				int tie = Integer.valueOf(tokens.nextToken());
				int lose = Integer.valueOf(tokens.nextToken());
				map[match][nation] = new Result(win,tie, lose); 
			}
		}
		matchCombination = new ArrayList<>(); 
		isUsed = new boolean[6];
		
		
		
	}
	
	static int[][] selecteds; 
	static boolean flag; 
	static void bt(int cur, int match) {
		//뽑아 놓은 각 팀마다의 승무패 순회 
		if(cur==matchCombination.size()) {
			////전체 경기 조합의 수 순회 
			for(int team=0; team<6; team++) {
				//해당 팀의 승 무 패가 같은지 각각 확인 
				for(int i=0; i<3; i++) {
					//하나라도 같지 않다면 바로 종료 
					if(map[match][team].result[i]!=selecteds[team][i]) {
						return; 
					}
				}
			}
			flag = true; 
			return; 
		}
		
		Match curMatch = matchCombination.get(cur); 
		
		//t1이 이겼을 경우 
		selecteds[curMatch.t1][WIN]++;
		selecteds[curMatch.t2][LOSE]++;
		
		bt(cur+1, match);

		selecteds[curMatch.t1][WIN]--;
		selecteds[curMatch.t2][LOSE]--;
		//t1이 졌을 경우 
		selecteds[curMatch.t2][WIN]++;
		selecteds[curMatch.t1][LOSE]++;
		
		bt(cur+1, match);

		selecteds[curMatch.t2][WIN]--;
		selecteds[curMatch.t1][LOSE]--;
		
		//비겼을 경우 
		selecteds[curMatch.t2][TIE]++;
		selecteds[curMatch.t1][TIE]++;
		
		bt(cur+1, match);
		
		selecteds[curMatch.t2][TIE]--;
		selecteds[curMatch.t1][TIE]--;
		
		
	}
	//미리 가능한 경기 조합 뽑아 놓기 
	static void makeMatchCombination(int cur, int last, int[] match) {
		if(cur==2) {
			//Match 클래스에 팀1과 팀2 속성으로 저장하기 
			matchCombination.add(new Match(match[0], match[1]));
			return; 
		}
		//6팀중 2개 중복 없이 조합으로 뽑는 경우의 수 
		for(int team=last+1; team<6; team++) {
			if(!isUsed[team]) {
				isUsed[team] = true; 
				match[cur] = team; 
				makeMatchCombination(cur+1, team, match);
				isUsed[team] = false; 
			}
		}
		
		
	}
	
	public static void main(String[] args) throws IOException{
		init(); 
		
		//가능한 경기 조합을 미리 만들어 놓고 저장 
		makeMatchCombination(0, -1, new int[2]); 

		for(int match=0; match<4; match++) {
			selecteds = new int[6][3]; 
			
			//최종 가능 여부 FLAG 선언 
			flag = false; 
			//백트래킹으로 전체 탐색 
			bt(0, match);
			//가능한 경우의 수인지 확인 
			if(flag) {
				result[match] = 1;
			}else {
				result[match] = 0; 
			}
		}
		//시간복잡도 3^15 * 18 (최대에서 백트래킹으로 가지치는 경우가 대부분)  => 14348907 *(~18)
		
		//결과 출력하기 
		print();
	}
	//최종 출력부 
	static void print() {
		StringBuilder sb = new StringBuilder(); 
		for(int i=0; i<4; i++) {
			sb.append(result[i]).append(" ");
		}
		System.out.println(sb); 
	}

}
	




