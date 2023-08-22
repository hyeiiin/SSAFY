

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main_10026_�̼��� {

    private static char[][] arr;
    private static int n, cnt = 0, ans1=0, ans2=0;
    private static boolean[][] visited;
    private static int[] moveR = {-1, 1, 0, 0};
    private static int[] moveC = {0, 0, -1, 1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        n = Integer.parseInt(br.readLine());

        arr = new char[n][n];
        visited = new boolean[n][n];

        //�迭 �Է� �ޱ�
        for (int i = 0; i < n; i++) {
            arr[i] = br.readLine().toCharArray();
        }

        //���� ���� ���. ���Ŀ� R�� G�� �ٲ㼭 ���ϻ��� ���
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if(!visited[i][j]){
                    dfs(i, j, arr[i][j]);
                    ans1++;
                } 
                
                //G�� R�� �ϳ��� ���ڷ� ���� �����ֱ⹮�� �ϳ��� �ٲ��ֱ�
                if(arr[i][j] == 'G')
                    arr[i][j] = 'R';
            }
        }
        
        //�ٽ��ѹ� dfs ���� ���� �湮�迭 �ʱ�ȭ
        visited = new boolean[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if(!visited[i][j]){
                    dfs(i, j, arr[i][j]);
                    ans2++;
                }
            }
        }

        System.out.println(ans1 + " " + ans2);

    }

    private static void dfs(int i, int j, char ch){
        visited[i][j] = true;

        for (int k = 0; k < 4; k++) {
            int newR = i + moveR[k];
            int newC = j + moveC[k];

            if (newR >= 0 && newC >= 0 && newR < n && newC < n && !visited[newR][newC]) {
                if(arr[newR][newC] == ch){
                    visited[newR][newC] = true;
                    dfs(newR, newC, ch);
                }

                }
            }
    }
}

