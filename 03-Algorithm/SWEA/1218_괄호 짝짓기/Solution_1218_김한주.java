import java.io.*; 

import java.util.*; 


class Solution_1218_김한주
{
	//입력 변수 정의 
	static BufferedReader buffer = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer tokens; 
	
    //열린 괄호 - 닫힌 괄호 쌍 
	static HashMap<Character, Character> dict;
    //열린 괄호 판단을 위한 기준 문자열  
	static final String opens = "[{(<";
    //닫힌 괄호 판단을 위한 기준 문자열 
	static final String closes = "]})>"; 
	
	
	static char[] datas;
	
	static void init() throws IOException{
		int n = Integer.valueOf(buffer.readLine()); 
		dict = new HashMap<>(); 
		datas = buffer.readLine().toCharArray(); 
		
		for(int i=0; i<4; i++) {
			dict.put(closes.charAt(i), opens.charAt(i));
		}
	}

	static boolean isValid() {
		Stack<Character> stack = new Stack<>(); 
		//문자열 순회 
		for(char data : datas ) {
			String d = String.valueOf(data); 
			//열린 문자일 경우  
			if(opens.contains(d)) {
				//스택에 넣어준다. 
				stack.add(data);
			}//닫힌 문자일 경우
			else {
				//스택에 값이 남아있는가? 없으면 flase
				if(stack.size()==0) {return false;}
				//스택 맨 위의 값을 꺼낸다. 
				char data2 = stack.pop(); 
				//쌍이 유효한가?
				if(dict.get(data) != data2) {
					return false; 
				}
				
			}
		}
		//다 돌고나서 남아있는 스택에 남아있는 문자가 있을 경우에도 유효하지 않다. 
		return stack.size()==0; 
		
	}
	
	public static void main(String[] args) throws IOException{
		
		StringBuilder result = new StringBuilder();
		
		for(int testCase=1; testCase<=10; testCase++) {
			init(); 
			
			if(isValid()) {
				result.append("#").append(testCase).append(" ").append(1).append("\n");
			}else {
				result.append("#").append(testCase).append(" ").append(0).append("\n");
			}
			
			
		}

		System.out.println(result);
	}
}
