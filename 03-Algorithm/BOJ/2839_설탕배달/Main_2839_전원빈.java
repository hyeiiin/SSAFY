import java.io.*;
import java.util.*;

public class Main {
	static int sugar;
	static int o;
	static int s;
	static int useless;
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		sugar = Integer.parseInt(bf.readLine());
		while(true) {
			int temp = sugar;
			o = temp / 5 - useless;
			temp -= o*5;
			if(temp%3 != 0) {
				if(o == 0) {
					s = -1;
					break;
				}else {					
					useless++;
					continue;
				}
			}
			s = temp/3;
			break;
		}
		StringBuilder sb = new StringBuilder();
		sb.append(o + s);
		System.out.println(sb.toString());
	}

}