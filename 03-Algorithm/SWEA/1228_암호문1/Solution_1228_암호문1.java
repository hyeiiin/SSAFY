package swea;


import java.io.BufferedReader;
import java.io.FileReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.StringTokenizer;

public class Solution_1228_암호문1 {
	public static void main(String[] args) throws Exception {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = 10;
		int N, M, index, num;
		//암호문의 값 변경이 자주 일어남 --> LinkedList 활용하면 좋음
		LinkedList<String> list = new LinkedList<>();
		StringTokenizer  st =null;
		for (int testcase = 1; testcase <= T; testcase++) {
			N = Integer.parseInt(br.readLine()); //암호문의 길이 N
			st = new StringTokenizer(br.readLine());
			for (int i = 0; i < N; i++) {
				list.add(st.nextToken()); //원본 암호문을 list에 추가
			}
			M = Integer.parseInt(br.readLine()); //명령어의 개수 들어옴
			st = new StringTokenizer(br.readLine()); //변경할 암호문의 명령어들
			for (int i = 0; i < M; i++) { 
				st.nextToken(); // 명령문의 시작을 알려주는 I.--> 암호문 바꿀때 활용 안하므로 저장 X
				
				index = Integer.parseInt( st.nextToken()); // X :  암호문의 삽입할 위치의 값
			// index --> 0부터 시작하기 때문에.
				
				num = Integer.parseInt( st.nextToken()); //y : 삽일할 숫자의 개수
				for (int j = index, end=index+num; j < end; j++) { // index~index+num 반복
					//add()메서드 통해서 추가 암호문 삽입
					//---> add(i) i라는 인덱스 위치에 값을 넣으라는 메서드.
					// 입력 받은 값 그대로 활용해서 넣어주면 됨.
					list.add(j, st.nextToken());
				}
			} //암호문 수정 완료
			System.out.print("#"+testcase+" ");
			
			for (int i = 0; i < 10; i++) { //출력 10개 숫자만 해주기
				System.out.print(list.get(i)+" ");
			}
			
			System.out.println();
			
			
			list.clear();
		}
	}
}
