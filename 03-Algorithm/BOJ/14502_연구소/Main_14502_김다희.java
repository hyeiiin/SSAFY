import java.io.*;
import java.util.*;
public class Main {
    static int n,m,index,minVal=Integer.MAX_VALUE,wall;
    static int[][] map;
    static Pair[] list;
    public static void main(String[] args) throws IOException {
        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st=new StringTokenizer(br.readLine());
        n=Integer.parseInt(st.nextToken());
        m=Integer.parseInt(st.nextToken());
        map=new int[n][m];
        list=new Pair[64];
        index=0;
        wall=0;
        for(int i=0;i<n;i++){
            st=new StringTokenizer(br.readLine());
            for(int j=0;j<m;j++){
                map[i][j]=Integer.parseInt(st.nextToken());
                if(map[i][j]==2){
                    list[index++]=new Pair(i,j);
                }
                if(map[i][j]==1){
                    wall++;
                }
            }
        }
        recur(0);
        System.out.println(n*m-minVal-wall-3);
    }
    public static class Pair{
        int r,c;
        public Pair(int r,int c){
            this.r=r;
            this.c=c;
        }
    }
    public static void recur(int start){
        if(start==3){
            boolean[][] visited=new boolean[n][m];
            int count=0;
            for(int i=0;i<index;i++){
                Pair curr=list[i];
                if(!visited[curr.r][curr.c]){
                    count+=bfs(curr,visited);
                }
            }
            minVal=count<minVal?count:minVal;
            return;
        }
        for(int i=0;i<n;i++){
            for(int j=0;j<m;j++) {
                if (map[i][j] == 0) {
                    map[i][j]=1;
                    recur(start+1);
                    map[i][j]=0;
                }
            }
        }
    }
    public static int bfs(Pair start,boolean[][] visited){
        Queue<Pair>q=new ArrayDeque<>();
        int[][] dir={{0,1},{1,0},{0,-1},{-1,0}};
        q.add(start);
        int count=0;
        Pair curr,next;
        while(!q.isEmpty()){
            curr=q.poll();
            count++;
            for(int i=0;i<4;i++){
                next=new Pair(0,0);
                next.r=curr.r+dir[i][0];
                next.c= curr.c+dir[i][1];
                if(next.r>=0&&next.r<n&&next.c>=0&&next.c<m){
                    if(map[next.r][next.c]==0){
                        if(!visited[next.r][next.c]){
                            q.add(next);
                            visited[next.r][next.c]=true;
                        }
                    }
                }
            }
        }
        return count;
    }

}
