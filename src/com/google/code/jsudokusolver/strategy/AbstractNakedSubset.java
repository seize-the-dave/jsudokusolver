package com.google.code.jsudokusolver.strategy;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

import com.google.code.jsudokusolver.Cell;
import com.google.code.jsudokusolver.Grid;
import com.google.code.jsudokusolver.House;
import com.google.code.jsudokusolver.SolvingStrategy;

abstract public class AbstractNakedSubset implements SolvingStrategy {
    private Grid grid;
    /**
     * Gets the size of the subset that is being searched for, e.g. 4 for a
     * Naked Quad, 3 for a Naked Triple.
     * 
     * @return the subset size
     */
    abstract protected int getSetSize();

    public void setGrid(Grid grid) {
        this.grid = grid;
    }

    public boolean solve() {
        Set<Set<Integer>> combinations = new HashSet<Set<Integer>>();
        combinations = Cell.generateCombinations(Cell.generateCandidateSet(1, 9), 
                                                 combinations, 
                                                 getSetSize());
        for (Set<Integer> combination : combinations) {
            if (solveHouses(grid.getBoxes(), combination)) {
                return true;
            }
            if (solveHouses(grid.getColumns(), combination)) {
                return true;
            }
            if (solveHouses(grid.getRows(), combination)) {
                return true;
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
    
    private boolean solveHouse(House house, Set<Integer> combination) {
        Set<Cell> selectedCells = new TreeSet<Cell>();
        for (Cell cell : house.getCells()) {
            if (cell.isSolved()) {
                // This house already contains a cell with
                // one of this quad's numbers.
                if (combination.contains(cell.getDigit())) {
                    return false;
                }
                continue;
            }
            Set<Integer> cellCandidates = cell.getCandidates();
            if (cellCandidates.size() > getSetSize()) {
                // Can't possibly be a subset
                continue;
            }
            Set<Integer> cellCandidatesCopy = new HashSet<Integer>(cellCandidates);
            cellCandidatesCopy.removeAll(combination);
            if (cellCandidatesCopy.size() == 0) {
                selectedCells.add(cell);
            }
        }
        if (selectedCells.size() == getSetSize()) {
            Set<Cell> houseCells = new HashSet<Cell>(house.getCells());
            houseCells.removeAll(selectedCells);
            boolean changed = false;
            for (Cell cell : houseCells) {
                if (cell.removeAll(combination)) {
                    Cell[] cells = selectedCells.toArray(new Cell[]{});
                    Grid.logCandidateRemoval(cell, combination, getName(), cells);
                    changed = true;
                }
            }
            return changed;
        }
        return false;
    }
}
