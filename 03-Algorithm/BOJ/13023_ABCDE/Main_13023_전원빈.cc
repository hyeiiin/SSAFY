#include<bits/stdc++.h>
using namespace std;

int n;
int m;
vector<int> bae[2001];
int visited[2001];
int cnt;
int a, b;
int flag;

void lego(int p){
	if(flag == 1)return;
	if(cnt >= 4){
		flag = 1;
		return;
	}
	for(int i = 0; i < bae[p].size(); i++){
		if(visited[bae[p][i]] == 1)continue;
		visited[bae[p][i]] = 1;
		cnt++;
		lego(bae[p][i]);
		cnt--;
		visited[bae[p][i]] = 0;
	}
}


int main(){
	ios_base::sync_with_stdio(false);
	cin.tie(NULL); cout.tie(NULL);
	cin >> n >> m;
	for(int i = 0; i < m; i++){
		int a, b;
		cin >> a >> b;
		bae[a].push_back(b);
		bae[b].push_back(a);
	}
	for(int i = 0; i < n; i++){
		for(int j = 0; j < bae[i].size(); j++){
			cnt = 1;
			visited[bae[i][j]] =1;
			visited[i] =1;
			lego(bae[i][j]);
			visited[i] = 0;
			visited[bae[i][j]] = 0;
			if(flag == 1)break;
			
		}
		if(flag == 1)break;
	}
	cout << flag;
}
