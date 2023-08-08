import java.io.*; 

import java.util.*; 


class Solution
{
	//입력 변수 정의 
	static BufferedReader buffer = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer tokens; 
	
	static char[] root;
	static int[] left;
	static int[] right; 
	static int n; 
	
	static StringBuilder result; 
	
	static void init() throws IOException{
		flag = true; 
		n = Integer.valueOf(buffer.readLine()); 
		root = new char[n+1];
		left = new int[n+1];
		right = new int[n+1]; 
		result = new StringBuilder();
		
		for(int i=1; i<=n; i++) {
			tokens = new StringTokenizer(buffer.readLine()); 
			
			int idx = Integer.valueOf(tokens.nextToken()); 
			char data = tokens.nextToken().charAt(0); 
			if(tokens.countTokens()==4) 
			{
				int leftIdx = Integer.valueOf(tokens.nextToken());
				int rightIdx = Integer.valueOf(tokens.nextToken());
			}  
			
			root[idx] = data; 
			 
		}
	}
	static boolean flag;
	
	static boolean isValid(char d) {
		return d=='+'||d=='-'||d=='*'||d=='/';
	}
	
	static void mid(int idx, int count) {
		
		if(idx>n) {return;}
		if(idx==0) {
			return; 
		}
		
		mid(idx*2, count+1);
		
		result.append(root[idx]);
		mid(idx*2+1, count+1);
		
	}
	
	public static void main(String[] args)throws IOException{
		StringBuilder sb = new StringBuilder(); 
		for(int testCase=1; testCase<=10; testCase++) {
			init(); 
			flag = true; 
			mid(1,1);
			String data = result.toString(); 
			for(int i=0; i<result.length()-1; i++) {
				if(isValid(data.charAt(i))&&isValid(data.charAt(i+1))) {
					flag = false; 
					break; 
				}
			}
			if(flag) {
				sb.append("#").append(testCase).append(" ").append(1).append("\n");
			}else {
				sb.append("#").append(testCase).append(" ").append(0).append("\n");
			}
			
			
		}
		System.out.println(sb); 
	}
	
}	



