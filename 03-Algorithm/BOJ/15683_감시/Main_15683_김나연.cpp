#include<iostream>
#include<algorithm>
#include<string>
#include<map>
#include<queue>
#include<memory.h>
#include<stack>
using namespace std;

int n, m, res=987654321;
int a[10][10];
vector <pair<int, int>> v;
int dy[] = { -1,0,1,0 }, dx[] = { 0,1,0,-1 };

vector <pair<int, int>> CCTV(int here, int dir) {
    vector <pair<int, int>> cctv;
    int y = v[here].first;
    int x = v[here].second;

    if (a[y][x] == 1) {
        while (1) {
            int ny = y + dy[dir];
            int nx = x + dx[dir];

            if (ny < 0 || nx < 0 || ny >= n || nx >= m)break;
            if (a[ny][nx] == 6)break;
            if (a[ny][nx] == 0) {
                a[ny][nx] = 8;
                cctv.push_back({ ny, nx });
            }
            y = ny;
            x = nx;
        }
    }
    else if (a[y][x] == 2) {
        for (int i = 0; i <= 2; i += 2) {
            int _y = y;
            int _x = x;

            while (1) {
                int ny = _y + dy[(dir + i) % 4];
                int nx = _x + dx[(dir + i) % 4];

                if (ny < 0 || nx < 0 || ny >= n || nx >= m)break;
                if (a[ny][nx] == 6)break;
                if (a[ny][nx] == 0) {
                    a[ny][nx] = 8;
                    cctv.push_back({ ny, nx });
                }
                _y = ny;
                _x = nx;
            }
        }
    }
    else if (a[y][x] == 3) {
        for (int i = 0; i < 2; i ++) {
            int _y = y;
            int _x = x;

            while (1) {
                int ny = _y + dy[(dir + i) % 4];
                int nx = _x + dx[(dir + i) % 4];

                if (ny < 0 || nx < 0 || ny >= n || nx >= m)break;
                if (a[ny][nx] == 6)break;
                if (a[ny][nx] == 0) {
                    a[ny][nx] = 8;
                    cctv.push_back({ ny, nx });
                }
                _y = ny;
                _x = nx;
            }
        }
    }
    else if (a[y][x] == 4) {
        for (int i = 0; i < 3; i++) {
            int _y = y;
            int _x = x;

            while (1) {
                int ny = _y + dy[(dir + i) % 4];
                int nx = _x + dx[(dir + i) % 4];

                if (ny < 0 || nx < 0 || ny >= n || nx >= m)break;
                if (a[ny][nx] == 6)break;
                if (a[ny][nx] == 0) {
                    a[ny][nx] = 8;
                    cctv.push_back({ ny, nx });
                }
                _y = ny;
                _x = nx;
            }
        }
    }
    else if (a[y][x] == 5) {
        for (int i = 0; i < 4; i++) {
            int _y = y;
            int _x = x;

            while (1) {
                int ny = _y + dy[(dir + i) % 4];
                int nx = _x + dx[(dir + i) % 4];

                if (ny < 0 || nx < 0 || ny >= n || nx >= m)break;
                if (a[ny][nx] == 6)break;
                if (a[ny][nx] == 0) {
                    a[ny][nx] = 8;
                    cctv.push_back({ ny, nx });
                }
                _y = ny;
                _x = nx;
            }
        }
    }

    return cctv;
}

void go(int here) {
    if (here == v.size()) {
        int cnt = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (a[i][j] == 0)cnt++;
            }
        }
        res = min(cnt, res);
        return;
    }
    for (int i = 0; i < 4; i++) {
        vector <pair<int, int>> cctv=CCTV(here, i);
        go(here + 1);
        for (auto b : cctv) {
            a[b.first][b.second] = 0;
        }
    }
}

int main() {
    ios::sync_with_stdio(false); 
    cin.tie(0); cout.tie(0);

    cin >> n >> m;

    for (int i = 0; i < n; i++) {
        for (int j = 0; j < m; j++) {
            cin >> a[i][j];
            if (a[i][j] != 0 && a[i][j] != 6) {
                v.push_back({ i,j });
            }
        }
    }

    go(0);
    cout << res << "\n";

    return 0;
}
