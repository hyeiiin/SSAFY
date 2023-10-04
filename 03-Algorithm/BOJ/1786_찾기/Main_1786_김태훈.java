package baekjoon;
import java.util.*;
import java.io.*;
public class Main_1786_김태훈 {
	
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder sb = new StringBuilder();

	static int[] makepi(String pattern) {
		int n = pattern.length();
		int[] pi = new int[n];
		int j = 0;
		for (int i = 1; i < n; i++) {
			
			if(pattern.charAt(i) == pattern.charAt(j)) {
				j++;
				pi[i] = j;
			}
			else {
				if(j != 0) {
					j = pi[j-1];
					i--;
				}
			}
		}
		
		return pi;
	}
	
	static void KMP(String T, String P) {
        int[] pi = makepi(P);
        int n1 = T.length();
        int n2 = P.length();
        int j = 0;
        
        List<Integer> list = new ArrayList<>();

        for (int i = 0; i < n1; i++) {
        	
        	if(T.charAt(i) == P.charAt(j)) {
        		if(j == n2-1) {
        			list.add((i-n2+1)+1);
        			j = pi[j];
        		}
        		else {
        			j++;
        		}
        	}
        	else {
        		if(j != 0) {
        			j = pi[j-1];
        			i--;
        		}
        	}
        }

        sb.append(list.size()).append("\n");

        for(int pos : list)
            sb.append(pos).append(" ");
    }

    public static void main(String[] args) throws IOException{
       String T = br.readLine();
       String P = br.readLine();

       KMP(T, P);

       System.out.println(sb); 
   }
}
