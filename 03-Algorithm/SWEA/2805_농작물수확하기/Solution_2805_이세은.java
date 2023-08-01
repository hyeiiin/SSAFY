package ssafyStudy.SWEA;

import java.io.*;
import java.util.StringTokenizer;

public class Solution_2805_이세은 {

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();

        int T = Integer.parseInt(br.readLine());
        for(int test_case=1; test_case<=T; test_case++){

            int n = Integer.parseInt(br.readLine());
            int[][] soil = new int[n][n];

            //농장물 가치 받기
            for(int i=0; i<n; i++){
                String str = br.readLine();
                for(int j=0; j<n; j++){
                    soil[i][j] = str.charAt(j)-'0';
                }
            }

            //첫행에 1, 두번째행 3, 세번째행 5..n까지 n-2...5..3..1
            int mid = n/2; //중간 인덱스
            int startCol = mid;
            int endCol = mid;
            int sum=0; //농작물 수확

            for(int row = 0; row<n; row++){
                for(int col=startCol; col<=endCol; col++){
                    sum += soil[row][col];
                }
                if(row<mid) { //절반 이전까지는 마름모 넓혀가기
                    startCol--;
                    endCol++;
                }
                else{ //절반 이상 넘어가면 마름모 좁히기
                    startCol++;
                    endCol--;
                }
            }

            sb.append("#"+test_case+" "+sum+"\n");

        }
        bw.write(sb.toString());
        bw.flush();
        br.close();
        bw.close();
    }
}
