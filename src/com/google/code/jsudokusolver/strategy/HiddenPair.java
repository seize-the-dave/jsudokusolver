package com.google.code.jsudokusolver.strategy;

import com.google.code.jsudokusolver.Cell;
import com.google.code.jsudokusolver.House;
import java.util.List;
import java.util.Set;

/**
 * @author David Grant
 */
public class HiddenPair extends AbstractHiddenSubset {
    private static final String NAME = "Hidden Pair";

    public String getName() {
        return NAME;
    }
    
    protected boolean solveHouses(List<House> houses) {
        for (int i = 1; i <= grid.getSize(); i++) {
            for (int j = i + 1; j <= grid.getSize(); j++) {
                // Generate pair to search for
                Set<Integer> pair = Cell.generateCandidateSet(j, i);
                for (House house : houses) {
                    if (house.getUnsolvedCells().size() < 3) {
                        continue;
                    }
                    if (searchHouse(house, pair)) {
                        return true;
                    }
                }
            }
        }
        return false;
    }
}
