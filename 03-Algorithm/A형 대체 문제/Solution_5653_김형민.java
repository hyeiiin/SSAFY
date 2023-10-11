import java.util.*;
import java.io.*;

class Solution {
    static int N, M, K, ans;
    static int[][] move = {{0,1},{0,-1},{1,0},{-1,0}};
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        int T = Integer.parseInt(br.readLine());
        for (int t = 1; t <= T; t++) {
            sb.append("#").append(t).append(" ");
            st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            M = Integer.parseInt(st.nextToken());
            K = Integer.parseInt(st.nextToken());
            ans = 0;
            HashMap<String, Cell> getCell = new HashMap<>();
            PriorityQueue<Cell> nextQ = new PriorityQueue<>((o1, o2) -> Integer.compare(o2.cost, o1.cost));
            for (int i = 0; i < N; i++) {
                st = new StringTokenizer(br.readLine());
                for (int j = 0; j < M; j++) {
                    String key = getKey(i,j);
                    int cost = Integer.parseInt(st.nextToken());
                    if (cost==0) continue;
                    Cell cell = new Cell(i, j, cost, "비활성상태", cost);
                    getCell.put(key, cell);
                    nextQ.add(cell);
                }
            }

            //K시간 후
            for (int k = 1; k <= K; k++) {
                HashMap<String, Cell> tempMap = new HashMap<>();
                PriorityQueue<Cell> q = nextQ;
                nextQ = new PriorityQueue<>((o1, o2) -> Integer.compare(o2.cost, o1.cost));
                while (!q.isEmpty()) {
                    Cell cell = q.poll();
                    String key = getKey(cell.x, cell.y);

                    if (cell.stat.equals("죽은상태")) continue; //현재 세포가 죽은 놈이면 할게 없음 pass

                    //현재 세포가 비활성 상태이면서 활성으로 바뀌는 시간이라면
                    if (cell.stat.equals("비활성상태")&&cell.activeTime==k){
                        // 비활성상태에서 활성상태로 변화
                        cell.stat = "활성상태";
                        getCell.put(key, cell);
                        nextQ.add(cell);

                        //번식
                        for (int[] mv : move) {//사방탐색
                            int dx = mv[0]+cell.x;
                            int dy = mv[1]+cell.y;
                            String newKey = getKey(dx, dy);
                            if (!getCell.containsKey(newKey)){//사방탐색한 곳이 비어 있을때 넣는다. = 선점한 세포가 없어야 함
                                //동시에 발생된 세포 임시 저장소에 넣어주기
                                if (!tempMap.containsKey(newKey)){
                                    //임시 저장소에 세포가 들어있지 않은 경우 -> 가장 큰 값 갱신
                                    Cell newCell = new Cell(dx, dy, cell.cost, "비활성상태", k + cell.cost + 1);
                                    tempMap.put(newKey,newCell);
                                }
                            }
                            //비어있지 않으면 -> 이미 선점한 세포가 있는 경우는 제낀다.
                        }
                        //번식 끝

                    }// 세포가 활성 상태 이후 cost 시간 만큼 지났다면
                    else if (cell.stat.equals("활성상태") && k == cell.activeTime + cell.cost) {
                        //활성상태에서 죽은상태로 변경
                        cell.stat="죽은상태";
                        getCell.put(key, cell);
                    } else if (cell.stat.equals("활성상태")||cell.stat.equals("비활성상태")) {
                        nextQ.add(cell);
                    }
                }

                if (k==K) ans=nextQ.size();

                //새로 추가된 놈들 넣어주기
                for (String tempKey : tempMap.keySet()) {
                    Cell newCell = tempMap.get(tempKey);
                    getCell.put(tempKey, newCell);
                    nextQ.add(newCell);
                }


            }

            sb.append(ans).append("\n");
        }
        System.out.println(sb);
    }
    static String getKey(int x, int y){
        return "("+x+","+y+")";
    }


}
class Cell{
    int x;
    int y;
    int cost;
    String stat;
    int activeTime;
    public Cell(int x, int y, int cost, String stat, int activeTime) {
        this.x = x;
        this.y = y;
        this.cost = cost;
        this.stat = stat;
        this.activeTime = activeTime;
    }
}
