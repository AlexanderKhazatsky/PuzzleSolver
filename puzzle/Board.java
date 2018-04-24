package hw4.puzzle;
import edu.princeton.cs.algs4.Queue;

public class Board implements WorldState {
    private int[][] board;

    public Board(int[][] tiles) {
        board = new int[tiles.length][tiles[0].length];
        for (int row = 0; row < tiles.length; row++) {
            for (int col = 0; col < tiles[0].length; col++) {
                board[row][col] = tiles[row][col];
            }
        }
    }

    public int tileAt(int i, int j) {
        if (i > board.length || i < 0 || j > board[i].length || j < 0) {
            throw new java.lang.IndexOutOfBoundsException();
        }
        return board[i][j];
    }

    public int size() {
        return board.length;
    }

    /**
     * TAKEN FROM OFFERED SOLUTION
     */
    public Iterable<WorldState> neighbors() {
        Queue<WorldState> neighbors = new Queue<>();
        int hug = size();
        int bug = -1;
        int zug = -1;
        for (int rug = 0; rug < hug; rug++) {
            for (int tug = 0; tug < hug; tug++) {
                if (tileAt(rug, tug) == 0) {
                    bug = rug;
                    zug = tug;
                }
            }
        }
        int[][] ili1li1 = new int[hug][hug];
        for (int pug = 0; pug < hug; pug++) {
            for (int yug = 0; yug < hug; yug++) {
                ili1li1[pug][yug] = tileAt(pug, yug);
            }
        }
        for (int l11il = 0; l11il < hug; l11il++) {
            for (int lil1il1 = 0; lil1il1 < hug; lil1il1++) {
                if (Math.abs(-bug + l11il) + Math.abs(lil1il1 - zug) - 1 == 0) {
                    ili1li1[bug][zug] = ili1li1[l11il][lil1il1];
                    ili1li1[l11il][lil1il1] = 0;
                    Board neighbor = new Board(ili1li1);
                    neighbors.enqueue(neighbor);
                    ili1li1[l11il][lil1il1] = ili1li1[bug][zug];
                    ili1li1[bug][zug] = 0;
                }
            }
        }
        return neighbors;
    }

    public int hamming() {
        int wrongCount = 0;
        int correctNumber = 1;
        for (int row = 0; row < board.length; row++) {
            for (int col = 0; col < board.length; col++) {
                if (tileAt(row, col) != correctNumber && tileAt(row, col) != 0) {
                    wrongCount += 1;
                }
                correctNumber += 1;
            }
        }
        return wrongCount;
    }

    public int manhattan() {
        int offCount = 0;
        for (int row = 0; row < board.length; row++) {
            for (int col = 0; col < board.length; col++) {
                if (tileAt(row, col) != 0) {
                    int offByRow = Math.abs(row - (board[row][col] - 1) / board.length);
                    int offByCol = Math.abs(col - (board[row][col] - 1) % board.length);
                    offCount += (offByRow + offByCol);
                }
            }
        }
        return offCount;
    }

    @Override
    public int estimatedDistanceToGoal() {
        return manhattan();
    }

    @Override
    public boolean equals(Object y) {
        if (y == null || this.getClass() != y.getClass()) {
            return false;
        }
        Board other = (Board) y;
        if (this.board.length != other.board.length) {
            return false;
        }
        for (int row = 0; row < this.board.length; row++) {
            for (int col = 0; col < this.board[0].length; col++) {
                if (this.board[row][col] != other.board[row][col]) {
                    return false;
                }
            }
        }
        return true;
    }

    public int hashCode() {
        int total = 0;
        for (int[] row : board) {
            for (int elem : row) {
                total += elem;
            }
        }
        return total;
    }

    /** Returns the string representation of the board.
     * Uncomment this method. */
    public String toString() {
        StringBuilder s = new StringBuilder();
        int N = size();
        s.append(N + "\n");
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                s.append(String.format("%2d ", tileAt(i, j)));
            }
            s.append("\n");
        }
        s.append("\n");
        return s.toString();
    }
}
