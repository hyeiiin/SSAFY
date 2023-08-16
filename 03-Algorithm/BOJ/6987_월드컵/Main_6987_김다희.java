import java.io.*;
import java.util.*;

public class Main {
	static int[][] scoreBoard;
	static int total,tmp;
	static boolean isEnd;
	static int[] curr= {0,0,0,0,0,1,1,1,1,2,2,2,3,3,4};
	static int[] comp= {1,2,3,4,5,2,3,4,5,3,4,5,4,5,5};
    public static void main(String[] args) throws IOException {
       BufferedReader br= new BufferedReader(new InputStreamReader(System.in));
       StringBuilder sb=new StringBuilder();
       StringTokenizer st;
       
       for(int i=0;i<4;i++) {
    	   scoreBoard=new int[6][3];
    	   total=0;
    	   isEnd=false;
    	   st=new StringTokenizer(br.readLine());
    	   for(int j=0;j<6;j++) {
    		   scoreBoard[j][0]=Integer.parseInt(st.nextToken());
    		   scoreBoard[j][1]=Integer.parseInt(st.nextToken());
    		   scoreBoard[j][2]=Integer.parseInt(st.nextToken());
    		   tmp=scoreBoard[j][0]+scoreBoard[j][1]+scoreBoard[j][2];
    		   if(tmp!=5) {
    			   isEnd=true;
    			   break;
    		   }
    		   total+=tmp;
    	   }
    	   if(total!=30) isEnd=true;
    	   if(isEnd) sb.append(0).append(" ");
    	   else sb.append(recur(0)?1:0).append(" ");
       }
       System.out.println(sb.toString());
    }
    public static boolean recur(int idx) {
    	if(idx==15) return true;
    	//이긴 경우
    	if(scoreBoard[curr[idx]][0]>0&&scoreBoard[comp[idx]][2]>0) {
    		scoreBoard[curr[idx]][0]--;
    		scoreBoard[comp[idx]][2]--;
    		if(recur(idx+1))return true;
    		scoreBoard[curr[idx]][0]++;
    		scoreBoard[comp[idx]][2]++;
    	}
    	//무승부
    	if(scoreBoard[curr[idx]][1]>0&&scoreBoard[comp[idx]][1]>0) {
    		scoreBoard[curr[idx]][1]--;
    		scoreBoard[comp[idx]][1]--;
    		if(recur(idx+1))return true;
    		scoreBoard[curr[idx]][1]++;
    		scoreBoard[comp[idx]][1]++;
    	}
    	//진경우
    	if(scoreBoard[curr[idx]][2]>0&&scoreBoard[comp[idx]][0]>0) {
    		scoreBoard[curr[idx]][2]--;
    		scoreBoard[comp[idx]][0]--;
    		if(recur(idx+1))return true;
    		scoreBoard[curr[idx]][2]++;
    		scoreBoard[comp[idx]][0]++;
    	}
    	return false;
    }
}
