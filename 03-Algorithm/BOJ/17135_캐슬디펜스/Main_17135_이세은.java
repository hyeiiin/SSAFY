/*
 * 백준_17135_캐슬디펜스
 */
package ssafyStudy.BOJ;

import java.io.*;
import java.util.*;

public class Main_17135_이세은 {

    private static int[][] field, initField; // 격자판, 초기화 위한 복사 격자
    private static List<Integer> achList;
    private static int n, m, d, max = 0;
    private static boolean[][] visited;
    private static boolean[] visitedC;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken()); // 행
        m = Integer.parseInt(st.nextToken()); // 열
        d = Integer.parseInt(st.nextToken()); // 궁수 공격 거리

        field = new int[n + 1][m]; // 궁수 위치할 행 하나 더 생성
        initField = new int[n + 1][m]; //초기화 배열
        visitedC = new boolean[m];


        achList = new ArrayList<>();
        // 격자판 저장
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < m; j++) {
                field[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        //턴마다 초기화 해주기 위한 배열 복사
        for (int i = 0; i <= n; i++) {
            for (int j = 0; j < m; j++) {
                initField[i][j] = field[i][j];
            }
        }
        // 조합으로 3개의 열 뽑기, 각 열에서 시작해서 제한 거리에 충족되는 적이 있다면 카운트
        comb(0, 0); // 궁수가 위치하는 위치 3개 뽑기
        System.out.println(max);
    }

    // D=1일 때 궁수는 앞의 표적밖에 맞추지 못함
    public static void fire(List<Integer> list) {
        int originN = n, cnt = 0;

        //n번 반복
        for (int turn = 0; turn < originN; turn++) {
            visited = new boolean[n][m];
            // 전체 격자 탐색 한 줄 씩 올려가며 탐색
            for (int k = 0; k < 3; k++) {
                int min = Integer.MAX_VALUE;
                int minX = Integer.MAX_VALUE, minY = Integer.MAX_VALUE;
                for (int i = 0; i < n; i++) {
                    for (int j = 0; j < m; j++) {
                        if (field[i][j] == 1) { // 적이 있는 위치라면
                            int val = checkDis(i, j, n, list.get(k));
                            if (min > val) { // 격자의 위치와 궁수들의 위치 거리 계산
                                // 거리 최솟값 가지는 좌표, 거리 저장
                                min = val;
                                minX = i;
                                minY = j;
                            } else if (min == val) { //최소길이와 같은 길이일 경우 더 왼쪽인 좌표를 타깃으로
                                if (minY > j) {
                                    minX = i;
                                    minY = j;
                                }
                            }
                        }
                    }
                }
                if (min <= d) { // 가장 가까운 적이 d일 때 여러 궁수에게 동시에 타격 받을 수 있으므로 먼저 true처리
                    visited[minX][minY] = true;
                }
            }

            for (int i = 0; i < n; i++) {
                for (int j = 0; j < m; j++) {
                    if (visited[i][j]) {
                        field[i][j] = 0; //적 제거
                        cnt++; //적 카운트
                    }
                }
            }

            n--; //한 행씩 줄여가면서 다음 줄 탐색
        }

        //다음 턴을 위한 원복
        n = originN; //n 원복
        //격자 원복
        for (int i = 0; i <= n; i++) {
            for (int j = 0; j < m; j++) {
                field[i][j] = initField[i][j];
            }
        }
        max = Math.max(max, cnt);
    }

    private static int checkDis(int x1, int y1, int x2, int y2) {
        return (Math.abs(x1 - x2) + Math.abs(y1 - y2));
    }

    private static void comb(int cnt, int idx) {
        if (cnt == 3) { // 궁수 3명의 위치 다 뽑았을 경우

            achList = new ArrayList<>(); //늘 저장되는 궁수 위치 초기화

            for (int i = 0; i < m; i++) {
                if (visitedC[i]) {
                    achList.add(i);
                }
            }
            fire(achList);
            return;
        }

        for (int i = idx; i < m; i++) {
            if (!visitedC[i]) {
                visitedC[i] = true;
                comb(cnt + 1, idx + 1);
                visitedC[i] = false;
            }
        }
    }
}