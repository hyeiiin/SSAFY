import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution_6808_김나연 {
	
	static int t, numbers[],gu_card[],in_card[], gu_cnt,in_cnt, gu, in, isSelected[], arrCheck[],num[];
	
	static void go(int cnt) {
		if(cnt==9) {
			gu=0; in=0;
			
			for (int i = 0; i < 9; i++) {
				if(gu_card[i]>num[i]) gu = gu + gu_card[i] + num[i];
				else if(gu_card[i]<num[i]) in = in + gu_card[i] + num[i];
			}
			
			// System.out.println(gu+" "+in);
			
			if(gu>in) gu_cnt++;
			else if(in>gu) in_cnt++;
			
			
			
			
		}
		else {
			for (int i = 0; i < 9; i++) {
				if(isSelected[i]==1)continue;
				num[cnt]=in_card[i];
				isSelected[i]=1;
				go(cnt+1);
				isSelected[i]=0;
			}
		}
		return;
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb;
		
		t=Integer.parseInt(br.readLine());
		
		for(int tc=1;tc<=t;tc++) {
			sb=new StringBuilder();
			gu=0; in=0;gu_cnt=0;in_cnt=0;
			
			numbers=new int[9];
			gu_card=new int[9];
			in_card=new int[9];
			num=new int[9];
			isSelected=new int[9];
			arrCheck = new int[19];
			
			st=new StringTokenizer(br.readLine());
			
			for (int i = 0; i < 9; i++) {
				gu_card[i]=Integer.parseInt(st.nextToken());
				arrCheck[gu_card[i]] = 1;
			}
			
			int cnt=0;
			for(int i = 1; i <= 18; i++) {
				if(arrCheck[i]==0) in_card[cnt++] = i;
			}

			
			
			go(0);
			
			
			
			
			sb.append("#").append(tc).append(" ").append(gu_cnt).append(" ").append(in_cnt);
			System.out.println(sb);
		}
	}
}
