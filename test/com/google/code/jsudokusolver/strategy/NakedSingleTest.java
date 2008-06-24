package com.google.code.jsudokusolver.strategy;

import com.google.code.jsudokusolver.CandidateChangeEvent;
import com.google.code.jsudokusolver.CellChangeListener;
import com.google.code.jsudokusolver.DigitChangeEvent;
import com.google.code.jsudokusolver.Grid;
import org.junit.Test;
import static org.junit.Assert.*;

public class NakedSingleTest {
    /**
     * Test of solve method, of class NakedSingle.
     */
    @Test
    public void testSolve() throws Exception {
        Grid grid = new Grid(9);
        // 6,1 should resolve to 8
        String puzzle = "100200004" +
                        "098503670" +
                        "040070000" +
                        "010000059" +
                        "002000300" +
                        "970000020" + 
                        "000010060" +
                        "085309010" +
                        "400006005";
        grid.fill(puzzle);
        grid.registerStrategy(new NakedSingle());
        grid.addCellChangeListener(new CellChangeListener() {
            public void candidatesChanged(CandidateChangeEvent event) {
                // Ignore
            }

            public void digitChanged(DigitChangeEvent event) {
                assertEquals(8, event.getCell().getDigit().intValue());
                assertEquals(6, event.getCell().getColumn().getOffset());
                assertEquals(1, event.getCell().getRow().getOffset());
            }
        });
        assertTrue(grid.step());
    }
}