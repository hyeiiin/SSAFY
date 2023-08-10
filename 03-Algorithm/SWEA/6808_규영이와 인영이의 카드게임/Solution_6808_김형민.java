package swea;
import java.io.*;
import java.util.*;
public class Solution_6808_김형민 {
    static int[] IYcards;
    static int[] GYcards;
    static int win;
    static int lose;
    public static void main(String[] args) throws IOException{
        System.setIn(new FileInputStream("C:\\SSAFY\\workspace\\CodingTest\\src\\swea\\input.txt"));

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        int T = Integer.parseInt(br.readLine());
        for (int t = 1; t <= T; t++) {
            win = 0;
            lose = 0;
            sb.append("#").append(t).append(" ");
            GYcards = new int[9];
            IYcards = new int[9];
            HashSet<Integer> GySet = new HashSet<>();
            st = new StringTokenizer(br.readLine());
            for (int i = 0; i < 9; i++) {
                int num = Integer.parseInt(st.nextToken());
                GYcards[i] = num;
                GySet.add(num);
            }
            int idx = 0;
            for (int n = 1; n <= 18; n++) {
                if (!GySet.contains(n)){
                    IYcards[idx++] = n;
                }
            }

            perm(IYcards,0,9,new boolean[9],new int[9]);
            sb.append(win).append(" ").append(lose).append("\n");



        }
        System.out.println(sb);
    }
    private static void perm(int[] IY, int cnt, int n,boolean[] isSelected, int[] result){
        if (cnt == n){
            int[] gameResult = game(GYcards, result);
            if (gameResult[0]>gameResult[2]){
                win++;
            }
            if (gameResult[0]<gameResult[2]){
                lose++;
            }
            return;
        }


        for (int i = 0; i < IY.length; i++) {
            if (isSelected[i]) continue;

            result[cnt] = IY[i];
            isSelected[i] = true;

            perm(IY,cnt+1, n, isSelected, result);

            isSelected[i] = false;
        }
    }
    static int[] game(int[] arr, int[] arr2){
        //승무패
        int[] gameResult = new int[3];
        for (int i = 0; i < 9; i++) {
            if (arr[i]>arr2[i]){
                //규영 승
                gameResult[0] += (arr[i]+arr2[i]);
                continue;
            }
            if (arr[i]==arr2[i]){
                //무승부
                gameResult[1]++;
                continue;
            }
            if (arr[i]<arr2[i]){
                //규영 패
                gameResult[2] += (arr[i]+arr2[i]);
            }
        }
        return gameResult;
    }
}
