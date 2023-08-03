import java.io.*; 

import java.util.*; 




class Main_2023_김한주
{
	//입력 변수 정의 
	static BufferedReader buffer = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer tokens; 
	

	static int n; 
	static boolean[] isNotPrime; 
	static StringBuilder result = new StringBuilder(); 
	
	//소수 판단 
	static boolean isPrime(int num) {
		//0,1은 소수가 아니다. 
		if(num<2) return false;
		//자기자신의 루트까지 순회하여 나누어 떨어지는 숫자가 있는지 확인하기 
		for(int i=2; i*i<=num; i++) {
			if(num%i==0) {
				return false;
			}
		}return true; 
	}
	
	//백트래킹으로 나머지 3자리 숫자 결정 
	static void bt(int cur, int num) {
		//넘어온 숫자가 소수가 아닐경우 바로 정지 
		if(!isPrime(num)) return; 
		
		//나머지 수를 전부 뽑고 소수인지 확인하여 스트링 빌더에 추가 
		if(cur==n-1&&isPrime(num)) {
			
			result.append(num).append("\n"); 
			return;
		}
		
		//중복하여 순서에 상관없이 뽑기 
		for(int next=0; next<10; next++) {
			bt(cur+1, num*10+next);
		}
	}
	
	public static void main(String[] args) throws IOException{
		n = Integer.valueOf(buffer.readLine()); 
		//첫번째 숫자 1~9중 선택하여 백트래킹 돌린다. 
		for(int i=1; i<=9; i++) {
			bt(0,i);
		}
		System.out.println(result); 
		
	}
	//1~n까지 
	
		//왼쪽부터 i번째 자릿수까지의 수가 소수인지 확인 
	
}	



