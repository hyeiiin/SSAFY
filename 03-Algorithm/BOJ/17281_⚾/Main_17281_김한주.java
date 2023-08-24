import java.io.*;
import java.math.BigInteger;
import java.util.*; 



class Main
{	
	static BufferedReader buffer = new BufferedReader(new InputStreamReader(System.in)); 
	static StringTokenizer tokens; 
	
	
	//팀
		//9명 
	
	
	static int n; // 이닝 수 
		//3아웃 발생시 이닝 종료 
		//공격과 수비 전환 
	
		//경기 시작전까지 타순을 정함 
		//정해진 타순은 변경 불가능 
		//타순은 이닝이 변경되어도 순서 유지해야함 
	
	//공격 
		//이닝 시작시 주자는 없음 
		//홈에 도착하면 1득점 
		//안타,
			//타자와 모든 주자 한 루 진출
		//2루타
			//타자와 모든 주자 2루 진출
		//3루타,
			//타자와 모든 주자 3루 진출 
		//홈런
			//타자와 모든 주자 홈까지 진루 
		//아웃 
			//모든 주자 진루 못하고 아웃카운트 증가 
	static final int HOMERUN = 4;
	static final int OUT = 0; 
	static final int NUM = 9; 
	
	
	static int[] selects; 
	static boolean[] isUsed; 
	static boolean[] isPlayerOnGround; 
	
	static int[][] results; 
	static int score = 0; 
	static int maxScore; 
	
	static int NOBODY = -1; 
	
	static void init() throws IOException{
		n = Integer.valueOf(buffer.readLine()); 
		
		
		results = new int[n][NUM];
		
		for(int inning=0; inning<n; inning++) {
			tokens = new StringTokenizer(buffer.readLine());
			for(int num = 0; num<NUM; num++) {
				results[inning][num] = Integer.valueOf(tokens.nextToken());
			}
		}
		selects = new int[NUM];
		isUsed = new boolean[NUM]; 
		maxScore = 0; 
	}
	

	
	static int[] getNextPosition(int[] currentPositions, int option, int player) {
		int[] nextPositions = new int[4]; 
		
		Arrays.fill(nextPositions, NOBODY);
		//현재 그라운드에 나가 있는 선수 전부 움직임 전부 처리 
		for(int i=3; i>=1; i--) {
			if(currentPositions[i]!=NOBODY) {
				//홈을 밟는 경우 
				if(i+option>3) {
					score++; 
					isPlayerOnGround[currentPositions[i]] = false; 
					nextPositions[i] = NOBODY; 
				}
				//홈을 밟지 않는 경우 
				else {
					nextPositions[i+option] = currentPositions[i]; 
					currentPositions[i] = NOBODY; 
				}
				
			}
		}
		
		
		//타자가 진출함 
		if(option==4) {
			score++;
		}else {
			nextPositions[option] = player; 
			isPlayerOnGround[player] = true; 
		}

		
		return nextPositions; 
	}
	
	static int currentPlayerIdx; 
	static void getScore() {
		score = 0; 
		
		currentPlayerIdx = 0; 
		for(int inning=0; inning<n; inning++) {
			isPlayerOnGround = new boolean[NUM]; 
			int out = 0; 
			boolean flag = true; 
			int[] playerPositions = new int[4];
			Arrays.fill(playerPositions, NOBODY);
			while(flag){
				int iResult = results[inning][selects[currentPlayerIdx]]; 
				
				
				if(iResult==OUT) {
					out++; 
				}else{
					playerPositions = getNextPosition(playerPositions, iResult, currentPlayerIdx);
				}
				
				if(out>=3) {
					flag = false; 
				}
//				System.out.println("이닝 번호 결과 아웃 점수");
//				System.out.printf("%2d  %2d  %2d  %2d  %2d \n",inning,currentPlayerIdx,iResult,out,score);
//				System.out.println("그라운드 상황: "+Arrays.toString(playerPositions));
				currentPlayerIdx = getNextPlayer(currentPlayerIdx);
				maxScore = Math.max(maxScore, score);
			}
		}
		
	}
	
	static int getNextPlayer(int currentPlayer) {
		
		int nextPlayer = currentPlayer+1; 
		if(nextPlayer>=NUM) {
			nextPlayer = 0; 
		}
//		System.out.println(nextPlayer);
		while(true) {
			if(!isPlayerOnGround[nextPlayer]) {
				return nextPlayer; 
			}
			
			nextPlayer = nextPlayer+1; 
			if(nextPlayer>=NUM) {
				nextPlayer = 0; 
			}
		}
		
	}
	
	static void select(int cur) {
		if(cur==NUM) {
//			System.out.println(Arrays.toString(selects));
			getScore();
			maxScore = Math.max(maxScore, score);
			return; 
		}
		
		for(int next=0; next<NUM; next++) {
			if(!isUsed[next]) {
				selects[cur] = next;
				isUsed[next] = true; 
				select(cur+1);
				isUsed[next] = false;
			}
		}
	}
	



	public static void main(String[] args) throws IOException{
		//4번타자 첫번째 
		//나머지 8명의 타자 순서 뽑기 
			//시뮬레이션 진행 
				//이닝 진행 
		init(); 
		isUsed[3] = true;
		selects[0] = 3;
		select(1);
		getScore(); 
		
		System.out.println(maxScore); 
		
	}
}
	




