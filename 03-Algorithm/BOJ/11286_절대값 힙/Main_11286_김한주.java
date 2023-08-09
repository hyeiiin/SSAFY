import java.io.*; 

import java.util.*; 

class Number implements Comparable<Number>{
    //절대값 저장 
	int num;
    //원본값 저장 
	int origin; 
	
	
	public Number(int num) {
        //절대값 저장 
		this.num = Math.abs(num);
        //원본값 저장 
		 this.origin = num; 
	}
	
	@Override
	public int compareTo(Number other) {
        //절대값이 같을 경우 
		if(this.num == other.num) {
            //부호를 포함한 원본값끼리 비교
			return this.origin - other.origin;
		}
        //기본적으로 절대값으로만 비교
		return this.num - other.num; 
	}
	
    //출력시 원본값만 이용 
	public String toString() {
		return String.valueOf(origin); 
	}
}

class Solution
{
	//입력 변수 정의 
	static BufferedReader buffer = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer tokens; 
	
	static PriorityQueue<Number> pq = new PriorityQueue<>(); 
	
	static int n; 
	
	static final int REMOVE = 0; 
	
	
	
	public static void main(String[] args) throws IOException{
		n = Integer.valueOf(buffer.readLine());
		StringBuilder result = new StringBuilder(); 
		for(int i=0; i<n; i++) {
            //명령 및 데이터 입력 
			int command = Integer.valueOf(buffer.readLine());
            //지우는 명령 처리 
			if(command==REMOVE) {
                //힙에 데이터가 없는 경우 
				if(pq.size()==0) {
                    //0출력 
					result.append(0).append("\n");
				}else {
                    //그외의 경우 데이터 출력 
					Number data = pq.poll();
					result.append(data).append("\n");  
				}
			}else {
                //데이터가 0이아닌 값이 들어온 경우 pq에 넣어준다. 
				pq.add(new Number(command)); 
				
			}
		}
		//최종 결과 출력 
		System.out.println(result); 
	}
	
}	



