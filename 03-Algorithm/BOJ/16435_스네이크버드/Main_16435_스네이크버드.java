
import java.util.Arrays;
import java.util.Scanner;

public class Main_16435_스네이크버드 {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int N = sc.nextInt(); //과일의 개수
		int L = sc.nextInt(); //스네이크 초기 길이
		
		int[] fruit = new int[N];
		for(int i=0; i<N;i++) {
			fruit[i] = sc.nextInt();
		}
		
		Arrays.sort(fruit); //받은 과일의 높이 정렬
		
		for(int i=0;i<N;i++) {
			if(fruit[i]<=L) {
				L++;
			}else {
				break;
			}
		}
		
		System.out.println(L);
	}

}
