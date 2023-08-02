
import java.io.*;
import java.util.*;

public class Solution_1210_Ladder1{
    public static void main(String[] args) throws IOException {
		Scanner sc = new Scanner(System.in);
		int N = 100;
		int map[][] = new int[N][N+2]; // 맨 왼쪽, 맨 오른쪽 열 경계체크 하지 않기 위해 +2 
		for(int t=1; t<=10; t++) {
			sc.nextInt();  // 테케번호 : 의미없음 
			int curx = 0, cury = 0;//현재위치-도착지
			for(int i=0; i<N; i++) {
				for(int j=1; j<=N; j++) {
					map[i][j] =sc.nextInt();
					if(map[i][j] == 2) cury = j;//도착 위치 찾아서 저장
                }
			}
			
			//---------INPUT END-----------------------
			
			for(curx=N-2; curx>0; curx--){//마지막 이전 행부터 위에서 2번째행까지,
				//도착열부터 따라 올라감(마지막행과 맨 윗행은 동,서쪽으로 갈수 없고 무조건 내려오므로)

				if(map[curx][cury-1]==1){// 왼쪽열확인
					while(true) {//반복문으로 이동 할 수 있는지 확인
						--cury;
						if(map[curx][cury-1] == 0) break;//갈 수 없으면 반복문 멈추기
					}
				}else if(map[curx][cury+1]==1){ //  오른쪽열확인
					while(true){
						++cury;
						if(map[curx][cury+1] == 0) break;
					}
				}
			}
			System.out.println("#"+t+" "+(cury-1));
		} 
		sc.close();
  }
   
}

