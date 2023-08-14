import java.io.*;
import java.util.*;

public class Main_16435_김현영 {

	public static void main(String[] args) throws IOException {
		BufferedReader br  = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int n = Integer.parseInt(st.nextToken());	//과일의 개수
		int l = Integer.parseInt(st.nextToken());	//스네이크 버드의 길이

		//n개의 과일 입력
		st = new StringTokenizer(br.readLine());
		int[] fruits = new int[n];
		for (int i = 0; i < n; i++) {
			fruits[i] = Integer.parseInt(st.nextToken());
		}

		//과일들 오름차순 정렬
		Arrays.sort(fruits);
		
		for (int i = 0; i < fruits.length; i++) {
			//스네이크버드보다 길이가 작거나 같은 과일을 하나씩 먹으며 길이 +1
			if( l >= fruits[i] ) { 
				l++;
			}
			//더이상 먹을 수 없는 과일을 만나면 종료
			else
				break;
		}
		
		//스네이크 버드의 길이 출력
		System.out.println(l);
		
	}

}
