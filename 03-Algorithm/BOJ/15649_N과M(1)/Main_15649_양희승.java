import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	
	public static void permutation(int[] arr, int[] result, boolean[] visited, int depth, int n, int m) {
		// if depth == r : sysout 찍고 return
		if (depth == m) {
			for (int i=0; i<m; i++) {
				System.out.print(result[i] + " ");
			}
			System.out.println();
			return;
		}
		// visted tf check > result depth = arr i > recur > visited false
		for (int i=0; i<n; i++) {
			if (visited[i] == false) {
				result[depth] = arr[i];
				visited[i] = true;
				permutation(arr, result, visited, depth+1, n, m);
				visited[i] = false;
			}	
		}	
	}
	public static void main(String[] args) throws IOException {
//		자연수 n, m이 주어짐
//		1~n 까지 자연수 중 중복없이 m개를 고른 수열을 출력
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String str = br.readLine();
		StringTokenizer st = new StringTokenizer(str);
		int n = Integer.parseInt(st.nextToken());
		int m = Integer.parseInt(st.nextToken());
		
		// n개 숫자 담긴 배열
		int[] arr = new int[n];
		for (int i=0; i<n; i++) {
			arr[i] = i+1;
		}
		
		int[] result = new int[m];
		boolean[] visited = new boolean[n];
		
		permutation(arr, result, visited, 0, n, m);
	}
}
