#include<bits/stdc++.h>
using namespace std;

int n;
int cnt;

void go(int floor){
	if(floor==0){
		cnt++;
		return;
	}
	if(floor >=2){
		go(floor-1);
		go(floor-1);
		go(floor-2);
	}else{
		go(floor-1);
		go(floor-1);
	}
	
}

int main(){
	ios_base::sync_with_stdio(false);
	cin.tie(NULL); cout.tie(NULL);
	cin >> n;
	go(n);
	cout << cnt;
}
