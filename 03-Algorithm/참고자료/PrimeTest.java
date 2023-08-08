import java.util.Scanner;

public class PrimeTest {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		System.out.println("소수를 확인 할 숫자를 입력하세요 : ");
		int n = sc.nextInt();
		
		if(isPrime2(n)) {
			System.out.println(n+"은 소수입니다.");
		}
		else {
			System.out.println("소수가 아니예요 ㅜ");
		}
		
		System.out.println("-------에라토스테네스의 체-----------");
		
		//1차원 배열 => 소수인지 아닌지 여부를 저장 (boolean)
		// --> 배열의 인덱스 번호가 소수인지 아닌지 판별한 값을 저장
		// check[17] = false(소수), true(소수가 아님)
		boolean[] check = new boolean[1000001]; //미리 충분 크기로 만들어두기
		
		check[0]=check[1]=true; //소수가 아니기 때문에 먼저 셋팅
		for (int i = 2; i*i <= 1000000; i++) {
			if(check[i] == false) {//워지지 않은 가장 작은 수를 만났을때
				//이 수는 소수이다. 
				//해당 수의 배수는 모두 소수가 아니므로 true값으로 바꿔 줌
				for (int j = i*i; j <=1000000; j+=i) {
					check[j] = true;
				}
			}
		}
		
		System.out.println("에라토스테네스의 체로 소수 확인할 숫자를 입력하세요 : ");
		int pri = sc.nextInt();
		if(!check[pri]) {
			System.out.println("소수");
		}else {
			System.out.println("소수가 아님");
		}
		
		System.out.println("100만 안쪽의 숫자 중 소수들은?");
		for (int i = 2; i <100; i++) {
			if(!check[i]) {
				System.out.print(i+" ");
			}
		}

	}
	/**
	 * 
	 * @param n : 소수판별 할 숫자
	 * @return : 소수인지에 대한 여부(true/false)
	 */
	private static boolean isPrime(int n) {
		if(n<2) { //1이하의 수는 소수가 아니므로 제외
			return false;
		}
		//숫자 n이 2이상 n-1이하의 수로 나누어 떨어지는지 확인
		for (int i = 2; i <= n-1; i++) {
			if(n%i ==0) {//하나라도 나누어 떨어지면 소수X
				System.out.println("나누어 떨어짐");
				return false;
			}
		}
		//여기까지 내려왔다는건, 소수!
		return true;
	}
	
	/**
	 *  - 소수 판별을 위해서 2~N/2에 해당하는 범위만 나눠보기
	 *  - 시간 복잡도 O(N/2)
	 * @param n : 소수판별 할 숫자
	 * @return : 소수인지에 대한 여부(true/false)
	 */
	private static boolean isPrime2(int n) {
		if(n<2) { //1이하의 수는 소수가 아니므로 제외
			return false;
		}
		//숫자 n이 2이상 n-1이하의 수로 나누어 떨어지는지 확인
		for (int i = 2; i <= n/2; i++) {
			if(n%i ==0) {//하나라도 나누어 떨어지면 소수X
				System.out.println("나누어 떨어짐");
				return false;
			}
		}
		//여기까지 내려왔다는건, 소수!
		return true;
	}
	
	/**
	 *  - 소수 판별을 위해서 2~루트N에 해당하는 범위만 나눠보기
	 *  - 시간 복잡도 O(루트N)
	 * @param n : 소수판별 할 숫자
	 * @return : 소수인지에 대한 여부(true/false)
	 */
	private static boolean isPrime3(int n) {
		if(n<2) { //1이하의 수는 소수가 아니므로 제외
			return false;
		}
		//숫자 n이 2이상 n-1이하의 수로 나누어 떨어지는지 확인
		for (int i = 2; i*i <= n; i++) {
			if(n%i ==0) {//하나라도 나누어 떨어지면 소수X
				System.out.println("나누어 떨어짐");
				return false;
			}
		}
		//여기까지 내려왔다는건, 소수!
		return true;
	}

}
