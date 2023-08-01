import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution_1210_문예은 {
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		for(int t = 0; t < 10; t++) { // 사다리타기 총 10번 반복
			int T = Integer.parseInt(br.readLine()); // 테스트케이스 번호 입력
			
			int[] targetIdx = new int[2]; // 2를 가진 목표 지점의 x,y 좌표 저장 (탐색 출발 지점으로 설정함)
			int[][] ladder = new int[100][100]; // 사다리 크기 100*100으로 고정됨
			for(int i = 0; i < 100; i++) {
				st = new StringTokenizer(br.readLine());
				for(int j = 0; j < 100; j++) {
					ladder[i][j] = Integer.parseInt(st.nextToken());
					if(i == 99 && ladder[i][j]==2) { // 값이 2이면 좌표 저장 (마지막 행으로 한정)
						targetIdx[0] = i;
						targetIdx[1] = j;
					}
				}
			} // 사다리 배열값 저장 끝
			// 목표지점에서부터 거꾸로 탐색 시작
			while(targetIdx[0] != 0) { // 맨 처음 99행에서 출발해서 0행에 도달할 시 종료
				int leftIdx = targetIdx[1]-1;
				int rightIdx = targetIdx[1]+1; 
				if(isIn(leftIdx) && ladder[targetIdx[0]][leftIdx] == 1) { // 왼쪽 탐색, 1이면 이동
					while(isIn(leftIdx-1) && ladder[targetIdx[0]][leftIdx-1] != 0) { // 왼쪽이 0일 때, 벽일 때 정지
						targetIdx[1] -= 1;
						leftIdx = targetIdx[1];
					}
					targetIdx[0] -= 1; // 정지 후 위로 한칸 올라가기
				}
				else if (isIn(rightIdx) && ladder[targetIdx[0]][rightIdx] == 1) { // 오른쪽 탐색, 1이면 이동
					while(isIn(rightIdx+1) && ladder[targetIdx[0]][rightIdx+1] != 0) { // 오른쪽이 0일 때, 벽일 때 정지
						targetIdx[1] += 1;
						rightIdx = targetIdx[1];
					}
					targetIdx[0] -= 1; // 정지 후 위로 한칸 올라가기
				}
				else targetIdx[0] -= 1; // 위의 해당사항 없을 때(왼쪽오른쪽이 벽이거나 1이 아니면) 위로 한칸 올라가기
			}
			System.out.printf("#%d %d%n", T, targetIdx[1]);
		}
	}
	public static boolean isIn(int idx) {
		if (idx < 0 || idx >= 100) return false;
		return true;
	}
}


