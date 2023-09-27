import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution_2115_이세은 {

	static int n, m, c;
	static int[][] honey, check;

	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();

		int T = Integer.parseInt(br.readLine());

		for (int test_case = 1; test_case <= T; test_case++) {

			StringTokenizer st = new StringTokenizer(br.readLine());
			n = Integer.parseInt(st.nextToken()); // n*n벌통
			m = Integer.parseInt(st.nextToken()); // 선택 가능한 벌통 칸 수
			c = Integer.parseInt(st.nextToken()); // 채취 가능한 최대 양

			honey = new int[n][n];
			check = new int[n][n-m+1];

			for (int i = 0; i < n; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j = 0; j < n; j++) {
					honey[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			
			//m영역 정하기
			for(int i=0; i<n; i++) {
				for(int j=0; j<n-m+1; j++) {
					getMax(0, i, j, 0, 0);
				}
			}
			
			//첫번째 벌꿀 최댓값
			int m1=0;
			int r1=-1, c1=-1;
			for(int i=0; i<n; i++) {
				for(int j=0; j<n-m+1; j++) {
					if(m1 < check[i][j]) {
						m1 = check[i][j];
						//첫번째 최대 벌꿀 위치 저장
						r1 = i;
						c1 = j;
					}
				}
			}
			
			
			//두번째 벌꿀 최댓값, 첫번쩨 벌꿀 최대부분과 겹치면 넘겨주기
			int m2=0;
			for(int i=0; i<n; i++) {
				for(int j=0; j<n-m+1; j++) {
					//첫번째 벌꿀과 겹치는지 확인
					//같은 행이면서 첫번째 벌집 위치와 같거나 
					if(i == r1 && ((j <= c1 && c1<=j+m-1) || (j<=c1+m-1 && c1+m-1<=j+m-1)))
						continue;
					m2 = Math.max(m2, check[i][j]);
				}
			}
			
			sb.append("#").append(test_case).append(" ").append(m1+m2).append("\n");

		}
		System.out.println(sb.toString());
	}

	private static void getMax(int cnt, int row, int col, int currSum, int earn) {

		// 벌꿀양 초과
		if (currSum > c)
			return;
		
		//m칸 채취
		if(cnt == m) {
			//지금까지 채취한 꿀 최대 양 저장
			if(check[row][col] < earn) check[row][col] = earn;
			return;
		}


		getMax(cnt+1, row, col, currSum+honey[row][col+cnt], honey[row][col+cnt] * honey[row][col+cnt] + earn);// 이익을 얻거나
		getMax(cnt+1, row, col, currSum, earn); // 얻지 않거나

	}
}
