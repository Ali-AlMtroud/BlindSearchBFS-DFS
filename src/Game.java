import java.util.Scanner;

public class Game {
    Grid grid;
    BFS bfs;
    DFS dfs;
    Scanner input;

    public Game() {
        grid = new Grid(5, 5, new Cell(0, 0));
        input = new Scanner(System.in);
        bfs = new BFS();
        dfs = new DFS();
    }

    void StartGame() {
        System.out.println("Enter ( 1 ) For DFS :");
        System.out.println("Enter ( 2 ) For BFS :");
        System.out.println("Enter ( 3 ) For Exit :");

        switch (input.nextInt()) {
            case 1:
                System.out.println(dfs.Dfs(grid));
                break;
            case 2:
                System.out.println(bfs.Bfs(grid));
                break;
            default:
                System.out.println("Wrong choice , Try Again");

        }


    }
}
