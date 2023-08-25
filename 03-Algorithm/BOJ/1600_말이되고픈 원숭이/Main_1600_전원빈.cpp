#include<iostream>
#include<queue>
#include<tuple>
using namespace std;

int k;
int h, w;
int map[202][202];
int hdy[] = {-2, -1, 2, 1, -2, -1, 2, 1};
int hdx[] = {-1, -2, -1, -2, 1, 2, 1, 2};
int dy[] = {1, 0, -1, 0};
int dx[] = {0, 1, 0, -1};
int visited[202][202][32];
int mi = 987654321;
struct pan{int y, x, z, cnt;};

void move(){
	queue<pan> q;
	q.push({0,0,0,1});
	visited[0][0][0] = 1;
	while(q.size() != 0){
		int y = q.front().y;
		int x = q.front().x;
		int z = q.front().z;
		int cnt = q.front().cnt;
		q.pop();
		if(y == h-1 && x == w-1) {
			mi = cnt;
			return;
		}
		if(z < k){
			for(int i = 0; i < 8; i++){
			int ny = y + hdy[i];
			int nx = x + hdx[i];
			int nz = z + 1;
			int ncnt = cnt+1;
			if(ny < 0 || ny >= h || nx < 0 || nx >= w || nz > k)continue;
			if(map[ny][nx] == 1)continue;
			if(visited[ny][nx][nz] == 1)continue;
			visited[ny][nx][nz] = 1;
			q.push({ny, nx, nz, ncnt});
			}
		}
		for(int i = 0; i < 4; i++){
			int ny = y + dy[i];
			int nx = x + dx[i];
			int nz = z;
			int ncnt = cnt +1;
			if(ny < 0 || ny >= h || nx < 0 || nx >= w|| nz > k)continue;
			if(map[ny][nx] == 1)continue;
			if(visited[ny][nx][nz] == 1)continue;
			visited[ny][nx][nz] = 1;
			q.push({ny, nx, nz, ncnt});
		}
		for(int i = 0; i < k; i++){
			cout << "i´Ù\n";
			for(int u = 0; u < h; u++){
				for(int n = 0; n <w; n++){
					cout << visited[u][n][i] << " ";
				}
				cout << "\n";
			}
			cout << "\n";
		}
		
	}
}

int main(){
	ios_base::sync_with_stdio(false);
	cin.tie(NULL); cout.tie(NULL);
	cin >> k;
	cin >> w >> h;
	for(int i = 0; i < h; i++){
		for(int j = 0; j <w; j++){
			cin>> map[i][j];
		}
	}
	
	move();
	
	if(mi == 987654321)cout << -1;
	else cout << mi;
}
 
