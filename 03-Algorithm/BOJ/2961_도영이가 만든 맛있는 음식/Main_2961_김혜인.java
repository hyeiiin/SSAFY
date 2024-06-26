package 코테연습;
import java.util.*;
import java.io.*;

public class Main_2961_김혜인 {

    static int N;
    static int[] taste1; // 신맛을 담은 배열
    static int[] taste2; // 쓴맛을 담은 배열
    static int[] output1; // 신맛의 재료 조합 결과
    static int[] output2; // 쓴맛의 재료 조합 결과
    static int minResult = Integer.MAX_VALUE; // 신맛과 쓴맛의 차이가 가장 작은 요리의 차이 결과값

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        taste1 = new int[N];
        taste2 = new int[N];
        output1 = new int[N];
        output2 = new int[N];

        // 각 재료의 신맛과 쓴맛을 배열에 저장
        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            taste1[i] = Integer.parseInt(st.nextToken()); // 신맛 입력 받음
            taste2[i] = Integer.parseInt(st.nextToken()); // 쓴맛 입력 받음
        }

        // 완전탐색을 이용하여 모든 조합 뽑아내기
        for (int i = 0; i < N; i++) {
            backTracking(0, i + 1, 0);
        }

        // 신맛과 쓴맛의 차이가 가장 작은 요리의 결과값 출력
        System.out.println(minResult);
    }

    // 백트래킹을 이용한 조합 메서드
    public static void backTracking(int depth, int count, int idx) {
        // 깊이가 해당 조합 개수만큼 도달했으면 (즉, 조합 개수를 충족하면)
        if (depth == count) {
            int result1 = 1; // 신맛 재료 섞어서 나오는 결과 값
            int result2 = 0; // 쓴맛 재료 섞어서 나오는 결과 값

            // 해당 조합 개수까지 돌려줌
            for (int i = 0; i < count; i++) {
                result1 *= output1[i]; // 신맛 조합은 곱
                result2 += output2[i]; // 쓴맛 조합은 합
            }

            // 신맛과 쓴맛의 차이가 가장 작은 요리의 결과값을 갱신해줌
            minResult = Math.min(minResult, Math.abs(result1 - result2));
            return; // 메서드 종료
        }

        // 재귀 호출을 이용하여 재료들의 조합 구해줌
        for (int i = idx; i < N; i++) {
            output1[depth] = taste1[i]; // 신맛 배열에서 해당 인덱스의 재료를 선택
            output2[depth] = taste2[i]; // 쓴맛 배열에서 해당 인덱스의 재료를 선택
            backTracking(depth + 1, count, i + 1); // 다음 재료 선택을 위해 재귀 호출
        }
    }

}

