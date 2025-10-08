package dijkstra;
import java.util.*;

public class Main {
    public static void main(String[] args) {

        int[][] weight = {
            { 0, 1, 0, 2, 0, 0, 0, 0},
            { 1, 0, 4, 3, 1, 6, 0, 0},
            { 0, 4, 0, 0, 0, 1, 1, 2},
            { 2, 3, 0, 0, 5, 0, 0, 0},
            { 0, 1, 0, 5, 0, 0, 2, 0},
            { 0, 6, 1, 0, 0, 0, 0, 9},
            { 0, 0, 1, 0, 2, 0, 0, 1},
            { 0, 0, 2, 0, 0, 9, 1, 0}
        };
        
        int N = weight.length;
        List<Edge>[] adjList = new List[N];
        
        // 인접 리스트 만들기
        for (int i = 0; i < N; i++) {
            adjList[i] = new LinkedList<>();
            for (int j = 0; j < N; j++) {
                // 가중치가 0이 아니면 간선 존재
                if (weight[i][j] != 0) {
                    Edge e = new Edge(i, j, weight[i][j]);
                    adjList[i].add(e);
                }
            }
        }
        
        // Dijkstra 실행
        DijkstraSP d = new DijkstraSP(adjList);
        System.out.println("정점 0으로부터의 최단 거리");
        int[] distance = d.shortestPath(0);
        
        // 최단 거리 출력
        for (int i = 0; i < distance.length; i++) {
            if (distance[i] == Integer.MAX_VALUE)
                System.out.println("0과 " + i + " 사이에 경로 없음.");
            else
                System.out.println("[0, " + i + "] = " + distance[i]);
        }
        
        System.out.printf("\n정점 0으로부터의 최단 경로\n");
        
        // 최단 경로 역추적 및 출력
        for (int i = 1; i < N; i++) {
            if (distance[i] == Integer.MAX_VALUE) {
                System.out.println("정점 " + i + "까지의 경로 없음");
            } else {
                // 경로를 역추적하여 리스트에 저장
                List<Integer> path = new ArrayList<>();
                int current = i;
                
                // 시작 정점(0)에 도달할 때까지 역추적
                while (current != 0) {
                    path.add(current);
                    current = d.previous[current];
                }
                path.add(0); // 시작 정점 추가
                
                // 경로 출력 (목적지부터 시작점까지 역순으로)
                for (int j = 0; j < path.size(); j++) {
                    if (j > 0) System.out.print("<-");
                    System.out.print(path.get(j));
                }
                System.out.println();
            }
        }
    }
}