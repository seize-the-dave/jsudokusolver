package com.google.code.jsudokusolver.strategy;

import com.google.code.jsudokusolver.Cell;
import com.google.code.jsudokusolver.Grid;
import com.google.code.jsudokusolver.House;
import com.google.code.jsudokusolver.SolvingStrategy;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @author David Grant
 */
public class HiddenPair implements SolvingStrategy {
    private Grid grid;
    
    public void setGrid(Grid grid) {
        this.grid = grid;
    }

    public boolean solve() {
         boolean changed = false;
        changed |= solveHouses(grid.getRows());
//        changed |= solveHouses(grid.getColumns());
//        changed |= solveHouses(grid.getBoxes());
        return changed;
    }
    
    private boolean solveHouses(List<House> houses) {
        boolean changed = false;
        for (int i = 0; i < grid.getSize(); i++) {
            for (int j = 0; j < grid.getSize(); j++) {
                if (j <= i) {
                    continue;
                }
                Set<Integer> candidates = Cell.generateCandidateSet(j + 1, i + 1);
                for (House house : houses) {
                    Set<Cell> matchingCells = new HashSet<Cell>();
                    for (Cell cell : house.getCells()) {
                        // Not correct!
                        if (cell.getCandidates().containsAll(candidates)) {
                            matchingCells.add(cell);
                        }
                    }
                    if (matchingCells.size() == 2) {
                        for (Cell cell : matchingCells) {
                            changed |= cell.getCandidates().retainAll(candidates);
                        }
                    }
                }
            }
        }
        return true;
    }
}
