import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Solution {
    static int N, R, C;
    static int ans;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        int size = (int) Math.pow(2, N); //8
        solve(0, 0, size);
    }

    public static void solve(int r, int c, int blockSize) {
        if (blockSize == 1) {
            System.out.println(ans);
            return;
        }
        int newSize = blockSize / 2;
        if (R < r + newSize && C < c + newSize) { //1사분면
            solve(r, c, newSize);
        } else if (R < r + newSize && C >= c + newSize) { //2사분면
            ans += ((blockSize * blockSize) / 4) * 1;
            solve(r, c + newSize, newSize);
        } else if (R >= r + newSize && C < c + newSize) { //3사분면
            ans += ((blockSize * blockSize) / 4) * 2;
            solve(r + newSize, c, newSize);
        } else if (R >= r + newSize && C >= c + newSize) { //4사분면
            ans += ((blockSize * blockSize) / 4) * 3;
            solve(r + newSize, c + newSize, newSize);
        }
    }
}
