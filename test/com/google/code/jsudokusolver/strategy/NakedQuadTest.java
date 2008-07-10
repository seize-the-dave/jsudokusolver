package com.google.code.jsudokusolver.strategy;

import java.util.Set;

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
public class NakedQuadTest {
    /**
     * Test of solve method, of class NakedTriple.
     */
    @Test
    public void testSolve() throws Exception {
        Grid grid = new Grid(9);
        String puzzle = "624900000739100008815004000400009370300040006591003002900400200100296004248357169";
        grid.fill(puzzle);
        grid.registerStrategy(new NakedQuad());
        assertTrue(grid.stepOnce());
    }
}
