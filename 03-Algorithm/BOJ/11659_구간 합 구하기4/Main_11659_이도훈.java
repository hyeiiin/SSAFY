import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main_11659_이도훈 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());

        // 입력값이 1부터 들어오므로 편하게 계산하려고 +1만큼 사이즈 추가
        int[] arr = new int[N+1];
        arr[1] = Integer.parseInt(st.nextToken());
        for (int i = 2; i <= N; i++) {
            arr[i] = arr[i - 1] + Integer.parseInt(st.nextToken());
        }

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            sb.append(arr[end] - arr[start-1]) // 누적합 되어있으므로 시작 이전부터 빼주면 가능
                    .append("\n");
        }
        System.out.println(sb);
    }
}
