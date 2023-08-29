package todo.lesson._0829;

import java.util.*;

public class Main_12869_최지웅 {

	static int N;
	
	static Integer[][][] memo = new Integer[61][61][61];

	static class State {
		int[] hpArr;
		int count;

		State() {
			hpArr = new int[3];
		}

		State(int[] hpArr) {
			this.hpArr = new int[3];
			for (int i = 0; i < 3; i++) {
				this.hpArr[i] = hpArr[i];
			}			
		}

		State getNextState(int[] attack) {

			State nextState = new State();

			for (int i = 0; i < 3; i++) {
				nextState.hpArr[i] = Math.max(this.hpArr[i] - attack[i], 0);
			}
			nextState.count = this.count + 1;

			return nextState;
		}

		boolean gameOver() {
			for (int i = 0; i < 3; i++) {
				if (hpArr[i] != 0)
					return false;
			}
			return true;
		}

		@Override
		public String toString() {
			return Arrays.toString(hpArr) + " " + count;
		}
	}

	static List<int[]> attacks = new ArrayList<>();
	static int caseAttacks;

	static int minCnt;

	static void BFS(State initial) {
		
		Queue<State> queue = new ArrayDeque<>();

		int[] hpArr;
		hpArr = initial.hpArr;
		memo[hpArr[0]][hpArr[1]][hpArr[2]] = initial.count;
		
		queue.offer(initial);
		
		State cur;
		State next;
		
		while (!queue.isEmpty()) {
			cur = queue.poll();

			if (cur.gameOver()) {
				return;
			}

			for (int i = 0; i < caseAttacks; i++) {
				
				next = cur.getNextState(attacks.get(i));
				hpArr = next.hpArr;
				
				if (memo[hpArr[0]][hpArr[1]][hpArr[2]] == null) {
					memo[hpArr[0]][hpArr[1]][hpArr[2]] = next.count;
					queue.offer(next);
				}
			}
		}
	}

	public static void main(String[] args) {

		Scanner in = new Scanner(System.in);

		N = in.nextInt();
		int[] hpArr = new int[3];
		for (int n = 0; n < N; n++) {
			hpArr[n] = in.nextInt();
		}
		for (int n = N + 1; n < 3; n++) {
			hpArr[n] = 0;
		}

		if (N == 1) {
			attacks.add(new int[] {9, 0, 0} );
			caseAttacks = 1;
		} else if (N == 2) {
			attacks.add(new int[] { 9, 3, 0});
			attacks.add(new int[] { 3, 9, 0});
			caseAttacks = 2;
		} else if (N == 3) {
			attacks.add(new int[] { 9, 3, 1 });
			attacks.add(new int[] { 9, 1, 3 });
			attacks.add(new int[] { 3, 9, 1 });
			attacks.add(new int[] { 3, 1, 9 });
			attacks.add(new int[] { 1, 9, 3 });
			attacks.add(new int[] { 1, 3, 9 });
			caseAttacks = 6;
		}

		BFS(new State(hpArr));

		System.out.println(memo[0][0][0]);

		//
		in.close();
	}

}
