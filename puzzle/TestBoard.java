package hw4.puzzle;
import org.junit.Test;
import static org.junit.Assert.*;

public class TestBoard {
    @Test
    public void verifyImmutability() {
        int r = 2;
        int c = 2;
        int[][] x = new int[r][c];
        int cnt = 0;
        for (int i = 0; i < r; i += 1) {
            for (int j = 0; j < c; j += 1) {
                x[i][j] = cnt;
                cnt += 1;
            }
        }
        Board b = new Board(x);
        assertEquals(0, b.tileAt(0, 0));
        assertEquals(1, b.tileAt(0, 1));
        assertEquals(2, b.tileAt(1, 0));
        assertEquals(3, b.tileAt(1, 1));

        x[1][1] = 1000;
        assertEquals(3, b.tileAt(1, 1));
    }
} 
