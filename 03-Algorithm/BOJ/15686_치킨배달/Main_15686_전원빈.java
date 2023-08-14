import java.io.*;
import java.util.*;


public class Main
{
	static int d;
	static int mc;
	static List<Integer> cy = new ArrayList<>();
	static List<Integer> cx = new ArrayList<>();
	static List<Integer> hy = new ArrayList<>();
	static List<Integer> hx = new ArrayList<>();
	static List<Integer> c = new ArrayList<>();
	static int chl = Integer.MAX_VALUE;
	
	static void com(int cnt, int start) {
		if(cnt == mc) {
			int tempchl = 0;
			for(int i = 0; i < hy.size(); i++) {
				int chlone = Integer.MAX_VALUE;
				for(int w: c) {
					chlone = Math.min(chlone, Math.abs(hy.get(i)-cy.get(w)) + Math.abs(hx.get(i)-cx.get(w)));
				}
				tempchl += chlone;
			}
			chl = Math.min(chl, tempchl);
			return;
		}
		for(int i = start; i < cy.size(); i++) {
			c.add(i);
			com(cnt+1, i+1);
			c.remove(cnt);
		}
	}
	
	
	public static void main(String[] args) throws IOException {
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(bf.readLine());
		d = Integer.parseInt(st.nextToken());
		mc = Integer.parseInt(st.nextToken());
		for(int i = 0; i < d; i++) {
			st = new StringTokenizer(bf.readLine());
			for(int j = 0; j < d; j++) {
				int temp = Integer.parseInt(st.nextToken());
				if(temp == 1) {
					hy.add(i);
					hx.add(j);
				}else if(temp == 2) {
					cy.add(i);
					cx.add(j);
				}
			}
		}
		com(0, 0);
		StringBuilder sb = new StringBuilder();
		sb.append(chl);
		System.out.println(sb.toString());
	}
}