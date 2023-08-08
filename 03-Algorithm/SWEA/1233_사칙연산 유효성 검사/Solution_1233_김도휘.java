import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Solution {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        for (int i = 1; i <= 10; i++) {
            int N = Integer.parseInt(br.readLine()); //트리가 갖는 정점의 개수
            char[] arr = new char[N + 1];
            boolean isPossible = true;

            for (int j = 1; j <= N; j++) {
                String str = br.readLine();
                StringTokenizer st = new StringTokenizer(str);
                int num = Integer.parseInt(st.nextToken());
                String value = st.nextToken();
                arr[j] = value.charAt(0);

            }
            for (int j = N; j >= 3; j -= 2) { //오른쪽노드 기준
                int root = j / 2;
                int leftNode = j - 1;
                int rightNode = j;

                //루트 노드가 연산자가 아니라면
                if (arr[root] != '+' && arr[root] != '-' && arr[root] != '*' && arr[root] != '/') {
                    isPossible = false;
                }
            }
            StringBuilder sb = new StringBuilder();
            sb.append("#" + i + " ");
            if (isPossible) {
                sb.append(1);
            }
            else {
                sb.append(0);
            }
            System.out.println(sb);

        }
    }
}
