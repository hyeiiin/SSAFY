import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;

class Pixel{ // 한 픽셀의 좌표와 rgb 문자값 저장하는 용도
	int r;
	int c;
	char rgb;
	public Pixel(int r, int c, char rgb) {
		this.r = r;
		this.c = c;
		this.rgb = rgb;
	}
}
public class Main_10026_문예은 {
	static int N; // 배열 크기
	static int[][] del = {{-1,0},{1,0},{0,-1},{0,1}}; // 사방탐색 배열 
	
	private static boolean isIn(int r, int c) { // 배열 내 범위인지 체크
		if(r < 0 || c < 0 || r >= N || c >= N) return false;
		return true;
	}
	private static int bfs(char[][] pic) {
		Queue<Pixel> checkQueue = new ArrayDeque<>(); // 같은 픽셀 좌표만 모아서 사방탐색시킬 큐
		boolean[][] visited = new boolean[N][N]; // 방문배열
		int count = 0; // 최종 출력시킬 영역 개수
		for (int r = 0; r < N; r++) {
			for (int c = 0; c < N; c++) {
				if(visited[r][c]) continue; // 이미 처리한 픽셀은 건너뛰기
				checkQueue.add(new Pixel(r, c, pic[r][c])); // 방문하지 않은 픽셀, 큐에 담기
				visited[r][c] = true; // 해당 픽셀 방문처리
				while (!checkQueue.isEmpty()) { // 같은 픽셀이 주변에 더이상 없을 때까지
					Pixel current = checkQueue.poll(); // 하나 꺼내서 사방탐색 후 같은 픽셀 찾기
					for (int i = 0; i < 4; i++) {
						int dr = current.r + del[i][0];
						int dc = current.c + del[i][1]; // 사방탐색 좌표
						if(isIn(dr, dc) && !visited[dr][dc]) { // 탐색좌표가 배열 범위 내에 있고 방문 전일 때
							if (pic[dr][dc]==current.rgb) { // 탐색좌표의 색과 현재좌표의 색이 같다면
								checkQueue.add(new Pixel(dr, dc, pic[dr][dc])); // 탐색좌표 픽셀, 큐에 담기
								visited[dr][dc] = true; // 방문처리
							}
						}
					}
				}
				count++; // 큐가 빌 때(더이상 같은 픽셀 주변에 없을 때) 영역 하나 생성
			}
		}
		return count;
	}
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		N = Integer.parseInt(br.readLine()); // 배열 크기
		char[][] pic = new char[N][N]; // 그림 rgb 배열
		char[][] pic_yesol = new char[N][N]; // 적록색약 그림 (초록인 경우 'R'로 변경, 빨강-파랑으로만 이루어진 배열로)
		for (int r = 0; r < N; r++) {
			String oneline = br.readLine();
			for (int c = 0; c < N; c++) {
				pic[r][c] = oneline.charAt(c); // rgb 한 문자씩 배열에 넣기
				if (pic[r][c]=='G') { // 초록색일 때
					pic_yesol[r][c] = 'R'; // 적록색약 그림 배열에서 빨강과 초록 똑같이 처리
				} else {
					pic_yesol[r][c] = oneline.charAt(c); // 빨강, 파랑은 문자 그대로 저장
				}
			}
		}
		System.out.println(bfs(pic)+" "+bfs(pic_yesol)); // 일반 그림배열과 적록색약 그림배열을 각각 탐색하여 영역개수 출력
	}
}
