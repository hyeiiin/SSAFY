package boj;
import java.util.*;
import java.io.*;
public class Main_1074_김형민 {
    static int N;
    static int R;
    static int C;
    static int cnt = 0;
    public static void main(String[] args) throws IOException {
        StringTokenizer st = new StringTokenizer(new BufferedReader(new InputStreamReader(System.in)).readLine());
        N = Integer.parseInt(st.nextToken());
        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        z((int)Math.pow(2, N),0,0);
    }
    static void z(int n, int x, int y) {
        
        //찾는 좌표라면 cnt를 출력하고 종료한다.
        if (x==R && y == C){
            System.out.println(cnt);
            return;
        }
        // 현재 좌표 범위가 찾는 좌표의 사분면이 아니라면
        if (!( (x<=R) && (R<(x+n)) && (y<=C) && (C<(y+n)) )){
            //현재 사분면을 카운트 한 값 (정사각형의 넓이)(n*n)을 넘겨준다.
            cnt += n*n;
            return;
        }

        //각 사분면으로 이동한다.
        z(n/2, x, y); // 1사분면
        z(n/2, x, y+n/2); // 2사분면
        z(n/2, x+n/2, y); //3 사분면
        z(n/2, x+n/2, y+n/2); //4 사분면
        
    }

}
