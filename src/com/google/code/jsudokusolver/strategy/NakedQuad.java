package com.google.code.jsudokusolver.strategy;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.logging.Logger;

import com.google.code.jsudokusolver.Cell;
import com.google.code.jsudokusolver.Grid;
import com.google.code.jsudokusolver.House;
import com.google.code.jsudokusolver.SolvingStrategy;

/**
 * 
 * @author David Grant
 */
public class NakedQuad implements SolvingStrategy {
    private static final String NAME = "Naked Quad";
    private static final Logger LOGGER = Logger.getLogger(NakedQuad.class.getCanonicalName());
    private Grid grid;
    
    public String getName() {
        return NAME;
    }

    public void setGrid(Grid grid) {
        this.grid = grid;
    }

    public boolean solve() {
        for (int i = 1; i <= grid.getSize(); i++) {
            for (int j = i + 1; j <= grid.getSize(); j++) {
                for (int k = j + 1; k <= grid.getSize(); k++) {
                    for (int l = k + 1; l <= grid.getSize(); l++) {
                        Set<Integer> quad = Cell.generateCandidateSet(i, j, k, l);
                        if (solveHouses(grid.getBoxes(), quad)) {
                            return true;
                        }
                        if (solveHouses(grid.getColumns(), quad)) {
                            return true;
                        }
                        if (solveHouses(grid.getRows(), quad)) {
                            return true;
                        }
                    }
                }
            }
        }
        return false;
    }

    private boolean solveHouses(List<House> houses, Set<Integer> quad) {
        for (House house : houses) {
            if (solveHouse(house, quad)) {
                return true;
            }
        }
        return false;
    }
    
    private boolean solveHouse(House house, Set<Integer> quad) {
        Set<Cell> selectedCells = new HashSet<Cell>();
        for (Cell cell : house.getCells()) {
            if (cell.isSolved()) {
                // This house already contains a cell with
                // one of this quad's numbers.
                if (quad.contains(cell.getDigit())) {
                    return false;
                }
                continue;
            }
            Set<Integer> cellCandidates = cell.getCandidates();
            if (cellCandidates.size() > 4) {
                // Can't possibly be a subset
                continue;
            }
            Set<Integer> cellCandidatesCopy = new HashSet<Integer>(cellCandidates);
            cellCandidatesCopy.removeAll(quad);
            if (cellCandidatesCopy.size() == 0) {
                selectedCells.add(cell);
            }
        }
        if (selectedCells.size() == 4) {
            Set<Cell> houseCells = new HashSet<Cell>(house.getCells());
            houseCells.removeAll(selectedCells);
            boolean changed = false;
            for (Cell cell : houseCells) {
                if (cell.removeAll(quad)) {
                    Cell[] cells = selectedCells.toArray(new Cell[]{});
                    LOGGER.info(NAME + ": " + cell.getPosition() + " cannot contain " + quad + " due to naked quad in " + cells[0].getPosition() + ", " + cells[1].getPosition() + ", " + cells[2].getPosition() + " and " + cells[3].getPosition());
                    changed = true;
                }
            }
            return changed;
        }
        return false;
    }
}