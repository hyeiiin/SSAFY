import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

//2493 탑_2차원 배열로 stack 자료구조 처럼 활용
public class Main_2493_탑_2 {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	
	static int N;
	static int[][] stack;
	
	public static void main(String[] args) throws Exception{
		N = Integer.parseInt(br.readLine());//건물의 수
		stack = new int[N+1][2];//N+1: 1번 인덱스부터 활용하기 위해 +1, 건물 번호, 높이를 위해 2차원
		
		int index = 0;
		
		StringBuilder sb = new StringBuilder();
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		for(int i=1; i<=N; i++) {//건물 수 만큼 진행
			int x = Integer.parseInt(st.nextToken());
			
			while(index>0&&stack[index][1]<x) //현재 건물 높이보다 그 앞의 모든 건물 높이가 작은지 확인.
				stack[index--][0]=0;//작은 건물 높이 값 삭제
			
			stack[++index][0] = i;//건물 번호 셋팅
			stack[index][1] =x; //건물 높이 셋팅
			sb.append(stack[index-1][0]+" ");//건물 번호 StringBuilder에 넣어줌
		}
		System.out.println(sb); //이 경우 표준 입출력 활용하면 시간초과 날 가능성 커서, StringBuilder로 한번에 출력
	}

}
