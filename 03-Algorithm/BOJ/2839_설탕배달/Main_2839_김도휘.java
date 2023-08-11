import java.util.Scanner;

public class Main {

	public static void main(String[] args) {

		Scanner scanner = new Scanner(System.in);

		int input = scanner.nextInt(); // 킬로그램 입력
		int bag=0;
		
		
		while(true)
		{
			if(input%5==0)
			{
				bag+=input/5;
				System.out.println(bag);
				break;
			}
			else
			{
				input=input-3;
				bag++;
			}
			if(input<0)
			{
				System.out.println(-1);
				break;
			}
		}
		

	}
}
