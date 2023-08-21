import java.io.*;
import java.util.*;

public class Main_1697_김현영 {
	// sec : 동생 위치까지 가는데 걸린 시간
	// count : 현재 초에서 이동가능한 경로의 수 
	static int n, k, sec, count; 
    
    
    static Deque<Integer> q = new ArrayDeque<>();
    static Set<Integer> used = new HashSet<>();	//방문한 위치 저장

    static void move(int time) {
    	sec++;	
    	count = 0;	// 현재 초에서 이동 가능한 경로의 수 초기화
    	//이전 초에서 이동가능한 경로의 수만큼 반복
        for (int i = 0; i < time; i++) {
            int now = q.poll();	//큐에서 이동가능한 위치 뽑아내기
            
            //현재 위치가 동생이라면 종료
            if (now == k) 
                return;
            
            // 현재 위치에서 이동가능한 위치 큐에 넣기
            if (addNumAtQ(now-1) || addNumAtQ(now+1) || addNumAtQ(now*2)) {
                return;
            }
        }

        // 이동가능한 경로의 수만큼 다시 탐색 시작
        move(count);

    }
    
    // 현재 위치에서 이동가능한 경로를 큐에 넣는 함수
    static boolean addNumAtQ(int num) {
    	
    	// 이동가능한 경로가 동생의 위치라면 종료
        if(num == k) 
            return true;
        
        // 이동가능한 경로가 이미 방문했거나 일정 숫자범위를 초과한다면 큐에 넣지 않음
        if (!used.contains(num) && -100000< num && num <300000) {
        	count ++;
            q.add(num);
            used.add(num);	// 방문처리
        }
        return false;
    }
    

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());	//수빈이 위치
        k = Integer.parseInt(st.nextToken());	//동생 위치
        
        //수빈이와 동생의 위치가 같다면 0을 출력하고 종료
        if(n== k) {
        	System.out.println(0);
        	return;
        }
        	
        // 큐에 수빈이의 위치 추가. 수빈이 위치 방문처리
        q.add(n);
        used.add(n);
        
        //동생위치까지 찾아가기
        move(1);
        
        //동생 위치까지 가는데 걸린 시간 출력
        System.out.println(sec);

    }

}