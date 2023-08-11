package algorithm.baekjoon;

import java.util.*;
import java.io.*;

//치킨 배달
class House{ //집
	int x;
	int y;
	
	House(int x, int y){
		this.x = x;
		this.y = y;
	}
}
class Chicken { //치킨집
	int x;
	int y;
	
	Chicken(int x, int y) {
		this.x = x;
		this.y = y;
	}
}
public class Main_15686_문혜린 {
	static int M;
	static ArrayList<House> house;
	static ArrayList<Chicken> chicken;
	static int minDistance = Integer.MAX_VALUE; //최소 치킨 거리
	static int input[]; //원소 가지는 배열
	static int numbers[]; //조합 저장
	
	//도시 치킨 거리 최솟값 구하기
	public static void getDistance() {
		int totalDistance = 0; //도시 치킨 거리
		int[] distance = new int[house.size()]; //각각 치킨 거리
		//최솟값 구하기 위한 초기화
		for (int i = 0; i < house.size(); i++) {
			distance[i] = Integer.MAX_VALUE;
		}
		
		for (int i = 0; i < house.size(); i++) { //집
			//치킨 거리 구하기
			for (int j = 0; j < numbers.length; j++) { //치킨집 (조합)
				int tmp = Math.abs(house.get(i).x-chicken.get(numbers[j]).x) + Math.abs(house.get(i).y-chicken.get(numbers[j]).y);
				//최소 치킨집-집 거리 구하기
				if(tmp<distance[i]) {
					distance[i] = tmp; //치킨 거리 갱신
				}
			}
		}
		
		//도시 치킨 거리
		for (int i = 0; i < house.size(); i++) {
			totalDistance += distance[i];
		}
		
		//최소 도시 치킨 거리
		if(totalDistance < minDistance) {
			minDistance = totalDistance;
		}
	}
	
	//폐업시키지 않을 치킨집 조합 구하기
	public static void comb(int cnt, int start) { //현재까지 뽑은 조합 원소 개수, 조합 시도할 원소 시작 인덱스
		if(cnt == M) { //살릴 치킨집 조합 완성
			getDistance();
		}
		else {
			for (int i = start; i < chicken.size(); i++) {
				numbers[cnt] = input[i];
				comb(cnt+1, i+1);
			}
		}
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken()); //배열 크기 NxN
		M = Integer.parseInt(st.nextToken()); //폐업시키지 않을 치킨집 개수
		
		house = new ArrayList<>();
		chicken = new ArrayList<>();
		
		for (int i = 1; i <= N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 1; j <= N; j++) {
				int tmp = Integer.parseInt(st.nextToken());
				if(tmp == 1) { //집일 경우
					house.add(new House(i, j));
				}
				else if(tmp == 2) { //치킨집일 경우
					chicken.add(new Chicken(i, j));
				}
			}
		}
		
		//치킨집 인덱스 삽입
		input = new int[chicken.size()];
		numbers = new int[M];
		for (int i = 0; i < chicken.size(); i++) {
			input[i] = i;
		}
		comb(0, 0); //M개 뽑는 조합 구하기
		
		System.out.println(minDistance);
	}

}
