package com.google.code.jsudokusolver.strategy;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.google.code.jsudokusolver.Cell;
import com.google.code.jsudokusolver.Grid;
import com.google.code.jsudokusolver.House;
import com.google.code.jsudokusolver.SolverStrategy;
import com.google.code.jsudokusolver.Util;

/**
 * @author David Grant
 */
public class HiddenSingle implements SolverStrategy {
    private static final String NAME = "Hidden Single";
    
    public String getName() {
        return NAME;
    }

    public boolean solve(Grid grid) 
    {
        if (solveHouses(grid.getRows())) {
            return true;
        }
        if (solveHouses(grid.getColumns())) {
            return true;
        }
        if (solveHouses(grid.getBoxes())) {
            return true;
        }
        return false;
    }
    
    private boolean solveHouses(List<? extends House> houses) {
        for (int i = 1; i <= 9; i++) {
            for (House house : houses) {
                if (solveHouse(house, i)) {
                    return true;
                }
            }
        }
        return false;
    }
    
    private boolean solveHouse(House house, int candidate) {
        Set<Cell> candidates = new HashSet<Cell>();
        for (Cell cell : house.getCells()) {
            if (cell.isSolved()) {
                if (cell.getDigit() == candidate) {
                    return false;
                }
                continue;
            }
            if (cell.contains(candidate)) {
                candidates.add(cell);
            }
        }
        if (candidates.size() == 1)
        {
            Cell cell = candidates.toArray(new Cell[]{})[0];
            cell.setDigit(candidate);
            Util.logCandidateRetention(cell, candidate, NAME);
            return true;
        }
        return false;
    }
}
