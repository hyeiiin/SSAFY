import java.io.*;
import java.util.*;

public class Solution_3307_김현영 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		StringBuilder sb = new StringBuilder();
		
		
		int T = Integer.parseInt(st.nextToken());
		for (int t = 1; t <= T; t++) {
			int n = Integer.parseInt(br.readLine()); //수열의 길이
			int[] a = new int[n];	//수열
			int[] c = new int[n];	//이진탐색으로 찾은 최장부분수열
			
			int k = 0; //c의 인덱스. 최장부분수열의 길이
			
			st = new StringTokenizer(br.readLine());
			for (int i = 0; i <n; i++) {
				a[i] = Integer.parseInt(st.nextToken());
				//수열의 첫번째 값을 입력받으면 c에 저장 후 LIS의 길이 증가
				if(i==0) {
					c[0] = a[0];
					k++;
				}
				else { //최장부분수열 찾기
					//현재까지의 LIS를 탐색하며 현재 원소가 들어갈 위치를 찾음. 
					for (int j = 0; j <= k; j++) {
						//LIS 길이 내에 현재 원소보다 큰 값이 있다면 그 위치에 현재 원소 저장
						if(a[i] < c[j]) {
							c[j]= a[i]; 
							break;
						}
						//LIS 길이 내 현재 원소보다 큰 값이 없다면(현재 원소가 가장 큰 값이라면) 길이 연장 후 원소 저장 
						else {
							if(c[j]==0) {
								c[j]=a[i];
								k++;
								break;
							}
						}
					}
				}
			}
			
//			System.out.println("k= " +k);
//			for (int i : c) {
//				System.out.print(i +" ");
//			}
			
			//LIS 길이 출력
			sb.append("#").append(t).append(" ").append(k).append("\n");
		}
		
		System.out.println(sb.toString());

	}
}
