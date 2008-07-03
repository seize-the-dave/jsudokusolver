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
public class SwordfishTest {
    /**
     * Test of solve method, of class Swordfish.
     */
    @Test
    public void testSolve() throws Exception {
        Grid grid = new Grid(9);
        String puzzle = "260907043730000089000000000100306005000070000400502001000000000340000097920403016";
        grid.fill(puzzle);
        grid.registerStrategy(new Swordfish());
        /**
        grid.addCellChangeListener(new CellChangeListener() {
            public void candidatesChanged(CandidateChangeEvent event) {
                Cell cell = event.getCell();
                int column = cell.getColumn().getOffset();
                assertTrue(column == 3 || column == 9);
            }

            public void digitChanged(DigitChangeEvent event) {
                fail("Was not expecting a digit to be changed");
            }
        });
        */
//        assertTrue(grid.step());
    }
}
