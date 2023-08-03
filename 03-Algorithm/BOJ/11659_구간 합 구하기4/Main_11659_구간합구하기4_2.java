
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;
import java.util.StringTokenizer;
/**
 * BOJ 11695 구간합 구하기 4
 * 입출력을 변경하면! StringBuilder+StringTokenizer+StringBuilder
 * s- 65960kb/728ms
 *
 */
public class Main_11659_구간합구하기4_2 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		StringBuilder sb = new StringBuilder();
//		Scanner sc = new Scanner(System.in);
		
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
//		int N = sc.nextInt(); //N개의 숫자
//		int M = sc.nextInt(); //M개의 구간 정보
		int num[] = new int[N+1];
		
		st = new StringTokenizer(br.readLine());
		for(int i=1; i<=N;i++) {
			num[i]= num[i-1]+Integer.parseInt(st.nextToken());
//				num[i]= num[i-1]+sc.nextInt(); //이전 값 + 현재 입력 값으로 누적값을 저장
		}
		
		int a=0, b=0;
		for(int i=1; i<=M;i++) {//M개의 구간합 구하기
			st = new StringTokenizer(br.readLine());
			a = Integer.parseInt(st.nextToken());
			b = Integer.parseInt(st.nextToken());
//			a = sc.nextInt(); //시작값
//			b = sc.nextInt(); //종료값
			//종료값까지의 누적합 - 시작값 이전까지의 누적합 = a~b까지의 합
//			System.out.println(num[b]-num[a-1]);
			sb.append(num[b]-num[a-1]+"\n");
		}
		System.out.println(sb);

	}

}
