import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main_16435_한정수 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int len = Integer.parseInt(st.nextToken());
		
		int[] arr = new int[N];
		st = new StringTokenizer(br.readLine());
		for (int i=0; i<N; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}
		// 입력 끝 ==================
		
		
		// 배열 정렬하고
		Arrays.sort(arr);	
		for (int i=0; i<arr.length; i++) {
			//작은건 먹고 길어지고
			if(arr[i]<= len) {
				len += 1;
				continue;
			}
			//작은것부터 다 먹었는데 그래도 큰게 있으면 끝난거
			break;
		}
		System.out.println(len);
	}

}
