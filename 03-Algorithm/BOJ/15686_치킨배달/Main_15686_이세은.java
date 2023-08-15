package ssafyStudy.BOJ;
import java.io.*;
import java.util.*;

public class Main_15686_이세은 {
    private static String[][] street;
    private static int m, j=0, ans = Integer.MAX_VALUE; //j->선택한 치킨집 탐색 인덱스
    private static List<int[]> home;
    private static List<int[]> store;
    private static boolean[] visited;
    private static int[] selected;
    
    public static void main(String[] args) throws IOException{

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken()); //n*n 도시
        m = Integer.parseInt(st.nextToken()); //치킨거리가 작은 상위 m개의 치킨집만 남기기
        street = new String[n+1][n+1];
        home = new ArrayList<>();
        store = new ArrayList<>();
        

        for(int i =1; i<=n; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j=1; j<=n; j++) {
                street[i][j] = st.nextToken();
                if(street[i][j].equals("1")){ //집인 경우 위치 체크
                    home.add(new int[]{i, j});
                }
                else if(street[i][j].equals("2")){ //치킨집인 경우 위치 체크
                    store.add(new int[]{i, j});
                }
            }
        }

        visited = new boolean[store.size()]; //저장된 치킨집 수만큼 방문배열 초기화
        selected = new int[m]; //선택한 치킨집 배열 초기화

        //더 가까운 치킨집의 거리 -> 각 집의 치킨 거리, 도시의 치킨 거리 -> 모든 집의 치킨 거리의 합
        //전체 치킨집 중 m개를 골랐을 때 도시의 치킨 거리의 최솟값
        comb(0, 0);
        System.out.println(ans);

    }

    public static void comb(int cnt, int idx){

        if(cnt == m){
            int cityDist=0; //각 치킨집 조합으로 거리 구할 때마다 도시의 치킨거리 초기화 필요
            //선택된 치킨집까지의 도시거리 측정, 각 집의 최소거리 구하기
            for(int i=0; i< home.size(); i++){
                int min = Integer.MAX_VALUE; //최대로 초기화
                int[] homeVal = home.get(i);
                //현재 집에대해 선택된 치킨집까지의 거리 측정
                for(int s=0; s< selected.length; s++){
                    int[] storeVal = store.get(selected[s]);
                    int dis = Math.abs(storeVal[0]-homeVal[0]) + Math.abs(storeVal[1]-homeVal[1]);
                    min = Math.min(min, dis); //더 짧은 경로 우선
                }
                //도시의 치킨거리 구하기 위해서 집에서의 모든 치킨 거리 더하기
                cityDist += min;

            }
            ans = Math.min(ans, cityDist); //도시의 치킨 거리 최솟값이 정답
            return;
        }
        
        for(int i=idx; i<store.size(); i++){
            if(!visited[i]){
                visited[i]=true;
                selected[j++] = i;
                comb(cnt+1, i+1); //다음 조합 찾기
                visited[i] = false;
                selected[--j] = 0;//넣은 조합 빼주기
            }
        }
    }
}
