

import java.io.*;
import java.util.*;




public class Solution {
    static BufferedReader buffer = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer tokens;
   
    //4개의 자석 
    
	//각 자석은 8개의 날 
    	//날마다 N S 
    
    //회전 
    	//붙어 있는 날의 자성과 다를 경우에만 반대방향으로 1칸 회전 
    
   static int k; // 
   
   static int[][] magnet; 
   static int[][] orders; 
   
   
   static void init() throws IOException{
	   magnet = new int[4][8]; 
	   
	   k= Integer.valueOf(buffer.readLine()); 
	   
	   for(int x=0; x<4; x++) {
		   tokens = new StringTokenizer(buffer.readLine()); 
		   for(int y=0; y<8; y++) {
			   magnet[x][y] = Integer.valueOf(tokens.nextToken());
		   }
	   }
	   orders = new int[k][2]; 
	   
	   for(int i=0; i<k; i++) {
		   tokens = new StringTokenizer(buffer.readLine()); 
		   orders[i][0] = Integer.valueOf(tokens.nextToken())-1;
		   orders[i][1] = Integer.valueOf(tokens.nextToken()); 
	   }
	   
	   
   }
   
   
   static int[] getRotates(int rowTarget, int order) {
	   
	   int[] rotates = new int[4]; 
	   
	   int prev = order; 
	   int next = order; 

       //현재 톱니 바퀴의 6번째 ,다음 톱니바퀴의 2 번째 요소 끼리 연결
	   
	   rotates[rowTarget] = order; 
	   //row 기준 앞에거 회전되는지 확인  
	   for(int row= rowTarget; row>=1; row--) {
		   if(magnet[row][6]!=magnet[row-1][2]) {
			   if(prev==-1) {
				   prev = 1; 
			   }else if(prev==1) {
				   prev = -1; 
			   }
			   rotates[row-1] = prev; 
		   }else {
			   break;
		   }
	   }
	   
	   
	   //row 기준 뒤에서 회전되는지 확인 
	   
	   for(int row = rowTarget; row<3; row++) {
		   if(magnet[row][2]!=magnet[row+1][6]) {
			   if(next==-1) {
				   next = 1; 
			   }else if(next==1) {
				   next = -1; 
			   }
			   rotates[row+1] = next; 
		   }else {
			   break; 
		   }
		   
	   }
	   
	   return rotates;
	   
	   
   }
   
   
   public static void main(String[] args)throws IOException{
	   tokens = new StringTokenizer(buffer.readLine()); 
	   int T = Integer.valueOf(tokens.nextToken());
	   StringBuilder sb = new StringBuilder(); 
	   //시간복잡도 : 50 * 4 * 8 = 1600 
	   for(int t=1; t<=T; t++) {
		   init(); 
		   
		   for(int o=0; o<k; o++) {
			   int row = orders[o][0]; 
			   int order = orders[o][1]; 
			   
			   //돌릴려는 row를 기준으로 위 아래 보면서 각 행의 돌리는 경우를 판단하여 배열에 담아주기 
               //0 :안돌림
               //1: 정방향 돌리기
               //-1 : 역방향 돌리기 
			   int[] rotates = getRotates(row, order); 
			   
			   
			   //돌릴려는 option을 담을 배열을 참고해 
               //모든 행 순회하면서 option에 맞게 돌려주기 
			   for(int i=0; i<4; i++) {
				   //8
				   rotate(i, rotates[i]);
			   }
			   
			
		   }
		   
//		   //4
            //점수 총합 구하기 
		   int score = getScore();
		   sb.append("#").append(t).append(" ").append(score).append("\n");
	   }
	   System.out.println(sb); 
	   
   }
   
   static int getScore() {
	   int score=0;
	   int partScore = 1; 
	   for(int row=1; row<=4; row++) {
		   score += magnet[row-1][0]*partScore; 
		   partScore*=2;    
	   }
	   
	   return score; 
	  
   }
   
   static void rotate(int row, int option) {
	   
	   //정방향 
	   if(option ==1) {
		   int tmp = magnet[row][7]; 
		   for(int i=7; i>=1; i--) {
			   magnet[row][i] = magnet[row][i-1]; 
		   }
		   magnet[row][0] = tmp; 
		   
	   }//역방향 
	   else if(option==-1) {
		   int tmp = magnet[row][0]; 
		   for(int i=0; i<=6; i++) {
			   magnet[row][i] = magnet[row][i+1]; 
		   }
		   magnet[row][7] = tmp; 
	   }
   }
    
   
   static void print(int[][] arr) {
	   for(int[] ar: arr) {
		   for(int a: ar) {
			   System.out.print(a+" ");
		   }System.out.println(); 
	   }
   }

}
