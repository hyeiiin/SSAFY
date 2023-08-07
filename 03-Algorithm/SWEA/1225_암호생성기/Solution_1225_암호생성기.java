package swea;

import java.io.FileReader;
import java.util.LinkedList;
import java.util.Scanner;

//0.15855s


public class Solution_1225_암호생성기 {
	//LinkedList로 구현
		static LinkedList<Integer> numbers= new LinkedList<Integer>();
		static int N=8; // 입력받는 숫자의 수 8개 고정
		public static void main(String[] args) throws Exception{
			int T = 10; // 테스트케이스 10개 
			Scanner scan = new Scanner(System.in);
			for (int testcase = 1; testcase <=T; testcase++) {
				int  t = scan.nextInt(); //입력 첫줄에 주어지는 테스트 케이스의 번호 입력 받기.
				numbers.clear(); //static으로 선언 되어 있어서 활용.
				for (int i = 0; i <N; i++) { 
					numbers.add(scan.nextInt()); //8개의 숫자값 입력 받기
				}
				makeCryptography(); // 암호 생성 규칙 수행 메소드
				System.out.print("#"+t+" "); //출력 포맷 잘 맞춰서 출력
				for (int num : numbers) { 
					System.out.print(num+" ");
				}
				System.out.println();
			}
		}
		private static void makeCryptography() { 
			boolean flag = false; //숫자가 0이하가 되면, flag를 true로 변경해서 암호 생성 종료
			int num=0;				
			top:
			while(!flag) { //flag가 true가 될때까지 계속 반복
				for (int i = 1; i <=5; i++) { //1~5 사이클 돌면서 수를 감소시킴
					num = numbers.poll() -i;	//맨 앞에 숫자 추출해서 암호로 변경
					if(num<=0) {//수가 0이하가 됐는지 확인하는 작업
						num = 0;
						flag = true; //while문 멈치기 위해 flag 변경
					}
					numbers.add(num); //맨앞에 추출한 숫자 변경 완료 했으니, 그 수를 가장 뒤에 넣어 줌.
					if(flag) {
						break top;
					}
				}
			}
		}
	}



