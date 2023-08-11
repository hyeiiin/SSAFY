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

        allHome = new ArrayList<>();//집 정보
        bbqStores = new ArrayList<>();//치킨 정보

        //입력 받아서 집 정보와 치킨 정보를 받아준다.
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
            }
        }

        //살아남는 치킨집의 경우의 수를 찾는 로직 안에 최소 치킨거리가 들어감.
        comb(bbqStores.size(),0,0, M, new int[M]);
        //답 출력
        System.out.println(ans);
    }
    
    //살릴 치킨 집의 모든 경우의 수
    private static void comb(int len, int cnt, int start, int n, int[] result){
        if (cnt == n){
            ArrayList<Node> temp = new ArrayList<>();
            for (int i : result) {//폐업이 되지 않을 치킨집만 temp에 들어간다.
                temp.add(bbqStores.get(i));
            }
            //폐업이 되지 않은 치킨집들 정보와, 집 정보를 getCityBBQDist 메소드에 넘기면 치킨거리가 나온다.
            // 모든 경우의 수 중 최소값이 ans가 된다.
            ans = Math.min(ans,getCityBBQDist(allHome,temp));
            return;
        }

        for (int i = start; i < len; i++) {
            result[cnt] = i;
            comb(len, cnt+1, i+1, n, result);
        }
    }
    
    //도시의 집 정보와, 치킨 정보를 넣으면 그 도시의 치킨거리가 나온다
    private static int getCityBBQDist(ArrayList<Node> allHome, ArrayList<Node> bbqStores) {
        int sum = 0;
        for (Node home : allHome) {// 각 집에서
            int minDist = Integer.MAX_VALUE;
            for (Node bbqStore : bbqStores) {//각 치킨집까지
                int dist = getDist(bbqStore, home);//거리를 구하는데
                if (dist<minDist){//그 거리가 이 도시에서 최소값인 치킨집이라면
                    minDist = dist;//그 집에서 가장 가까운 치킨집까지의 거리를 저장한다.
                }
            }
            sum += minDist;//도시의 치킨 거리에 더해준다.
        }
        return sum;
    }
    
    // 두 노드 사이의 거리 구함
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