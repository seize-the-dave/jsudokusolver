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
public class LockedCandidateTest {
    /**
     * Test of solve method, of class HiddenPair.
     */
    @Test
    public void testSolve() throws Exception {
        Grid grid = new Grid(9);
        String puzzle = "200000100" +
                        "140020083" +
                        "003010500" +
                        "000000000" +
                        "006700050" +
                        "809201300" + 
                        "000003200" +
                        "001802030" +
                        "000160094";
        grid.fill(puzzle);
        grid.registerStrategy(new LockedCandidate());
        assertTrue(grid.stepOnce());
    }
}
