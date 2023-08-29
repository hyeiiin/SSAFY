package boj;
import java.util.*;
import java.io.*;
public class Main_12869_김형민 {
    static int N;
    static int[][] move = {{9,3,1},{9,1,3},{3,1,9},{3,9,1},{1,9,3},{1,3,9}};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());
        int[] getSCV = new int[3];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            getSCV[i] =  Integer.parseInt(st.nextToken());
        }

        ThreeSCV startSCV = new ThreeSCV(getSCV[0], getSCV[1], getSCV[2], 0);//시작 노드 생성
        System.out.println(bfs(startSCV));// scv의 체력이 0, 0, 0 이 되는 6방향 bfs 탐색

    }
    static int bfs(ThreeSCV startScv){

        boolean[][][] visited = new boolean[61][61][61];// 같은 체력 조건인 scv는 다시 탐색 x
        ArrayDeque<ThreeSCV> q = new ArrayDeque<>();
        q.add(startScv);//시작노드를 큐에 넣는다.
        int ans = startScv.cnt;//수행횟수 (공격횟수) 가 답이 된다.
        while (!q.isEmpty()){

            ThreeSCV scv = q.poll();

            if (scv.one==0&&scv.two==0&&scv.three==0){//세마리가 0 0 0 이 되면 탈출
                ans=scv.cnt;//수행횟수(공격횟수)
                break;
            }

            if (visited[scv.one][scv.two][scv.three])continue;//같은 체력 조건인 scv는 다시 탐색 x
            visited[scv.one][scv.two][scv.three] = true;// 방문 처리

            for (int[] mv : move) {
                int one = scv.one - mv[0];
                if (one<0) one = 0;//체력이 0보다 작아지면 0으로 바꿔준다.
                int two = scv.two - mv[1];
                if (two<0) two = 0;//체력이 0보다 작아지면 0으로 바꿔준다.
                int three = scv.three - mv[2];
                if (three<0) three = 0;//체력이 0보다 작아지면 0으로 바꿔준다.
                q.add(new ThreeSCV(one,two,three, scv.cnt+1));//다음 공격으로
            }
        }
        return ans;
    }
}
class ThreeSCV{
    int one;
    int two;
    int three;
    int cnt;

    public ThreeSCV(int one, int two, int three, int cnt) {
        this.one = one;
        this.two = two;
        this.three = three;
        this.cnt = cnt;
    }
}

