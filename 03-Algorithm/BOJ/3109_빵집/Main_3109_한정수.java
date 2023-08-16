import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main_3109_한정수 {
	static int N;
	static int M;
	static char[][] arr;
	static int answer;
	
	static boolean test1 = true;
	static boolean test2 = true;
	static boolean test3 = true;
	static boolean global_res = true;
	static boolean[] arrive_end;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		arr = new char[N][M];
		
		for (int i=0 ; i<N ; i++) {
			String temp = br.readLine();
			for (int j=0 ; j<M ; j++) {
				arr[i][j] = temp.charAt(j);
			}
		}
		//배열입력 완료
		
		//맨 왼쪽 위 기준으로 일단 무조건 오른쪽 위 > 오른쪽 > 오른쪽 아래 순으로 탐색하고 아래로 내려와야함.
		//어차피 위쪽 가스관에서 어느 열에서든 한칸 내려오면 밑에 있는 가스칸라인도 해당 열부터 전부 다 한칸씩 내려가기 때문에,
		//무조건 오른쪽 위 > 오른쪽 > 오른쪽 아래 순으로 진행하는게 가장 파이프 많이 까는 방법.
		
		//지나가면서 파이프 지으면 그 자리를 x로 만들어줘야함. 그리고 return하면 다시 .으로 바꿔주고. 
		          /**
		           * 다시 .으로 바꾸면 안됨. 시간초과 
		           */
		
		// 근데 애초에 위로 올라가는 경우가 생길 수가 있나????? 오른쪽 위로 올라가는 경우가 있으면 최대가 아닐텐데
		//   >> 내려갔다가 올라가는 경우. 이때 안올라가고 직선으로 가면 설치 가능 파이프가 줄어듬.
//		.xx..
//		..x..
//		.....
//		...x.
//		...x.   0,0에서 시작했을때 2,2에서 오른쪽 위가 아니라 오른쪽으로 가면 파이프 개수가 하나 줄어든다.
		
		
		answer = 0;
		arrive_end = new boolean[N];
		//탐색 시작
		for(int i=0; i<N; i++) {
			global_res = true;
			search(0, i, 0, i);
		}
		System.out.println(answer);
		
	}
	public static void search(int cnt, int x, int y, int origin_x) {
		boolean test1 = true;
		boolean test2 = true;
		boolean test3 = true;
		arr[x][y] = 'x';
		if (cnt == M-1) {
			//즉, 맨 오른쪽 열까지 도착했을 때.
			answer += 1;
			arrive_end[origin_x] = true;
			return;
		}
		
		
		
		//오른쪽 위
		if(arrive_end[origin_x]) {
			return;
		}
		if(x-1 >= 0 && y+1 < M && arr[x-1][y+1] == '.') {
			//다음 위치가 배열 안에 있고 '.'인 경우만 더 들어가기.
			test1 = true;
			search(cnt+1, x-1, y+1, origin_x);
		}
//		else {
//			test1 = false;
//		}
		
		//오른쪽
		if(arrive_end[origin_x]) {
			return;
		}
		if(x >= 0 && y+1 < M && arr[x][y+1] == '.') {
			test2 = true;
			search(cnt+1, x, y+1, origin_x);
		}
//		else {
//			test2 = false; 
//		}
		
		//오른쪽 아래
		if(arrive_end[origin_x]) {
			return;
		}
		if(x+1 < N && y+1 < M && arr[x+1][y+1] == '.') {
			test3 = true;
			search(cnt+1, x+1, y+1, origin_x);
		}
//		else {
//			test3 = false;
//		}
//		//3방향 다 못가면 강제 종료.      
//		if(!test1 && !test2 && !test3) {
//			global_res = false;
//		}
//		if(!global_res) {
//			arr[x][y] = '.';
//		}
		/**
		 * 3방향이 전부 다 false인지 체크할 필요가 없다. 왜냐하면 성공했으면 파이프가 설치됬으니까 탐색할 필요가 없고,
		 * false인 경우에도 결국 그 길로 빠지면 어차피 똑같이 false를 향해 나아가기 때문에.
		 * 따라서 이미 지나간 자리를 다시 .으로 바꿔줄 필요가 없다. 겨우 else절 3개 지운거 가지고 통과가 됨.
		 */
		
		
	}
}
