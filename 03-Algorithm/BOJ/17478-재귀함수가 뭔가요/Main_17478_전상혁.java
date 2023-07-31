import java.util.Scanner;

public class p17478 {

	
	public static void fc(String n) {
		if (n.contains("_")) {
			System.out.print(n);
			
		}
		if (n.contains("재귀")) {
			System.out.println(n);
			
		}
		if (n.contains("잘 들어보게.")) {
			System.out.println(n);
			
		}
	}
	public static void f(int n) {
		if (n<0) {
			return;
		}
		if (n!=2) {
			System.out.print("----");
		}
		System.out.println("\"재귀함수가 뭔가요?\"");
		System.out.println("\"잘 들어보게. 옛날옛날 한 산 꼭대기에 이세상 모든 지식을 통달한 선인이 있었어.");
		System.out.println("마을 사람들은 모두 그 선인에게 수많은 질문을 했고, 모두 지혜롭게 대답해 주었지.");
		System.out.println("그의 답은 대부분 옳았다고 하네. 그런데 어느 날, 그 선인에게 한 선비가 찾아와서 물었어.\"");
		f(n-1);
	}
		

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int N = sc.nextInt();
		
		
		System.out.println("어느 한 컴퓨터 공학과 학생이 유명한 교수님을 찾아가 물었다.");
		System.out.println("\"재귀함수가 뭔가요?\"");
		for (int i = 0; i <= N; i++) {
			for (int j = 0; j < i; j++) {
				for (int k = 0; k < j; k++) {
					System.out.print("----");	
				for (int k2 = 0; k2 < j; k2++) {
					System.out.println("\"재귀함수가 뭔가요?\"");
				}
				System.out.println("\"잘 들어보게. 옛날옛날 한 산 꼭대기에 이세상 모든 지식을 통달한 선인이 있었어.");
				System.out.println("마을 사람들은 모두 그 선인에게 수많은 질문을 했고, 모두 지혜롭게 대답해 주었지.");
				System.out.println("그의 답은 대부분 옳았다고 하네. 그런데 어느 날, 그 선인에게 한 선비가 찾아와서 물었어.\"");
				
				}
			
				System.out.println("");
			}
		}
		for (int i = 0; i <= N; i++) {
			fc("\"재귀함수가 뭔가요?\"");
			fc("\"잘 들어보게. 옛날옛날 한 산 꼭대기에 이세상 모든 지식을 통달한 선인이 있었어.");
			fc("마을 사람들은 모두 그 선인에게 수많은 질문을 했고, 모두 지혜롭게 대답해 주었지.");
			for (int j = 0; j < i; j++) {
				fc("----");
			}
		}
		
	

	}

}
