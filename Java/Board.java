import java.util.*;

public class Board {
    private final int ROWS = 24;
    private final int COLS = 24;
    private String BORDER = "|";

    Tile[][] board = new Tile[ROWS][COLS];

    public Board() {
        buildSquareEstate(2, 2);
        buildSquareEstate(17, 2);
        buildSquareEstate(2, 17);
        buildSquareEstate(17, 17);

        buildRectangleEstate(9, 10);

        buildGreyArea(5, 11);
        buildGreyArea(11, 5);
        buildGreyArea(11, 17);
        buildGreyArea(17, 11);

        buildEntrances();

        for (int i = 0; i < ROWS; i++) {
            for (int j = 0; j < COLS; j++) {
                if (!(board[i][j] instanceof entranceTile || board[i][j] instanceof wallTile)) {
                    board[i][j] = new gameTile();
                }
            }
        }
    }

    private void buildGreyArea(int x, int y) {
        for (int i = x; i <= x + 1; i++) {
            for (int j = y; j <= y + 1; j++) {
                board[i][j] = new wallTile().setG();
            }
        }
    }

    private void buildSquareEstate(int y, int x) {
        //build column
        for (int i = x; i < x + 5; i++) {
            int j = y;
            board[i][j] = new wallTile();
            j = y + 4;
            board[i][j] = new wallTile();
        }

        //build row
        for (int j = y + 1; j < y + 4; j++) {
            int i = x;
            board[i][j] = new wallTile();
            i = x + 4;
            board[i][j] = new wallTile();
        }
    }

    private void buildRectangleEstate(int y, int x) {
        //build column
        for (int i = x; i < x + 4; i++) {
            int j = y;
            board[i][j] = new wallTile();
            j = y + 5;
            board[i][j] = new wallTile();
        }

        //build row
        for (int j = y + 1; j < y + 5; j++) {
            int i = x;
            board[i][j] = new wallTile();
            i = x + 3;
            board[i][j] = new wallTile();

        }
    }

    private void buildEntrances() {
        board[3][6] = new entranceTile();
        board[6][5] = new entranceTile();
        buildEstate("Haunted House", (entranceTile) board[3][6], (entranceTile) board[6][5]);

        board[5][17] = new entranceTile();
        board[6][20] = new entranceTile();
        buildEstate("Calamity House", (entranceTile) board[5][17], (entranceTile) board[6][20]);

        board[10][12] = new entranceTile();
        board[12][9] = new entranceTile();
        board[11][14] = new entranceTile();
        board[13][11] = new entranceTile();
        buildRectangleEstate("Visitation Villa", (entranceTile) board[10][12], (entranceTile) board[12][9], (entranceTile) board[11][14], (entranceTile) board[13][11]);

        board[18][6] = new entranceTile();
        board[17][3] = new entranceTile();
        buildEstate("Manic Manor", (entranceTile) board[18][6], (entranceTile) board[17][3]);

        board[20][17] = new entranceTile();
        board[17][18] = new entranceTile();
        buildEstate("Peril Palace", (entranceTile) board[20][17], (entranceTile) board[17][18]);
    }


    private void buildEstate(String name, entranceTile left, entranceTile right) {
        new Estate(name, left, right);
    }

    private void buildRectangleEstate(String name, entranceTile left, entranceTile right, entranceTile up, entranceTile down) {
        new Estate(name, left, right, up, down);
    }

    /**
     * Draws the board
     */
    public void draw() {
        // draws the Board including the players and items
        for (int row = 0; row < ROWS; row++) {
            for (int col = 0; col < COLS; col++) {
                System.out.print(BORDER);
                board[row][col].draw();
            }
            System.out.println(BORDER);
        }
    }

    /**
     * Sets tile at pos x,y
     */
    public void setTile(Tile tile, int x, int y) {
    }

    /**
     * Returns tile at pos x,y
     */
    public Tile getTile(int x, int y) {
        return board[x][y];
    }

    public void setTile(int x, int y, Tile t) {
        board[x][y] = t;
    }

    public void deleteBoard() {
        board = new Tile[ROWS][COLS];
    }

    /**
     * Turns every border on the board invisible so players can get a better grasp of their respective positions.
     */
    public void gridOff() {
        BORDER = " ";
    }

    /**
     * Turns every border on the board back on
     */
    public void gridOn() {
        if (BORDER.equals("|")) {
            gridOff();
        } else {
            BORDER = "|";
        }
    }

    public void setEntrance() {
        for (Estate e : estates) {
            switch (e.getName()) {
                case "Haunted House":
                    e.addItemsToThisEstate(board.get(6).get(3).get());
                    e.addItemsToThisEstate(board.get(5).get(6).getStored());
                    e.addPaths("2", new Item(null, null, 7, 3)); // valid exit points
                    e.addPaths("3", new Item(null, null, 5, 7));

                    e.addPaths("1", new Item(null, null, 0, 0)); // invalid exit points
                    e.addPaths("4", new Item(null, null, 0, 0));
                    break;
                case "Manic Manor":
                    e.addItemsToThisEstate(board.get(17).get(5).getStored());
                    e.addItemsToThisEstate(board.get(20).get(6).getStored());
                    e.addPaths("1", new Item(null, null, 16, 5)); // valid exit points
                    e.addPaths("3", new Item(null, null, 20, 7));

                    e.addPaths("2", new Item(null, null, 0, 0)); // invalid exit points
                    e.addPaths("4", new Item(null, null, 0, 0));
                    break;
                case "Visitation Villa":
                    e.addItemsToThisEstate(board.get(9).get(12).getStored());
                    e.addItemsToThisEstate(board.get(11).get(13).getStored());
                    e.addItemsToThisEstate(board.get(12).get(10).getStored());
                    e.addItemsToThisEstate(board.get(14).get(11).getStored());
                    e.addPaths("1", new Item(null, null, 8, 12)); // valid exit points
                    e.addPaths("2", new Item(null, null, 15, 11));
                    e.addPaths("3", new Item(null, null, 11, 14));
                    e.addPaths("4", new Item(null, null, 12, 9));
                    break;
                case "Calamity Castle":
                    e.addItemsToThisEstate(board.get(3).get(17).getStored());
                    e.addItemsToThisEstate(board.get(6).get(18).getStored());
                    e.addPaths("2", new Item(null, null, 7, 18)); // valid exit points
                    e.addPaths("4", new Item(null, null, 3, 16));

                    e.addPaths("1", new Item(null, null, 0, 0)); // invalid exit points
                    e.addPaths("3", new Item(null, null, 0, 0));
                    break;
                case "Peril Palace":
                    e.addItemsToThisEstate(board.get(17).get(20).getStored());
                    e.addItemsToThisEstate(board.get(18).get(17).getStored());
                    e.addPaths("1", new Item(null, null, 16, 20)); // valid exit points
                    e.addPaths("4", new Item(null, null, 18, 16));

                    e.addPaths("2", new Item(null, null, 0, 0)); // invalid exit points
                    e.addPaths("3", new Item(null, null, 0, 0));
                    break;
            }
        }
    }


    /**
     * makes sure that the value in board at x and y is not occupied by a wall or grayspace or player
     */
    public int isSafeMove(int x, int y) {
        if (x > 23 || x < 0 || y > 23 || y < 0) {
            return 0;
        }

        Tile target = getTile(x, y);

        if (target instanceof gameTile targetG) {
            if (targetG.getStored().getName().equals("Used")){
                return 0;
            }
            return 1;
        } else if (target instanceof entranceTile) {
            return 2;
        }
        return 0;
    }


    /**
     * estate checker
     */
    public Estate checkEstate(int x, int y) {
        Tile t = board[x][y];
        for (Estate e : estates) {
            if (e.getItemsToThisEstate().contains(t.getStored())) {
                return e;
            }
        }
        return null;
    }

}
