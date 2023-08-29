#include <iostream>
#include <algorithm>
#include <vector>
#include <math.h>
#include <stack>
#include <queue>
#include <memory.h>
#include<tuple>
using namespace std;

int a[100];

// 재귀
/* int y(int n);
int b(int n);
int go(int n);

int y(int n) {
	if (n == 1) return 1;
	return y(n - 1) + b(n - 1);
}

int b(int n) {
	if (n == 1) return 1;
	return y(n - 1);
}

int go(int n) {
	return y(n) + b(n);
} */

int main(){
	// cout << go(8) << "\n";

	// DP
	a[1] = 2;
	a[2] = 3;

	for (int i = 3; i < 100; i++) {
		a[i] = a[i - 1] + a[i - 2];
	}

	cout << a[8] << "\n";

	return 0;
}
