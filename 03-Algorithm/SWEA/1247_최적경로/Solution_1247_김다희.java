package boj;
import java.io.*;
import java.util.*;

public class Solution {
	static int visit,minVal,n;
	static Pair[] map; 
	static BufferedReader br= new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
    public static void main(String[] args) throws IOException {
       StringBuilder answer=new StringBuilder();
       int T=Integer.parseInt(br.readLine());
       for(int tc=1;tc<=T;tc++) {
    	   answer.append("#").append(tc).append(" ");
    	   visit=0;
    	   minVal=Integer.MAX_VALUE;
    	   n=Integer.parseInt(br.readLine())+2;
    	   init();
    	   dfs(0,map[1]);
    	   answer.append(minVal).append("\n");
       }
       System.out.println(answer.toString());
    }
    
    public static void dfs(int current,Pair start) {
    	if(current>=minVal) return;
    	if(visit==(1<<n-2)-1) {
    		int tmp=current+Math.abs(start.r-map[2].r)+Math.abs(start.c-map[2].c);
    		minVal=tmp<minVal?tmp:minVal;
    		return;
    	}
    	for(int i=3;i<=n;i++) {
    		if((visit&(1<<(i-3)))!=1<<(i-3)) {
    			visit^=1<<(i-3);
    			dfs(current+Math.abs(start.r-map[i].r)+Math.abs(start.c-map[i].c),map[i]);
    			visit^=1<<(i-3);
    		}
    	}
    }
    
    public static void init() throws IOException {
    	map=new Pair[n+1];
    	st=new StringTokenizer(br.readLine());
    	for(int i=1;i<=n;i++) {
    		map[i]=new Pair(Integer.parseInt(st.nextToken()),Integer.parseInt(st.nextToken()));
    	}
    }
    
	public static class Pair{
		int r,c;
		public Pair(int r,int c) {
			this.r=r;
			this.c=c;
		}
	}
}
