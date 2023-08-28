import java.util.*;
import java.io.*;

public class Main_17143_������ {
		public static class Shark {
			int r, c, speed, direction, size;
			
			public Shark(int r, int c, int speed, int direction, int size) {
				super();
				this.r = r;
				this.c = c;
				this.speed = speed;
				this.direction = direction;
				this.size = size;
			}
		}
		
		static Shark[][] map;
		static int R,C,M;
		
		// �� �� �� ��
		static int[] dr = {-1, 1, 0, 0};
		static int[] dc = {0, 0, 1, -1};
		
		public static void main(String[] args) throws IOException {
			BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
			StringTokenizer st = null;
			
			st = new StringTokenizer(br.readLine());
			R = Integer.parseInt(st.nextToken()); // ���� ũ�� R
			C = Integer.parseInt(st.nextToken()); // ���� ũ�� C
			M = Integer.parseInt(st.nextToken()); // ��� ��
			
			if(M==0) { // ��� ������ 0 ����ϰ� �״�� ��
				System.out.println(0);
				return;
			}
			
			map = new Shark[R+1][C+1]; // �� ĭ�� �� �������� ��� ���� �� �����Ƿ� 3���� �迭�� ������
			// ��� ���� -> ��ġ (r,c), �ӷ�(s), �̵� ����(d), ũ��(z)
			// �̵�����(d) -> 1: ��, 2: �Ʒ�, 3: ������, 4: ����
			for(int i=0; i<M; i++) {
				st = new StringTokenizer(br.readLine());
				int r = Integer.parseInt(st.nextToken());
				int c = Integer.parseInt(st.nextToken());
				int s = Integer.parseInt(st.nextToken());
				int d = Integer.parseInt(st.nextToken());
				int z = Integer.parseInt(st.nextToken());
				map[r][c] = new Shark(r, c, s,d,z);
			}
			
			// ���ÿ��� 1���� ������
			// 	- 1. �ش� ������ ���� ���� ����� �� ã�Ƽ� ����
			// ���ÿ��� �� ������, ������ ���� �̵���
			// 	- 1. ����, �ӵ��� ���� �̵�
			//	- 2. �� ������ ���� �������� ��ȯ�ؼ� �̵�
			//	- 3. ĭ�� ��ġ�� ũ�Ⱑ ū ���� �����ϰ� �������� ����
			
			int initCol = 1; // ���ÿ� 1������ ���� ����
			int sum = 0;
			

			while(initCol != C+1) {
				
				// ���ÿ� ���� ����
				for(int r=1; r<=R; r++) { // 1����� ��� �ִ��� ����
					if(map[r][initCol] != null) { // ������
//						System.out.println("������" + " " +map[r][initCol].size); // ���� ��� Ȯ��
						sum += map[r][initCol].size; // �ش� ��ġ�� ��� ũ�� ������ ��  
						map[r][initCol] = null; // ��� ����
						M--; // ��� ���� �� ����
						break; // �ش� ���� �� ������� ��
					}
				}
				// ���� ��
				
				// ��� �̵� ����
				moveShark();
				// ��� �̵� ��
				
				initCol++; // ���ÿ� ���� ���� �̵�
			}
			System.out.println(sum);
			
		}
		
		static boolean isIn(int nr, int nc) {
			if(nr > 0 && nr <= R && nc > 0 && nc <= C) return true;
			return false;
		}
		
		static void moveShark() {
			List<Shark> sharkList = new ArrayList<>();
			
			// map Ž���ϸ鼭 �� ������ sharkList�� �߰��ϰ� map ����д�
			for(int i=0; i<=R; i++) {
				for(int j=0; j<=C; j++) {
					if(map[i][j] != null) {
						sharkList.add(map[i][j]);
						map[i][j] = null;
					}
				}
			}
			
			// list�� �߰��� �� �ϳ��� ������ �̵�������
			for(Shark shark: sharkList) {
				int d = shark.direction - 1; // ��� direction�� 1���Ͱ�, dx dy �� index�� 0���ʹϱ�, 1 ����
				int nr = shark.r; // �ʱ� ��ġ
				int nc = shark.c;

				for(int t = 0; t<shark.speed; t++) { // ����� speed��ŭ �̵�
					nr += dr[d];
					nc += dc[d];
					
					if(!isIn(nr,nc)) { // ���� ����� ���� ��ȯ
						nr -= dr[d]; // �̵� ����ϰ�
						nc -= dc[d];
						
						// ��-�� / ��-�� ���� ���ָ� �Ǵϱ� 0->1, 1->0 / 2->3, 3->2 �� �ٲ��� 
						if(d == 0) {
							d = 1;
						} else if (d==1) {
							d = 0;
						}
						if (d==2) {
							d = 3;
						} else if(d==3) {
							d=2;
						}
						
						nr += dr[d]; // �ٲ��� �������� �ٽ� �̵�
						nc += dc[d];
					}


				}
				// �̵��� ��ģ ���� ��ġ�� ������ ��������
				shark.r = nr;
				shark.c = nc;
				shark.direction = d+1;
				
				// �̵��� ��ģ ��ġ�� �ƹ��͵� ������ �� ��ġ�� ���� ��� �־��ְ�
				if(map[shark.r][shark.c] == null) {
					map[shark.r][shark.c]= shark; 
				}
				// �̵��� ��ģ ��ġ�� �� �����ϸ�, ũ�⸦ ���ؼ� �� ū �ɷ� �־���
				else {
					if(map[shark.r][shark.c].size < shark.size ) {
						map[shark.r][shark.c] = shark;
					}
				}
			}
		}

	}