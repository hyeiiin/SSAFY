package swea;
import java.util.Scanner;
/**
 * SWEA 1289 원재의 메모리 복구하기
 * 단순 구현
 * @author KimDaol
 * S - 21,204kb/ 137ms
 */
public class Solution_1289_원재의메모리복구하기 {

	public static void main(String[] args) {
		// 기본 주어진 Scanner 사용
		Scanner sc = new Scanner(System.in);
		
		int T = sc.nextInt(); // nextInt(); 입력값을 정수형 숫자로 읽어오는 메소드
		
		for(int tc = 1; tc<=T; tc++) {
			String data = sc.next(); // next(); 스트링 문자열로 데이터를 읽어옴.
			// 어디까지 읽어 오지? 공백 직전까지!
			// nextLine()과의 차이점? \n 개행문자까지 읽고, 개행 문자를 버린뒤 나머지를 가져옴.
			
			int size = data.length(); //입력받은 문자열 길이 저장. 
			int N = 0; //답을 출력 할 횟수 카운트 변수
			if(data.startsWith("1")) { //StartsWith();  : 비교대상 문자열이 입력한 문자열 값으로 
														// 시작되는지 여부를 확인 후 boolean 값으로 반환 메소드
				// 1부터 시작하면, 0->1로 숫자 변환이 일어나기 때문에 체크.
				N++;
			}
			for(int i = 0; i<size-1; i++) { //숫자 변환 체크
				if(data.charAt(i)!= data.charAt(i+1)) { //charAt() :  스트링으로 입력된 문자열 중
					// 문자 하나만 선택해서 character로 바꿔 주는 메서드.
					N++;
				}
			}
			System.out.println("#"+tc+" "+N);
		}

	}

}
