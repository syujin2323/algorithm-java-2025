package topological;
import java.util.*;

public class Main {
    public static void main(String[] args) {
        int N = 9; // 정점 개수
        List<Integer>[] adjList = new List[N];
        int[] indegree = new int[N]; // 각 정점의 진입 차수 저장

        // 인접 리스트 초기화
        for (int i = 0; i < N; i++) {
            adjList[i] = new ArrayList<>();
        }

        // 간선 추가
        adjList[2].add(0); indegree[0]++;
        adjList[2].add(1); indegree[1]++;
        adjList[0].add(1); indegree[1]++;
        adjList[1].add(3); indegree[3]++;
        adjList[1].add(4); indegree[4]++;
        adjList[4].add(5); indegree[5]++;
        adjList[5].add(3); indegree[3]++;
        adjList[5].add(7); indegree[7]++;
        adjList[3].add(6); indegree[6]++;
        adjList[6].add(7); indegree[7]++;
        adjList[7].add(8); indegree[8]++;

        // 순방향 위상 정렬 (Kahn’s algorithm)
        TopologicalSort1 ts1 = new TopologicalSort1(adjList, indegree);
        List<Integer> sorted1 = ts1.tsort();
        System.out.printf("순방향 위상 정렬: ");
        System.out.println(sorted1);

        // 역방향 위상 정렬 (DFS 기반)
        TopologicalSort2 ts2 = new TopologicalSort2(adjList);
        List<Integer> sorted2 = ts2.tsort();
        System.out.printf("역방향 위상 정렬: ");
        System.out.println(sorted2);
    }
}