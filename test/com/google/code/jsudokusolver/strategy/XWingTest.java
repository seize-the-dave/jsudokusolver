package com.google.code.jsudokusolver.strategy;

import com.google.code.jsudokusolver.CandidateChangeEvent;
import com.google.code.jsudokusolver.Cell;
import com.google.code.jsudokusolver.CellChangeListener;
import com.google.code.jsudokusolver.DigitChangeEvent;
import com.google.code.jsudokusolver.Grid;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author David Grant
 */
public class XWingTest {
    /**
     * Test of solve method, of class NakedTriple.
     */
    @Test
    public void testSolve() throws Exception {
        Grid grid = new Grid(9);
        String puzzle = "900051730" +
                        "107398205" +
                        "500076091" +
                        "810724350" +
                        "200165007" +
                        "075983012" + 
                        "021537000" +
                        "758649123" +
                        "390812570";
        grid.fill(puzzle);
        grid.registerStrategy(new XWing());
        assertTrue(grid.stepOnce());
    }
}
