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

            // Queue로 사용할 ArrayDeque
            ArrayDeque<Integer> queue = new ArrayDeque<>();

            st = new StringTokenizer(br.readLine());
            // 입력 값을 Queue에 순서대로 저장
            for (int i = 0; i < 8; i++) {
                queue.addLast(Integer.parseInt(st.nextToken()));
            }

            // 감소 숫자 카운트
            int cnt = 1;

            while (true) {
                // 가장 앞에서 하나 가져오기
                Integer cur = queue.pollFirst();
                // 감소 숫자가 5가 넘으면 사이클이 한번 돈 것이기 때문에 1로 변경
                if (cnt > 5) cnt = 1;
                // 감소 숫자 만큼 뺄 때, 0 이하이면 종료 조건 달성
                if (cur - cnt <= 0) {
                    queue.add(0);  // 큐에 0 추가 하고 마무리
                    break;
                }
                queue.addLast(cur - cnt++); // 감소 숫자 만큼 빼고 큐에 넣기
            }

            // 출력문
            sb.append("#").append(test_case).append(" ");
            while (!queue.isEmpty()) {
                sb.append(queue.poll()).append(" ");
            }
            sb.append("\n");
        }
        System.out.println(sb);
    }
}
