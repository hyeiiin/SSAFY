#include <iostream>
#include <algorithm>
#include <vector>
#include <math.h>
#include <stack>
#include <queue>
#include <memory.h>
#include<tuple>
using namespace std;

int n, res;
int a[18][18];
int dy[] = { 0,1,1 };
int dx[] = { 1,1,0 };

void go(int y, int x, int dir) {
	if (y == n - 1 && x == n - 1) {
		res++;
		return;
	}

	for (int i = 0; i < 3; i++) {
		int ny = y + dy[i];
		int nx = x + dx[i];

		if (ny < 0 || nx < 0 || ny >= n || nx >= n)continue;
		if (a[ny][nx] == 1)continue;
		if (dy[i] == 1 && dx[i] == 1) {
			if (a[ny-1][nx] == 1 || a[ny][nx-1] == 1)continue;
		}
		if (dir == 1 && dx[i] == 0)continue;
		if (dir == 3 && dy[i] == 0)continue;

		if (dy[i] == 0 && dx[i] == 1) go(ny, nx, 1);
		else if (dy[i] == 1 && dx[i] == 1) go(ny, nx, 2);
		else if (dy[i] == 1 && dx[i] == 0) go(ny, nx, 3);
		
	}

	return;
}

int main(){
	ios_base::sync_with_stdio();
	cin.tie(0); cout.tie(0);

	cin >> n;

	for (int i = 0; i < n; i++) {
		for (int j = 0; j < n; j++) {
			cin >> a[i][j];
		}
	}
	
	go(0, 1, 1);

	cout << res << "\n";

	return 0;
}
