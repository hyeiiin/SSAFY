import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class Main {
	static int n;
	static List<Integer> sosu = new ArrayList<>();	
	
	static void com(int len) {
		
		if(sosu.size() > 1) {
			int temp = 0;
			for(int j = 0; j < sosu.size() ; j++) {
				temp = temp * 10 + sosu.get(j);
			}
			for(int j = 2; j*j <= temp; j++) {
				if(temp % j == 0)return;
			}
		}
		
		if(len == n) {
			StringBuilder sb = new StringBuilder();
			for(int i = 0; i < n; i++) {
				sb.append(sosu.get(i));
			}
			System.out.println(sb.toString());
			return;
		}
		
		
		for(int i = 1; i < 10;i++) {
			if(sosu.size() == 0) {
				if(i != 2 && i != 3 && i != 5 && i != 7)continue;
			}
			sosu.add(i);
			com(len+1);
			sosu.remove(sosu.size()-1);
		}
	}
	
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		n = Integer.parseInt(bf.readLine());
		
		com(0);
	}

}