package com.google.code.jsudokusolver.strategy;

import com.google.code.jsudokusolver.Cell;
import com.google.code.jsudokusolver.Grid;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author David Grant
 */
public class HiddenPairTest {
    /**
     * Test of solve method, of class HiddenPair.
     */
    @Test
    public void testSolve() throws Exception {
        Grid grid = new Grid(9);
        String puzzle = "801006094" +
                        "300009080" +
                        "970080500" +
                        "547062030" +
                        "632000050" +
                        "198375246" + 
                        "083620915" +
                        "065198000" +
                        "219500008";
        grid.fill(puzzle);
        grid.registerStrategy(new HiddenPair());
        assertTrue(grid.step());
    }
}
