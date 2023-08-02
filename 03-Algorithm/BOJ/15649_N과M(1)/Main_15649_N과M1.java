import java.util.Scanner;

/**
 * BOJ 15649 N과M(1)
 * - 1부터 N까지 자연수 중에서 중복 없이 M개를 고른 수열
 * s - 73336kb/	2240ms
 * @author KimDaol
 *
 */
public class Main_15649_N과M1 {
	static int N, M;
	static int[] number;
	static boolean[] isSelected;
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		N = sc.nextInt();
		M = sc.nextInt();
		
		number = new int[M];
		isSelected = new boolean[N+1]; //인덱스를 숫자처럼 사용 예정, 1부터 쓸거라서 N+1
		//매개 변수 cnt : 뽑은 개수
		Permutation(0);//변경 되는 값은 몇개째 뽑는 값인지 뿐이라 매개변수 1개

	}
	
	private static void Permutation(int cnt) {
		if(cnt == M) {//기저 조건, M개 뽑았으면?
			for(int i = 0; i<M; i++) {
				System.out.print(number[i]+" ");
			}
			System.out.println();
			return;
		}
		
		for(int i = 1; i<=N; i++) {
			if(isSelected[i]==false) {
				number[cnt]=i; //cnt 번째에 i숫자 사용
				isSelected[i] = true;//사용 체크.
				Permutation(cnt+1); //다음 숫자 선택하러 가기
				isSelected[i]=false; //return되어 되돌아 왔으면 끝까지 갔다가 돌아온것. 사용 해제.
				//static 배열이라서 값이 유지 되므로 반드시 해제!
			}
		}

	}

}
