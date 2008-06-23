package com.google.code.jsudokusolver.strategy;

import com.google.code.jsudokusolver.Cell;
import com.google.code.jsudokusolver.Grid;
import com.google.code.jsudokusolver.House;
import com.google.code.jsudokusolver.SolvingStrategy;
import java.util.List;

public class NakedPair implements SolvingStrategy {
    private Grid grid;
    
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
    
    private boolean solveHouses(List<House> houses)
    {
        boolean changed = false;
        for (House house : houses) {
            for (Cell cell : house.getCells()) {
                if (cell.isSolved()) {
                    continue;
                }
                if (cell.getCandidates().size() != 2) {
                    continue;
                }
                for (Cell pairCell : house.getCells()) {
                    if (cell == pairCell) {
                        continue;
                    }
                    if (pairCell.getCandidates().equals(cell.getCandidates())) {
                        for (Cell notCell : house.getCells()) {
                            if (notCell.equals(pairCell)) {
                                continue;
                            }
                            if (notCell.equals(cell)) {
                                continue;
                            }
                            if (notCell.isSolved()) {
                                continue;
                            }
                            if (notCell.removeCandidates(cell.getCandidates())) {
                                changed = true;
                            }
                        }
                        break;
                    }
                }
            }
        }
        return changed;
    }
}
