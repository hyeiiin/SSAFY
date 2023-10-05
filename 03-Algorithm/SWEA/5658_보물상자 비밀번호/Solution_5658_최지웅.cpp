#include <bits/stdc++.h>
using namespace std;
bool comp(string a, string b) {
	return a > b;
}
int main() {
	freopen("input.txt", "r", stdin);
	int t; cin >> t;
	for (int tc = 1; tc <= t; tc++) {
		int n, k; cin >> n >> k;
		string str; cin >> str;
		deque<char> dq;
		dq.clear();
		for (int i = 0; i < str.length(); i++) dq.push_back(str[i]);
		vector<string> v;
		v.clear();
		for (int i = 0; i < n / 4; i++) {
			for (int j = 0; j < 4; j++) {
				string t = "";
				for (int k = 0; k < n / 4; k++) t += dq[j * n / 4 + k];
				if (find(v.begin(), v.end(), t) == v.end()) {
					v.push_back(t);
				}
			}
			char c = dq.front(); dq.pop_front();
			dq.push_back(c);
		}
		sort(v.begin(), v.end(), comp);
		string pw = v[k - 1];
		int base = 1;
		int ans = 0;
		for (int i = n / 4 - 1; i >= 0; i--) {
			if (isdigit(pw[i])) ans += (pw[i] - '0') * base;
			else if (isalpha(pw[i])) ans += (pw[i] - 'A' + 10) * base;
			base *= 16;
		}
		cout << "#" << tc << " " << ans << endl;
	}
}