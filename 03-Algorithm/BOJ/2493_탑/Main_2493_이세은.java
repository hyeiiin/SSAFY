package ssafyStudy.BOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Stack;
import java.util.StringTokenizer;

public class Main_2493_이세은 {

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine()); // 탑의 수

        // 탑 번호,높이 저장 배열 생성
        Tower[] towers = new Tower[n + 1];

        StringTokenizer st = new StringTokenizer(br.readLine()); // 탑 높이 입력받기

        towers[0] = new Tower(0,0);
        for (int i = 1; i <= n; i++) { // 탑들 정보 저장
            towers[i] = new Tower(i, Integer.parseInt(st.nextToken()));
        }
        Stack<Tower> stack = new Stack<>();
        int i=1;
        StringBuilder sb = new StringBuilder();
        while(i<=n) {
            while (!stack.isEmpty()) { // stack 찼다면 높이 비교하면서 pop 진행
                //이전값보다 작은 값 나왔다면 바로 이전값이 수신지
                if (towers[i].height <= stack.peek().height) {
                    sb.append(stack.peek().no).append(" ");
                    break;
                }
                stack.pop(); //이전값 pop
            }
            //stack 비는 경우 -> 이전에 더 높은 탑 없음. 수신지 X
            if (stack.isEmpty())
                sb.append("0 ");

            stack.push(towers[i]); //먼저 탑 푸시
            i++; //다음 탑 주목
        }

        System.out.println(sb.toString());
    }

    static class Tower {
        int no;
        int height;

        public Tower(int no, int height) {
            super();
            this.no = no;
            this.height = height;
        }

    }
}