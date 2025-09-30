package biconnected;
import java.util.*;

public class Main {
    public static void main(String[] args) {
        int N = 4;
        List<Integer>[] adjList = new List[N];
        for (int i = 0; i < N; i++) adjList[i] = new ArrayList<>();

        // 무방향 간선: 양방향으로 추가
        adjList[0].add(1); adjList[1].add(0);
        adjList[1].add(2); adjList[2].add(1);
        adjList[1].add(3); adjList[3].add(1);
        adjList[2].add(3); adjList[3].add(2);

        biconnected b = new biconnected(adjList);
        b.bc(0, -1);
    }
}