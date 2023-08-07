aimport java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main_1158_이도훈 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        Queue<Integer> queue = new ArrayDeque<>();
        for (int i = 1; i <= N; i++) {
            queue.add(i);
        }

        StringBuilder sb = new StringBuilder();
        sb.append("<");
        int cnt = 0;
        while (queue.size() != 1) {
            Integer cur = queue.poll();
            cnt++;
            if (cnt == K) {
                cnt = 0;
                sb.append(cur).append(", ");
                continue;
            }
            queue.add(cur);
        }

        sb.append(queue.poll()).append(">");
        System.out.println(sb);


    }
}
