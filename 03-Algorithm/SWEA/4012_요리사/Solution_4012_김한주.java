import java.io.*; 

import java.util.*; 

class Solution
{
	//입력 변수 정의 
	static BufferedReader buffer = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer tokens; 
	
	
	//식재료 개수 
	static int n; 
	
	// 1 2 , 3 4
	// 1 3 , 2 4
	// 1 4 , 2 3 
	
	
	//s[i][j] = i와 j를 같이 요리한 궁합 점수 
	static int[][] s;
	static boolean[] isUsed;
	static void init() throws IOException{
		n = Integer.valueOf(buffer.readLine());
		s = new int[n][n]; 
		isUsed = new boolean[n]; 
		a = new ArrayList<>();
		result = Integer.MAX_VALUE; 
		for(int x=0; x<n; x++) {
			tokens = new StringTokenizer(buffer.readLine()); 
			for(int y=0; y<n; y++) {
				s[x][y] = Integer.valueOf(tokens.nextToken()); 
			}
		}
	}
	
	static ArrayList<Integer> a;
	static ArrayList<Integer> b;
	static int result; 
	//점수 리스트를 입력으로 받아 맛의 총합을 계산 
	static int getScore(ArrayList<Integer> scores) {
		int totalScore = 0;
		
		for(int i=0; i<scores.size(); i++) {
			for(int j=0; j<scores.size(); j++) {
				if(i!=j) {
					totalScore += s[scores.get(i)][scores.get(j)]; 
				}
			}
		}
		return totalScore; 
	}
	//bt로 n개 중 n/2를 뽑고 사용한 것을 체크한다.
	static void bt(int cur, int last) {
		if(cur==n/2) {
            //체크리스트로 b음식 재료 리스트를 생성한다.
			b = new ArrayList<>(); 
			for(int i=0; i<n; i++) {
				if(!isUsed[i]) {
					b.add(i);
				}
			}
            //a음식 재료 리스트로 시너지 점수를 계산한다 
			int scoreA = getScore(a);
            //b음식 재료 리스트로 시너지 점수를 계산한다.
			int scoreB = getScore(b); 
            //두 음식의 점수 차를 구하고 갱신한다. 
			result = Math.min(result, Math.abs(scoreA-scoreB)); 
			return; 
		}
		
		for(int next=last+1; next<n; next++) {
			if(!isUsed[next]) {
				isUsed[next] = true;
				a.add(next);
				bt(cur+1, next);
				a.remove(a.size()-1); 
				isUsed[next] = false; 
			}
		}
		
	}
	
	public static void main(String[] args)throws IOException{
		int T = Integer.valueOf(buffer.readLine());
		StringBuilder sb = new StringBuilder();
        //테스트 케이스 순회 
		for(int t=1; t<=T; t++) {
            //변수 초기화 
			init(); 
            //완전 탐색 진행  
			bt(0,-1);
            //진행 결과 sb에 추가
			sb.append("#").append(t).append(" ").append(result).append("\n");
		}
        //최종 결과 출력 
		System.out.println(sb); 
	}
	
	
		 
		
		
		
}
	




