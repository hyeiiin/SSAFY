import java.util.Scanner;

public class Main_2839_설탕배달 {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		int N = sc.nextInt(); //설탕의 무게
		int cnt=0; //봉지 수 카운트
		while(N>0) { //마이너스가 될때까지 반복
			if(N%5 ==0) { //5kg 정확히 나누어지는지 확인
				cnt += (N/5); //나누어지면 답
				break;
			}
			N-=3; //3kg씩 계속 빼면서 5kg으로 나누어지는지 확인
			cnt++; //3kg 봉지에 담았으니 봉지개수 count는 추가
		}
		System.out.println(N<0?-1:cnt ); //N을 확인해서 출력
	}
}
