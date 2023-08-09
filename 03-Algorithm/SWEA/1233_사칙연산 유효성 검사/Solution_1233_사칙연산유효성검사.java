package swea;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
// 해당 노드에 자식 노드가 있으면, 리프노드가 아니므로 인덱스는 전체 배열 크기의 N의 절반보다 작아야 함.
// 즉, N/2 보다 작거나 같아야한다.
// 리프노드인 경우에는 정점의 번호가 N/2보다 커야한다. 
// 리프노드에는 숫자만 들어올 수 있고, 리프노드가 아닐때만 연산자가 들어갈 수 있다  라는 성질 이용.

public class Solution_1233_사칙연산유효성검사 {
    public static int N;
    public static String[] tree;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder("");
        StringTokenizer st;

        for (int tc = 1; tc <= 10; ++tc) {
            N = Integer.parseInt(br.readLine());

            tree = new String[N + 1];
            int answer = 1; //유효성 여부 - 유효하다.

            for (int i = 0; i < N; ++i) {
                st = new StringTokenizer(br.readLine());
                tree[Integer.parseInt(st.nextToken())] = st.nextToken(); //정점 번호와 값만 받음.
            }

            int nodeIdx = N / 2; //유효성 판단의 기준 셋팅
            for (int i = 1; i < N + 1; ++i) {
            	// '0'~'9' 문자형태로 저장이 되어 있음
                if (tree[i].charAt(0) >= '0' && tree[i].charAt(0) <= '9') {
                    if (i <= nodeIdx) { //정점의 번호가 N/2보다 작거나 같은지? --> 리프노드 X, 숫자가 올 수 없음
                        answer = 0; // 유효하지 않음 처리.
                        break;
                    }
                }
                else { //연산자가 왔을때.
                    if (i > nodeIdx) { //정점의 번호가 N/2 크면 리프노드, 연산자 올 수 없음 
                        answer = 0; //유효하지 않음.
                        break;
                    }
                }
            }

            sb.append("#").append(tc).append(" ").append(answer).append("\n");
        }

        System.out.println(sb);
    }
}

