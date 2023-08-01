import java.io.*; 

import java.util.*; 



class Main_15649_김한주
{
	static BufferedReader buffer = new BufferedReader(new InputStreamReader(System.in));
	static int m; 
	static int n; 
	static StringBuilder result = new StringBuilder();
	static boolean[] isUsed;
	static void bt(int cur, int[] selecteds) {
		if(cur==m) {
			for(int selected : selecteds) {
				result.append(selected).append(" ");
			}result.append("\n");
			return; 
		}
		
		for(int next=1; next<=n; next++ ) {
			if(!isUsed[next]) {
				isUsed[next] = true; 
				selecteds[cur] = next; 
				bt(cur+1, selecteds);
				
				isUsed[next] = false; 
			}
			
		}
		
		
	}
	
	public static void main(String[] args) throws IOException{
		StringTokenizer tokens = new StringTokenizer(buffer.readLine());
		
		n = Integer.valueOf(tokens.nextToken());
		m = Integer.valueOf(tokens.nextToken()); 
		
		isUsed = new boolean[n+1]; 
		int[] selecteds = new int[m]; 
		bt(0, selecteds);
		
		System.out.println(result);

	}

	
}	



