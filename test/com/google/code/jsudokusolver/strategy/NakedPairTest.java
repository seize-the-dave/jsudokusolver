package com.google.code.jsudokusolver.strategy;

import com.google.code.jsudokusolver.Cell;
import com.google.code.jsudokusolver.Grid;
import java.util.HashSet;
import java.util.Set;
import org.junit.Test;
import static org.junit.Assert.*;

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
        assertTrue(grid.solve());
        assertEquals(Cell.generateCandidateSet(3, 9), grid.getCandidates(8, 0));
        assertEquals(Cell.generateCandidateSet(3, 9), grid.getCandidates(7, 2));
        assertEquals(Cell.generateCandidateSet(1, 4, 7), grid.getCandidates(6, 5));
        assertEquals(Cell.generateCandidateSet(4), grid.getCandidates(6, 6));
    }
}