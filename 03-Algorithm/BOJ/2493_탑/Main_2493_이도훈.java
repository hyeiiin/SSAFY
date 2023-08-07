import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_2493_이도훈 {

    static int N;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());

        StringTokenizer st = new StringTokenizer(br.readLine());
        int[] towers = new int[N + 1];
        for (int i = 1; i <= N; i++) {
            towers[i] = Integer.parseInt(st.nextToken());
        }

        int[] answer = new int[N + 1];

        // 왼쪽으로 탐색하다가 자기보다 큰애가 나오면 그 녀석이 수신
        // 자기보다 작은애가 나오면 그 녀석이 수신하고 있는 타워 인덱스부터 탐색
        for (int i = 1; i <= N; i++) {
            int left = i-1;

            while (left > 0) {
                if (towers[i] <= towers[left]) {
                    answer[i] = left;
                    break;
                } else {
                    left = answer[left];
                }
            }

        }

        StringBuilder sb = new StringBuilder();
        for (int i = 1; i <= N; i++) {
            sb.append(answer[i]).append(" ");
        }
        System.out.println(sb);

    }
}
