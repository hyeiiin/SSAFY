#include <bits/stdc++.h>
using namespace std;
#define endl '\n'
void fastIO() {
	ios::sync_with_stdio(false);
	cin.tie(nullptr);
	cout.tie(nullptr);
}
int main() {
	//freopen("input.txt", "r", stdin);
	fastIO();
	int t; cin >> t;
	const int MAXN = 1000;
	int a[MAXN];
	int LIS[MAXN];
	for (int tc = 1; tc <= t; tc++) {
		int n; cin >> n;
		for (int i = 0; i < n; i++) {
			cin >> a[i];
		}
		memset(LIS, 0, sizeof(LIS));
		for (int i = 0; i < n; i++) {
			LIS[i] = 1;
			for (int j = 0; j < i; j++) {
				if (a[i] > a[j]) {
					LIS[i] = max(LIS[i], LIS[j] + 1);
				}
			}
		}
		int ans = 0;
		for (int i = 0; i < n; i++) {
			ans = max(ans, LIS[i]);
		}
		cout << "#" << tc << " " << ans << endl;
	}
	return 0;
}