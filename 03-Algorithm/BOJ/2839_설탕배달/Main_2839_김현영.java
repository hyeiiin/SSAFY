import java.io.*;
import java.util.*; 

public class Main_2839_김현영 { 

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader((System.in)));
		int n = Integer.parseInt(br.readLine());    //총 무게
 
		int count = 0;    //봉지의 수
		
        //설탕이 5의 배수가 될 때까지 반복
		while (n%5 != 0) {
			n= n-3;    //설탕 3키로씩 빼기
			count++;    
			if(n==0)    //설탕을 3으로 다 나누었으면 종료
				break;
            //설탕이 3과 5로 정확히 나뉘어지지않았을 때 count = -1
			if(n<3) {    
				count = -1;
				break;
			}
		}
		//설탕을 3의 배수만큼 빼고 남은 무게를 5의배수로 나누기
		count += n/5;
		
		System.out.println(count);
		
	}
}
