import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Scanner;

public class Main_17471_게리맨더링 {
    
    static int[] person; //구역별 인구 수를 저장하기 위한 배열
    static int[][] graph; 
    static int N;//구역의 개수
    static List<Integer> A, B; // 선거구 A, B의 리스트
    static int pickCnt;
    static int answer = Integer.MAX_VALUE;
    
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        N = Integer.parseInt(sc.next());

        A = new ArrayList<>();
        B = new ArrayList<>();
        
        person = new int[N+1];
        
        //1. N개의 구역별 인구수를 입력 받고, 
        for(int i=1; i<=N; i++) {
            person[i] = Integer.parseInt(sc.next());
            A.add(i); // 입력 받는 모든 구역을 선거구 A에 배정하고 시작
        }
        
        graph = new int[N+1][N+1]; //구역간의 연결
        for(int i=1; i<=N; i++) {
            int cnt = Integer.parseInt(sc.next());
            for(int c=0; c<cnt; c++) { //연결 정보 입력 받기.
                int vertex = Integer.parseInt(sc.next());
                graph[i][vertex] = 1;
                graph[vertex][i] = 1;
            }
        } 
        
        //------------INPUT END------------------
        
       //2. A,B 선거구 배정
        //half => A선거구에서 B선거구로 뽑히게 될 조합의 수. 
        // N의 수가 짝수, 홀수일때도 있기 때문에 
        //A 선거구에 모든 구역이 들어가 있어서 x개 만큼 뽑아서 B에 넣어주면 선거구 분리 완료되기 때문.
        int half = (N%2) == 1 ? (N/2) + 1 : (N/2); 
        
        // 1~N/2까지의 조합 만들기
        for(pickCnt=1; pickCnt <= half; pickCnt++) {
            combi(0);
        }    
        
        //최소값 출력
        //선거구 나눌 수 없는 경우에는 -1, 나눠진 경우에는 최소값이 출력
        answer = (answer == Integer.MAX_VALUE) ? -1 : answer;
        System.out.println(answer);
    }
    
    
    //idx : A선거구에서 뽑으려는 인덱스
    private static void combi(int idx) {
    	// 기저 조건 : 뽑으려던 개수 만큼 다 뽑았는지?(조합 완성)
        if (B.size() == pickCnt) { 
        	//3. 완성된 조합의 연결 확인하기.(BFS, DFS 탐색)- 선거구 A,B 모두 확인하기.
            if(BFS(A) && BFS(B)) {
            	//4. 각 선거구 인구차 구하기
            	//4-1. A선거구 인원 구하기
                int person_A = 0;
                for(int i=0; i<A.size(); i++) {
                    person_A += person[A.get(i)];
                }
              //4-2. B선거구 인원 구하기
                int person_B = 0;
                for(int i=0; i<B.size(); i++) {
                    person_B += person[B.get(i)];
                }
                //4-3. 연결된 선거구 인원 차이값의 최소값 갱신
                answer = Math.min(answer, Math.abs(person_A - person_B));
            }
            return;
        }
        
        if(idx >= A.size()) return;
        // 조합을 만들기 위해서 A에서 구역 뽑아서 B로 넣어줌
        B.add(A.remove(idx));
        combi(idx);
        //다른 조합을 만들기 위해서 되돌리기
        //B에 넣어준 idx, A로 되돌리기.
        A.add(idx, B.remove(B.size()-1));
        //A에서 idx 뽑지 않고 다음 idx로 넘어간 경우의 조합으로 진행
        combi(idx+1); 
    }

    private static boolean BFS(List<Integer> list) {
        
        Queue<Integer> queue = new LinkedList<>();
        boolean[] visited = new boolean[graph.length+1];
        //연결 확인을 위해서 시작 정점 넣어주기(아무곳에서나 시작해도 무관)
        int v = list.get(0);
        queue.add(v);

        visited[v] = true;
        
        List<Integer> temp = new ArrayList<>();
        while(!queue.isEmpty()) {
            int cur_v = queue.poll(); 
            //모두 연결되었는지 확인하기 위한 리스트
            temp.add(cur_v);
            for(int i=1; i<graph.length; i++) {
            	// 간선이 존재하는지, 방문하지 않았던 정점인지 확인.
                if(graph[cur_v][i] == 1 && !visited[i]) {
                    visited[i] = true;
                   // 소속된 선거구의 구역이 맞는지 확인
                    //같은 선거구면 BFS 탐색 지속
                    if(list.contains(i)) {
                        queue.add(i);
                            
                    }
                }
            }            
        }
        //나누어진 A,B 선거구에서 빠진게 없는지 확인
        for(int i=0; i<list.size(); i++) {
            if(!temp.contains(list.get(i))) {
                return false;
            }
        }
        //모두 연결됨 확인!
        return true;
    }
}