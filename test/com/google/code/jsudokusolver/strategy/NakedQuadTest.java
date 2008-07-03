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
        String puzzle = "624900000" +
                        "739100008" +
                        "815004000" +
                        "400009370" +
                        "300040006" +
                        "591003002" + 
                        "900400200" +
                        "100296004" +
                        "248357169";
        grid.fill(puzzle);
        grid.registerStrategy(new NakedQuad());
        /**
        grid.addCellChangeListener(new CellChangeListener() {
            public void candidatesChanged(CandidateChangeEvent event) {
                final Cell cell = event.getCell();
                Set<Integer> changes = Cell.generateCandidateSet(1, 3, 5, 7);
                for (Integer change : changes) {
                    assertFalse(event.getPostChangeCandidates().contains(change));
                }
            }

            public void digitChanged(DigitChangeEvent event) {
                fail("Was not expecting a digit to be changed");
            }
        });
        */
        assertTrue(grid.step());
    }
}
