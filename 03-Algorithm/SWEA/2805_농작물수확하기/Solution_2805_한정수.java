import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Solution_2805_한정수 {
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		
		for(int test_case=1 ; test_case <= T; test_case++) {
			int num = Integer.parseInt(br.readLine());
			char[][]arr = new char[num][num];
			for(int i=0 ; i<num ; i++) {
				arr[i] = br.readLine().toCharArray();
			}
			
			int q = num / 2;
			boolean isPoint = false;
			int sum = 0;
			for (int i =0; i < num; i++) {
//				int temp = q - (q-i) +1;
//				while(temp >0) {
//					if (temp == 1) {
//						sum += (int)(arr[i][q-(temp-1)])-48;
//					}
//					else {
//						sum += (int)(arr[i][q-(temp-1)])-48;
//						sum += (int)(arr[i][q+(temp-1)])-48;
//					}	
//					temp -= 1;
//				}
				if(!isPoint) {
					int temp = i;
					while(temp > -1) {
						if (temp == 0) {
							sum += (int)(arr[i][q-(temp)])-48;
						}
						else {
							sum += (int)(arr[i][q-(temp)])-48;
							sum += (int)(arr[i][q+(temp)])-48;
						}	
						temp -= 1;
					}
					if (i == q) {
						isPoint = true;
					}
					
					
				}
				else {
					int temp = Math.abs(2*q-i);
					while(temp > -1) {
						if (temp == 0) {
							sum += (int)(arr[i][q-(temp)])-48;
						}
						else {
							sum += (int)(arr[i][q-(temp)])-48;
							sum += (int)(arr[i][q+(temp)])-48;
						}	
						temp -= 1;
						
					}
				}
				
				
			}
			System.out.printf("#%d %d", test_case, sum);
			System.out.println();
			
		}
		
		
		// num = 5  q = 2 
		// i = 0 >> temp = 2 -(2-0)+1 = 1
		// sum+= arr[0][2-0];
		// temp = 0
		//i = 1 >> temp = 2 -(2-1)+1 = 2
		// sum+= arr[1][2-(2-1)] >> sum += arr[1][1]
		// sum+= arr[1][2+(2-1)] >> sum += arr[1][3]
//		char temp1 = '1';
//		char temp2 = '2';
//		System.out.println((int)temp1 - 48);
//		System.out.println((int)temp2 - 48);
	}

}


//	1 4 0 5 4
//	4 4 2 5 0
//	0 2 0 3 2
//	5 1 2 0 4
//	5 2 2 1 2

//        02               0부터,    [0, q]
//     11 12 13				1  ,    [1, q-1] [1, q+1]
//  20 21 22 23 24			2,      [2, q-2] [2, q-1]  [2, q],  [2, q+1]  [2, q+2]   i == q이면, 전환.
//     31 32 33 			3,      [3, q-1] [3, q+1]
//        42				4,      [4, q]

//  num / 2   7 5 3 1
//  q = num / 2
//  num = 7이면 q= 3     1 3 5 7 5 3 1
//                  7-2q 7-2(q-1) 7-2(q-2) 7-2(q-3) 7-2(q-4)  7-2(q-5) 7-2(q-6)


