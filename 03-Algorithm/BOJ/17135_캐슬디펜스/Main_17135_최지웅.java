package todo.lesson._0818;

import java.io.*;
import java.util.*;

public class Main_17135_최지웅 {
	
	static int N, M, D;
	
	static final int ENEMY = 1;
	
	static int CASTLEROW;
	
	static class Position implements Comparable<Position> {
		int r;
		int c;
		
		Position() {
			
		}
		
		Position(int r, int c) {
			this.r = r;
			this.c = c;
		}
		
		@Override
		public boolean equals(Object obj) {
			Position pos = (Position) obj;
			return this.r == pos.r && this.c == pos.c;
		}

		@Override
		public String toString() {
			return "Position [r=" + r + ", c=" + c + "]";
		}

		@Override
		public int compareTo(Position o) {
			if (this.c == o.c) return this.r - o.r;
			else return this.c - o.c;
		}
		
		@Override
		public int hashCode() {
			// TODO Auto-generated method stub
			return super.hashCode();
		}
	}
	
	static boolean[] selected;
	static int[] seq;
	
	static Position[] archers = new Position[3];
	static Set<Position> enemies = new TreeSet<>();
	static Set<Position> enemiesCopy = new TreeSet<>();
	static Set<Position> targets = new TreeSet<>();
	
	static boolean gameOver;
	
	static int cnt;
	static int maxCnt;
	
	static int taxiDist(Position p, Position q) {
		return Math.abs(p.r - q.r) + Math.abs(p.c - q.c);
	}
	
	static void setTarget() {
		
		targets.clear();
				
		Position target;
		int dist;
		int minDist;
		
		for (int i = 0; i < 3; i++) {
			target = null;
			minDist = Integer.MAX_VALUE;
			for (Position enemy : enemiesCopy) {
				dist = taxiDist(archers[i], enemy);
				if (dist <= D && minDist > dist) {
					minDist = dist;
					target = enemy;
				}
			}
			if (target != null) targets.add(target);
		}
	}
	
	static void attack() {
		boolean killed;
		for (Position target : targets) {
			killed = enemiesCopy.remove(target);
			if (killed) cnt++;
		}
	}
	
	static void move() {
		
		Set<Position> nextEnemiesCopy = new TreeSet<>();
		
		int nr, nc;
		for (Position enemy : enemiesCopy) {
			nr = enemy.r + 1; nc = enemy.c;
			if (nr >= CASTLEROW) {
				continue;
			}
			nextEnemiesCopy.add(new Position(nr, nc));
		}
		
		enemiesCopy = nextEnemiesCopy;
	}
	
	static void setArcher() {
		for (int i = 0; i < 3; i++) {
			archers[i].r = CASTLEROW;
			archers[i].c = seq[i];
		}
	}
	
	static void combination(int count, int start) {
		if (count == 3) {
			gameOver = false;
			enemiesCopy.clear();
			enemiesCopy.addAll(enemies);
			cnt = 0;
			setArcher();
			while (!gameOver) {
				setTarget();
				attack();
				move();
				if (enemiesCopy.size() == 0) gameOver = true;
			}
			maxCnt = Math.max(maxCnt, cnt);
		} else {
			for (int i = start; i < M; i++) {
				selected[i] = true;
				seq[count] = i;
				combination(count + 1, i + 1);
				selected[i] = false;
			}
		}
	}
	
	public static void main(String[] args) throws Exception {

//		System.setIn(new FileInputStream("input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		D = Integer.parseInt(st.nextToken());
		
		int[][] grid = new int[N][M];
		CASTLEROW = N;
		
		for (int n = 0; n < N; n++) {
			st = new StringTokenizer(br.readLine());
			for (int m = 0; m < M; m++) {
				grid[n][m] = st.nextToken().charAt(0) - '0';
				if (grid[n][m] == ENEMY) {
					enemies.add(new Position(n, m));
				}
			}
		}
		
		selected = new boolean[M];
		seq = new int[3];
		
		for (int i = 0; i < 3; i++) {
			archers[i] = new Position();
		}
		
		maxCnt = Integer.MIN_VALUE;
		combination(0, 0);
		
		System.out.println(maxCnt);
	}

}
