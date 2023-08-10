import java.io.*; 

import java.util.*; 

class Solution
{
	//입력 변수 정의 
	static BufferedReader buffer = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer tokens; 
	
	
	static int n;
	static int limit; 
	static int[] s;
	static int[] c; 
	static int result; 

    //백트래킹 정의 
	static void bt(int cur, int totalS, int totalC) {
		if(cur == n) {
            //칼로리 기준을 넘지 않았을 경우에만 갱신 
			if(totalC<=limit) {
				result = Math.max(result, totalS); 
			}
			return; 
		}
		//cur번째의 재료를 사용하는 경우 
		bt(cur+1, totalS+s[cur], totalC+c[cur]); 
        //cur번째의 재료를 사용하지 않는 경우 
		bt(cur+1, totalS, totalC);
	}
	
	public static void main(String[] args) throws IOException{
		int T = Integer.valueOf(buffer.readLine()); 
		StringBuilder sb = new StringBuilder(); 
        //테스트 케이스 순회 
		for(int t=1; t<=T; t++) {
            //입력받기
			init(); 
            //완전탐색 진행 
			bt(0,0,0); 
            //결과 출력 형식에 맞게 포맷팅 
			sb.append("#").append(t).append(" ").append(result).append("\n"); 
		}
        //결과 출력 
		System.out.println(sb); 
		
		
	}
	
	static void init() throws IOException{
		tokens = new StringTokenizer(buffer.readLine()); 
		n = Integer.valueOf(tokens.nextToken());
		limit = Integer.valueOf(tokens.nextToken());
		
		c = new int[n];
		s = new int[n];
		result = 0; 
		for(int i=0; i<n; i++) {
			tokens = new StringTokenizer(buffer.readLine()); 
			s[i] = Integer.valueOf(tokens.nextToken());
			c[i] = Integer.valueOf(tokens.nextToken()); 
		}
	}
}
	




