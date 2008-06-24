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
public class HiddenPair implements SolvingStrategy {
    private static final Logger LOGGER = Logger.getLogger(HiddenPair.class.getCanonicalName());
    private static final String NAME = "Hidden Pair";
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
        for (int i = 1; i <= grid.getSize(); i++) {
            for (int j = i + 1; j <= grid.getSize(); j++) {
                // Generate pair to search for
                Set<Integer> pair = Cell.generateCandidateSet(j, i);
                for (House house : houses) {
                    changed |= searchHouse(house, pair);
                }
            }
        }
        return changed;
    }
    
    private boolean searchHouse(House house, Set<Integer> pair) {
        boolean changed = false;
        final Set<Cell> matchingCells = new HashSet<Cell>();
        for (Cell cell : house.getCells()) {
            if (cell.isSolved()) {
                continue;
            }
            Set<Integer> cellCandidates 
                    = new HashSet<Integer>(cell.getCandidates());
            cellCandidates.retainAll(pair);
            if (cellCandidates.size() == 2) {
                matchingCells.add(cell);
            } else if (cellCandidates.size() == 1) {
                return false;
            }
        }
        if (matchingCells.size() == 2) {
            for (Cell cell : matchingCells) {
                changed |= cell.retainAll(pair);
            }
            Cell[] cells = matchingCells.toArray(new Cell[]{});
            LOGGER.info(NAME + ": " + cells[0].getPosition() + " and " + cells[1].getPosition() + " contain a hidden pair: " + pair + ".");
        }
        return changed;
    }
}
