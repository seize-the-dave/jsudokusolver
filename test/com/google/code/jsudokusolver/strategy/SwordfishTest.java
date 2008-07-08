package com.google.code.jsudokusolver.strategy;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

import com.google.code.jsudokusolver.Grid;

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
        String puzzle = "080007060076020408031600070069300054010000086850006700048001690697840500120960847";
        grid.fill(puzzle);
        grid.registerStrategy(new Swordfish());
        assertTrue(grid.stepOnce());
    }
}
