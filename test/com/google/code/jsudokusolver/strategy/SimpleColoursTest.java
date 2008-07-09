package com.google.code.jsudokusolver.strategy;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

import com.google.code.jsudokusolver.Grid;

/**
 * 
 * @author David Grant
 */
public class SimpleColoursTest {
    /**
     * Test of solve method, of class SimpleColours.
     * @throws Exception 
     */
    @Test
    public void testSolve() throws Exception {
	Grid grid = new Grid(9);
//	String puzzle = "807090602952860040306020598781934256264000009539602004600000421120046980408210065";
	String puzzle = "548003000067409835900857600090038257300570400875294163689745312000900500051302000";
	grid.fill(puzzle);
	grid.registerStrategy(new SimpleColours());
//	assertTrue(grid.stepOnce());
    }
}
