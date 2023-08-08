import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class  Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st=new StringTokenizer(br.readLine());
		int n=Integer.parseInt(st.nextToken());
		int m=Integer.parseInt(st.nextToken());
		int r=Integer.parseInt(st.nextToken());
		int[][] map=new int[n][m];
		int[][] newMap=new int[n][m];
		int currH=0,currW=0,height=n-1,width=m-1;
		for(int i=0;i<n;i++) {
			st=new StringTokenizer(br.readLine());
			for(int j=0;j<m;j++) {
				map[i][j]=Integer.parseInt(st.nextToken());
			}
		}
		int count=0;
		while(count<n*m*r) {
			if(count!=0&&count%(n*m)==0) {
				for(int i=0;i<n;i++) {
					map[i]=Arrays.copyOf(newMap[i],m);
				}
				currH=0;
				currW=0;
				height=n-1;
				width=m-1;
			}
			//상변
			for(int j=width;j>=currW;j--) {
				if(j==width&&currH<height) {
					newMap[currH][j]=map[currH+1][j];
					count++;
				}else {
					newMap[currH][j]=map[currH][j+1];
					count++;
				}
			}
			currH++;
			//우변
			for(int j=height;j>=currH;j--) {
				if(j==height) {
					newMap[j][width]=map[j][width-1];
					count++;
				}else {
					newMap[j][width]=map[j+1][width];
					count++;
				}
			}
			width--;
			//하변
			for(int j=currW;j<=width;j++) {
				if(j==currW) {
					newMap[height][j]=map[height-1][j];
					count++;
				}else {
					newMap[height][j]=map[height][j-1];
					count++;
				}
			}
			height--;
			//좌변
			for(int j=currH-1;j<=height;j++) {
				if(j==currH-1) {
					newMap[j][currW]=map[j][currW+1];
				}else {
					newMap[j][currW]=map[j-1][currW];
					count++;
				}
			}
			currW++;
		}
		print(newMap,n,m);
	}
	public static void print(int[][] arr,int n,int m) {
		for(int i=0;i<n;i++) {
			for(int j=0;j<m;j++) {
				System.out.print(arr[i][j]+" ");
			}
			System.out.println();
		}
	}
}
