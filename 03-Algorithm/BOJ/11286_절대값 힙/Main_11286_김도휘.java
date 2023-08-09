import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        PriorityQueue<Integer> pqueue = new PriorityQueue<>(new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                int result = Math.abs(o1) - Math.abs(o2);
                if (result > 0) {
                    return 1;
                } else if (result == 0) {
                    return o1 - o2;
                }
                else {
                    return -1;
                }
            }
        });
        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < N; i++) {
            int n = Integer.parseInt(br.readLine());

            if (n == 0) { //배열에서 절댓값이 가장 작은 값 출력 후 제거
                if (!pqueue.isEmpty()) {
                    sb.append(pqueue.poll() + "\n");
                }
                else {
                    sb.append(0 + "\n");
                }

            } else if (n != 0) { //배열에서 x값 추가
                pqueue.add(n);

            }
        }
        System.out.println(sb);

    }

}
