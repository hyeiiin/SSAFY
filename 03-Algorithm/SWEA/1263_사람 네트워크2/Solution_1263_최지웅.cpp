#include <bits/stdc++.h>
using namespace std;
#define INF 987654321
int t, n;
int a[1000][1000], cc[1000];
int main() {
	cin >> t;
	for (int tc = 1; tc <= t; tc++) {
		cin >> n;
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				cin >> a[i][j];
				if (!a[i][j]) a[i][j] = INF;
			}
		}
		for (int k = 0; k < n; k++) {
			for (int i = 0; i < n; i++) {
				for (int j = 0; j < n; j++) {
					a[i][j] = min(a[i][j], a[i][k] + a[k][j]);
				}
			}
		}
		memset(cc, 0, sizeof(cc));
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				if (i != j) cc[i] += a[i][j];
			}
		}
		cout << "#" << tc << " " << *min_element(cc, cc + n) << endl;
	}
}