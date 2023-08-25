import java.io.*;
import java.util.*;
public class Main {
	static int R,C,M,r,c,s,d,z,alive;
	static int[] nearest;
	static Deque<Shark>[][] ocean; 
	static int[][] maxMap;
	static Shark[] sharks;
	static int[][] dir= {{-1,0},{1,0},{0,1},{0,-1}};
	public static void main(String[] args) throws IOException{
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st=new StringTokenizer(br.readLine());
		R=Integer.parseInt(st.nextToken());
		C=Integer.parseInt(st.nextToken());
		M=Integer.parseInt(st.nextToken());
		alive=M;
		ocean=new ArrayDeque[R][C];
		sharks=new Shark[M];
		int answer=0;
		for(int i=0;i<R;i++) {
			for(int j=0;j<C;j++) {
				ocean[i][j]=new ArrayDeque<>();
			}
		}
		for(int i=0;i<M;i++) {
			st=new StringTokenizer(br.readLine());
			r=Integer.parseInt(st.nextToken())-1;
			c=Integer.parseInt(st.nextToken())-1;
			s=Integer.parseInt(st.nextToken());
			d=Integer.parseInt(st.nextToken())-1;
			z=Integer.parseInt(st.nextToken());
			sharks[i]=new Shark(r,c,s,d,z,i);
			ocean[r][c].add(sharks[i]);
		}
		Shark pick;
		for(int i=0;i<R;i++) {
			if(!ocean[i][0].isEmpty()) {
				pick=ocean[i][0].poll();//잡고
				answer+=pick.size;//잡은 사이즈 더하고
				sharks[pick.index].alive=false;
				alive--;
				break;
			}
		}
		for(int i=1;i<C;i++) {
			if(alive!=0) {
				fishing();
				if(ocean[nearest[i]][i].isEmpty()) 
					continue;
				pick=ocean[nearest[i]][i].poll();//잡고
				answer+=pick.size;//잡은 사이즈 더하고
				sharks[pick.index].alive=false;
				alive--;
			}
		}
		System.out.println(answer);
	}
	public static void fishing() {
		nearest=new int[C];
		maxMap=new int[R][C];
		Shark curr;
		int nr=0,nc=0;
		//상어이동
		for(int i=0;i<M;i++) {
			if(sharks[i].alive) {
				curr=sharks[i];
				nr=curr.r;
				nc=curr.c;
				for(int j=0;j<curr.pace;j++) {
					if(curr.direction<2) {//상하
						if(nr==R-1&&curr.direction==1||nr==0&&curr.direction==0) {
							curr.direction=curr.direction==1?0:1;
						}
						nr=nr+dir[curr.direction][0];
					}else {
						if(nc==C-1&&curr.direction==2||nc==0&&curr.direction==3) {
							curr.direction=curr.direction==2?3:2;
						}
						nc=nc+dir[curr.direction][1];
					}
				}
				ocean[nr][nc].addLast(ocean[curr.r][curr.c].pollFirst());
				maxMap[nr][nc]=ocean[nr][nc].peekLast().size>maxMap[nr][nc]?ocean[nr][nc].peekLast().size:maxMap[nr][nc];
				sharks[i].r=nr;
				sharks[i].c=nc;
			}
		}
		//상어 잡아 먹기
		Shark maxShark=null;
		for(int c=C-1;c>=0;c--) {
			for(int r=R-1;r>=0;r--) {
				if(!ocean[r][c].isEmpty()) {
					while(!ocean[r][c].isEmpty()) {//한마리만 남을 때까지
						curr=ocean[r][c].poll();
						if(curr.size==maxMap[r][c]) {
							maxShark=curr;
						}else {
							sharks[curr.index].alive=false;//다 죽임
							alive--;
						} 
					}
					ocean[r][c].add(maxShark);
					nearest[c]=r;
				}
			}
		}
	}
	public static class Shark {
		int size,direction,pace,index,r,c;
		boolean alive;
		public Shark(int r,int c,int pace,int direction,int size,int index) {
			this.r=r;
			this.c=c;
			this.pace=pace;
			this.direction=direction;
			this.size=size;
			this.index=index;
			alive=true;
		}
	}
}
