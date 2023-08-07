/* [Python]
 * for tc in range(1, 11):
    # n: 원문 암호문의 길이
    n = int(input())

    # 원본 암호문
    pw = list(map(int, input().split()))

    # 명령어의 개수 (5 ~ 10)
    num = int(input())
    arr = list(input().split())

    max_num = 0
    for i in range(len(arr)):
        if arr[i] == 'I':
            if int(arr[i+1]) > max_num:
                max_num = int(arr[i+1])

    command = [[] for _ in range(num)]

    count = -1
    for i in range(len(arr)):
        if arr[i] == 'I':
            count += 1
            continue
        else:
            command[count].append(int(arr[i]))

    for i in range(num):
        for j in range(command[i][1], 0, -1):
            pw.insert(command[i][0], command[i][j+1])

    print('#' + str(tc), *pw[0:10])

 */

package SWEA;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.StringTokenizer;

public class D3_1228 {
	
	static int pwLen, cmdLen, ROW;		// 첫번째 줄: 원본 암호문의 길이 (10 <= pwLen <= 20) / 세번째 줄: 명령어 개수 (5 <= cmdLen <= 10)
	static LinkedList<Integer> PW;		// 두번째 줄: 원본 암호문 LinkedList
	static int IDX, NUM, CMD;			// 네번째 줄: 명령어 배열
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		for (int TC = 1; TC < 11; TC++) {
		
			// 첫번째 줄: 원본 암호문의 길이
			pwLen = Integer.parseInt(br.readLine());
			st = new StringTokenizer(br.readLine());
			
			
			// 두번째 줄: 원본 암호문 LinkedList
			PW = new LinkedList<>();
			while (st.hasMoreTokens()) PW.add(Integer.parseInt(st.nextToken()));
			
			
			// 세번째 줄: 명령어 개수 (5 <= cmdLen <= 10)
			cmdLen = Integer.parseInt(br.readLine());
			st = new StringTokenizer(br.readLine());
			
			
			// 네번째 줄: 명령어 배열
			while (st.hasMoreTokens()) {
				
				// I 버리기
				String I = st.nextToken();
				IDX = Integer.parseInt(st.nextToken());
				NUM = Integer.parseInt(st.nextToken());
				
				// PW 연산
				for (int i = 0; i < NUM; i++) {
					CMD = Integer.parseInt(st.nextToken());
					PW.add(IDX, CMD);
					IDX++;
				}
			
			}
			
			// 출력
			System.out.print("#" + TC + " ");
			for (int i = 0; i < 10; i++) {
				System.out.print(PW.pollFirst() + " ");
			}
			System.out.println();
		}
	}
}
