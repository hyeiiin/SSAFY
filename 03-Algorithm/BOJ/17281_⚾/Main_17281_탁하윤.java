package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_17281_탁하윤 {
    static int N, order[], predict[][];
    static boolean visited[];
    static int max;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = null;

        N = Integer.parseInt(br.readLine());    // 이닝 수

        order = new int[10];    // 순서 순열로 뽑기
        visited = new boolean[10];  // 방문처리
        predict = new int[N+1][10]; // 선수들의 이닝마다 얻는 결과
        max = Integer.MIN_VALUE;    // 점수 최대값

        for (int i = 1; i <= N; i++) {  // 각 선수가 각 이닝에서 얻는 결과
            st = new StringTokenizer(br.readLine());
            for (int j = 1; j <= 9; j++) {
                predict[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        
        // 4번 타자는 항상 1번 선수
        order[4] = 1;
        visited[4] = true;
        perm(2);    // 순서 뽑기, 1번 선수는 뽑혔기 때문에 2번부터 시작

        System.out.println(max);
    }

    static void perm(int cnt) {    // 순서 정하기
        if(cnt == 10){   // BC
            playball(); // 야구 게임 시작
            return;
        }
        for (int i=1; i<=9; i++){
            if(visited[i]) continue;    // 방문한적 있으면 다음 스텝
            visited[i] = true;
            order[i] = cnt; // 순서 뽑기
            perm(cnt+1);
            visited[i] = false; // 방문한적 있으면 원위치
        }

    }

    static void playball() {
        int score = 0;  // 이번 게임 점수
        int start = 1;  // 시작 순서
        int base[]; // 각 base 상태

        for (int i = 1; i < N+1 ; i++) {    // 1이닝~N이닝 까지
            int out = 0;    // out 수
            base = new int[4];  // base 상태 초기화

            while (true) {  // out < 3인 동안
                for (int j = start; j <= 9; j++) {  // 각 선수 점수
                    int game = predict[i][order[j]];
                    switch (game) {
                        case 0: // out인 경우
                            out++;
                            break;
                        case 1: // 안타
                            if(base[3] == 1){   // 3루 -> 홈 득점
                                score++;
                                base[3] = 0;    // 3루 사람 빠졌으니 0으로
                            }
                            if(base[2] == 1){   // 2루 -> 3루
                                base[3] = 1;
                                base[2] = 0;
                            }
                            if(base[1] == 1){   // 1루 -> 2루
                                base[2] = 1;
                                base[1] = 0;
                            }
                            base[1] = 1;    // 홈 -> 1루
                            break;
                        case 2: // 2루타
                            if(base[3] == 1){   // 3루 -> 홈 득점
                                score++;
                                base[3] = 0;
                            }
                            if(base[2] == 1){   // 2루 -> 홈 득점
                                score++;
                                base[2] = 0;
                            }
                            if(base[1] == 1){   // 1루 -> 3루
                                base[3] = 1;
                                base[1] = 0;
                            }
                            base[2] = 1;    // 홈 -> 2루
                            break;
                        case 3: // 3루타
                            if(base[3] == 1){   // 3루 -> 홈 득점
                                score++;
                            }
                            if(base[2] == 1){   // 2루 -> 홈 득점
                                score++;
                                base[2] = 0;
                            }
                            if(base[1] == 1){   // 1루 -> 홈 득점
                                score++;
                                base[1] = 0;
                            }
                            base[3] = 1;    // 홈 -> 3루
                            break;
                        case 4: // 홈런
                            for (int k = 1; k <= 3; k++) {  // 1~3루 -> 홈 득점
                                if(base[k] == 1){
                                    score++;
                                    base[k] = 0;
                                }
                            }
                            score++;    // 타자(홈) -> 홈 득점
                            break;
                    }
                    if (out == 3) { // out이 3개라면 for문 탈출
                        start = j + 1;  // 시작 위치는 out 한 사람 다음
                        if (start >= 10) start = 1; // 만약 시작 위치가 10보다 크다면 다시 1번부터
                        break;
                    }
                }
                if(out == 3) break; // out이 3개라면 while문 탈출
                start = 1;  // 탈출안한 상황이라면, 다시 1번부터 for문 돌기
            }
        }
        max = Math.max(max, score); // 뽑은 순서중 득점 최고점 저장
    }

}