/*
    n, L = map(int, input().split())

    h = list(map(int, input().split()))

    h.sort()

    for i in h:
        if i <= L:
            L += 1

    print(L)
*/
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
	
	static int N, L;
	static int[] H;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		L = Integer.parseInt(st.nextToken());
		
		st = new StringTokenizer(br.readLine());
		H = new int[N];
		for (int i = 0; i < N; i++) {
			H[i] = Integer.parseInt(st.nextToken());
		}
		
		Arrays.sort(H);
		
		for (int h : H) {
			if (h <= L) {
				L++;
			}
		}
		
		System.out.println(L);
		
	}
}
