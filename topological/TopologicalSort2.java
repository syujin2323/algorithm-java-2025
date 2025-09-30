package topological;
import java.util.*;

public class TopologicalSort2 {
    int N;                       // 정점 개수
    boolean[] visited;           // 방문 여부
    List<Integer>[] adjList;     // 그래프 인접 리스트
    List<Integer> sequence;      // 위상 정렬 결과

    public TopologicalSort2(List<Integer>[] graph) {
        N = graph.length;
        visited = new boolean[N];
        adjList = graph;
        sequence = new ArrayList<>();
    }

    public List<Integer> tsort() {
        // 모든 정점에 대해 DFS 실행
        for (int i = 0; i < N; i++) {
            if (!visited[i]) {
                dfs(i);
            }
        }

        // 역순으로 바꿔 정렬
        Collections.reverse(sequence);
        return sequence;
    }

    private void dfs(int v) {
        visited[v] = true;
        for (int next : adjList[v]) {
            if (!visited[next]) {
                dfs(next);
            }
        }
        sequence.add(v); // 모든 인접 정점 처리 후 자기 자신 추가
    }
}