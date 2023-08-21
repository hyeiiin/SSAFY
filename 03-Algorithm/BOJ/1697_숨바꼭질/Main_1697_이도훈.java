import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Main_1697_이도훈 {

    static int N;
    static int K;
    static int answer;

    public static void main(String args[]) throws Exception {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());


        int[] dp = new int[100001];
        Arrays.fill(dp, Integer.MAX_VALUE);


        Queue<Node> queue = new ArrayDeque<>();

        queue.add(new Node(N, 0));

        answer = Math.abs(N - K);

        while (!queue.isEmpty()) {
            Node cur = queue.poll();

            dp[cur.pos] = Math.min(dp[cur.pos], cur.cnt);

            int teleport = cur.pos * 2;
            int forward = cur.pos + 1;
            int back = cur.pos - 1;

            int newCnt = cur.cnt + 1;
            if(teleport <= 100000 && dp[teleport] > newCnt){
                queue.add(new Node(teleport , newCnt));
            }
            if(forward <= 100000 && dp[forward] > newCnt){
                queue.add(new Node(forward, newCnt));
            }
            if(back >= 0 && dp[back] > newCnt){
                queue.add(new Node(back, newCnt));
            }
        }

        System.out.println(dp[K]);
    }

    static class Node {
        int pos;
        int cnt;

        public Node(int pos, int cnt) {
            this.pos = pos;
            this.cnt = cnt;
        }
    }


}

