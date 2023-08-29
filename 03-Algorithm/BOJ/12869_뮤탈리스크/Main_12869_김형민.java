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

        ThreeSCV startSCV = new ThreeSCV(getSCV[0], getSCV[1], getSCV[2], 0);
        System.out.println(bfs(startSCV));


    }
    static int bfs(ThreeSCV startScv){

        boolean[][][] visited = new boolean[61][61][61];
        ArrayDeque<ThreeSCV> q = new ArrayDeque<>();
        q.add(startScv);
        int ans = startScv.cnt;
        while (!q.isEmpty()){

            ThreeSCV scv = q.poll();

            if (scv.one==0&&scv.two==0&&scv.three==0){
                ans=scv.cnt;
                break;
            }

            if (visited[scv.one][scv.two][scv.three])continue;
            visited[scv.one][scv.two][scv.three] = true;

            for (int[] mv : move) {
                int one = scv.one - mv[0];
                if (one<0) one = 0;
                int two = scv.two - mv[1];
                if (two<0) two = 0;
                int three = scv.three - mv[2];
                if (three<0) three = 0;
                q.add(new ThreeSCV(one,two,three, scv.cnt+1));
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

