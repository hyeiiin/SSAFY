import java.io.*; 

import java.util.*; 


class Solution_1228_김한주
{
	//입력 변수 정의 
	static BufferedReader buffer = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer tokens; 
	
	
	static int n; 
	static int k; 
	
	static ArrayList<Integer> cypher;
	
	static void init() throws IOException{
		n = Integer.valueOf(buffer.readLine()); 
		tokens = new StringTokenizer(buffer.readLine());
		cypher = new ArrayList<>(); 
        //처음 암호문 입력 
		for(int i=0; i<n; i++) {
			cypher.add(Integer.valueOf(tokens.nextToken()));
		}
		k= Integer.valueOf(buffer.readLine());
        //명령어 입력 
		tokens = new StringTokenizer(buffer.readLine()); 
		for(int command= 0; command<k; command ++) {
            //i 명령어 입력 
			char dummy = tokens.nextToken().charAt(0);
            //시작할 인덱스 입력  
			int startIdx = Integer.valueOf(tokens.nextToken());
            //삽입할 숫자의 개수 입력  
			int insertNum = Integer.valueOf(tokens.nextToken());
			
            //삽입할 숫자를 전체 반복 
			for(int i=0; i<insertNum; i++) {
                //해당 인덱스에 삽입 
				cypher.add(startIdx, Integer.valueOf(tokens.nextToken()));
                //다음 번째에 넣기 위해 인덱스 증가  
				startIdx++; 
                //이 때 인덱스가 유효값을 넘어가면 조정해줌 
				if(startIdx== cypher.size()) {
					startIdx = cypher.size()-1; 
				}
			}
		}
	}

	public static void main(String[] args ) throws IOException{
		StringBuilder printResult = new StringBuilder();
		//테스트 케이스 10회 반복 
        for(int testCase=1; testCase<=10; testCase++) {
			init();

            //만들어놓은 암호를 출력하기 위해 stringbuilder에 넣기 
			printResult.append("#").append(testCase).append(" ");
			for(int i=0; i<10; i++) {
				printResult.append(cypher.get(i)).append(" ");
			}
			printResult.append("\n");
		}
        //최종 출력 
		System.out.println(printResult);
		
	}
	
}
