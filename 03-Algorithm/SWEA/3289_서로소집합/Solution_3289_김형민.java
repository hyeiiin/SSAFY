package swea;
import java.io.*;
import java.util.*;
public class Solution_3289_김형민 {

    public static void main(String[] args) throws IOException {


        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        int T = Integer.parseInt(br.readLine());
        for (int t = 1; t <= T; t++) {
            sb.append("#").append(t).append(" ");
            st = new StringTokenizer(br.readLine());
            int N = Integer.parseInt(st.nextToken());
            int M = Integer.parseInt(st.nextToken());
            
            // 각 n개 의 서로소 집합
            int[] set = new int[N+1];
            for (int i = 0; i < N; i++) {
                set[i] = i;
            }


            for (int m = 0; m < M; m++) {
                st = new StringTokenizer(br.readLine());
                int comd = Integer.parseInt(st.nextToken());
                int A = Integer.parseInt(st.nextToken());
                int B = Integer.parseInt(st.nextToken());
                if (comd==0){
                    union(set, A, B);
                }
                if (comd==1){
                    if (find(set, A)==find(set, B)){
                        sb.append(1);
                    }else {
                        sb.append(0);
                    }
                }
            }

            sb.append("\n");
        }
        System.out.println(sb);

    }
    static int find(int[] arr, int i){
        if (arr[i]==i) return i;
        return find(arr, arr[i]);
    }
    static void union(int[] arr, int a, int b){
        int pA = find(arr, a);//a의 부모 찾기
        int pB = find(arr, b); // b의 부모 찾기

        if (pA<pB){// A의 부모가 B의 부모보다 작다면
            arr[pA] = pB; //A의 부모를 B의 부모로 바꿔준다.
        }else {
            arr[pB] = pA; //B의 부모를 A의 부모로 바꿔준다.
        }

    }


}
