import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main_12891_한정수 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int pwLength = Integer.parseInt(st.nextToken());
		int subLength = Integer.parseInt(st.nextToken());
		String input = br.readLine();
		char[] input_char = input.toCharArray();
		int A,C,G,T;
		int sum = 0;
		st = new StringTokenizer(br.readLine());
		A = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		G = Integer.parseInt(st.nextToken());
		T = Integer.parseInt(st.nextToken());

		int cnt_A = 0; int cnt_C = 0; int cnt_G = 0; int cnt_T = 0;
		char[] temp = new char[subLength];
		temp = input.substring(0, subLength).toCharArray();
		//일단 최초로 한번 substring만큼 싹 읽고 카운팅.
		for (int i=0 ; i<temp.length; i++) {
			if (temp[i] == 'A') {
				cnt_A += 1;
			}
			else if(temp[i] == 'C'){
				cnt_C += 1;
			}
			else if(temp[i] == 'G') {
				cnt_G += 1;
			}
			else if(temp[i] == 'T'){
				cnt_T += 1;
			}
		}
		if(A <= cnt_A && C <= cnt_C && G <= cnt_G && T <= cnt_T) {
			sum += 1;
		}

		
		//이제 원본 input_char에서 현재 subString의 첫번째 알파벳 빼고, 현재 subString의 마지막 idx+1 위치의 알파벳을 더함.
		for(int i=0 ; i<pwLength - subLength; i++) {
			if (input_char[i] == 'A') {
				cnt_A -= 1;
			}
			else if(input_char[i] == 'C'){
				cnt_C -= 1;
			}
			else if(input_char[i] == 'G') {
				cnt_G -= 1;
			}
			else if(input_char[i] == 'T'){
				cnt_T -= 1;
			}
			
			
			if (input_char[i+subLength] == 'A') {
				cnt_A += 1;
			}
			else if(input_char[i+subLength] == 'C') {
				cnt_C += 1;
			}
			else if(input_char[i+subLength] == 'G') {
				cnt_G += 1;
			}
			else if(input_char[i+subLength] == 'T'){
				cnt_T += 1;
			}
			
			if(A <= cnt_A && C <= cnt_C && G <= cnt_G && T <= cnt_T) {
				sum += 1;
			}
		}
		System.out.printf("%d",sum);
		
		
				
	}

}
