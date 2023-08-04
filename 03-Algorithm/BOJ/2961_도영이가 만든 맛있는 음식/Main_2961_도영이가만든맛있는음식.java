
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_2961_도영이가만든맛있는음식 {
	
	static int[][] materials;
	static int minVal;
	static boolean[] visited;
	
	public static void makeSet(int current) {
		int N = materials.length;
		//바이너리 카운팅을 이용한 부분집합
		//N = 10 이면, 비트를 이용해서 각 요소의 자리수에 해당하는 경우의 수를 만들 수 있음
		// 0000000000~111111111
		// 부분집합의 개수 = 2^N개
		for(int i = 0; i<(1<<N); i++) {// 재료의 개수로 만들 수 있는 부분집합의 개수 만큼 반복
			int sour = 1;
			int bitter = 0;
			for(int j = 0; j<N; j++) { //재료의 개수 자리마다 확인하기
				if((i&(1<<j))!=0) { //확인하려는 재료의 순서 비트가 i부분집합에 들어가 있는지 확인
					// 들어가 있으면 신맛, 쓴맛 연산처리
					sour *= materials[j][0];
					bitter += materials[j][1];
				}
			}
			//공집합이 아니면서, 현재 차이가 더 작다면 ==> 최소값 갱신
			if(!(sour==1&&bitter==0) && minVal>Math.abs(sour-bitter)) minVal = Math.abs(sour-bitter);
		}
		
		
		
		
		//기존 재귀 활용 부분집합 코드
//		if (current == materials.length) {  // N개의 재료 선택이 완료되었을때 --> 부분집합 완성!
//			int sour = 1;
//			int bitter = 0;
//			int cnt = 0;
//			for (int i = 0; i < visited.length; i++) {
//				if (visited[i]) { //재료 선택 확인
//					cnt++;
//					sour *= materials[i][0];
//					bitter += materials[i][1];
//				}
//			}
//			if(cnt == 0) return; 
//			if(minVal > Math.abs(sour - bitter))
//				minVal = Math.abs(sour - bitter);
//			return;
//		}
//		visited[current] = true; //재료 선택했을때
//		makeSet(current + 1);
//		visited[current] = false; //재료 선택하지 않았을때
//		makeSet(current + 1);
	}

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());
		materials = new int[n][2];
		visited = new boolean[n]; //재료 선택 여부
		
		for(int i=0; i<n; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			materials[i][0] = Integer.parseInt(st.nextToken()); //신맛
			materials[i][1] = Integer.parseInt(st.nextToken()); //쓴맛
		}
		
		minVal = Integer.MAX_VALUE; //신맛 쓴맛 차이 최소값 
		makeSet(0);
		System.out.println(minVal);
	}
}