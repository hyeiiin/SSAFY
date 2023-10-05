

import java.io.*;
import java.util.*;



 class Solution {

	static BufferedReader buffer = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer tokens; 
	
	static int n;
	static int k; 
	static int maxRotateNum; 
	
	//k번째로 큰수를 10진수로 만든 수 
	
	static char[] keys; 
	static HashSet<String> filter; 
	static ArrayList<Long> result; 
	
	private static long toDecimal(String hex) {
		return Long.valueOf(hex,16); 
		
	}
	
	
	static void init() throws IOException{
		tokens = new StringTokenizer(buffer.readLine()); 
		
		n = Integer.valueOf(tokens.nextToken()); 
		k = Integer.valueOf(tokens.nextToken()); 
		
		
		keys = new char[n];
		String line = buffer.readLine(); 
		for(int i=0; i<n; i++) {
			keys[i] = line.charAt(i);
		}
		maxRotateNum = n/4; 
		filter = new HashSet<>(); 
		result = new ArrayList<>(); 
		
	}
	
    //배열 회전 시키기 
	static void rotate() {
		char tmp = keys[n-1]; 
		
		for(int i=n-2; i>=0; i--) {
			keys[i+1] = keys[i]; 
		}
		
		keys[0] = tmp; 
		
		
	}
	
	public static void main(String[] args)throws IOException{
		int T = Integer.valueOf(buffer.readLine());
		
		StringBuilder sb = new StringBuilder(); 
		
		for(int t=1; t<=T; t++) {
			init(); 
			
			
			for(int rotate =0; rotate<maxRotateNum; rotate++) {
				//돌리기
				rotate(); 
				//문자열 파싱하기
				for(int start =0; start<n-maxRotateNum+1; start+=maxRotateNum) {
					String data = new String(keys,start,maxRotateNum);
					filter.add(data); 
				}
				
				//해쉬셋에 넣어주기 
				
			}
			for(String data : filter) {
				result.add(toDecimal(data));
			}
			
			
			Collections.sort(result, Collections.reverseOrder());
			
			sb.append("#").append(t).append(" ").append(result.get(k-1)).append("\n");
			//해쉬셋 전부 가져오기 
			//배열에 넣어주기 
			//배열 정렬하기 
			
		}
		System.out.println(sb); 
		
		
		
	}
}
