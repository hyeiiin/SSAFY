
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int N, M;
    static int[] parents;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int T = Integer.parseInt(br.readLine());

        for (int t = 0; t < T; t++) {
            sb.append("#" + (t + 1) + " ");
            StringTokenizer st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken()); //N개
            M = Integer.parseInt(st.nextToken()); //M개의 연산
            parents = new int[N + 1];

            for (int i = 1; i <= N; i++) {
                parents[i] = i;
            }
            for (int i = 0; i < M; i++) {
                st = new StringTokenizer(br.readLine());
                int type = Integer.parseInt(st.nextToken()); //1이면 같은 집합인지 확인, 0이면 합집합
                int a = Integer.parseInt(st.nextToken());
                int b = Integer.parseInt(st.nextToken());

                if (type == 0) { //유니온
                    union(a, b);
                } else if (type == 1) { //파인드
                    int pA = find(a);
                    int pB = find(b);
                    if (pA == pB) {
                        sb.append(1);
                    }
                    else
                        sb.append(0);
                }
            }
            sb.append("\n");
        }
        System.out.println(sb);
    }

    public static int find(int x) {
        if (x != parents[x]) {
            return parents[x] = find(parents[x]);
        }
        return parents[x];
    }

    public static void union(int a, int b) {
        int pA = find(a);
        int pB = find(b);

        if (pA != pB) {
            parents[pB] = pA;
        }
    }

}
