import java.io.*;
import java.util.*;
/*
 * 리스트 두개 사용 맛, 칼로리
 * 해시맵순회하면서 칼로리 확인하고 맛점수저장 -> 부분조합, 맛점수 리스트로 만듦
 * 12, 13, 14,15, 123,124,125 ....
 * 
 * */

public class Solution_5215_김현영 {
	static Set<Integer> grades; // 조합에 대한 맛점수의 리스트
	static List<Integer> nowComps;	//현재 조합에 대한 재료들 인덱스
	
	static List<Integer> grade;	//맛점수 리스트
	static List<Integer> calorie;//칼로리 리스트
	
	static int nowCalorie;	//현재 조합의 칼로리합
	static int nowGrade;	//현재 조합의 맛점수합
	
	public static void findHamburger (int calorieLimit, int start) {
		nowCalorie = 0; //현재 칼로리 초기화
		nowGrade = 0; //현재 맛점수합 초기화
		
		for (int i : nowComps) { //현재 조합까지 칼로리와 맛점수 합 구하기
			nowCalorie += calorie.get(i);
			nowGrade += grade.get(i);
		} 
		
		if(nowCalorie > calorieLimit) //현재 조합의 칼로리가 제한칼로리보다 크다면 종료
			return;
		else	//현재 조합의 칼로리가 제한 칼로리보다 낮다면 맛조합리스트에 저장 
			grades.add(nowGrade);
		
		//재료들 부분조합 구하기
		for (int i = start; i < grade.size(); i++) {
			nowComps.add(i);
			findHamburger(calorieLimit, i+1);
			nowComps.remove(nowComps.size()-1);	//마지막 재료 삭제
		}
	}
	

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int T = Integer.parseInt(st.nextToken()); // 테스트케이스 수

		for (int t = 1; t <= T; t++) {
			st = new StringTokenizer(br.readLine());
			int n = Integer.parseInt(st.nextToken()); // 재료 수
			int calorieLimit = Integer.parseInt(st.nextToken()); // 제한 칼로리

			grade = new ArrayList<>();
			calorie = new ArrayList<>();
			
			// 재료 수만큼 맛과 칼로리 입력
			for (int i = 0; i < n; i++) {
				st = new StringTokenizer(br.readLine());
				grade.add(Integer.parseInt(st.nextToken()));
				calorie.add(Integer.parseInt(st.nextToken()));
			}
 
			//햄버거 재료의 모든 부분 조합 구하기
			nowComps = new ArrayList<>();
			grades = new HashSet<>();
			findHamburger(calorieLimit, 0);
			
			//재료부분조합의 맛점수 합 리스트 내림차순정렬
			List<Integer> gradesList = new ArrayList<>(grades);
			Collections.sort(gradesList, Collections.reverseOrder()); 
			
			//출력
			StringBuilder sb = new StringBuilder();
			sb.append("#").append(t).append(" ");
			sb.append(gradesList.get(0));
			System.out.println(sb.toString());
		}
	}

}
