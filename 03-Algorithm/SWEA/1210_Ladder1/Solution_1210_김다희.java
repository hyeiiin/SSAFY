import java.io.*;
import java.util.*;
 
class newMain {
    static int ladder[][] = new int[100][100];
    public static void main(String args[]) throws Exception {
        int T = 10;
        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        for(int test_case = 1; test_case <= T; test_case++) {
    		int answer=0;
    		br.readLine();
            for(int i=0;i<100;i++) {
				st=new StringTokenizer(br.readLine());
				for(int j=0;j<100;j++) {
					ladder[i][j]=Integer.parseInt(st.nextToken());
					if(ladder[i][j]==2) {
						answer=find(j);
					}
				}
			}
            System.out.println("#" + test_case + " " + answer);
        }
    }
    static int find(int c) {
    	int r = 99;
    	boolean left;
    	while (r-- > 0) {
    		left = false;
    		while (c+1<100 && ladder[r][c+1]==1) {
    			c++;
    			left = true;
    		}
    		while (!left && c-1>=0 && ladder[r][c-1]==1) 
    			c--;
    	}
    	return c;
    }
}
