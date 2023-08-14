import java.io.*;
import java.util.*;
public class Main {
	public static int N,R,C;
	public static int count=0;
	public static int answer=-1;
	public static void main(String[] args) throws IOException {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st=new StringTokenizer(br.readLine());
		N=Integer.parseInt(st.nextToken());
		R=Integer.parseInt(st.nextToken());
		C=Integer.parseInt(st.nextToken());
		int width=(int)Math.pow(2, N);
		int r=0,c=0,n=width;
		while(r!=R||c!=C) {
			if(R<r+n/2&&C>=c+n/2) {
				count+=(n/2)*(n/2);
				c+=n/2;
			}
			//3사분면에 있다면
			else if(R>=r+n/2&&C<c+n/2) {
				count+=2*(n/2)*(n/2);
				r+=n/2;
			}
			//4사분면에 있다면
			else if(R>=r+n/2&&C>=c+n/2) {
				count+=3*(n/2)*(n/2);
				r+=n/2;
				c+=n/2;
			}
			n/=2;
			N--;
		}
		System.out.println(count);
	}
}
