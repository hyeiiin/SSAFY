import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Solution {
    static int[] arr;
    static int[] ans;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int T = Integer.parseInt(br.readLine());

        for (int t = 0; t < T; t++) {
            int N = Integer.parseInt(br.readLine());
            arr = new int[N];
            ans = new int[N];
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int i = 0; i < N; i++) {
                arr[i] = Integer.parseInt(st.nextToken());
            }
            ans[0] = arr[0];

            for (int i = 1; i < N; i++) {
                int fix = arr[i]; //기존 배열
                boolean isPossible = true;

                for (int j = 0; j < i; j++) {
                    //정답 배열에 담겼던 값들이랑 비교
                    if (fix < ans[j]) { //ans값과 arr배열 바꾸기
                        isPossible = false;
                        ans[j] = arr[i];
                        break;
                    }
                }
                if (isPossible) {
                    ans[i] = fix;
                }

            }
            int cnt = 0;
            for (int i = 0; i < N; i++) {
                if (ans[i] != 0) {
                   cnt++;
                }
            }
            sb.append("#").append(t + 1).append(" ");
            sb.append(cnt);
            sb.append("\n");

        }
        System.out.println(sb);
    }

}
