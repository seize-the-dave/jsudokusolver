package com.google.code.jsudokusolver.strategy;

import com.google.code.jsudokusolver.Cell;
import com.google.code.jsudokusolver.Grid;
import com.google.code.jsudokusolver.House;
import com.google.code.jsudokusolver.SolvingStrategy;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.logging.Logger;

/**
 * @author David Grant
 */
public class HiddenSingle implements SolvingStrategy {
    private static final String NAME = "Hidden Single";
    private static final Logger LOGGER = Logger.getLogger(HiddenPair.class.getCanonicalName());
    private Grid grid;
    
    public String getName() {
        return NAME;
    }

    public void setGrid(Grid grid) {
        this.grid = grid;
    }

    public boolean solve() {
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
    
    private boolean solveHouses(List<House> houses) {
        for (int i = 1; i <= grid.getSize(); i++) {
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
            grid.logCandidateRetention(cell, candidate, NAME);
            return true;
        }
        return false;
    }
}