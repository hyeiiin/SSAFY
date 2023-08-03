import java.io.*; 

import java.util.*; 




class Main_2961_김한주
{
	//입력 변수 정의 
	static BufferedReader buffer = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer tokens; 
	
	static int n; 
	static long[] s;
	static long[] b; 
	static boolean[] isUsed; 
	static ArrayList<Integer> selecteds = new ArrayList<>(); 
	
	static long result = Long.MAX_VALUE; 
	//중복을 뽑지 않는 조합 
	static void bt(int cur, long sumS, long sumB, boolean flag) {
		if(cur==n) {
			//재료를 한번이라도 선택했을 경우에만 최솟값 갱신 
			if(flag) {
				result = Math.min(result, Math.abs(sumS-sumB));
			}
			 
			return;
		}
		
		//해당 재료를 사용하지 않았을 경우 추가해보는 경우 
		if(!isUsed[cur]) {

			isUsed[cur] = true; 
			bt(cur+1, sumS*s[cur],sumB+b[cur], true); 
			isUsed[cur] = false; 

		}
		
		//재료를 이미 하나라도 선택했으면 flag 유지 
		if(flag) {
			//CUR번째 재료를 사용하지 않음 
			bt(cur+1, sumS,sumB, true);
			
		}else //재료를 한번도 선택한 적이 없다면 FLAG 거짓으로 유지  
		{
			//CUR번째 재료를 사용하지 않음 
			bt(cur+1, sumS, sumB, false);
		}
		 
	}
	
	static void init() throws IOException{
		n = Integer.valueOf(buffer.readLine());
		b = new long[n];
		s = new long[n]; 
		isUsed = new boolean[n]; 
		for(int i=0; i<n; i++) {
			tokens = new StringTokenizer(buffer.readLine());
			s[i] = Integer.valueOf(tokens.nextToken());
			b[i] = Integer.valueOf(tokens.nextToken()); 
		}
		
	}
	
	public static void main(String[] args)throws IOException{
		init(); 
		bt(0,1,0,false); 
		System.out.println(result); 
	}
	
}	



