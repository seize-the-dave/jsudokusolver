/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

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
 *
 * @author David Grant
 */
public class NakedTriple implements SolvingStrategy {
    private static String NAME = "Naked Triple";
    private static final Logger LOGGER = Logger.getLogger(NakedTriple.class.getCanonicalName());
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
                    Set<Integer> triple = Cell.generateCandidateSet(i, j, k);
                    if (solveHouses(grid.getBoxes(), triple)) {
                        return true;
                    }
                    if (solveHouses(grid.getColumns(), triple)) {
                        return true;
                    }
                    if (solveHouses(grid.getRows(), triple)) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    private boolean solveHouses(List<House> houses, Set<Integer> triple) {
        for (House house : houses) {
            if (solveHouse(house, triple)) {
                return true;
            }
        }
        return false;
    }
    
    private boolean solveHouse(House house, Set<Integer> triple) {
        Set<Cell> selectedCells = new HashSet<Cell>();
        for (Cell cell : house.getCells()) {
            if (cell.isSolved()) {
                // This house already contains a cell with
                // one of this numbers triples.
                if (triple.contains(cell.getDigit())) {
                    return false;
                }
                continue;
            }
            Set<Integer> cellCandidates = cell.getCandidates();
            if (cellCandidates.size() > 3) {
                // Can't possibly be a subset
                continue;
            }
            Set<Integer> cellCandidatesCopy = new HashSet<Integer>(cellCandidates);
            cellCandidatesCopy.removeAll(triple);
            if (cellCandidatesCopy.size() == 0) {
                selectedCells.add(cell);
            }
        }
        if (selectedCells.size() == 3) {
            Set<Cell> houseCells = new HashSet<Cell>(house.getCells());
            houseCells.removeAll(selectedCells);
            boolean changed = false;
            for (Cell cell : houseCells) {
                if (cell.removeAll(triple)) {
                    Cell[] cells = selectedCells.toArray(new Cell[]{});
                    grid.logCandidateRemoval(cell, triple, NAME, cells);
                    changed = true;
                }
            }
            return changed;
        }
        return false;
    }
}
