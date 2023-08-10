package swea;

import java.io.*;
import java.util.*;

import javax.swing.plaf.synth.SynthSeparatorUI;

public class _4012 {
	//배열 크기를 받을 변수
	static int n;
	//남은 재료로 만든 시너지의 총량
	static int lefttotal;
	//조합된 배열의 시너지 총량;
	static int comtotal;
	//시너지를 받을 배열 생성
	static int s[][];
	//조합을 사용할 배열
	static int f1[];
	static int f2[];
	//사용된 재료를 표시할 배열
	static int ff[];
	//정답을 사용할 최솟값 변수
	static int mi;
	
	
	static void comcom(int cnt, int start) {
		//기저조건 재료의 개수가 n/2가 나오면 종료
		if(cnt == n/2) {
			lefttotal = 0;
			comtotal = 0;
			int w = 0;
			for(int i = 1; i <= n; i++) {
				if(ff[i] == 1)continue;
				else {
					f2[w] = i;
					w++;
				}
			}
			for(int i = 0; i <n/2; i++) {
				for(int j = 0; j < n/2; j++) {
					comtotal += s[f1[i]][f1[j]];
				}
			}
			for(int i = 0; i <n/2; i++) {
				for(int j = 0; j < n/2; j++) {
					lefttotal += s[f2[i]][f2[j]];
				}
			}
			mi = Math.min(mi, Math.abs(comtotal-lefttotal));
			return;
		}
		
		for(int i = start; i <= n; i++) {
			f1[cnt] = i;
			ff[i] = 1;
			comcom(cnt+1, i+1);
			ff[i] = 0;
		}
	}
	
	
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int t = Integer.parseInt(bf.readLine());
		for(int test = 1; test <= t; test++) {
			n = Integer.parseInt(bf.readLine());
			s = new int[n+1][n+1];
			f1 = new int[n/2];
			f2 = new int[n/2];
			ff = new int[n+1];
			for(int i = 1; i <= n; i++) {
				StringTokenizer st = new StringTokenizer(bf.readLine());
				for(int j = 1; j <=n; j++) {
					s[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			mi = Integer.MAX_VALUE;
			comcom(0, 1);
			sb.append("#").append(test).append(" ").append(mi).append("\n");
		}
		System.out.println(sb.toString());
	}

}
