import java.util.*;
import java.io.*;

public class Main_16926_김현영 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		int n = Integer.parseInt(st.nextToken());	//이차원배열의 row
		int m = Integer.parseInt(st.nextToken());	//이차원배열의 col
		int r = Integer.parseInt(st.nextToken());	//회전횟수

		// 이차원 배열 넣어주기
		String[][] arr = new String[n][m];
		for (int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < m; j++) {
				arr[i][j] = st.nextToken();
			}
		}

		//이차원배열을 바깥부터 시계방향으로 꺼내서 box 리스트에 저장
		List<List<String>> boxList = new ArrayList<>();
		for (int i = 0; i < n / 2; i++) {
			List<String> box = new ArrayList<>(); //이차원배열의 각 박스를 저장할 리스트
			List<String> left = new ArrayList<>();//박스의 왼쪽부분(왼쪽 중 위,아래와 겹치는 부분 제외)
			List<String> bottom = new ArrayList<>();//박스의 아래부분

			for (int x = i; x < n - i; x++) {
				for (int y = i; y < m - i; y++) {
					// 상자 위
					if (x == i)
						box.add(arr[x][y]);
					//상자 아래
					else if (x == n - i - 1)
						bottom.add(arr[x][y]);
					else {
						// 상자 왼쪽
						if (y == i)
							left.add(arr[x][y]);
						// 상자 오른쪽
						else if (y == m - i - 1)
							box.add(arr[x][y]);
					}
				}
			}
			
			//상자의 왼쪽, 아래 부분을 box에 추가 하고 boxlist에 추가
			for (int j = bottom.size() - 1; j >= 0; j--) {
				box.add(bottom.get(j));
			}
			for (int j = left.size() - 1; j >= 0; j--) {
				box.add(left.get(j));
			} 
			boxList.add(box);
		} 

		//회전 횟수를 리스트 박스의 크기로 나눈 나머지값으로 저장
		//만약 박스 크기가 4일 때 회전 횟수가 6이라면 6%4=2 2번만 회전시키면 됨
		int listSize = boxList.size();
		int[] rotation = new int[listSize];
		for (int i = 0; i < listSize; i++) {
			int rot = (n - (i * 2)) * 2 + (m - 2 - (i * 2)) * 2;
			if(rot == 0)
				rotation[i] = r;
			else
				rotation[i] = r % rot;
		}

		//리스트 박스에 해당하는 회전횟수만큼 리스트 앞부분을 잘라서 뒤에 붙여주기
		//회전횟수가 0이라면 회전시킬 필요 없음
		for (int i = 0; i < listSize; i++) {
			if (rotation[i] == 0 || boxList.get(i).size()==0)
				continue;
			List<String> temp = boxList.get(i).subList(rotation[i], boxList.get(i).size());
			temp.addAll(temp.size(), boxList.get(i).subList(0, rotation[i]));
			boxList.set(i, temp);
		}


		//리스트를 다시 이차원 배열로 전환
		String[][] result = new String[n][m];
		for (int i = 0; i < n / 2; i++) {
			if(boxList.get(i).size() == 0)
				continue;
			String[] temp = boxList.get(i).toArray(new String[boxList.get(i).size()]);
			int index = 0;
			for (int x = i; x < n - i; x++) {
				// 상자 위쪽
				if (x == i) {
					for (int y = i; y < m - i; y++) {
						result[x][y] = temp[index++];
					}
				}
				// 상자 아래
				else if (x == n - i - 1) {
					for (int y = m - i - 1; y >= i; y--) {
						result[x][y] = temp[index++];
					}
				}
				else if (i < x && x < n - i - 1) {
					// 상자 왼쪽
					result[x][i] = temp[temp.length - x+i];
					// 상자 오른쪽
					result[x][m - i - 1] = temp[index++];
				}
			}

		}
		
		
		//회전시킨 결과 출력
		StringBuilder sb = new StringBuilder();
				
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < m; j++) {
				sb.append(result[i][j]).append(" ");
			}
			sb.append("\n");
		}
		System.out.println(sb.toString());

	}// main
}
