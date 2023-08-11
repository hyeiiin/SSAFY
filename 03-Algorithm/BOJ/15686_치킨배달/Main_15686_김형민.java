package boj;

import java.io.*;
import java.util.*;

public class Main_15686_김형민 {
    static ArrayList<Node> allHome;
    static ArrayList<Node> bbqStores;
    static int ans = Integer.MAX_VALUE;
    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken()); //도시 크기 N*N
        int M = Integer.parseInt(st.nextToken()); //남길 치킨집 개수

        int[][] city = new int[N][N];
        allHome = new ArrayList<>();
        bbqStores = new ArrayList<>();


        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                int num = Integer.parseInt(st.nextToken());
                if (num==1){
                    allHome.add(new Node(i,j));
                }
                if (num==2){
                    bbqStores.add(new Node(i,j));
                }
                city[i][j] = num;
            }
        }
        comb(bbqStores.size(),0,0,M,new int[M]);
        System.out.println(ans);
    }
    private static void comb(int len, int cnt, int start, int n, int[] result){
        if (cnt == n){
            ArrayList<Node> temp = new ArrayList<>();
            for (int i : result) {
                temp.add(bbqStores.get(i));
            }
            ans = Math.min(ans,getCityBBQDist(allHome,temp));
            return;
        }

        for (int i = start; i < len; i++) {
            result[cnt] = i;
            comb(len, cnt+1, i+1, n, result);
        }
    }
    private static int getCityBBQDist(ArrayList<Node> allHome, ArrayList<Node> bbqStores) {
        int sum = 0;
        for (Node home : allHome) {
            int minDist = Integer.MAX_VALUE;
            for (Node bbqStore : bbqStores) {
                int dist = getDist(bbqStore, home);
                if (dist<minDist){
                    minDist = dist;
                }
            }
            sum += minDist;
        }
        return sum;
    }

    static int getDist(Node a, Node b){
        return Math.abs(a.x-b.x)+Math.abs(a.y-b.y);
    }
}
class Node{
    int x;
    int y;

    public Node(int x, int y) {
        this.x = x;
        this.y = y;
    }

}