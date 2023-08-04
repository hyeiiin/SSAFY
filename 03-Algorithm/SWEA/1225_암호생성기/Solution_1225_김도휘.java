import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Solution {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        for (int T = 0; T < 10; T++) {
            int num = Integer.parseInt(br.readLine());
            Queue<Integer> queue = new ArrayDeque<>();
            StringTokenizer st = new StringTokenizer(br.readLine());
            int reduce = 1;

            for (int i = 0; i < 8; i++) {
                queue.add(Integer.parseInt(st.nextToken()));
            }
            while (!queue.isEmpty()) {
                int now = queue.poll();
                if (now > 0) {
                    if (reduce > 5) {
                        reduce = 1;
                    }
                    int newValue = now-reduce;
                    if (newValue < 0) {
                        newValue = 0;
                    }
                    queue.add(newValue);
                    if ((newValue) == 0) {
                        break;
                    }
                    reduce++;
                }

            }
            StringBuilder sb = new StringBuilder();
            sb.append("#" + (T + 1) + " ");
            while (!queue.isEmpty()) {
                sb.append(queue.poll() + " ");
            }
            System.out.println(sb);
        }
    }
}
