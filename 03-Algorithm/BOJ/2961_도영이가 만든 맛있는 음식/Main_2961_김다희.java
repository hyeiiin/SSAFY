import java.io.*;
import java.util.*;

public class Main {
	static long minVal=Long.MAX_VALUE;
	static int n;
	static Food[] food;
	static Food[] queue;
	public static void main(String[] args)throws IOException{
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		n=Integer.parseInt(br.readLine());
		StringTokenizer st;
		long bitter,sour;
		food=new Food[n];
		
		for(int i=0;i<n;i++) {
			st=new StringTokenizer(br.readLine());
			sour=Long.parseLong(st.nextToken());
			bitter=Long.parseLong(st.nextToken());
			food[i]=new Food(sour,bitter);
		}
		for(int i=1;i<=n;i++) {
			queue=new Food[i];
			comb(0,i,0);
		}
		System.out.println(minVal);
	}
	public static void comb(int cnt,int target,int start) {
		if(cnt==target) {
			long tmpSour=1,tmpBitter=0,tmpTotal=0;
			for(Food i:queue) {
				tmpSour*=i.sour;
				tmpBitter+=i.bitter;
			}
			tmpTotal=Math.abs(tmpSour-tmpBitter);
			minVal=tmpTotal<minVal?tmpTotal:minVal;
			return;
		}
		for(int i=start;i<n;i++) {
			queue[cnt]=food[i];
			comb(cnt+1,target,i+1);
		}
	}
	public static class Food{
		long sour;
		long bitter;
		public Food(long sour,long bitter){
			this.sour=sour;
			this.bitter=bitter;
		}
	}
}
