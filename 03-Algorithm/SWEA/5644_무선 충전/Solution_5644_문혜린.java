package algorithm.swea;

import java.util.*;
import java.io.*;

//[모의 SW 역량테스트] 무선 충전

class BatteryCharger { //BC 정보
	int x; //열 좌표
	int y; //행 좌표
	int c; //충전 범위
	int p; //성능
	
	public BatteryCharger(int x, int y, int c, int p) {
		this.x = x;
		this.y = y;
		this.c = c;
		this.p = p;
	}
}

public class Solution_5644_문혜린 {
	static int A; //BC의 개수
	static ArrayList<BatteryCharger> bc; //BC 정보
	static ArrayList<Integer> aCharger; //사용자 A가 속하는 충전소
	static ArrayList<Integer> bCharger; //사용자 B가 속하는 충전소
	static int result = 0; //충전량 합의 최대값 결과
	
	public static void checked(int ax, int ay, int bx, int by) {
		aCharger = new ArrayList<>();
		bCharger = new ArrayList<>();
		
		for (int j = 0; j < A; j++) {
			//사용자 A, B 위치와 충전소 사이 거리
			int disA = Math.abs(ax-bc.get(j).x) + Math.abs(ay-bc.get(j).y);
			int disB = Math.abs(bx-bc.get(j).x) + Math.abs(by-bc.get(j).y);
			//충전소 범위 안에 들어오면 리스트 삽입
			if(disA <= bc.get(j).c) {
				aCharger.add(j);
			}
			if(disB <= bc.get(j).c) {
				bCharger.add(j);
			}
		}
		//둘 다 충전소 범위 안에 있지 않으면 넘어가기
		if(aCharger.isEmpty() && bCharger.isEmpty()) {
			return;
		}
		
		int max = Integer.MIN_VALUE; //최대값 찾기 위한 변수
		//한 명만 충전소 범위 안에 있을 경우
		if(aCharger.size()>=1 && bCharger.isEmpty()) { //사용자 A
			for (int j = 0; j < aCharger.size(); j++) {
				max = Math.max(max, bc.get(aCharger.get(j)).p);
			}
		}
		if(aCharger.isEmpty() && bCharger.size()>=1) { //사용자 B
			for (int j = 0; j < bCharger.size(); j++) {
				max = Math.max(max, bc.get(bCharger.get(j)).p);
			}
		}
		//두 사용자 모두 충전소 범위 안에 있을 경우
		else if(aCharger.size()>=1 && bCharger.size()>=1) {
			//조합으로 최대값 찾기
			for (int j = 0; j < aCharger.size(); j++) {
				for (int k = 0; k < bCharger.size(); k++) {
					//다른 충전소일 경우
					if(aCharger.get(j) != bCharger.get(k)) {
						max = Math.max(max, bc.get(aCharger.get(j)).p + bc.get(bCharger.get(k)).p);
					}
					//같은 충전소일 경우
					else {
						max = Math.max(max, bc.get(aCharger.get(j)).p); //같은 충전소 성능만큼 더해지면 됨
					}
				}
			}
		}
		result += max; //결과에 더해주기
	}
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();
		
		int T = Integer.parseInt(br.readLine()); //테스트 케이스 개수
		for (int t = 1; t <= T; t++) {
			st = new StringTokenizer(br.readLine());
			int M = Integer.parseInt(st.nextToken()); //총 이동 시간
			A = Integer.parseInt(st.nextToken()); //BC의 개수
			//사용자 A의 이동 정보
			st = new StringTokenizer(br.readLine());
			int moveA[] = new int[M];
			for (int i = 0; i < M; i++) {
				moveA[i] = Integer.parseInt(st.nextToken());
			}
			//사용자 B의 이동 정보
			st = new StringTokenizer(br.readLine());
			int moveB[] = new int[M];
			for (int i = 0; i < M; i++) {
				moveB[i] = Integer.parseInt(st.nextToken());
			}
			//BC 정보
			bc = new ArrayList<>();
			for (int i = 0; i < A; i++) {
				st = new StringTokenizer(br.readLine());
				bc.add(new BatteryCharger(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken())));
			}
			
			//사용자 A, B 이동
			int dx[] = {0, 0, 1, 0, -1};
			int dy[] = {0, -1, 0, 1, 0};
			//사용자 초기 위치
			int ax = 1;
			int ay = 1;
			int bx = 10;
			int by = 10;
			
			result = 0; //충전량 합의 최대값 결과
			checked(ax, ay, bx, by); //초기 위치 충전 확인
			
			for (int i = 0; i < M; i++) {
				//A 이동
				int nxA = ax + dx[moveA[i]];
				int nyA = ay + dy[moveA[i]];
				//B 이동
				int nxB = bx + dx[moveB[i]];
				int nyB = by + dy[moveB[i]];
				
				//이동 위치로 옮겨주기
				ax = nxA;
				ay = nyA;
				bx = nxB;
				by = nyB;
				
				checked(ax, ay, bx, by); //충전 확인
			}
			sb.append("#"+t+" "+result+"\n");
		}
		System.out.println(sb);
	}

}
