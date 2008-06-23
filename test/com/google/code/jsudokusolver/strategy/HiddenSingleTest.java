package com.google.code.jsudokusolver.strategy;

import com.google.code.jsudokusolver.Grid;
import org.junit.Test;
import static org.junit.Assert.*;

public class HiddenSingleTest {
    /**
     * Test of solve method, of class HiddenSingle.
     */
    @Test
    public void testSolve() throws Exception {
        Grid grid = new Grid(9);
        String puzzle = "000000500" +
                        "160900000" +
                        "009064000" +
                        "000000004" +
                        "400020100" +
                        "000300050" + 
                        "002089000" +
                        "010250030" +
                        "700100009";
        grid.fill(puzzle);
        grid.registerStrategy(new HiddenSingle());
        assertEquals(0, grid.toArray()[5][4]);
        assertTrue(grid.solve());
        assertEquals(4, grid.toArray()[5][4]);
    }

}