import java.io.*;
import java.util.PriorityQueue;

public class Main_11286_이도훈 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());

        PriorityQueue<Integer> pq = new PriorityQueue<>((o1, o2) -> {

            int l = Math.abs(o1);
            int r = Math.abs(o2);
            if (l == r) {
                return Integer.compare(o1, o2);
            }
            return Integer.compare(l, r);
        });

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < N; i++) {
            int num = Integer.parseInt(br.readLine());
            if (num == 0) {
                if (pq.isEmpty()) {
                    sb.append('0');
                } else {
                    sb.append(pq.poll());
                }
                sb.append("\n");
            } else {
                pq.add(num);
            }
        }
        System.out.println(sb);
    }
}
