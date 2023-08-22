#include<iostream>
using namespace std;
int t, n, m;
int k, c, d;
int p[1000000];


void make() {
	for (int i = 0; i < n; i++) {
		p[i] = i;
	}
}

int find(int a) {
	if (a == p[a])return p[a];
	return p[a] = find(p[a]);
}

void uni(int a, int b) {
	int aRoot = find(a);
	int bRoot = find(b);
	if (aRoot == bRoot) return;
	else {
		p[bRoot] = aRoot;
		return;
	}
}

void ff(int a, int b) {
	int wa = find(a);
	int wb = find(b);
	if (wa == wb) cout << 1;
	else cout << 0;
}

int main() {
    ios_base::sync_with_stdio(false);
	cin.tie(NULL); cout.tie(NULL);
	cin >> t;
	for (int test = 1; test <= t; test++) {
		cin >> n >> m;
		make();
		cout << "#" << test << " ";
		for (int i = 0; i < m; i++) {
			cin >> k >> c >> d;
			c--;
			d--;
			if (k == 0) {
				uni(c, d);
			}
			else {
				ff(c, d);
			}

		}
		cout <<"\n";
	}
}
