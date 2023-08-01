package swea;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * SWEA 1289 원재의 메모리 복구하기
 * 단순 구현
 * - 풀이 방법 동일, 입력 방법만 바꿔보기
 * @author KimDaol
 * S - 19,124 kb/ 99 ms
 */
public class Solution_1289_원재의메모리복구하기2 {
	/*
	 * BufferedReader와 BufferedWriter 등의 클래스의 메소드들은 입출력 입출력 에러가 발생할 경우
	 * 자체적으로 IOException을 던지도록 정의되어 있음. 
	 * 메인함수에서 Throws IOException해주면 편함
	 */
	 
	public static void main(String[] args) throws IOException {

		// 왜 사용?? Scanner 8배 정도의 버퍼를 가지고
		// 입출력이 많은 문제를 처리할때 Buffered- 클래스를 사용하면 빠르다!
		// System.in 입력값을 받는 경우 String 형태로 읽고, InputStreamReader를 사용
	    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	    
	    //사용이유 ?  속도도 빠르고 성능적으로 좋습니다. 
	    // 그 이유는? String 객체끼리 값을 더하는 일이 발생하면 String + String --> 
	    // 새로운 String 객체가 생성되고 거기에 합쳐진 값이 들어감 --> 메모리 할당 메모리 해제가 계속 발생
	    // --> 성능이 안좋아지는 경우 발생
	    // StringBuilder를 사용하면 새로운 객체를 생성 하지 X
	    // 기존의 데이터에 더하는 방식으로 진행. ---> 속도도 빠르고 성능적으로 좋다.
	    StringBuilder sb = new StringBuilder();
	    int T = Integer.parseInt(br.readLine());
	    // read() : 리턴 타입 int, 문자 하나 읽어서 정수형 리턴해줌.
	    // --> 문자를 읽어서 그에 해당하는 아스키 코드 정수값을 리턴. 0-9 숫자를 읽어오면?
	    // 아스키코드 int 값인 48-57이 반환.
	    // readLine() : 리턴 타입이 String, 한줄의 문자열 읽음
	    // readLine() 으로 String 읽어온 후 ParseInt() 메소드를 이용해서 int 형으로 반환.
	   
	    
	    for(int tc=1; tc<=T; tc++) {
			int N = 0;
	        sb.append("#").append(tc).append(" ");
	        ////////////////////////////////////////TC  처리
	        String data = br.readLine();
	        if(data.startsWith("1")) {
	            N++;
	        }
	        for(int i=0; i<data.length()-1; i++) {
	            if(data.charAt(i)!=data.charAt(i+1)) {
	                N++;
	            }
	        }

	        sb.append(N).append("\n");
	    }
	    System.out.println(sb);
	}
}
