package algorithm.boj;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

/**
 * https://www.acmicpc.net/problem/12891
 * DNA 비밀번호
 * @author SSAFY
 *
 */
public class Main_12891_박정인 {
	static int S, P;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		S = Integer.parseInt(st.nextToken());
		P = Integer.parseInt(st.nextToken());
		
		String str = br.readLine();
		
		int cnt = 0;
		char[] arr = {'A', 'C', 'G', 'T'};
		Map<Character, Integer> map = new HashMap<>();
		st = new StringTokenizer(br.readLine());				
		
		for (char c : arr) {
			map.put(c, Integer.parseInt(st.nextToken()));
		}
		
		for (int i = 0; i < P; i++) {
			char ch = str.charAt(i);
			map.put(ch, map.get(ch)-1);
		}
		
		if (check(map)) {
			cnt++;
		}
		
		for (int i = 0; i < S - P; i++) {
			char remove = str.charAt(i);			
			char add = str.charAt(i + P);
			
			map.put(remove, map.get(remove) + 1);
			map.put(add, map.get(add) - 1);			
			
			if (check(map)) {
				cnt++;
			}
		}
		
		System.out.println(cnt);
	}
	
	private static boolean check(Map<Character, Integer> map) {
		for (Integer n : map.values()) {
			
			if (n > 0) {
				return false;
			}
		}
		
		return true;
	}
}
