import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_16935_김나연 {
	
	static int n,m,r, type, flag;
	static int a[][], b[][];
	
	public static void copyOrignal() {
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < m; j++) {
				a[i][j]=b[i][j];
			}
		}
	}
	
	public static void one() {
		for (int i = 0; i < n/2; i++) {
			for (int j = 0; j < m; j++) {
				b[n-1-i][j]=a[i][j];
			}
		}
		
		for (int i = n/2; i < n; i++) {
			for (int j = 0; j < m; j++) {
				b[n-1-i][j]=a[i][j];
			}
		}
		
		
	}
	
	public static void two() {
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < m/2; j++) {
				b[i][m-1-j]=a[i][j];
			}
		}
		
		for (int i = 0; i < n; i++) {
			for (int j = m/2; j < m; j++) {
				b[i][m-1-j]=a[i][j];
			}
		}
	}
	
	public static void three() {
		int p = 0;
        int q = 0;
        for (int i = 0; i < m; i++) {
           q = 0;
            for (int j = n-1; j >= 0; j--) {
            	b[p][q]=a[j][i];
                 q++;
            }
            p++;
        }
        
	}
	
	public static void four() {
		int p = 0;
        int q = 0;
        for (int i = m-1; i >= 0; i--) {
            q = 0;
            for (int j = 0;j<n;j++) {
        	    b[p][q]=a[j][i];
                q++;
            }
            p++;
        }
        
	}
	
	public static void five() {
		for (int i = 0; i < n/2; i++) {
			for (int j = 0; j < m/2; j++) {
				b[i][j+m/2]=a[i][j];
			}
		}
		for (int i = 0; i < n/2; i++) {
			for (int j = m/2; j < m; j++) {
				b[i+n/2][j]=a[i][j];
			}
		}
		for (int i = n/2; i < n; i++) {
			for (int j = m/2; j < m; j++) {
				b[i][j-m/2]=a[i][j];
			}
		}
		for (int i = n/2; i < n; i++) {
			for (int j = 0; j < m/2; j++) {
				b[i-n/2][j]=a[i][j];
			}
		}
	}
	
	public static void six() {
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < m/2; j++) {
				b[i+n/2][j]=a[i][j];
			}
		}
		
		for (int i = n/2; i < n; i++) {
			for (int j = 0; j < m/2; j++) {
				b[i][j+m/2]=a[i][j];
			}
		}
		
		for (int i = n/2; i < n; i++) {
			for (int j = m/2; j < m; j++) {
				b[i-n/2][j]=a[i][j];
			}
		}
		
		for (int i = 0; i < n/2; i++) {
			for (int j = m/2; j < m; j++) {
				b[i][j-m/2]=a[i][j];
			}
		}
		
		
		
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();
		
		st = new StringTokenizer(br.readLine());
		n=Integer.parseInt(st.nextToken());
		m=Integer.parseInt(st.nextToken());
		r=Integer.parseInt(st.nextToken());
		a=new int[2000][2000];
		b=new int[2000][2000];
		
		for (int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < m; j++) {
				a[i][j]=Integer.parseInt(st.nextToken());
			}
		}
		
		st = new StringTokenizer(br.readLine());
		
		//int flag=0;
		
		for(int i=0;i<r;i++) {
			type=Integer.parseInt(st.nextToken());
			if(type==1) {
				one();
			}
			else if(type==2) {
				two();
			}
			else if(type==3) {
				three();
				int temp=m;
				m=n;
				n=temp;
			}
			else if(type==4) {
				four();
				int temp=m;
				m=n;
				n=temp;
			}
			else if(type==5) {
				five();
			}
			else if(type==6) {
				six();
			}

			copyOrignal();
		}
		
		if(flag==0) {
			for (int i = 0; i < n; i++) {
				for (int j = 0; j < m; j++) {
					sb.append(a[i][j]).append(" ");
				}
				sb.append("\n");
			}
		}else if(flag==1) {
			for (int i = 0; i < m; i++) {
				for (int j = 0; j < n; j++) {
					sb.append(b[i][j]).append(" ");
				}
				sb.append("\n");
			}
		}
		
		System.out.println(sb);
	}

}
