import java.util.Scanner;

public class Main_12891_김나연 {
	
	static int s, p,a,c,g,t, aa,cc,gg,tt, res;
	static String dna;
	static int key[];
	
	public static void main(String[] args) {
		
		Scanner in = new Scanner(System.in);
		
		s=in.nextInt();
		p=in.nextInt();
		
		dna=in.next();
		
		a=in.nextInt();
		c=in.nextInt();
		g=in.nextInt();
		t=in.nextInt();
		
		for (int i = 0; i < p; i++) {
			if(dna.charAt(i)=='A')aa++;
			if(dna.charAt(i)=='C')cc++;
			if(dna.charAt(i)=='G')gg++;
			if(dna.charAt(i)=='T')tt++;
		}
		
		if(aa>=a&&cc>=c&&gg>=g&&tt>=t) res++;
		
		int cnt=0;
		
		for (int i = p; i < s; i++) {
			if(dna.charAt(i)=='A')aa++;
			if(dna.charAt(i)=='C')cc++;
			if(dna.charAt(i)=='G')gg++;
			if(dna.charAt(i)=='T')tt++;
			
			if(dna.charAt(cnt)=='A')aa--;
			else if(dna.charAt(cnt)=='C')cc--;
			else if(dna.charAt(cnt)=='G')gg--;
			else if(dna.charAt(cnt)=='T')tt--;
			
			if(aa>=a&&cc>=c&&gg>=g&&tt>=t) res++;
			
			cnt++;
		}
		
		System.out.println(res);
	}
}
