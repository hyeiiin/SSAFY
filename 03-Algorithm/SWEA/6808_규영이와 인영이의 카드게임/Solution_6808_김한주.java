import java.io.*; 

import java.util.*; 

class Solution
{
	//입력 변수 정의 
	static BufferedReader buffer = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer tokens; 
	
	static final int SIZE = 18; 
	static int[] a;
	static int[]  b; 
	static boolean[] isUsed;
	

    //입력부 정의
	static void init() throws IOException{
		a =  new int[SIZE/2];
		b = new int[SIZE/2];
		isUsed = new boolean[SIZE+1];
		tokens = new StringTokenizer(buffer.readLine());
        //주어진 카드덱 입력 
		for(int i=0; i<SIZE/2; i++) {
			a[i] = Integer.valueOf(tokens.nextToken());
			isUsed[a[i]] = true; 
		}
		
        //아직 사용하지 않은 여벌의 카드로 또다른 카드덱 생성 
		int count = 0; 
		for(int i=1; i<=SIZE; i++) {
			if(!isUsed[i]) {
				b[count++] = i; 
			}
		}
		
		
		
	}
	//뽑히고 난 후 승패를 가린다. 
	static boolean bWin() {
		int bScore = 0;
		int aScore = 0; 
		
		for(int i=0; i<9; i++) {
            //a의 카드가 더 클 경우 a점수에 더한다. 
			if(a[i]>b[i]) {
				aScore += (a[i]+b[i]);
			}
            //b의 카드가 더 클 경우 b 점수에 더한다. 
            else {
				bScore += (a[i]+b[i]);
			}
		}
		return bScore>aScore; 
	}
	
	public static void main(String[] args)throws IOException{
		int T = Integer.valueOf(buffer.readLine());
		StringBuilder sb = new StringBuilder(); 
        //테스트케이스만큼 반복 
		for(int tc=1; tc<=T; tc++) {
			init(); 

			int winCount = 0; 
			int loseCount = 0; 
            //순열을 뽑는다.
			do {
                //뽑은 순열로 승패판단 
				if(bWin()) {
					winCount ++; 
				}else {
					loseCount ++; 
				}
			}while(np(b)); 
			
			sb.append("#").append(tc).append(" ").append(loseCount).append(" ").append(winCount).append("\n");
		}
		
		System.out.println(sb); 
	}
	
	//next permutation으로 순열 생성 
	static boolean np(int[] p) {
		int N = p.length; 
		int i = N-1; 
		//꼭대기 찾기 
		while(i>0&&p[i-1]>=p[i]) --i; 
		
        //다음 순열이 없는 경우 
		if(i==0) return false; 
		
        //꼭대기 직전 위치에 교환할 한단계 큰 수를 뒤쪽부터 찾기 
		int j = N-1; 
		while(p[i-1]>=p[j]) --j; 
		
        //꼭대기 직전 위치의 수와 한단계 큰 수를 교환하기 
		swap(p, i-1, j); 
		
        //꼭대기부터 맨 뒤까지의 수를 오름차순의 형태로 바꾼다.
		int k = N-1;
		while(i<k) {
			swap(p, i++, k--);
		}
		return true; 
	}

    //배열, 인덱스, 인덱스2 를 입력받아 위치내 요소를 교환한다. 
	static void swap(int[] p, int a, int b) {
		int tmp = p[a];
		p[a] = p[b]; 
		p[b] = tmp; 
	}
}	




