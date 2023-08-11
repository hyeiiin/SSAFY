import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

class house {
	int y;
	int x;
	
	public house(int y, int x) {
		this.y = y;
		this.x = x;
	}
	
}

class chicken {
	int y;
	int x;
	
	public chicken(int y, int x) {
		this.y = y;
		this.x = x;
	}
	
}

public class Main_15686_김나연 {
	
	static int n,m,a[][],sum, ans=Integer.MAX_VALUE;
	static int cnt1=0, cnt2=0;
	static house h[];
	static chicken c[];
	static ArrayList<chicken> isSelected = new ArrayList<>();
	
	public static void go(int cnt, int start, int chickenNum) {
		if(cnt==chickenNum) {
			sum=0;
			for(int k = 0; k < cnt1; k++) {
				// 현재 선택된 치킨집 조합에서 집과 치킨집 거리 계산 후 최소값 찾기
				int dis=Integer.MAX_VALUE;
				for (chicken ch:isSelected) {
					dis=Math.min(dis, (Math.abs(h[k].y-ch.y) + Math.abs(h[k].x-ch.x)));
				}
				sum+=dis;
			}
            // (1~m개 선택한) 모든 치킨집 조합 중 최소값 찾기
			ans=Math.min(ans, sum);
		}
		else {
			for (int i = start; i < cnt2; i++) {
				isSelected.add(c[i]);
				go(cnt+1, i+1, chickenNum);
				isSelected.remove(isSelected.size()-1);
			}
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();
		
		st=new StringTokenizer(br.readLine());
		
		n=Integer.parseInt(st.nextToken());
		m=Integer.parseInt(st.nextToken());
		
		a=new int [n][n];
		h=new house [n*n];
		c=new chicken [13];
		
		for (int i = 0; i < n; i++) {
			st=new StringTokenizer(br.readLine());
			for (int j = 0; j < n; j++) {
				a[i][j]=Integer.parseInt(st.nextToken());
				
				if(a[i][j]==1) h[cnt1++]=new house(i,j);
                if(a[i][j]==2) c[cnt2++]=new chicken(i,j);
			}
		}
		
		for (int i = 1; i <= m; i++) {
			// 조합으로 1~m개 치킨집 뽑아줌
			// 뽑아준 치킨집과 집(h 배열)과의 거리 측정
			isSelected.clear();
			go(0,0,i);
		}
		
		sb.append(ans);
		
		System.out.println(sb);
	}

}

