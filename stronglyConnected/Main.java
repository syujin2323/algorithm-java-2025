package stronglyConnected;
import java.util.*;

public class Main {
    public static void main(String[] args) {

        int N = 9;
        List<Integer>[] adjList = new List[N];
        for (int i = 0; i < N; i++) {
            adjList[i] = new ArrayList<>();
        }

        adjList[0].add(7);
        adjList[1].add(8);
        adjList[2].add(5);
        adjList[3].add(1);
        adjList[3].add(4);
        adjList[3].add(8);
        adjList[4].add(2);
        adjList[4].add(5);
        adjList[5].add(3);
        adjList[6].add(0);
        adjList[6].add(4);
        adjList[7].add(2);
        adjList[7].add(6);
        adjList[8].add(1);
        
        SCC scc = new SCC(adjList);
        scc.start();
    }
}