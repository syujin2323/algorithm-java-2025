package topological;
import java.util.*;

public class TopologicalSort1 {
    int N;                       // 정점 개수
    List<Integer>[] adjList;     // 그래프 인접 리스트
    int[] indegree;              // 각 정점의 진입 차수
    List<Integer> sequence;      // 위상 정렬 결과

    public TopologicalSort1(List<Integer>[] adjLists, int[] indegreeArr) {
        N = adjLists.length;
        adjList = adjLists;
        indegree = indegreeArr.clone(); // 원본 유지를 위해 복사
        sequence = new ArrayList<>();
    }

    public List<Integer> tsort() {
        Queue<Integer> q = new LinkedList<>();

        // 진입 차수가 0인 정점들을 큐에 삽입
        for (int i = 0; i < N; i++) {
            if (indegree[i] == 0) {
                q.add(i);
            }
        }

        while (!q.isEmpty()) {
            int v = q.poll();
            sequence.add(v);

            // 인접한 정점들의 진입 차수 감소
            for (int next : adjList[v]) {
                indegree[next]--;
                if (indegree[next] == 0) {
                    q.add(next);
                }
            }
        }

        return sequence;
    }
}