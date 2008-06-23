package com.google.code.jsudokusolver.strategy;

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
        assertEquals(makeCandidateSet(3, 9), grid.getCandidates(0, 8));
        assertEquals(makeCandidateSet(3, 9), grid.getCandidates(2, 7));
        assertEquals(makeCandidateSet(1, 4, 7), grid.getCandidates(5, 6));
        assertEquals(makeCandidateSet(4), grid.getCandidates(6, 6));
    }
    
    private Set<Integer> makeCandidateSet(Integer... candidates) {
        Set<Integer> candidateSet = new HashSet<Integer>();
        for (Integer candidate : candidates) {
            candidateSet.add(candidate);
        }
        return candidateSet;
    }
}