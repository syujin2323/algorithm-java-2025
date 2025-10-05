package kruskal;

public class UnionFind {
    protected int[] p;    // p[i]는 i의 부모를 저장
    protected int[] rank; // 트리의 깊이를 저장
    
    public UnionFind(int N) {
        p = new int[N];
        rank = new int[N];
        
        // 초기에는 각 원소가 자기 자신을 부모로 가짐
        for (int i = 0; i < N; i++) {
            p[i] = i;      // 각 원소가 자기 자신이 부모
            rank[i] = 0;   // 초기 rank는 0
        }
    }
    
    // Find 연산: i가 속한 집합의 루트를 찾음 (경로 압축 적용)
    protected int find(int i) {
        // 루트를 찾는 과정에서 경로상의 모든 노드를 루트에 직접 연결
        if (p[i] != i) {
            p[i] = find(p[i]); // 재귀적으로 루트를 찾고 부모를 루트로 갱신
        }
        return p[i];
    }
    
    // i와 j가 같은 집합에 속하는지 검사
    public boolean isConnected(int i, int j) {
        return find(i) == find(j);
    }
    
    // i와 j가 속한 두 집합을 합침
    public void union(int i, int j) {
        int iRoot = find(i); // i의 루트 찾기
        int jRoot = find(j); // j의 루트 찾기
        
        // 이미 같은 집합에 속하면 아무것도 하지 않음
        if (iRoot == jRoot) {
            return;
        }
        
        // rank가 낮은 트리를 rank가 높은 트리 아래에 붙임
        if (rank[iRoot] < rank[jRoot]) {
            p[iRoot] = jRoot; // iRoot를 jRoot의 자식으로
        } else if (rank[iRoot] > rank[jRoot]) {
            p[jRoot] = iRoot; // jRoot를 iRoot의 자식으로
        } else {
            // rank가 같으면 한쪽을 다른쪽의 자식으로 만들고 rank 증가
            p[jRoot] = iRoot;
            rank[iRoot]++;
        }
    }
}