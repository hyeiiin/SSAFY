package algorithm.baekjoon;

import java.io.*;
import java.util.*;

class Node {
    int x; //위치
    int time; //시간

    public Node(int x, int time) {
        this.x = x;
        this.time = time;
    }
}
public class Main_1697_문혜린 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken()); //수빈 위치
        int K = Integer.parseInt(st.nextToken()); //동생 위치

	    Queue<Node> q = new LinkedList<>();
	    boolean visited[] = new boolean[100001]; //방문 여부
	    int min = Integer.MAX_VALUE; //가장 빠른 시간
	    //처음 수빈 위치 방문
	    visited[N] = true;
	    q.add(new Node(N, 0));
	    
	    //BFS
	    while(!q.isEmpty()) {
	        Node node = q.remove();
	        if(node.x == K) { //동생을 찾을 경우
	            min = Math.min(min, node.time); //최솟값 비교
	        }
	        
	        for (int i = 0; i < 3; i++) { //동생 찾는 3가지 방법 탐색
	            if(i==0) { //x-1 이동
	            	//범위 벗어나지 않고 아직 방문 안했을 경우
	            	//방문 했다는건 현재보다 더 나은 경우가 있다는 뜻
	                if(node.x-1>=0 && node.x-1<100001 && !visited[node.x-1]) {
	                	visited[node.x-1] = true; //방문처리
	                    q.add(new Node(node.x-1, node.time+1)); //이동, 1초 증가
	                }
	            }
	            if(i==1) { //x+1 이동
	            	//범위 벗어나지 않고 아직 방문 안했을 경우
	                if(node.x+1>=0 && node.x+1<100001 && !visited[node.x+1]) {
	                	visited[node.x+1] = true; //방문처리
	                    q.add(new Node(node.x+1, node.time+1)); //이동, 1초 증가
	                }
	            }
	            if(i==2) { //2*x 이동
	            	//범위 벗어나지 않고 아직 방문 안했을 경우
	                if(2*node.x>=0 && 2*node.x<100001 && !visited[2*node.x]) {
	                	visited[2*node.x] = true; //방문처리
	                    q.add(new Node(2*node.x, node.time+1)); //이동, 1초 증가
	                }
	            }
	        }
	    }
	    System.out.println(min);
    }
}

