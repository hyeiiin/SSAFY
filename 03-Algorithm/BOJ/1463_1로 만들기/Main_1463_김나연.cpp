#include <iostream>
#include <algorithm>
#include <vector>
#include <math.h>
#include <stack>
#include <queue>
#include <memory.h>
#include<tuple>
using namespace std;

int n, a[1000004];

int main(){
	cin >> n;
	a[2] = 1;
	a[3] = 1;

	for (int i = 4; i <= n; i++) {
		a[i] = a[i - 1] + 1;
		if (i % 2 == 0) a[i] = min(a[i], a[i / 2] + 1);
		if (i % 3 == 0) a[i] = min(a[i], a[i / 3] + 1);
	}

	cout << a[n] << "\n";

	return 0;
}
