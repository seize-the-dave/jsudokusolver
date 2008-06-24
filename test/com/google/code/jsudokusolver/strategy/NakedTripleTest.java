/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

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
 * @author Dave
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
        System.out.println(grid);
        grid.registerStrategy(new NakedTriple());
        grid.addCellChangeListener(new CellChangeListener() {
            public void candidatesChanged(CandidateChangeEvent event) {
                Cell cell = event.getCell();
                assertEquals(2, cell.getColumn().getOffset(), 2);
                if (cell.getRow().getOffset() == 1) {
                    assertEquals(Cell.generateCandidateSet(4, 9),
                                 event.getPostChangeCandidates());
                } else if (cell.getRow().getOffset() == 3) {
                    assertEquals(Cell.generateCandidateSet(5, 9),
                                 event.getPostChangeCandidates());
                } else {
                    fail("Change occured in wrong row");
                }
            }

            public void digitChanged(DigitChangeEvent event) {
                fail("Was not expecting a digit to be changed");
            }
        });
        assertTrue(grid.step());
    }
}