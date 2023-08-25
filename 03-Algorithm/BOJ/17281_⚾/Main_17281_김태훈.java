package baekjoon;
import java.util.*;
import java.io.*;

public class Main_17281_김태훈 {
	/*
	 * 입력 2차원 배열로 입력받기 입력 다 받고 순열부터 만든다. 입력 받으면 순열 생성, 순열을 저장하는 배열 numbers에 저장하고,
	 * i번째부터 시작하는데 처음 i는 0으로 초기화 numbers[3] == 1이 아니면 검사 하지말고 다음 순열로 그냥 넘김 => 1번 타자는
	 * 4번이 고정이니까 numbers에 적힌 타자 순서대로 확인하자. while (out_count !=3) 계속 돌자 큐를 만들고 큐 사이즈가
	 * 4가 되면 앞에서부터 한개씩 제거하면될거같다. 1이 들어오면 타자번호 예를 들어서 numbers[0]이 3이고 3이 1안타를 치면 큐에
	 * 3을 1개, 2개치면 3을 2개, 3개치면 3을 3개 이런식으로 큐에 쌓아놓자 그리고 안타 나올때마다 큐 사이즈 체크해서 4 이상이면
	 * poll하는데 넣은거 다 제거 size가 4이상이라서 맨 앞에 제거하는데 그 변수랑 그 다음 제거 대상이랑 같은숫자면 그것도 제거
	 * 이런식으로 만약에 0이나오면 아웃 카운트 변수만 증가 만약에 4나오면 큐 비우고 몇명 나오는지 체크해서 점수에 플러스 3아웃되서
	 * while문 나오게 되면 그 numbers[i]에서 i정보 저장해놓기 그 다음 이닝 입력 받아서 i번째부터 시작하기
	 */
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st = null;
	static int N, ainta[][], p = 9, numbers[], max_score = 0, score = 0;
	static boolean iscelect[];

	/*
	 * N = 이닝 정보 ainta[][] = 각 타자가 그 이닝에서 얻게될 결과 p = 타자 9명 max_score = 정답
	 */
	static void init() throws IOException {
		N = Integer.parseInt(br.readLine());
		ainta = new int[N][p];
		numbers = new int[p];
		iscelect = new boolean[p];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < p; j++) {
				ainta[i][j] = Integer.parseInt(st.nextToken());
			}
		}
	}

	static void perm(int cnt) {
		//만들때 하나 작게 만들어서 그 4번에 넣기만 하기
		if (cnt == p) {
            if (numbers[3] != 0) {
                return;
            }
            int scores = solve();
            max_score = Math.max(max_score, scores);
            return;
        }
		for (int i = 0; i < p; i++) {
			if (iscelect[i])
				continue;
			numbers[cnt] = i;
			iscelect[i] = true;
			perm(cnt + 1);
			iscelect[i] = false;
		}

	}

	static int solve() {
	    int order = 0;
	    score = 0; // 게임 점수 초기화

	    for (int i = 0; i < N; i++) {
	        int out_count = 0;
	        boolean[] base = new boolean[4]; 

	        while (out_count != 3) {
	            switch (ainta[i][numbers[order]]) {
	                case 0:
	                    out_count++;
	                    break;
	                case 4:
	                    score += countRunners(base) + 1;
	                    base = new boolean[4];
	                    break;
	                default:
	                    moveRunners(base, ainta[i][numbers[order]]);
	            }
	            order=(order+1)%9;
	        }
	    }
	    return score;
	}
	
	static void moveRunners(boolean[] base, int hit) {
	    for(int i=3; i>0; --i){
	        if(base[i]){
	            if(i+hit>3){
	                ++score;
	            } else{
	                base[i+hit] = true;
	            }
	            base[i] = false;
	        }
	    }

	    base[hit] = true; // 현재 타자 위치 반영
	}

	static int countRunners(boolean[] base) { 
	   int cnt=0;

	   for(int i=1;i<=3;++i){
	      if(base[i]){
	         base[i] = false;
	         ++cnt;
	      }
	   }

	   return cnt;
	}

	public static void main(String[] args) throws IOException {
		init();
		perm(0);
		System.out.println(max_score);
	}

}
