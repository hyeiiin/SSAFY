import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.StringTokenizer;

class Solution_1225_이도훈 {
    public static void main(String args[]) throws Exception {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st;
        StringBuilder sb = new StringBuilder();
        for (int test_case = 1; test_case <= 10; test_case++) {
            br.readLine();

            ArrayDeque<Integer> queue = new ArrayDeque<>();

            st = new StringTokenizer(br.readLine());
            for (int i = 0; i < 8; i++) {
                queue.addLast(Integer.parseInt(st.nextToken()));
            }

            int cnt = 1;

            while (true) {
                Integer cur = queue.pollFirst();
                if (cnt > 5) cnt = 1;
                if (cur - cnt <= 0) {
                    queue.add(0);
                    break;
                }
                queue.addLast(cur - cnt++);
            }

            sb.append("#").append(test_case).append(" ");
            while (!queue.isEmpty()) {
                sb.append(queue.poll()).append(" ");
            }
            sb.append("\n");
        }
        System.out.println(sb);
    }
}
