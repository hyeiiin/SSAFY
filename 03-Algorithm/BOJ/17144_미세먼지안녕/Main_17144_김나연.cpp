#include<iostream>
#include<algorithm>
#include<memory.h>
#include<string>
#include<vector>
#include<math.h>
#include<queue>
#include<tuple>
using namespace std;

int r, c, t, res;
int a[52][52], temp[52][52];
int dy[] = { -1,0,1,0 }, dx[] = { 0,1,0,-1 };
pair<int, int> gong_up;
pair<int, int> gong_down;

void spread() {
	for (int i = 0; i < r; i++) {
		for (int j = 0; j < c; j++) {
			if (a[i][j] == 0 || a[i][j]==-1)continue;
			int mise = a[i][j];

			for (int k = 0; k < 4; k++) {
				int ny = i + dy[k];
				int nx = j + dx[k];

				if (ny < 0 || nx < 0 || ny >= r || nx >= c) continue;
				if (a[ny][nx] == -1)continue;

				temp[ny][nx] += mise / 5;
				a[i][j] -= mise / 5;
			}
		}
	}

	for (int i = 0; i < r; i++) {
		for (int j = 0; j < c; j++) {
			a[i][j] += temp[i][j];
		}
	}
}

void spin_gong_down() {
	int ny = gong_down.first;
	int nx = gong_down.second;
	int prev = 0;
	int next = 0;

	while (1) {
		nx++;
		if (ny < 0 || nx < 0 || ny >= r || nx >= c) {
			nx--;
			break;
		}
		prev = next;
		next = a[ny][nx];
		a[ny][nx] = prev;
	}
	
	while (1) {
		ny++;
		if (ny < 0 || nx < 0 || ny >= r || nx >= c) {
			ny--;
			break;
		}
		prev = next;
		next = a[ny][nx];
		a[ny][nx] = prev;


	}

	while (1) {
		nx--;
		if (ny < 0 || nx < 0 || ny >= r || nx >= c) {
			nx++;
			break;
		}
		prev = next;
		next = a[ny][nx];
		a[ny][nx] = prev;
	}

	while (1) {
		ny--;
		if (ny == gong_down.first && nx == gong_down.second) {
			break;
		}
		prev = next;
		next = a[ny][nx];
		a[ny][nx] = prev;
	}
}

void spin_gong_up() {
	int ny = gong_up.first;
	int nx = gong_up.second;
	int prev = 0;
	int next = 0;

	while (1) {
		nx++;
		if (ny < 0 || nx < 0 || ny >= r || nx >= c) {
			nx--;
			break;
		}
		prev = next;
		next = a[ny][nx];
		a[ny][nx] = prev;
	}

	while (1) {
		ny--;
		if (ny < 0 || nx < 0 || ny >= r || nx >= c) {
			ny++;
			break;
		}
		prev = next;
		next = a[ny][nx];
		a[ny][nx] = prev;
	}

	while (1) {
		nx--;
		if (ny < 0 || nx < 0 || ny >= r || nx >= c) {
			nx++;
			break;
		}
		prev = next;
		next = a[ny][nx];
		a[ny][nx] = prev;
	}

	while (1) {
		ny++;
		if (ny == gong_up.first && nx == gong_up.second) {
			break;
		}
		prev = next;
		next = a[ny][nx];
		a[ny][nx] = prev;
	}
}

int main() {
	ios_base::sync_with_stdio(false);
	cin.tie(NULL); cout.tie(NULL);

	cin >> r >> c >> t;

	int flag = 0;
	for (int i = 0; i < r; i++) {
		for (int j = 0; j < c; j++) {
			cin >> a[i][j];
			
			if (a[i][j] == -1 && flag==0) {
				gong_up = { i,j };
				gong_down = { i + 1, j };
				flag = 1;
			}
		}
	}

	for (int i = 0; i < t; i++) {
		fill(&temp[0][0], &temp[0][0] + 52 * 52, 0);
		spread();
		spin_gong_up();
		spin_gong_down();
	}
	

	for (int i = 0; i < r; i++) {
		for (int j = 0; j < c; j++) {
			res += a[i][j];
		}
	}

	cout << res + 2 << "\n";

	return 0;
}
