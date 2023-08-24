#include<iostream>
#include<vector>
#include<set>
#include<queue>
#include<memory.h>
using namespace std;

vector<int> v;
int n,m;
int h[11][11];
int li[11];
int mi = 987654321;
int visited[11];
int total;
int jip[11];
int anjip[11];

bool route(int a, int cnt){
	queue<int> q;
	q.push(a);
	jip[a] = 0;
	while(q.size() != 0){
		int here = q.front();
		q.pop();
		for(int i = 1; i <= n; i++){
			if(jip[i] == 0)continue;
			if(h[here][i] == 0)continue;
			jip[i] = 0;
			q.push(i);
			cnt--;
		}
	}
	if(cnt == 0)return true;
	else return false;
}

bool anroute(int a, int cnt){
	queue<int> q;
	q.push(a);
	anjip[a] = 0;
	while(q.size() != 0){
		int here = q.front();
		q.pop();
		for(int i = 1; i <= n; i++){
			if(anjip[i] == 0)continue;
			if(h[here][i] == 0)continue;
			anjip[i] = 0;
			q.push(i);
			cnt--;
		}
	}
	if(cnt == 0)return true;
	else return false;
}

bool check(){
	memset(jip, 0, 11*sizeof(int));
	int ji = 0;
	int anji= 0;
	int jicnt = 0;
	int anjicnt = 0;
	for(int i = 1; i <= n; i++){
		if(visited[i] == 1){
			ji = i;
			jip[i] = 1;
			jicnt++;
		}
		if(visited[i] == 0){
			anji = i;
			anjip[i] = 1;
			anjicnt++;
		}
	}

	if(jicnt == 0)return false;
	if(route(ji, jicnt-1) && anroute(anji, anjicnt-1))return true;
	else return false;
}

void com(int start, int cnt){
	if(cnt == n)return;
	if(v.size() != 0&&check()){
		int oneside = 0;
		for(int a : v){
			oneside += li[a];
		}
		mi = min(mi, abs(total-(2*oneside)));
	}
	
	for(int i = start; i <=n; i++){
		v.push_back(i);
		visited[i] = 1;
		com(i+1, cnt+1);
		v.pop_back();
		visited[i] = 0;
		com(i+1, cnt+1);	
	}
}

int main(){
	ios_base::sync_with_stdio(false);
	cin.tie(NULL); cout.tie(NULL);
	cin >> n;
	for(int i = 1; i <= n; i ++){
		cin >> li[i];
		total += li[i];
	}
	for(int i = 1; i <= n; i++){
		cin >> m;
		for(int j = 0; j < m; j++){
			int temp = 0;
			cin >> temp;
			h[i][temp] = 1;
		}
	}
	com(1, 0);
	if (mi == 987654321)mi = -1;
	cout << mi;
}
