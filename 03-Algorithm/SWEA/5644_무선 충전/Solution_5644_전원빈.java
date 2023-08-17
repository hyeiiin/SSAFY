package swea;

import java.io.*;
import java.util.*;

public class _5644 {
	static int m, a;
	static int amove[];
	static int bmove[];
	static int bcy[];
	static int bcx[];
	static int bcc[];
	static int bcp[];
	static int dx[] = {0, 0, 1, 0, -1};
	static int dy[] = {0, -1, 0, 1, 0};
	static int bcastatus[];
	static int bcbstatus[];
	static int total;
	static int cnta;
	static int cntb;
	
	static void comcom(int[] a, int[] b, int cnta, int cntb) {
		int tempa = 0;
		int tempb = 0;
		int aloc = -1;
		int bloc = -1;
		for(int i = 0; i < cnta; i++) {
			if(bcp[a[i]]> tempa) {
				tempa = bcp[a[i]];
				aloc = a[i];
			}
		}
		
		for(int i = 0; i < cntb; i++) {
			if(bcp[b[i]] > tempb) {
				tempb = bcp[b[i]];
				bloc = b[i];
			}
		}
		
		if(aloc == -1 && bloc == -1)return;
		
		if(aloc == bloc) {
			int nothere = aloc;
			total += tempa;
			
			tempa = 0;
			tempb = 0;
			aloc = -1;
			bloc = -1;
			for(int i = 0; i < cnta; i++) {
				if(tempa < bcp[a[i]]) {
					if(a[i] == nothere)continue;
					tempa = bcp[a[i]];
					aloc = a[i];
				}
			}
			
			for(int i = 0; i < cntb; i++) {
				if(tempb < bcp[b[i]]) {
					if(b[i] == nothere)continue;
					tempb = bcp[b[i]];
					bloc = b[i];
				}
			}
			total += Math.max(tempa, tempb);
		}else {
			total += tempa + tempb;
		}
		
	}
	
	
	static void lego(int y1, int x1, int y2, int x2, int cnt) {
		bcastatus = new int[a];
		bcbstatus = new int[a];
		cnta = 0;
		cntb = 0;
		for(int i = 0; i < a; i++) {
			if(Math.abs(y1- bcy[i]) + Math.abs(x1-bcx[i]) <= bcc[i]) {
				bcastatus[cnta] = i;
				cnta++;
			}
			if(Math.abs(y2- bcy[i]) + Math.abs(x2-bcx[i]) <= bcc[i]) {
				bcbstatus[cntb] = i;
				cntb++;
			}
		}
		
		comcom(bcastatus, bcbstatus, cnta, cntb);
		
		cnt++;
		if(cnt == m) {
			return;
		}else {			
			int ny1 = y1+dy[amove[cnt]];
			int nx1 = x1+dx[amove[cnt]];
			int ny2 = y2+dy[bmove[cnt]];
			int nx2 = x2+dx[bmove[cnt]];
			lego(ny1, nx1, ny2, nx2, cnt);
		}
		
	}
	
	
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int t = Integer.parseInt(bf.readLine());
		for(int test = 1; test <= t; test++) {
			StringTokenizer st = new StringTokenizer(bf.readLine());
			m = Integer.parseInt(st.nextToken());
			a = Integer.parseInt(st.nextToken());
			amove = new int[m];
			bmove = new int[m];
			st = new StringTokenizer(bf.readLine());
			for(int i = 0; i < m; i++) {
				amove[i] = Integer.parseInt(st.nextToken());
			}
			st = new StringTokenizer(bf.readLine());
			for(int i = 0; i < m; i++) {
				bmove[i] = Integer.parseInt(st.nextToken());
			}
			bcx = new int[a];
			bcy = new int[a];
			bcc = new int[a];
			bcp = new int[a];
			for(int i = 0; i< a; i++) {
				st = new StringTokenizer(bf.readLine());
				bcx[i] = Integer.parseInt(st.nextToken());
				bcy[i] = Integer.parseInt(st.nextToken());
				bcc[i] = Integer.parseInt(st.nextToken());
				bcp[i] = Integer.parseInt(st.nextToken());
			}
			total = 0;
			lego(1, 1, 10, 10, -1);
			sb.append("#").append(test).append(" ").append(total).append("\n");
		}
		System.out.println(sb.toString());
	}

}
