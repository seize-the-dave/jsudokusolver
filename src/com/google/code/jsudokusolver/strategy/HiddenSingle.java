package com.google.code.jsudokusolver.strategy;

import com.google.code.jsudokusolver.Cell;
import com.google.code.jsudokusolver.Grid;
import com.google.code.jsudokusolver.House;
import com.google.code.jsudokusolver.SolvingStrategy;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class HiddenSingle implements SolvingStrategy {
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
        for (int i = 0; i < grid.getSize(); i++) {
            for (House house : houses) {
                Set<Cell> candidates = new HashSet<Cell>();
                for (Cell cell : house.getCells()) {
                    if (cell.isSolved()) {
                        continue;
                    }
                    if (cell.hasCandidate(i + 1)) {
                        candidates.add(cell);
                    }
                }
                if (candidates.size() == 1)
                {
                    ((Cell) candidates.toArray()[0]).setDigit(i + 1);
                    changed = true;
                }
            }
        }
        return changed;
    }
}
