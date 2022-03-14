import java.util.ArrayList;
import java.util.Arrays;
import java.util.Map;

public class Grid {
    String[][] Maze;
    int Row;
    int Column;
    Cell PlayerPosition;
    int Cost;


    public Cell getPlayerPosition() {
        return PlayerPosition;
    }

    public Grid(int Row, int Column, Cell player) {
        this.Cost = 0;
        PlayerPosition = player;
        this.Row = Row;
        this.Column = Column;
        this.Maze = new String[Row][Column];
        for (int i = 0; i < Row; i++) {
            Arrays.fill(this.Maze[i], " ");
        }
        InitialBlocks();

    }
            /* Copy Constructor */
    public Grid(Grid grid) {

        this.Maze = new String[grid.Row][grid.Column];
        this.Row = grid.Row;
        this.Column = grid.Column;
        for (int i = 0; i < grid.Row; i++) {
            if (grid.Column >= 0) System.arraycopy(grid.Maze[i], 0, this.Maze[i], 0, grid.Column);
        }
        PlayerPosition = new Cell(0, 0);
        this.PlayerPosition.setX(grid.getPlayerPosition().getX());
        this.PlayerPosition.setY(grid.getPlayerPosition().getY());
        this.Cost = grid.Cost;


    }


    void SetCells(int x, int y, String value) {
        this.Maze[x][y] = value;
    }

    String GetCells(int x, int y) {
        return Maze[x][y];
    }
    /* Initial Grid  */
    void InitialBlocks() {

        this.SetCells(this.PlayerPosition.x, this.PlayerPosition.y, "❤");
        this.SetCells(1, 1, "\uD83D\uDD25");
        this.SetCells(0, 3, "\uD83D\uDD25");
        this.SetCells(1, 3, "\uD83D\uDD25");
        this.SetCells(2, 1, "\uD83D\uDD25");
        this.SetCells(3, 1, "\uD83D\uDD25");
        this.SetCells(4, 4, "\uD83D\uDD25");


    }

    public String GetCell(int x, int y) {
        return this.Maze[x][y];
    }
    /* Check if Game End  */
    public GameState EndOfGame() {
        for (int i = 0; i < this.Row; i++)
            for (int j = 0; j < this.Column; j++) {
                if (this.GetCells(i, j).equals(" ")) {
                    return GameState.Player_Running;
                }
            }
        return GameState.Player_is_Winner;


    }
    /* Check if Next Move is Possible */
    boolean NextMove(int x, int y) {

        if (x >= 0 && y >= 0) {
            return y < Column && x < Row && (GetCell(x, y).equals(" ") || GetCell(x, y).equals("❤") || GetCell(x, y).equals("⚡"));
        }

        return false;
    }

    boolean canMoveUp() {
        int x1 = PlayerPosition.x;
        int y1 = PlayerPosition.y;
        return NextMove(--x1, y1);
    }

    boolean canMoveDown() {
        int x1 = PlayerPosition.x;
        int y1 = PlayerPosition.y;
        return NextMove(++x1, y1);
    }

    boolean canMoveLeft() {
        int x1 = PlayerPosition.x;
        int y1 = PlayerPosition.y;
        return NextMove(x1, --y1);
    }

    boolean canMoveRight() {
        int x1 = PlayerPosition.x;
        int y1 = PlayerPosition.y;
        return NextMove(x1, ++y1);
    }

    void moveRight() {
        while (this.canMoveRight()) {
            this.Maze[PlayerPosition.getX()][PlayerPosition.getY()] = "❤";
            PlayerPosition.setY(++PlayerPosition.y);
            //  System.out.println("x:"+x+" y:"+y);
            //  States.push(new position(x,y));
            this.Maze[PlayerPosition.getX()][PlayerPosition.getY()] = "❤";

        }
        this.Maze[PlayerPosition.getX()][PlayerPosition.getY()] = "⚡";
    }

    void moveLeft() {
        while (this.canMoveLeft()) {
            this.Maze[PlayerPosition.getX()][PlayerPosition.getY()] = "❤";
            PlayerPosition.setY(--PlayerPosition.y);
            //  System.out.println("x:"+x+" y:"+y);
            //  States.push(new position(x,y));
            this.Maze[PlayerPosition.getX()][PlayerPosition.getY()] = "❤";
        }
        this.Maze[PlayerPosition.getX()][PlayerPosition.getY()] = "⚡";
    }

    void moveUp() {
        while (this.canMoveUp()) {
            this.Maze[PlayerPosition.getX()][PlayerPosition.getY()] = "❤";
            PlayerPosition.setX(--PlayerPosition.x);
            //  System.out.println("x:"+x+" y:"+y);
            //  States.push(new position(x,y));
            this.Maze[PlayerPosition.getX()][PlayerPosition.getY()] = "❤";
        }
        this.Maze[PlayerPosition.getX()][PlayerPosition.getY()] = "⚡";
    }

    void moveDown() {
        while (this.canMoveDown()) {
            this.Maze[PlayerPosition.getX()][PlayerPosition.getY()] = "❤";
            PlayerPosition.setX(++PlayerPosition.x);
            //  System.out.println("x:"+x+" y:"+y);
            //  States.push(new position(x,y));
            this.Maze[PlayerPosition.getX()][PlayerPosition.getY()] = "❤";
        }
        this.Maze[PlayerPosition.getX()][PlayerPosition.getY()] = "⚡";
    }

    @Override
    public String toString() {
        for (int i = 0; i < this.Row; i++) {
            System.out.print("_______");
        }
        for (int i = 0; i < Row; i++) {
            System.out.println();
            for (int j = 0; j < Column; j++) {
                if (j == 0) {
                    System.out.print("|__" + GetCell(i, j) + " _|__");
                } else if (j == Column - 1)
                    System.out.print(GetCell(i, j) + "__|");
                else
                    System.out.print(GetCell(i, j) + "__|__");
            }
        }
        System.out.println();
        return "";
    }
    /* Get Next Possible Grid */
    public ArrayList<Grid> GetNextGrid() {
        ArrayList<Grid> list = new ArrayList<>();
//        int xx = this.getPlayerPosition().getX();
//        int yy = this.getPlayerPosition().getY();
        if (canMoveRight()) {
            Grid right = new Grid(this);
            right.moveRight();
            if (!DFS.VisitedGrid.contains(right) && !BFS.VisitedGrid.contains(right))
                list.add(right);
//            this.PlayerPosition.x = xx;
//            this.PlayerPosition.y = yy;
        }

        if (canMoveDown()) {
            Grid down = new Grid(this);

            down.moveDown();

            if (!DFS.VisitedGrid.contains(down) && !BFS.VisitedGrid.contains(down))
                list.add(down);
//            this.PlayerPosition.x = xx;
//            this.PlayerPosition.y = yy;
        }

        if (canMoveLeft()) {
            Grid left = new Grid(this);
            left.moveLeft();
            if (!DFS.VisitedGrid.contains(left) && !BFS.VisitedGrid.contains(left))
                list.add(left);
//            this.PlayerPosition.x = xx;
//            this.PlayerPosition.y = yy;
        }

        if (canMoveUp()) {
            Grid up = new Grid(this);
            up.moveUp();
            if (!DFS.VisitedGrid.contains(up) && !BFS.VisitedGrid.contains(up))
                list.add(up);
//            this.PlayerPosition.x = xx;
//            this.PlayerPosition.y = yy;
        }


        return list;
    }

    @Override
    public int hashCode() {
        return Arrays.deepHashCode(this.Maze);
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Grid) {
            Grid input = (Grid) obj;
            for (int i = 0; i < Row; i++) {
                for (int j = 0; j < Column; j++) {
                    if (!this.Maze[i][j].equals(input.Maze[i][j]))
                        return false;
                }
            }
            return true;
        }
        return false;

    }

    /* Print Final Solution  */
    public static void PrintPath(Map<Grid, Grid> track, Grid finalNode) {
        if (track.get(finalNode) == null) {
            return;
        } else {
            PrintPath(track, track.get(finalNode));

            System.out.println(finalNode);
        }

    }


}

