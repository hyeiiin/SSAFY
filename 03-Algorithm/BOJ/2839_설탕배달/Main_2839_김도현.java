package baekjun;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main_2839_김도현 {
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int N = Integer.parseInt(br.readLine());
		int res = 0;
		
		while(true) {
			if((N%5)%3==0) { // 5로 나누어질 때 3으로도 나누어진다면!
				res+=N/5;   
				res+=(N%5)/3; 
				System.out.println(res);
				break;
			}else {			//  5로 나누어질 때 3으로도 나누어지지 않는다면!
				if(go(N)) {	//  go함수에서 true라면
					for (int i = 1; i < N/3; i++) {
						if((N-3*i)%5==0) {
							res+=i;
							res+=(N-3*i)/5;
							break;
						}
					}
					System.out.println(res);
					break;
				}else {  	//  go함수에서 false라면
					if(N%3==0) { 	// 3으로만으로 나누어진다면
						res+=N/3;
						System.out.println(res);
						break;
					}else {			// 아니라면
						System.out.println(-1);
						break;
					}
				}
			}
		}
	}
	
	// 3의 배수 만큼 뺀 값이 5으로 나누어지는지 체크하는 함수
	public static boolean go(int N) {
		int res = 0;
		for (int i = 1; i < N/3; i++) {
			if((N-3*i)%5==0) {
				res+=i;
				res+=(N-3*i)/5;
			}
		}
		if(res==0) {
			return false;
		}else {
			return true;
		}
	}
}	
