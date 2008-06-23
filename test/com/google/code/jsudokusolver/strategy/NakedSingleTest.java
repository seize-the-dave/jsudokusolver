package com.google.code.jsudokusolver.strategy;

import com.google.code.jsudokusolver.Grid;
import org.junit.Test;
import static org.junit.Assert.*;

public class NakedSingleTest {
    /**
     * Test of solve method, of class NakedSingle.
     */
    @Test
    public void testSolve() throws Exception {
        Grid grid = new Grid(9);
        String puzzle = "100200004" +
                        "090503670" +
                        "040070000" +
                        "010000059" +
                        "002000300" +
                        "970000020" + 
                        "000010060" +
                        "085309010" +
                        "400006005";
        grid.fill(puzzle);
        grid.registerStrategy(new NakedSingle());
        assertEquals(0, grid.toArray()[0][5]);
        assertEquals(0, grid.toArray()[1][2]);
        assertTrue(grid.solve());
        assertEquals(8, grid.toArray()[0][5]);
        assertEquals(8, grid.toArray()[1][2]);
    }
}