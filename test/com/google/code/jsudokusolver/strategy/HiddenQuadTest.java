package com.google.code.jsudokusolver.strategy;

import com.google.code.jsudokusolver.CandidateChangeEvent;
import com.google.code.jsudokusolver.Cell;
import com.google.code.jsudokusolver.CellChangeListener;
import com.google.code.jsudokusolver.DigitChangeEvent;
import com.google.code.jsudokusolver.Grid;
import java.util.Set;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author David Grant
 */
public class HiddenQuadTest {
    /**
     * Test of solve method, of class HiddenTriple.
     */
    @Test
    public void testSolve() throws Exception {
        Grid grid = new Grid();
        String puzzle = "502600749" +
                        "000900612" +
                        "006020385" +
                        "004096100" +
                        "000000000" +
                        "005270930" +
                        "837062091" +
                        "261009000" +
                        "459718263";
        grid.fill(puzzle);
        grid.registerStrategy(new HiddenQuad());
        assertTrue(grid.stepOnce());
    }
}