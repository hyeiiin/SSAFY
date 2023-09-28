import java.io.*;
import java.util.*;

public class Solution {


    public static void main(String[] args) throws Exception {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        int T = Integer.parseInt(br.readLine());
        for (int t = 1; t <= T; t++) {
            sb.append("#").append(t).append(" ");
            int N = Integer.parseInt(br.readLine());
            int[] arr = new int[N];
            st = new StringTokenizer(br.readLine());
            for (int i = 0; i < N; i++) {
                arr[i] = Integer.parseInt(st.nextToken());
            }

            // 최장 증가 부분 수열 구하기
            int lis[] = new int[N];
            int cnt = 0;
            lis[cnt++] = arr[0];
            for (int i = 1; i < N; i++) {
                if (arr[i] > lis[cnt-1]) lis[cnt++]=arr[i]; // 현재 arr 인덱스가 최장 부분 수열의 마지막 값이 크다면 최장 부분 수열에 저장
                else lis[bSearch(lis, 0, cnt, arr[i])] = arr[i];
                // 현재 arr 인덱스가 최장 부분 수열의 마지막 값이 작다면
                // 현재까지 저장된 lis에서 해당 값이 들어갈 수 있는 곳 을 찾아서 넣어준다.
            }
            // lis <- 에 최장 부분 수열이 리턴된다.


            int ans = 1;
            int preI = 0;
            for (int i = 1; i < N; i++) {
                if (lis[preI]<lis[i]){
                    preI = i;
                    ans++;
                }else {
                    break;
                }
            }
            sb.append(ans).append("\n");
        }
        System.out.println(sb);
    }
    static int bSearch(int[] arr, int start, int end, int findNum){
        while(start<end){
            int mid = (start+end) / 2;
            if (findNum>arr[mid]) start = mid + 1;
            else end = mid;
        }
        return end;
    }


}

