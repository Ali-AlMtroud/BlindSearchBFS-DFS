import java.util.*;

public class BFS {
    static ArrayList<Grid> VisitedGrid;
    Queue<Grid> Queue_State;

    public BFS() {
        VisitedGrid = new ArrayList<>();
        this.Queue_State = new LinkedList<>();
    }


    public boolean Bfs(Grid Maze) {
        Map<Grid, Grid> track = new HashMap<>();
        Queue_State.add(Maze);

        while (!Queue_State.isEmpty()) {
            Grid Current = Queue_State.remove();
            if (!VisitedGrid.contains(Current)) {
                VisitedGrid.add(Current);
                System.out.println("   ");
            }

            if (Current.EndOfGame() == GameState.Player_is_Winner) {
                System.out.println(" The Final Path is :  ");
                Grid.PrintPath(track, Current);

                return true;
            }
            else {

                ArrayList<Grid> children = Current.GetNextGrid();
                for (Grid grid : children) {
                    track.put(grid, Current);

                    Queue_State.add(grid);

                    if (!VisitedGrid.contains(grid)) {
                        VisitedGrid.add(grid);
                        System.out.println("   ");
                    }


                    if (grid.EndOfGame() == GameState.Player_is_Winner) {
                        System.out.println(" The Final Path is :  ");
                        Grid.PrintPath(track, Current);
                        System.out.println(grid);
                        return true;
                    }
                }
            }
        }

        return false;

    }


}