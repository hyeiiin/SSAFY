#include<bits/stdc++.h>
using namespace std;

int n;
int cnt;

void go(int floor, int color){
	if(floor==0){
		cnt++;
		return;
	}
	if(color == 1){
		go(floor-1, 0);
		return;
	}else{
		go(floor-1, 0);
		go(floor-1, 1);
		return;
	}
}

int main(){
	ios_base::sync_with_stdio(false);
	cin.tie(NULL); cout.tie(NULL);
	cin >> n;
	go(n, 0);
	cout << cnt;
}
