import java.util.*;
import java.io.*;

public class Solution {
    static int M, A, ans;
    static int[] moveUserA, moveUserB;
    static int[] ansA, ansB;
    static Charger[] chargers;
    static int[][] move = {{0,0},{-1,0},{0,1},{1,0},{0,-1}};
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        int T = Integer.parseInt(br.readLine());
        for (int t = 1; t <= T; t++) {
            sb.append("#").append(t).append(" ");
            st = new StringTokenizer(br.readLine());
            M = Integer.parseInt(st.nextToken());
            A = Integer.parseInt(st.nextToken());

            moveUserA = new int[M+1];
            ansA = new int[M+1];
            st = new StringTokenizer(br.readLine());
            for (int i = 1; i < M+1; i++) {
                moveUserA[i] = Integer.parseInt(st.nextToken());
            }

            moveUserB = new int[M+1];
            ansB = new int[M+1];
            st = new StringTokenizer(br.readLine());
            for (int i = 1; i < M+1; i++) {
                moveUserB[i] = Integer.parseInt(st.nextToken());
            }

            chargers = new Charger[A];
            for (int i = 0; i < A; i++) {
                st = new StringTokenizer(br.readLine());
                int y = Integer.parseInt(st.nextToken())-1;
                int x = Integer.parseInt(st.nextToken())-1;
                int c = Integer.parseInt(st.nextToken());
                int p = Integer.parseInt(st.nextToken());
                chargers[i] = new Charger(x,y,c,p);
            }

            ans = 0; // 답
            User userA = new User(0,0); // User A 세팅
            User userB = new User(9,9); // User B 세팅
            for (int i = 0; i < M+1; i++) {//시간 흐르기
                moveUser(userA, moveUserA[i]); // User A 옮기기
                moveUser(userB, moveUserB[i]); // User B 옮기기
                process(userA, userB, i); // 프로세스 시작
            }

            sb.append(ans).append("\n");
        }
        System.out.println(sb);
    }

    /**
     * user를 옮겨주는 메소드
     * @param user user 객체와 정보
     * @param moveNum 시간마다의 각 유저의 이동 정보
     */
    private static void moveUser(User user, int moveNum) {
        user.x = user.x+move[moveNum][0];
        user.y = user.y+move[moveNum][1];
    }

    /**
     * user 정보를 받아서 현재 user위치에서 충전가능한 충전기를 우선순위 큐로 가져온다.
     * @param user user 정보
     * @return 충전기의 충전속도가 큰 순위의 우선순위 큐
     */
    static PriorityQueue<Charger> getSelectCharger(User user){
        PriorityQueue<Charger> chargerQueue = new PriorityQueue<>((o1, o2) -> Integer.compare(o2.performance, o1.performance));
        for (Charger charger : chargers) {
            if (getDist(user, charger) > charger.coverage) continue;
            chargerQueue.add(charger);
        }
        return chargerQueue;
    }

    /**
     * 메인 로직 : 현재시간에서의 두 유저의 충전 총 합이 가장 크도록 하는 로직
     * @param userA userA 정보
     * @param userB userB 정보
     * @param time 현재 시간
     */
    static void process(User userA, User userB, int time){
        //두 유저가 같은 자리에 있을때
        if (userA.x==userB.x&&userA.y==userB.y){
            PriorityQueue<Charger> selectChargers = getSelectCharger(userA);//같은 좌표
            if (selectChargers.size()==0) return;
            if (selectChargers.size()==1){
                //현재 위치에서 얻을 수 있는 충전기가 1개일 때,
                Charger charger = selectChargers.poll();
                ansA[time] = charger.performance/2;
                ansB[time] = charger.performance/2;
            }else {
                //현재 위치에서 얻을 수 있는 충전기가 1개 이상일때
                Charger chargerA = selectChargers.poll();
                Charger chargerB = selectChargers.poll();
                ansA[time] = chargerA.performance;
                ansB[time] = chargerB.performance;
            }
        }else {//같은 자리에 없을 때,
            PriorityQueue<Charger> selectUserAChargers = getSelectCharger(userA);
            PriorityQueue<Charger> selectUserBChargers = getSelectCharger(userB);

            if (selectUserAChargers.size()==0&&selectUserBChargers.size()==0) return;//둘다 접속할 수 있는 충전기가 없을때
            else if (selectUserAChargers.size()==0){//A만 접속할 수 있는 충전기가 없을때
                Charger chargerB = selectUserBChargers.poll();
                ansA[time] = 0;
                ansB[time] = chargerB.performance;
            } else if (selectUserBChargers.size()==0) {//B만 접속할 수 있는 충전기가 없을때
                Charger chargerA = selectUserAChargers.poll();
                ansA[time] = chargerA.performance;
                ansB[time] = 0;
            } else if (selectUserAChargers.size()==1&&selectUserBChargers.size()==1) {
                //두 사람 다 접속할 수 있는 충전기가 1개일때
                Charger chargerA = selectUserAChargers.poll();
                Charger chargerB = selectUserBChargers.poll();
                if (chargerA.x==chargerB.x&&chargerA.y==chargerB.y){
                    //1개인 충전기가 둘 다 같을때
                    ansA[time] = chargerA.performance/2;
                    ansB[time] = chargerB.performance/2;
                }else {
                    //1개인 충전기가 둘이 다를때
                    ansA[time] = chargerA.performance;
                    ansB[time] = chargerB.performance;
                }
            } else if (selectUserAChargers.size()==1) {
                //user A가 사용할 수 있는 충전기기가 1개일때 B는 두개 이상
                Charger chargerA = selectUserAChargers.poll();
                Charger chargerB = selectUserBChargers.poll();
                if (chargerA.x==chargerB.x&&chargerA.y==chargerB.y){
                    Charger chargerB2 = selectUserBChargers.poll();
                    ansA[time] = chargerA.performance;
                    ansB[time] = chargerB2.performance;
                }else {
                    ansA[time] = chargerA.performance;
                    ansB[time] = chargerB.performance;
                }
            } else if (selectUserBChargers.size()==1) {
                //user B가 사용할 수 있는 충전기기가 1개일때 A는 두개 이상
                Charger chargerA = selectUserAChargers.poll();
                Charger chargerB = selectUserBChargers.poll();
                if (chargerA.x==chargerB.x&&chargerA.y==chargerB.y){
                    Charger chargerA2 = selectUserAChargers.poll();
                    ansA[time] = chargerA2.performance;
                    ansB[time] = chargerB.performance;
                }else {
                    ansA[time] = chargerA.performance;
                    ansB[time] = chargerB.performance;
                }
            } else {
                //두 사람 다 접속할 수 있는 충전기기가 2개 이상일때
                Charger chargerA = selectUserAChargers.poll();
                Charger chargerB = selectUserBChargers.poll();
                if (chargerA.x==chargerB.x&&chargerA.y==chargerB.y){
                    //가장 출력이 좋은 충전기가 둘이 같을때
                    Charger chargerA2 = selectUserAChargers.poll();
                    Charger chargerB2 = selectUserBChargers.poll();
                    if (chargerA2.performance>chargerB2.performance){
                        ansA[time] = chargerA2.performance;
                        ansB[time] = chargerB.performance;
                    }else {
                        ansA[time] = chargerA.performance;
                        ansB[time] = chargerB2.performance;
                    }
                }else {
                    //가장 출력이 좋은 충전기가 둘이 다를때
                    ansA[time] = chargerA.performance;
                    ansB[time] = chargerB.performance;
                }
            }
        }
        ans += (ansA[time]+ansB[time]);
    }

    /**
     * 유저와 충전기 간의 거리를 계산하는 메소드
     * @param user
     * @param charger
     * @return
     */
    static int getDist(User user, Charger charger){
        return Math.abs(user.x-charger.x) + Math.abs(user.y-charger.y);
    }

}
class User{
    int x;
    int y;

    public User(int x, int y) {
        this.x = x;
        this.y = y;
    }

}
class Charger{
    int x;
    int y;
    int coverage;
    int performance;

    public Charger(int x, int y, int coverage, int performance) {
        this.x = x;
        this.y = y;
        this.coverage = coverage;
        this.performance = performance;
    }

}
