#include<iostream>
#include<vector>
#include<algorithm>
using namespace std;

int t;
int v, e;
int p[100001];
struct l { int f, t, w; };
int z, x, c;
vector<l> gan;

int find(int a) {
	if (p[a] == a)return a;
	return p[a] = find(p[a]);
}

bool unip(int a, int b) {
	int aRoot = find(a);
	int bRoot = find(b);
	if (aRoot == bRoot)return false;
	p[bRoot] = aRoot;
	return true;
}

bool compare(l a, l b) {
	return a.w < b.w;
}

int main() {
	scanf("%d", &t);
	for (int test = 1; test <= t; test++)
	{
		scanf("%d %d", &v, &e);
		for (int i = 1; i <= v; i++) {
			p[i] = i;
		}
		gan.clear();
		for (int i = 0; i < e; i++) {
			scanf("%d %d %d", &z, &x, &c);
			gan.push_back((l) { z, x, c });
		}
		sort(gan.begin(), gan.end(), compare);
		long long ans = 0;
		for (int i = 0; i < e; i++) {
			if (unip(gan[i].f, gan[i].t)) {
				ans += gan[i].w;
			}
		}
		cout << "#" << test << " " << ans << "\n";
	}
}