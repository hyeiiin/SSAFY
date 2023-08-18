

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;
/*
5
20 3
2 2 3 2 2 2 2 3 3 4 4 3 2 2 3 3 3 2 2 3
4 4 1 4 4 1 4 4 1 1 1 4 1 4 3 3 3 3 3 3
4 4 1 100
7 10 3 40
6 3 2 70
*/

//사용자 관점
//사용자 이동위치에서 충전 가능한 충전기들을 확인
public class Solution_5644_무선충전_사용자관점 {

	static int M,aCnt;
	static int[] pathA, pathB, playerA, playerB, ap[];
	static int[] dx = {0,0,1,0,-1}; //사용자 이동 방향
	static int[] dy = {0,-1,0,1,0}; // 움직이지 않음, 상, 우, 하, 좌
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int TC = Integer.parseInt(br.readLine().trim());
		StringTokenizer st = null;
		playerA = new int[2]; //사용자 A (1,1)
		playerB = new int[2]; //사용자 B (10,10)
		
		for(int t=1; t<=TC; ++t) {
			st = new StringTokenizer(br.readLine());
			M = Integer.parseInt(st.nextToken()); //사용자 이동 시간
			aCnt = Integer.parseInt(st.nextToken()); //BC의 개수 
			
			playerA[0] = playerA[1] = 1;
			playerB[0] = playerB[1] = 10;
			
			pathA = new int[M+1]; //사용자 이동 경로 0초~M초 -> M+1초
			pathB = new int[M+1]; //B의 이동 경로
			ap = new int[aCnt][4]; //충전기 정보를 저장 위치(x,y), 범위, 성능
			
			String[] charsA = br.readLine().split(" ");
			String[] charsB = br.readLine().split(" ");
			for(int c=0; c<M; ++c) { //이동경로 나눠 담기, 시간이 0일땐 이동하지 않도록 인덱스 1부터 담앚
				pathA[c+1] = charsA[c].charAt(0)-'0'; 
				pathB[c+1] = charsB[c].charAt(0)-'0'; 
			}
			
			for(int a=0; a<aCnt; ++a) { //BC 정보 입력 받기
				st = new StringTokenizer(br.readLine());
				ap[a][0] = Integer.parseInt(st.nextToken()); //row
				ap[a][1] = Integer.parseInt(st.nextToken()); //col
				ap[a][2] = Integer.parseInt(st.nextToken()); //coverage
				ap[a][3] = Integer.parseInt(st.nextToken()); //performance
			}
			// ----------------input End
			System.out.println("#"+t+" "+move());
		}
	}
	
	static int move() {
		ArrayList<Integer> apListA, apListB; //A,B 충전 가능한 BC들을 담을 List
		int totalSum = 0;
		int time = 0;
		 while (time<=M){ //매 타임마다 이동
			//두 플레이어 위치 이동
			playerA[0] += dx[pathA[time]];
			playerA[1] += dy[pathA[time]];
			playerB[0] += dx[pathB[time]];
			playerB[1] += dy[pathB[time]];
			// 현 위치에서 충전 가능한 두 플레이서의 BC 리스트 넣어주기	
			apListA= getAp(playerA[0],playerA[1]);
			apListB= getAp(playerB[0],playerB[1]);			
			
			//현재 위치에서 충전 가능한 BC 중에 최대로 충전할 값 가져오기
			totalSum += getCombinationMaxPower(apListA,apListB);
			time++;
//			System.out.println(time+":"+totalSum);
		}
		return totalSum;
	}
	

	/**
	 * 
	 * @param apListA :A의 충전 BC리스트
	 * @param apListB :B의 충전 BC리스트
	 * @return
	 */
	private static int getCombinationMaxPower(ArrayList<Integer> apListA, ArrayList<Integer> apListB) {
		int max  = 0,temp = 0;
		int aSize = apListA.size(), bSize = apListB.size();
		// 충전한 BC리스트가 없으면 return 0
		if(aSize == 0 && bSize == 0) return 0;
		//BC리스트가 한쪽 사용자만 있는 경우 -> 리스트 중 가장 높은 충전량 BC 선택
		else if(aSize==0) return getMaxPower(apListB);
		else if(bSize==0) return getMaxPower(apListA);
		//중복 순열? -- 순서 상관 없이 값을 찾고: 중복 조합
		//다만, 코드상 list에 가능한 ap를 담았기 때문에 코드는 중복순열 구하는 형태
		//사용자 A, 사용자B 모두한테 접속 가능한 BC 리스트가 경우
		for (Integer a : apListA) { //A의 BC 가져오기
			for (Integer b : apListB) { //B의 BC 가져오기
				temp = Math.max(ap[a][3],ap[b][3]);	//두 충전소의 충전 값 중에서 더 높은 값을 temp에 저장
				//A와B가 같은 충전소에서 충전하는 경우? 어차피 충전되는 P는 같으므로 한쪽만 넣어줘도 됨
				if(a != b) temp += Math.min(ap[a][3], ap[b][3]); //다른 충전소에서 충전을 하는 겨우
				// 다른쪽 충전 값도 넣어줘야해서 BC 값 추가
				max = Math.max(max,temp); //최대 충전량 갱신(기존 max와 이번에 충전량을 비교 갱신)
			}
		}
		return max;
	}
	
	static int getMaxPower(ArrayList<Integer> apList) {
		int max = 0;
		for (Integer a : apList) {
			if(ap[a][3]>max) max = ap[a][3];
		}
		return max;
	}

	/**
	 * 
	 * @param x :현재 플레이어의 위치
	 * @param y :현재 플레이어의 위치
	 * @return : 충전 가능한 BC 리스트 반환
	 */
	static ArrayList<Integer> getAp(int x, int y){
		ArrayList<Integer> apList = new ArrayList<Integer>();
		int d=0;
		for(int a=0; a<aCnt; ++a) {

			d = Math.abs(ap[a][0]-x)+Math.abs(ap[a][1]-y); // BC와의 거리 계산
			
			if(d<=ap[a][2])	apList.add(a); //BC의 충전 가능 범위 안에 있으면 apList에 추가.
		}
		return apList;
	}
}
