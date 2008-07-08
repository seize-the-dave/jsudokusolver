package com.google.code.jsudokusolver.strategy;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

import com.google.code.jsudokusolver.Grid;

/**
 *
 * @author David Grant
 */
public class SquirmbagTest {
    /**
     * Test of solve method, of class Squirmbag.
     */
    @Test
    public void testSolve() throws Exception {
        Grid grid = new Grid(9);
        String puzzle = "310064257420537009007102340005701403730000001102306975273409500800603792060270034";
        grid.fill(puzzle);
        grid.registerStrategy(new Squirmbag());
//        assertTrue(grid.stepOnce());
    }
}
