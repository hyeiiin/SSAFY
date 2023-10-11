package swea;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

public class Solution_5644_김도현 {

	static class Charger implements Comparable<Charger>{
		int x;
		int y;
		int range;
		int power;
		public Charger(int x, int y, int range, int power) {
			super();
			this.x = x;
			this.y = y;
			this.range = range;
			this.power = power;
		}
		@Override
		public int compareTo(Charger o) {
			return this.power-o.power;
		}
	}
	
	static int M,A,person[][];
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int T = Integer.parseInt(st.nextToken());
		for (int tc = 1; tc <= T; tc++) {
			st = new StringTokenizer(br.readLine());
			M = Integer.parseInt(st.nextToken());
			A = Integer.parseInt(st.nextToken());
			person = new int[2][M];
			for (int i = 0; i < 2; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j = 0; j < M; j++) {
					person[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			Charger[] charger = new Charger[A];
			for (int i = 0; i < A; i++) {
				st = new StringTokenizer(br.readLine());
				int r = Integer.parseInt(st.nextToken());
				int c = Integer.parseInt(st.nextToken());
				charger[i]=new Charger(c, r, Integer.parseInt(st.nextToken()),Integer.parseInt(st.nextToken()));
			}
			//System.out.println(Arrays.toString(charger));
			// A B 의 위치를 정해준다
			int personA_r = 1;
			int personA_c = 1;
			int personB_r = 10;
			int personB_c = 10;
			int sum = 0;
			// A B 를 M만큼 한칸씩 이동하면서 충전량을 저장한다
			for (int i = 0; i <= M; i++) {
				// A 사람이 충전할수 있는 충전기를 모두 찾습니다
				List<Charger> chargerA = getCharger(personA_r,personA_c,charger);
				// B 사람이 충전할수 있는 충전기를 모두 찾습니다
				List<Charger> chargerB = getCharger(personB_r,personB_c,charger);
				
				// chargerA 만 충전가능하면 최대값을 충전합니다
				if(chargerA.size()>0 && chargerB.size()==0) {
					sum += Collections.max(chargerA).power;
				}
				// chargerB 만 충전가능하면 최대값을 충전합니다
				if(chargerA.size()==0 && chargerB.size()>0) {
					sum += Collections.max(chargerB).power;
				}
				// 둘 다 가능하다면 모든 경우의 수에 대해 최대 충전량을 구한다
				if(chargerA.size()>0 && chargerB.size()>0) {
					int max = 0;
					for (int j = 0; j < chargerA.size(); j++) {
						for (int k = 0; k < chargerB.size(); k++) {
							int tmp = chargerA.get(j).power + chargerB.get(k).power;
							// 두 충전기가 같다면 /2 한다
							if(chargerA.get(j) == chargerB.get(k)) {
								tmp /= 2;
							}
							max = Math.max(max, tmp);
						}
					}
					sum += max;
				}
				// 마지막은 이동하지 안고 충전만 한다
				if(i==M) break;
				
				// 이동
				personA_r += dr[person[0][i]];
				personA_c += dc[person[0][i]];
				personB_r += dr[person[1][i]];
				personB_c += dc[person[1][i]];
				
			}
			
			System.out.println("#" + tc + " " + sum);
		}
	}
	static int[] dr = {0,-1,0,1, 0};
	static int[] dc = {0, 0,1,0,-1};
	private static List<Charger> getCharger(int r, int c, Charger[] charger) {
		List<Charger> list = new ArrayList();
		for (int i = 0; i < charger.length; i++) {
			if( Math.abs(r-charger[i].x) + Math.abs(c-charger[i].y) <= charger[i].range) {
				list.add(charger[i]);
			}
		}
		return list;
	}

}
