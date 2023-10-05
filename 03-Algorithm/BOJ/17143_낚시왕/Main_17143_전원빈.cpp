#include<bits/stdc++.h>
using namespace std;

struct shark{
	int sp;
	int di;
	int si;
};

shark water[101][101];
int r, c, m;
int ans;


void jap(int now){
	for(int i = 1; i <=r; i++){
		int size = water[i][now].si;
		if(size>0){
			ans += size;
			water[i][now] = {};
			break;
		}
	}
}

void move(){
	shark nextwater[101][101];
	memset(nextwater, {}, sizeof(nextwater));
	for(int i = 1; i <= r; i++){
		for(int j = 1; j <= c; j++){
			if(water[i][j].di != 0){
				int speed = water[i][j].sp;
				int direct = water[i][j].di;
				int size = water[i][j].si;
				if(direct == 1){
					int ny = i;
					int d = 0;
					for(int u = 0; u < speed; u++){
						if(d==0){
							if(ny == 1){
								d=1;
								ny++;
							}else{
								ny--;
							}
						}else{
							if(ny==r){
								d=0;
								ny--;
							}
							else{
								ny++;
							}
						}
					}
					if(nextwater[ny][j].si > size)continue;
					if(d==1)direct = 2;
					nextwater[ny][j] = {speed, direct, size};
				}else if(direct == 2){
					int ny = i;
					int d = 1;
					for(int u = 0; u < speed; u++){
						if(d==0){
							if(ny == 1){
								d=1;
								ny++;
							}else{
								ny--;
							}
						}else{
							if(ny == r){
								d=0;
								ny--;
							}
							else{
								ny++;
							}
						}
					}
					if(nextwater[ny][j].si > size)continue;
					if(d==0)direct = 1;
					nextwater[ny][j] = {speed, direct, size};
				}else if(direct == 3){
					int nx = j;
					int d = 1;
					for(int u = 0; u < speed; u++){
						if(d==0){
							if(nx == 1){
								d=1;
								nx++;
							}else{
								nx--;
							}
						}else{
							if(nx==c){
								d=0;
								nx--;
							}else{
								nx++;	
							}
						}
					}
					if(nextwater[i][nx].si > size)continue;
					if(d==0)direct = 4;
					nextwater[i][nx] = {speed, direct, size};
				}else if(direct == 4){
					int nx = j;
					int d = 0;
					for(int u = 0; u < speed; u++){
						if(d==0){
							if(nx == 1){
								d=1;
								nx++;
							}else{
								nx--;
							}
						}else{
							if(nx==c){
								d=0;
								nx--;
							}else{
								nx++;
							}
							
						}
					}
					if(nextwater[i][nx].si > size)continue;
					if(d==1)direct = 3;
					nextwater[i][nx] = {speed, direct, size};
				}
			}
		}
	}
	memcpy(water, nextwater, sizeof(water));
}

int main(){
	ios_base::sync_with_stdio(false);
	cin.tie(0); cout.tie(0);
	
	cin>> r >> c >> m;
	for(int i = 0; i < m; i++){
			int y, x, s, d, z = 0;
			cin >> y >> x >> s >> d>> z;
			shark sh = {s, d, z};
			water[y][x] = sh;
	}
	
	for(int i = 1; i <= c; i++){
		jap(i);
		move();
	}
	
	cout<< ans;
}
