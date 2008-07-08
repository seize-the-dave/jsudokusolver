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
public class NakedTripleTest {
    /**
     * Test of solve method, of class NakedTriple.
     */
    @Test
    public void testSolve() throws Exception {
        Grid grid = new Grid(9);
        String puzzle = "600802735" +
                        "702356940" +
                        "300407062" +
                        "100975024" +
                        "200183079" +
                        "079624003" + 
                        "400560207" +
                        "067240300" +
                        "920738406";
        grid.fill(puzzle);
        grid.registerStrategy(new NakedTriple());
        assertTrue(grid.stepOnce());
    }
}
