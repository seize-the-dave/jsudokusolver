/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.google.code.jsudokusolver.strategy;

import com.google.code.jsudokusolver.Cell;
import com.google.code.jsudokusolver.House;
import java.util.List;
import java.util.Set;

/**
 *
 * @author David Grant
 */
public class HiddenQuad extends AbstractHiddenSubset {
    private static final String NAME = "Hidden Quad";

    public String getName() {
        return NAME;
    }

    protected boolean solveHouses(List<House> houses) {
        for (int i = 1; i <= grid.getSize(); i++) {
            for (int j = i + 1; j <= grid.getSize(); j++) {
                for (int k = j + 1; k <= grid.getSize(); k++) {
                    for (int l = k + 1; l <= grid.getSize(); l++) {
                        // Generate quad to search for
                        Set<Integer> quad = Cell.generateCandidateSet(i, j, k, l);
                        for (House house : houses) {
                            if (house.getUnsolvedCells().size() < 5) {
                                continue;
                            }
                            if (searchHouse(house, quad)) {
                                return true;
                            }
                        }
                    }
                }
            }
        }
        return false;
    }
}