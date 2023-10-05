import java.io.*;
import java.util.*;
 
 
 class Solution {
      
     
      
    static BufferedReader buffer = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer tokens; 
     
    static int[][] graph; 
    static int[][] dist; 
     
    static final int NOT_CONN = 0;
    static final int CONN = 1; 
    static final int MAX = (int)1e9; 
     
    static int n; 
     
     
    static void init() throws IOException{
        tokens = new StringTokenizer(buffer.readLine()); 
         
        n = Integer.valueOf(tokens.nextToken()); 
         
        graph = new int[n+1][n+1]; 
         //인접행렬 입력받기 
        for(int x=1; x<=n; x++) {
            for(int y=1; y<=n; y++) {
                graph[x][y] = Integer.valueOf(tokens.nextToken()); 
            }
        }
         
         
    }
     
    public static void main(String[] args) throws IOException{
        int T = Integer.valueOf(buffer.readLine()); 
        StringBuilder sb = new StringBuilder(); 
        for(int test=1; test<=T; test++) {
            init(); 
             //인접행렬을 dist 배열로 바꾸기 
            dist = new int[n+1][n+1]; 
            //간선이 존재할 경우 1 간선이 존재하지않을 경우 max로 dist 배열 채우기 
            for(int start=1; start<=n; start++) {
                for(int end=1; end<=n; end++) {
                    if(graph[start][end]==NOT_CONN) {
                        dist[start][end]=MAX; 
                    }else {
                        dist[start][end] = 1; 
                    }
                }
            }
             
             //플로이드 워셜로 모든 노드에 대해 채워주기 
            for(int k=1; k<=n; k++) {
                for(int start=1; start<=n; start++) {
                    for(int end=1; end<=n; end++) {
                         dist[start][end] = Math.min(dist[start][k]+dist[k][end], dist[start][end]);
                    }
                }
                //자기자신에서 자기자신으로 갈 경우 길이는 0이다. 
                dist[k][k] = 0; 
            }
             
            //각 출발점마다 cc값을 계산하여 최소값 찾아주기 
            int result = Integer.MAX_VALUE; 
             
            for(int node=1; node<=n; node++) {
                int cc = 0; 
                for(int end=1; end<=n; end++) {
                    cc+= dist[node][end]; 
                }
                result = Math.min(cc, result);
                 
            }
             
            sb.append("#").append(test).append(" ").append(result).append("\n");
             
        }
        System.out.println(sb); 
    }

}
