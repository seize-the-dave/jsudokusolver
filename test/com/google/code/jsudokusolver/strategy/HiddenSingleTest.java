package com.google.code.jsudokusolver.strategy;

import com.google.code.jsudokusolver.CandidateChangeEvent;
import com.google.code.jsudokusolver.CellChangeListener;
import com.google.code.jsudokusolver.DigitChangeEvent;
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
        // 5,6 should resolve to 4
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
        grid.addCellChangeListener(new CellChangeListener() {
            public void candidatesChanged(CandidateChangeEvent event) {
                // Ignore
            }

            public void digitChanged(DigitChangeEvent event) {
                assertEquals(4, event.getCell().getDigit().intValue());
                assertEquals(5, event.getCell().getColumn().getOffset());
                assertEquals(6, event.getCell().getRow().getOffset());
            }
        });
        assertTrue(grid.step());
    }
}