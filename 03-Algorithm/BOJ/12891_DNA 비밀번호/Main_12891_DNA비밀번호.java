
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * BOJ 12891 DNA비밀번호 
 * - 슬라이딩 윈도우 활용 --> 비밀번호 검사 시 기존 검사 결과를 활용, 새로 들어온 문자열/제거되는
 * 문자열만 반영하여 확인
 * 
 * @author KimDaol
 *
 */
public class Main_12891_DNA비밀번호 {

	static int passCheck[];//비밀번호 체크용 배열(각 문자별 최소 포함 개수 저장 예정)
	static int passArr[]; //부분 문자열의 각 문자별 상태 확인용 배열
	static int passComplete; //유효한 비밀번호 확인용 변수

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int S = Integer.parseInt(st.nextToken()); //DNA 문자열의 길이
		int P = Integer.parseInt(st.nextToken()); //부분 문자열의 길이
		int res = 0;

		char dna[] = new char[S];//DNA 문자열 받기
		passCheck = new int[4];//각 인덱스 순서대로 'A','C','G','T'의 최소 조건 입력 받음 
		passArr = new int[4];//각 인덱스 순서대로 'A','C','G','T'의 개수 카운팅

		passComplete = 0; //문자 조건 충족 개수 확인
		dna = br.readLine().toCharArray();
		st = new StringTokenizer(br.readLine());

		for (int i = 0; i < 4; i++) {
			passCheck[i] = Integer.parseInt(st.nextToken()); //부분 문자열 포함 되어야 하는 최소 개수
			if (passCheck[i] == 0) { //부분 문자열에 포함될 문자의 최소 개수가 0인 경우
				passComplete++; //문자 조건 충족 개수 늘려주기
			}
		}
		/////////////Input End/////////////////
		
		//초기 부분 문자열 셋팅(부분 문자열의 길이 P)
		for (int i = 0; i < P; i++) {
			add(dna[i]);
		}
		//셋팅한 부분 문자열이 유효한 비밀번호 인지 확인
		if (passComplete == 4) { 
			res++;
		}
		//슬라이딩 윈도우를 활용
		//한칸씩 옆으로 밀면서 유효한 비밀 번호인지 확인하기
		for (int i = P; i < S; i++) {
			int j = i - P; //앞 문자를 제거하기 위한 인덱싱 변수
			add(dna[i]); //셋팅된 문자열 뒤의 문자부분 넣기
			remove(dna[j]); //셋팅된 문자열의 가장 앞 문자 빼기
			if (passComplete == 4) { //변경된 부분 문자열이 유효한 비밀번호인지 확인
				res++;
			}
		}
		System.out.println(res);//출력
		br.close();

	}

	private static void add(char c) {
		switch (c) {
		case 'A':
			passArr[0]++;
			if (passArr[0] == passCheck[0]) {
				passComplete++;
			}
			break;
		case 'C':
			passArr[1]++;
			if (passArr[1] == passCheck[1]) {
				passComplete++;
			}
			break;
		case 'G':
			passArr[2]++;
			if (passArr[2] == passCheck[2]) {
				passComplete++;
			}
			break;
		case 'T':
			passArr[3]++;
			if (passArr[3] == passCheck[3]) {
				passComplete++;
			}
			break;

		}
	}

	private static void remove(char c) {
		switch (c) {
		case 'A':

			if (passArr[0] == passCheck[0]) {
				passComplete--;
			}
			passArr[0]--;
			break;
		case 'C':
			if (passArr[1] == passCheck[1]) {
				passComplete--;
			}
			passArr[1]--;
			break;
		case 'G':
			if (passArr[2] == passCheck[2]) {
				passComplete--;
			}
			passArr[2]--;
			break;
		case 'T':
			if (passArr[3] == passCheck[3]) {
				passComplete--;
			}
			passArr[3]--;
			break;

		}
	}

}
