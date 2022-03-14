import java.util.*;

public class DFS {
    static ArrayList<Grid> VisitedGrid;
    Stack<Grid> Stack_State;
    Map<Grid, Grid> Path = new HashMap<>();

    public DFS() {
        VisitedGrid = new ArrayList<>();
        this.Stack_State = new Stack<>();
    }


    public boolean Dfs(Grid board) {

        Stack_State.push(board);

        while (!Stack_State.isEmpty()) {
            Grid Current = Stack_State.pop();
            Current.PlayerPosition.PrintCell();
            if (!VisitedGrid.contains(Current)) {
                VisitedGrid.add(Current);
                System.out.println("   ");
            }

            if (Current.EndOfGame() == GameState.Player_is_Winner) {
                System.out.println(" The Final Path is :  ");
                Grid.PrintPath(Path, Current);

                return true;
            }
            else {

                ArrayList<Grid> children = Current.GetNextGrid();
                for (Grid grid : children) {
                    Path.put(grid, Current);
                    Stack_State.push(grid);

                    if (!VisitedGrid.contains(grid)) {
                        VisitedGrid.add(grid);
                        System.out.println("   ");
                    }


                    if (grid.EndOfGame() == GameState.Player_is_Winner) {
                        System.out.println(" The Final Path is :  ");
                        Grid.PrintPath(Path, Current);
                        System.out.println(grid);
                        return true;
                    }
                }
            }
        }

        return false;

    }


}