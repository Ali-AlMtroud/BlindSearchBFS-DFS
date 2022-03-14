import java.util.*;

public class Dijkstra implements Comparator<Grid> {
    private PriorityQueue<Grid> priorityQueue;

    public Set<Integer> settled;

    public int distances[];

    private int source, destination;

    public Dijkstra() {
    }


    public boolean DijkstraSearch(Grid Maze) {

        return true;
    }

    @Override
    public int compare(Grid node1, Grid node2) {
        if (node1.Cost < node2.Cost)
            return -1;
        if (node1.Cost > node2.Cost)
            return 1;
        if (node1.Cost == node2.Cost)
            return 0;
        return 0;
    }
}
