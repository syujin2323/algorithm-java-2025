package dijkstra;
import java.util.*;

public class DijkstraSP {
    public int N; // 그래프 정점의 수
    List<Edge>[] graph;
    public int[] previous; // 최단 경로상 이전 정점을 기록하기 위해
    
    public DijkstraSP(List<Edge>[] adjList) {
        N = adjList.length;
        previous = new int[N];
        graph = adjList;
    }
    
    // Dijkstra 최단 경로 알고리즘
    public int[] shortestPath(int s) {
        boolean[] visited = new boolean[N]; // 방문 여부 체크
        int[] D = new int[N]; // 시작 정점으로부터의 최단 거리
        
        // 초기화
        for(int i = 0; i < N; i++) {
            visited[i] = false;
            previous[i] = -1;
            D[i] = Integer.MAX_VALUE;
        }
        
        // 시작점 s의 관련 정보 초기화
        previous[s] = -1;
        D[s] = 0; // distance
        
        // 우선순위 큐 생성 (거리가 짧은 정점을 우선으로)
        PriorityQueue<Integer> pq = new PriorityQueue<>(
            Comparator.comparingInt(vertex -> D[vertex])
        );
        pq.offer(s); // 시작 정점 추가
        
        // Dijkstra 알고리즘 수행
        while(!pq.isEmpty()) {
            // 방문하지 않은 정점 중 최단 거리를 가진 정점 선택
            int u = pq.poll();
            
            // 이미 방문한 정점이면 스킵
            if(visited[u]) continue;
            
            visited[u] = true; // 방문 표시
            
            // 선택된 정점 u와 인접한 모든 정점에 대해
            for(Edge edge : graph[u]) {
                int v = edge.adjvertex; // 인접 정점
                int weight = edge.weight; // 간선의 가중치
                
                // 방문하지 않은 정점이고, 더 짧은 경로를 발견한 경우
                if(!visited[v] && D[u] != Integer.MAX_VALUE 
                   && D[u] + weight < D[v]) {
                    D[v] = D[u] + weight; // 최단 거리 갱신
                    previous[v] = u; // 이전 정점 기록
                    pq.offer(v); // 우선순위 큐에 추가
                }
            }
        }
        
        return D; // 최단 거리 배열 반환
    }
}