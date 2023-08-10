import java.io.*;
import java.util.*;

public class Solution_4012_김현영 {
	static int[] foodA;			//조합 별 음식A의 재료 저장
	static int[] foodB;			//조합 별 음식A의 재료 저장
	static int sA,sB = 0; 		//각 음식의 시너지 
	static List<Integer> difs;	//음식의 시너지 차이를 저장
	static int[] foodComps; 	//시너지를 구할 때 재료 i,j를 저장하는 배열
	static int sum;				//음식의 시너지 총 합을 저장
	
	static int n; //식재료 수
	static int[][] s;		//음식 간 시너지 정보
	static int[] components;	//식재료 총모음
	
	//음식 재료 간 시너지를 구하는 함수
	public static int getSynergy(char type, int cnt, int start) {
		int[] food = new int[n/2];
		// 음식 A, 음식B 중 어느 음식의 시너지 합을 구하는지 판단
		switch(type) {
			case 'A' :
				food = foodA;
				break;
			case 'B' :
				food = foodB;
				break;
		}
		
		//재료 중 2개를 뽑으면 시너지를 구해서 sum에 저장
		if(cnt == 2) {
			sum += s[foodComps[0]][foodComps[1]];
			sum += s[foodComps[1]][foodComps[0]];
			return sum;
		}
		for (int i = start; i < n/2; i++) {
			foodComps[cnt] = food[i];
			getSynergy(type, cnt+1, i+1);
		}
		
		return sum;
	}
	
	public static void findComponents(int count, int start) {
		//식재료 조합을 모두 구했으면 식재료간의 시너지 합 구하기
		if(count == n/2) {
			//음식B의 식재료 찾기 : 전체 음식 재료 components에서 음식A가 사용하지 않은 재료 선택
			for (int i = 0, j = 0; i < n; i++) {
				if(components[i] == 0)
					foodB[j++]= i;
			}    
			
			//음식A와 음식B의 시너지를 구하고 시너지 차이의 절댓값을 difs 리스트에 추가
			sum = 0;	//이전 음식이 구한 시너지의 합 초기화
			sA = getSynergy('A',0,0);
			sum = 0;	//이전 음식이 구한 시너지의 합 초기화
			sB = getSynergy('B',0,0);
			difs.add(Math.abs(sA-sB));
			return;
		}
		
		for (int i = start; i < n; i++) {
			foodA[count] = i;	//음식 A의 재료 추가
			components[i] = 1;	//방문처리
			findComponents(count+1, i+1);	//다음 식재료 찾기
			components[i] = 0; //방문처리 되돌리기
			}
	}
	
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		StringBuilder sb = new StringBuilder();
		
		int T = Integer.parseInt(st.nextToken()); //테스트 케이스 수
		for (int t = 1; t <= T; t++) {
			n = Integer.parseInt(br.readLine()); 	// 식재료 수 입력
			
			//음식 시너지 입력
			s = new int[n][n];
			for (int i = 0; i < n; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j = 0; j < n; j++) {
					s[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			
			components = new int[n];	//식재료 n개 할당
			foodA = new int[n/2];		//음식A의 식재료 n/2개 할당
			foodB = new int[n/2];		//음식B의 식재료 n/2개 할당
			foodComps = new int [2];	//음식의 시너지를 구할 때 사용하는 배열 : 2개를 뽑아서 시너지 구함
			difs = new ArrayList<>();
			findComponents(0, 0);
			
			//시너지 차이의 리스트 오름차순 정렬
			Collections.sort(difs);
			
			//출력
			sb.append("#").append(t).append(" ").append(difs.get(0)).append("\n");
		}
		System.out.println(sb.toString());
		
	}
}
