package kruskal;
import java.util.*;

public class KruskalMST {
    int N, M; // 그래프 정점의 수, 간선의 수
    List<Edge>[] graph; // 그래프의 인접 리스트
    UnionFind uf; // Union-Find 연산을 사용하기 위한 객체
    Edge[] tree; // 최소 신장 트리의 간선들을 저장
    
    // 간선의 가중치를 기준으로 정렬하기 위한 Comparator
    static class Weight_Comparison implements Comparator<Edge> {
        public int compare(Edge e, Edge f) {
            if(e.weight > f.weight)
                return 1;
            else if(e.weight < f.weight)
                return -1;
            return 0;
        }
    }
    
    // 인접 리스트와 간선의 수를 받아 초기화
    public KruskalMST(List<Edge>[] adjList, int numOfEdges) {
        N = adjList.length;
        M = numOfEdges;
        graph = adjList;
        uf = new UnionFind(N); // Union-Find 객체 생성
        tree = new Edge[N-1]; // MST는 N-1개의 간선을 가짐
    }
    
    // Kruskal 알고리즘 구현
    public Edge[] mst() {
        // 우선순위 큐: 가중치가 작은 간선부터 처리
        PriorityQueue<Edge> pq = new PriorityQueue<>(M, new Weight_Comparison());
        
        // 모든 간선을 우선순위 큐에 삽입
        // 무방향 그래프이므로 중복 간선을 방지하기 위해 vertex < adjvertex인 간선만 추가
        for (int i = 0; i < N; i++) {
            for (Edge e : graph[i]) {
                if (e.vertex < e.adjvertex) { // 중복 방지
                    pq.add(e);
                }
            }
        }
        
        int index = 0; // tree 배열의 인덱스
        
        // 우선순위 큐가 빌 때까지 또는 N-1개의 간선을 선택할 때까지
        while (!pq.isEmpty() && index < N - 1) {
            Edge e = pq.poll(); // 가중치가 가장 작은 간선 추출
            
            // 두 정점이 같은 집합에 속하지 않으면
            if (!uf.isConnected(e.vertex, e.adjvertex)) {
                tree[index++] = e; // MST에 간선 추가
                uf.union(e.vertex, e.adjvertex); // 두 집합을 합침
            }
        }
        
        return tree;
    }
}