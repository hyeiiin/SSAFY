

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

// BC 관점 -시뮬레이션 + 중복 조합
// 각 BC에서 범위에 사용자가 들어왔는지 체크해서 충전량 더하기
public class Solution_5644_무선충전_충전기관점{

	static int M,aCnt;
	static int[] pathA, pathB, players[], ap[];
	static int[] dx = {0,0,1,0,-1}; //움직이지 않음, 상,우,하,좌
	static int[] dy = {0,-1,0,1,0};	
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int TC = Integer.parseInt(br.readLine().trim());
		StringTokenizer st = null;
		players = new int[2][2];
		
		for(int t=1; t<=TC; ++t) {
			st = new StringTokenizer(br.readLine());
			M = Integer.parseInt(st.nextToken()); 
			aCnt = Integer.parseInt(st.nextToken()); 
			
			players[0][0] = players[0][1] = 1; //(1,1)  위치
			players[1][0] = players[1][1] = 10; //(10,10) 위치
			
			pathA = new int[M+1];
			pathB = new int[M+1];
			ap = new int[aCnt][4];
			 
			String[] charsA = br.readLine().split(" ");
			String[] charsB = br.readLine().split(" ");
			for(int c=0; c<M; ++c) {
				pathA[c+1] = charsA[c].charAt(0)-'0'; 
				pathB[c+1] = charsB[c].charAt(0)-'0'; 
			}
			
			for(int a=0; a<aCnt; ++a) {
				st = new StringTokenizer(br.readLine());
				ap[a][0] = Integer.parseInt(st.nextToken()); //row
				ap[a][1] = Integer.parseInt(st.nextToken()); //col
				ap[a][2] = Integer.parseInt(st.nextToken()); //c
				ap[a][3] = Integer.parseInt(st.nextToken()); //p
			}
			//---------input end
			System.out.println("#"+t+" "+move());
		}
	}

	static int move() {
		int totalSum = 0;
		for(int time=0; time<=M; ++time){ // 처음 있는 자리부터 처리해야하므로 시간 0부터 
			// 두 플레이어의 위치 이동
			players[0][0] += dx[pathA[time]];
			players[0][1] += dy[pathA[time]];
			players[1][0] += dx[pathB[time]];
			players[1][1] += dy[pathB[time]];
			
			totalSum += getCharge();
		}
		
		return totalSum;
	}

	//중복 조합
	static int getCharge() {
		int max = 0;
		for(int a=0; a<aCnt; ++a) {// 사용자 a가 BC의 충전 범위에 들어왔는지?
			for (int b = 0;  b< aCnt; ++b) {//사용자 B가 BC의 충전 범위에 들어왔는지?
				int sum = 0;
				int aSum = check(a,players[0][0],players[0][1]);
				int bSum = check(b,players[1][0],players[1][1]);
				if(a != b) { // 다른 BC를 확인 했을때, 
					sum = aSum + bSum; // 충전 값 더해주기
				}else { // 충전 범위 안들어 왔을때, 한쪽만 들어 왔을때, 같은 충전 범위에 들어 왔을때 포함
					// 같은 충전소에서 둘 다 충전이 가능하면 충전량이 같으므로 둘 중 아무거나 충전량으로 사용
					// 같은 충전소에서 한쪽만 충전이 돼야 하는 경우 하나는 0, 다른 하는 0이 아닌 값으로 들어감
					// 결국 최댓값만 사용하면 됨
					sum = Math.max(aSum, bSum);
				}
				if(max<sum) max = sum; // 최대값 갱신.
			}
		}
		return max;
	}
	/**
	 * 
	 * @param a : 충전기 넘버
	 * @param x :사용자 위치
	 * @param y
	 * @return
	 */
	static int check(int a, int x, int y) {
		//충전기와 사용자의 거리계산
		//계산된 거리가 BC의 충전범위에 들어오는지 확인
		// 범위 안이면 충전 값 반환 아니면 0
		return Math.abs(ap[a][0]-x)+Math.abs(ap[a][1]-y) <= ap[a][2] ? ap[a][3] : 0;
	}
}
