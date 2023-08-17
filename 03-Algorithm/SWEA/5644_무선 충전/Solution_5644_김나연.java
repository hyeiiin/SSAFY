import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Solution_5644_김나연 {

	static int t;
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br =new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb;
		
		t=Integer.parseInt(br.readLine());

		for (int test_case = 1; test_case <= t; test_case++) {
			int m = 0, a = 0, ap_x = 0, ap_y = 0, ap_c = 0, ap_p = 0, res = 0;
			int[] m_a = new int[101]; int[] m_b= new int[101]; int[][] bc = new int[100][4];
			int[] user_a = new int[2]; int[] user_b = new int[2];
			sb = new StringBuilder();
			
			st = new StringTokenizer(br.readLine());
			m=Integer.parseInt(st.nextToken()); // m: 총 이동 시간
			a=Integer.parseInt(st.nextToken()); // a: bc의 개수

			st = new StringTokenizer(br.readLine());
			for (int i = 0; i < m; i++) { // 사용자 A 이동
				m_a[i] = Integer.parseInt(st.nextToken());
			}
			
			st = new StringTokenizer(br.readLine());
			for (int i = 0; i < m; i++) { // 사용자 B 이동
				m_b[i] = Integer.parseInt(st.nextToken());
			}

			for (int i = 0; i < a; i++) {
				st = new StringTokenizer(br.readLine());
				bc[i][0] = Integer.parseInt(st.nextToken());
				bc[i][1] = Integer.parseInt(st.nextToken());
				bc[i][2] = Integer.parseInt(st.nextToken()); 
				bc[i][3] = Integer.parseInt(st.nextToken());
			}

			user_a[0] = 1; user_a[1] = 1;
			user_b[0] = 10; user_b[1] = 10;

			for (int i = 0; i <= m; i++) {
				// 충전
				ArrayList<Integer> a_list = new ArrayList<Integer>();
				ArrayList<Integer> b_list = new ArrayList<Integer>();

				for (int j = 0; j < a; j++) {
					if (Math.abs(bc[j][0] - user_a[0]) + Math.abs(bc[j][1] - user_a[1]) <= bc[j][2]) {
						a_list.add(j);
						// cout <<i<<"번 째, a: "<< j << "\n";
					}
					if (Math.abs(bc[j][0] - user_b[0]) + Math.abs(bc[j][1] - user_b[1]) <= bc[j][2]) {
						b_list.add(j);
						// cout << i << "번 째, b: " << j << "\n";
					}
				}

				int current_mx = 0;
				// a, b 둘 다 없는 경우
				if (a_list.size() == 0 && b_list.size() == 0) {
				}

				// a, b 하나만 있는 경우
				else if (a_list.size() != 0 && b_list.size() == 0) {
					for (int al : a_list) {
						current_mx = Math.max(current_mx, bc[al][3]);
					}
				}

				else if (a_list.size() == 0 && b_list.size() != 0) {
					for (int bl : b_list) {
						current_mx = Math.max(current_mx, bc[bl][3]);
					}
				}

				// a, b 모두 bc 있는 경우
				else if (a_list.size() != 0 && b_list.size() != 0) {
					for (int al : a_list) {
						for (int bl : b_list) {
							if (al == bl) {
								current_mx = Math.max(current_mx, bc[al][3]);
							}
							else {
								current_mx = Math.max(current_mx, bc[al][3] + bc[bl][3]);
							}
						}
					}
				}

				res += current_mx;

				// 이동
				if (m_a[i] == 1) user_a[1]--;
				else if (m_a[i] == 2) user_a[0]++;
				else if (m_a[i] == 3) user_a[1]++;
				else if (m_a[i] == 4) user_a[0]--;

				if (m_b[i] == 1) user_b[1]--;
				else if (m_b[i] == 2) user_b[0]++;
				else if (m_b[i] == 3) user_b[1]++;
				else if (m_b[i] == 4) user_b[0]--;
			}
			sb.append("#").append(test_case).append(" ").append(res);
			System.out.println(sb);
		}
		
	}

}
