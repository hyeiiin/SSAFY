
import java.util.Scanner;
//0.13836s
//결국 논리적 흐름 queue 혹은 linkedList 활용하는 것과 같음
//queue, linkedList를 활요하면 직접 값을 이동, 배열로 하는 경우 인덱스를 활용해서 돌려주는 작업 하면됨.
public class Solution_1225_암호생성기_2 {
	static int n=8; //입력 받을 숫자의 수 8
	static int[] queue=new int[n]; //배열 명이 queue 0~7
	static int front=0; 
	static int rear=0;
	
	static void rotate() {
		while(true) {
			for(int i=1; i<=5; i++) {
				int num=queue[(++front)%n]-i;// queue 배열의 길이는 8, 암호 생성 규칙으로 돌리는 건 5
				//나머지 값을 이용해서 돌려주면 배열에서 값을 이동할 필요가 없음
				// i는 for문의 값을 그대로 활용하면 되고, 배열의 길이를 잘못 고려하면 혹시나 꼬일 수 주의!
				
				if(num<1){
					queue[(++rear)%n]=0; 
					return;
				}
				queue[(++rear)%n]=num; // num 0이하가 안됐으면 계속 돌려주기.
			}
		}
	}
	public static void main(String[] args) throws Exception{
		Scanner sc  = new Scanner(System.in);
		for(int tc=1; tc<=10; tc++) { //테스트 케이스 고정
			sc.next(); //입력받은 테스트케이스 수는 저장할 필요 없음.

			for(int i=0; i<n; i++) {
				queue[(++rear)%n]=sc.nextInt(); 
//				System.out.println(queue[i]);
			}
			rotate(); //암호 생성 규칙 돌리는 메소드
			System.out.print("#"+tc+" ");
			while(front!=rear) System.out.print(queue[(++front)%n]+" ");
			System.out.println();
		}
		sc.close();
	}
}
