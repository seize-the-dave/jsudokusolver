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
public class HiddenTripleTest {
    /**
     * Test of solve method, of class HiddenTriple.
     */
    @Test
    public void testSolve() throws Exception {
        Grid grid = new Grid();
        String puzzle = "528600049" +
                        "136490025" +
                        "794205630" +
                        "000100200" +
                        "007826300" +
                        "002509060" + 
                        "240300976" +
                        "809702413" +
                        "070904582";
        grid.fill(puzzle);
        grid.registerStrategy(new HiddenTriple());
        grid.addCellChangeListener(new CellChangeListener() {
            public void candidatesChanged(CandidateChangeEvent event) {
                final Cell cell = event.getCell();
                Set<Integer> changes = Cell.generateCandidateSet(1, 2, 5, 6, 8, 9);
                for (Integer change : changes) {
                    assertFalse(event.getPostChangeCandidates().contains(change));
                }
            }

            public void digitChanged(DigitChangeEvent event) {
                fail("Was not expecting a digit to be changed");
            }
        });
        assertTrue(grid.step());
    }
}