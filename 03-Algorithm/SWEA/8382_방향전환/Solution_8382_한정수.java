package algorithm;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution_8382_한정수 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int T = Integer.parseInt(st.nextToken());
		
		for (int test_case=1 ; test_case <= T ; test_case++) {
			st = new StringTokenizer(br.readLine());
			int x1 = Integer.parseInt(st.nextToken());
			int y1 = Integer.parseInt(st.nextToken());
			int x2 = Integer.parseInt(st.nextToken());
			int y2 = Integer.parseInt(st.nextToken());
			
			int dist_x = Math.abs(x2-x1);
			int dist_y = Math.abs(y2-y1);
			
			int result = 0;
			// dist_x == dist_y >> result = dist_x * 2 or dist_y * 2
			// dist_x +1 == dist_y >> result = dist_x * 2 + 1
			// dist_x == dist_y +1 >> result = dist_y * 2 + 1
			
			
			// dist_x + 1 < dist_y >> result += 2, dist_x += 1, dist_y -= 1
			// dist_x > dist_y+1 >> result += 2, dist_x -= 1, dist_y += 1
			
			// 결국 문제를 풀기 위해 필요한건 가로로 움직여야하는 거리(dist_x)와 세로로 움직여야하는 거리(dist_y).
			// 이때, 점 a에서 점 b로 옮기는 것은 (0, 0)에서 (dist_x ,dist_y)로 옮기는 일이랑 똑같음.
			
			// ex) (1, 5)에서 (10, 8)로 움직이는 것은, dist_x = 9, dist_y = 3이므로
			//     (0,0)에서 (9, 3)으로 옮기는 거랑 똑같은 일임.
			
			// 일반적으로 와리가리 치지 않고 바로 도착할 수 있는 경우는 다음의 3가지
			// 1. dist_x == dist_y    ex) (0,0) (2, 2) / (1, 5) (2, 6) >> (0,0) (1, 1)
			//     >> result = dist_x * 2 또는 dist_y * 2
			// 2. dist_x +1 == dist_y  ex) (0,0) (0,1) / (1, 3) (5, 8) >> (0,0) (4, 5) 스타트를 세로선으로 시작하면 세/가/세/가/세/가/세 이런식으로.
			//     >> result = 1 + dist_x * 2 
			// 3. dist_x == dist_y +1  ex) (0,0) (1,0) / (4, 2) (1, 4) >> (0,0) (3, 2) 스타트를 가로선으로 시작. 가/세/가/세/가
			//     >> result = 1 + dist_y * 2
			
			// 그럼 dist_x - dist_y 가 2이상 일 경우에는 어떻게 되는가?	
			
			// (0,0) (0, 3)을 예시로 들면,
			// (0, 0) >> (1,1)로 움직인 후, (1,1)에서 (0,3)으로 가기
			//    => 2 + (0,0)에서 (1,2)로 가기 >> (dist_x + 1 == dist_y) 임.
			//    => 따라서 result = 2 + (1 + 2*dist_x) = 2 + 3 = 5
			
			
			
			// (2,0) (0,5)를 예시로 들면, 
			// (0,0) (2, 5)와 동일.  
			// (-1, 1)로 이동 후, (2, 5)로 이동    
			// 2 + (-1,1)에서 (2,5) 
			// 2 + (0,0)에서 (3,4)
			// 2 + (1 + dist_x*2)
			// 2 + 7
			
			// 즉, dist_x < dist_y 이고, 그 차이가 2 이상이라면
			// dist_y는 1 작게, dist_x는 1 키우고 result += 2
			
			// 반대로 dist_x > dist_y 이고, 그 차이가 2 이상이라면
			// dist_x는 1 작게, dist_y는 1 키우고 result += 2
			
			// 다른 관점으로 생각한다면, (0, 0)에서 (a, b)로 갈때, 최대한 (a,b)를 (y=x) 직선에 가까워지도록 
			// 점(a,b)를 고정시키고 전체 공간을 평행이동시킨다는 느낌.
			
			
			
			while(true) {
				if (dist_x == dist_y) {
					result += dist_x * 2;
					break;
				}
				else if (dist_x + 1 == dist_y) {
					result += dist_x * 2 +1;
					break;
				}
				else if (dist_x == dist_y+1) {
					result += dist_y * 2 +1;
					break;
				}
				else if (dist_x + 1 < dist_y) {
					result += 2;
					dist_x += 1;
					dist_y -= 1;
				}
				else if (dist_x > dist_y+1) {
					result += 2;
					dist_x -= 1;
					dist_y += 1;
				}
				
			}
			
			
			
			System.out.println("#"+test_case+" "+result);
			
		}
			
	}

}
