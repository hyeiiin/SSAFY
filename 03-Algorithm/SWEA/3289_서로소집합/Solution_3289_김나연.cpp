#include<iostream>
#include<algorithm>
#include<string>
#include<map>
#include<queue>
#include<memory.h>
#include<stack>
using namespace std;

int t, n, m, a, b, command;
int parents[1000004];
string res;

void make() {
	for (int i = 0; i < n + 1; i++) {
		parents[i] = i;
	}
}

int find(int a) {
	if (a == parents[a]) return a;
	return parents[a] = find(parents[a]);
}

bool union_func(int a, int b) {
	int aRoot = find(a);
	int bRoot = find(b);

	if (aRoot == bRoot) return false;
	parents[bRoot] = aRoot;
	return true;
}

string check(int a, int b) {
	int aRoot = find(a);
	int bRoot = find(b);
	if (aRoot == bRoot) return "1";
	return "0";
}

int main() {
	ios_base::sync_with_stdio(0);
	cin.tie(0); cout.tie(0);

	cin >> t;

	

	for (int test_case = 1; test_case <= t; test_case++) {
		res = "";

		cin >> n >> m;

		make();

		for (int i = 0; i < m; i++) {
			cin >> command >> a >> b;

			if (command == 0) union_func(a, b);
			else if (command == 1) res+=check(a, b);
		}

		cout << "#" << test_case << " " << res << "\n";
	}

	return 0;
}
