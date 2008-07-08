package com.google.code.jsudokusolver.strategy;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

import com.google.code.jsudokusolver.Grid;

/**
 * @author David Grant
 */
public class NakedPairTest {
    /**
     * Test of solve method, of class NakedPair.
     */
    @Test
    public void testSolve() throws Exception {
        Grid grid = new Grid(9);
        String puzzle = "000000010" +
                        "136958274" +
                        "000000008" +
                        "000006900" +
                        "710002800" +
                        "860000000" + 
                        "300010002" +
                        "641000307" +
                        "007600050";
        grid.fill(puzzle);
        grid.registerStrategy(new NakedPair());
        assertTrue(grid.stepOnce());
    }
}