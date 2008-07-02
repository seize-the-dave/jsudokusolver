package com.google.code.jsudokusolver.strategy;

import com.google.code.jsudokusolver.Cell;
import com.google.code.jsudokusolver.Grid;
import com.google.code.jsudokusolver.House;
import com.google.code.jsudokusolver.SolvingStrategy;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.logging.Logger;

public class NakedPair implements SolvingStrategy {
    private static final String NAME = "Naked Pair";
    private static final Logger LOGGER = Logger.getLogger(HiddenPair.class.getCanonicalName());
    private Grid grid;
    
    public String getName() {
        return NAME;
    }
    
    public void setGrid(Grid grid) {
        this.grid = grid;
    }

    public boolean solve() {
        boolean changed = false;
        changed |= solveHouses(grid.getRows());
        changed |= solveHouses(grid.getColumns());
        changed |= solveHouses(grid.getBoxes());
        return changed;
    }
    
    private boolean solveHouses(List<House> houses) {
        boolean changed = false;
        for (House house : houses) {
            changed |= matchCell(house);
        }
        return changed;
    }
    
    private boolean matchCell(House house) {
        boolean changed = false;
        for (Cell cell : house.getCells()) {
            if (cell.isSolved()) {
                continue;
            }
            if (cell.getCandidates().size() != 2) {
                // Not a pair
                continue;
            }
            Set<Integer> pair = cell.getCandidates();
            Set<Cell> otherCells = new HashSet<Cell>(house.getCells());
            // Remove the current pair cell
            otherCells.remove(cell);
            for (Cell matchCell : otherCells) {
                if (pair.equals(matchCell.getCandidates())) {
                    // We have a match, so loop through all the other cells
                    Set<Cell> affectedCells = new HashSet<Cell>(otherCells);
                    affectedCells.remove(matchCell);
                    
                    for (Cell affectedCell : affectedCells) {
                        if (affectedCell.isSolved()) {
                            continue;
                        }
                        if (affectedCell.removeAll(pair)) {
                            grid.logCandidateRemoval(affectedCell, pair, NAME, cell, matchCell);
                            changed = true;
                        }
                    }
                }
            }
        }
        return changed;
    }
}
