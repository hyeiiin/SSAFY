import java.io.*;
import java.util.*;

public class Main {
	private static String[][] map,mapTmp;
	private static String[][] mapTmps1,mapTmps2,mapTmps3,mapTmps4;
	private static int n,m;
	private static boolean isChange=false;
	private static StringBuilder sb;
	public static void main(String[] args) throws IOException {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st=new StringTokenizer(br.readLine());
		sb=new StringBuilder();
		n=Integer.parseInt(st.nextToken());
		m=Integer.parseInt(st.nextToken());
		int r=Integer.parseInt(st.nextToken());
		int op;
		map=new String[n][m];
		for(int i=0;i<n;i++) {
			st=new StringTokenizer(br.readLine());
			for(int j=0;j<m;j++) {
				map[i][j]=st.nextToken();
			}
		}
		st=new StringTokenizer(br.readLine());
		while(r-->0) {
			op=Integer.parseInt(st.nextToken());
			if(op==1) {
				operation1();
				continue;
			}
			if(op==2) {
				operation2();
				continue;
			}
			if(op==3) {
				operation3();
				continue;
			}
			if(op==4) {
				operation4();
				continue;
			}
			if(op==5) {
				operation5();
				continue;
			}
			if(op==6) {
				operation6();
				continue;
			}
		}
		if(isChange) printTrue();
		else printFalse();
		System.out.println(sb.toString());
	}
	public static void printFalse() {
		for(int i=0;i<n;i++) {
			for(int j=0;j<m;j++) {
				sb.append(map[i][j]).append(" ");
			}
			sb.append("\n");
		}
	}
	public static void printTrue() {
		for(int i=0;i<m;i++) {
			for(int j=0;j<n;j++) {
				sb.append(map[i][j]).append(" ");
			}
			sb.append("\n");
		}
	}
	public static void operation1() {
	//상하반전
		String tmp;
		if(!isChange) {
			for(int i=0;i<m;i++) {
				for(int j=0;j<n/2;j++) {
					tmp=map[j][i];
					map[j][i]=map[n-j-1][i];
					map[n-j-1][i]=tmp;
				}
			}
		}else {
			for(int i=0;i<n;i++) {
				for(int j=0;j<m/2;j++) {
					tmp=map[j][i];
					map[j][i]=map[m-j-1][i];
					map[m-j-1][i]=tmp;
				}
			}
		}
	}
	public static void operation2() {
	//좌우반전
		String tmp;
		if(!isChange) {
			for(int i=0;i<n;i++) {
				for(int j=0;j<m/2;j++) {
					tmp=map[i][j];
					map[i][j]=map[i][m-j-1];
					map[i][m-j-1]=tmp;
				}
			}
		}else {
			for(int i=0;i<m;i++) {
				for(int j=0;j<n/2;j++) {
					tmp=map[i][j];
					map[i][j]=map[i][n-j-1];
					map[i][n-j-1]=tmp;
				}
			}
		}
	}
	public static void operation3() {
	//90도 회전(우)
		if(!isChange) {
			//nm->mn
			mapTmp=new String[m][n];
			for(int i=0;i<m;i++) {
				for(int j=0;j<n;j++) {
					mapTmp[i][j]=map[n-j-1][i];
				}
			}
			map=new String[m][n];
			for(int i=0;i<m;i++) {
				for(int j=0;j<n;j++) {
					map[i][j]=mapTmp[i][j];
				}
			}
			isChange=true;
		}else {
			mapTmp=new String[n][m];
			for(int i=0;i<n;i++) {
				for(int j=0;j<m;j++) {
					mapTmp[i][j]=map[m-j-1][i];
				}
			}
			map=new String[n][m];
			for(int i=0;i<n;i++) {
				for(int j=0;j<m;j++) {
					map[i][j]=mapTmp[i][j];
				}
			}
			isChange=false;
		}
	}
	public static void operation4() {
	//90도 회전(좌)
		if(!isChange) {
			//nm->mn
			mapTmp=new String[m][n];
			for(int i=0;i<m;i++) {
				for(int j=0;j<n;j++) {
					mapTmp[i][j]=map[j][m-i-1];
				}
			}
			map=new String[m][n];
			for(int i=0;i<m;i++) {
				for(int j=0;j<n;j++) {
					map[i][j]=mapTmp[i][j];
				}
			}
			isChange=true;
		}else {
			mapTmp=new String[n][m];
			for(int i=0;i<n;i++) {
				for(int j=0;j<m;j++) {
					mapTmp[i][j]=map[j][n-i-1];
				}
			}
			map=new String[n][m];
			for(int i=0;i<n;i++) {
				for(int j=0;j<m;j++) {
					map[i][j]=mapTmp[i][j];
				}
			}
			isChange=false;
		}
		
	}
	public static void operation5() {
	//4분할 -> 4 1 3 2
		if(!isChange) {
			mapTmps1=getMaps(0,0,n/2,m/2);
			mapTmps2=getMaps(0,m/2,n/2,m/2);
			mapTmps3=getMaps(n/2,m/2,n/2,m/2);
			mapTmps4=getMaps(n/2,0,n/2,m/2);
			change5(n,m);
		}else {
			mapTmps1=getMaps(0,0,m/2,n/2);
			mapTmps2=getMaps(0,n/2,m/2,n/2);
			mapTmps3=getMaps(m/2,n/2,m/2,n/2);
			mapTmps4=getMaps(m/2,0,m/2,n/2);
			change5(m,n);
		}
	}
	public static void change5(int n,int m) {
		//4분할 -> 4 1 3 2
		for(int i=0;i<n/2;i++) {
			for(int j=0;j<m/2;j++) {
				map[i][j]=mapTmps4[i][j];
			}
		}
		for(int i=0;i<n/2;i++) {
			for(int j=0;j<m/2;j++) {
				map[i][j+m/2]=mapTmps1[i][j];
			}
		}
		for(int i=0;i<n/2;i++) {
			for(int j=0;j<m/2;j++) {
				map[i+n/2][j+m/2]=mapTmps2[i][j];
			}
		}
		for(int i=0;i<n/2;i++) {
			for(int j=0;j<m/2;j++) {
				map[i+n/2][j]=mapTmps3[i][j];
			}
		}
	}
	public static void operation6() {
	//4분할 -> 2 3 1 4
		if(!isChange) {
			mapTmps1=getMaps(0,0,n/2,m/2);
			mapTmps2=getMaps(0,m/2,n/2,m/2);
			mapTmps3=getMaps(n/2,m/2,n/2,m/2);
			mapTmps4=getMaps(n/2,0,n/2,m/2);
			change6(n,m);
			
		}else {
			mapTmps1=getMaps(0,0,m/2,n/2);
			mapTmps2=getMaps(0,n/2,m/2,n/2);
			mapTmps3=getMaps(m/2,n/2,m/2,n/2);
			mapTmps4=getMaps(m/2,0,m/2,n/2);
			change6(m,n);
		}
	}
	public static void change6(int n,int m) {
		//4분할 -> 2 3 1 4
		for(int i=0;i<n/2;i++) {
			for(int j=0;j<m/2;j++) {
				map[i][j]=mapTmps2[i][j];
			}
		}
		for(int i=0;i<n/2;i++) {
			for(int j=0;j<m/2;j++) {
				map[i][j+m/2]=mapTmps3[i][j];
			}
		}
		for(int i=0;i<n/2;i++) {
			for(int j=0;j<m/2;j++) {
				map[i+n/2][j+m/2]=mapTmps4[i][j];
			}
		}
		for(int i=0;i<n/2;i++) {
			for(int j=0;j<m/2;j++) {
				map[i+n/2][j]=mapTmps1[i][j];
			}
		}
	}
	public static String[][] getMaps(int r,int c,int n,int m){
		String[][] tmp=new String[n][m];
		for(int i=0;i<n;i++) {
			for(int j=0;j<m;j++) {
				tmp[i][j]=map[r+i][c+j];
			}
		}
		return tmp;
	}
}
