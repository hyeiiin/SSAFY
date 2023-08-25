package BOJ.Test;

import java.io.*;
import java.util.*;

public class Main_17143_김수린 {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	static StringTokenizer st;
	static int R, C, M, r, c, s, d, z, person = -1, ans;
	static int[] dx = { 0, -1, 1, 0, 0 };
	static int[] dy = { 0, 0, 0, 1, -1 };
	static Shark arr[][];
	static class Shark {
		int r, c, s, d, z;
		
		public Shark(int r, int c, int s, int d, int z) {
			this.r = r;
			this.c = c;
			this.s = s;
			this.d = d;
			this.z = z;
		}
	}
	
	public static boolean isIn(int nx, int ny) {
		return nx >= 0 && ny >= 0 && nx < R && ny < C; 
	}

	public static void moveShark() {
		PriorityQueue<Shark> moveShark = new PriorityQueue<>((o1, o2) -> (o1.z - o2.z));
		for(int i = 0; i < R; i++) {
			for(int k = 0; k < C; k++) {
				if(arr[i][k] != null) {
					Shark s = arr[i][k];
					int x = s.r;
					int y = s.c;
					for(int d = 1; d <= s.s; d++) {
						int nx = x + dx[s.d];
						int ny = y + dy[s.d];
						if(!isIn(nx, ny)) {
							if(s.d == 1) {
								nx *= -1;
								s.d = 2;
							} else if(s.d == 2) {
								nx = nx - 2;
								s.d = 1;
							} else if(s.d == 3) {
								ny = ny - 2;
								s.d = 4;
							} else if(s.d == 4) {
								ny *= -1;
								s.d = 3;
							}
						}
						x = nx;
						y = ny;
					}
					arr[i][k] = null;
					moveShark.offer(new Shark(x, y, s.s, s.d, s.z));
				}
			}
		}
		while(!moveShark.isEmpty()) {
			Shark s = moveShark.poll();
			arr[s.r][s.c] = s;
		}
	}

	public static void catchShark() {
		person++;
		for(int i = 0; i < R; i++) {
			if(arr[i][person] != null) {
				ans += arr[i][person].z;
				arr[i][person] = null;
				break;
			}
		}
	}

	public static void init() throws IOException {
		st = new StringTokenizer(br.readLine());
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		arr = new Shark[R][C];
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			r = Integer.parseInt(st.nextToken());
			c = Integer.parseInt(st.nextToken());
			s = Integer.parseInt(st.nextToken());
			d = Integer.parseInt(st.nextToken());
			z = Integer.parseInt(st.nextToken());
			arr[r - 1][c - 1] = new Shark(r - 1, c - 1, s, d, z);
		}
	}
	
	public static void main(String[] args) throws IOException {
		init();
		do {
			catchShark();
			moveShark();
		} while(person < C - 1);
		bw.write(ans + "\n");
		bw.flush();
		bw.close();
	}
}
