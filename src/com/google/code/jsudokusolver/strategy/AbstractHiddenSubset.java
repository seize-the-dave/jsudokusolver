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

/**
 *
 * @author Dave
 */
abstract public class AbstractHiddenSubset implements SolvingStrategy {
    protected Grid grid;
    
    abstract protected boolean solveHouses(List<House> houses);
    
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
    
    protected boolean searchHouse(House house, Set<Integer> subset) {
        boolean changed = false;
        final Set<Cell> matchingCells = new HashSet<Cell>();
        for (Cell cell : house.getCells()) {
            if (cell.isSolved()) {
                continue;
            }
            Set<Integer> cellCandidates 
                    = new HashSet<Integer>(cell.getCandidates());
            cellCandidates.retainAll(subset);
            if (cellCandidates.size() != 0) {
                matchingCells.add(cell);
            }
        }
        if (matchingCells.size() == subset.size()) {
            // Make sure all of the quad if covered
            Set<Integer> subsetCopy = new HashSet<Integer>(subset);
            for (Cell cell : matchingCells) {
                subsetCopy.removeAll(cell.getCandidates());
            }
            // Some left, so this is false
            if (subsetCopy.size() != 0) {
                return false;
            }
            for (Cell cell : matchingCells) {
                if (cell.retainAll(subset)) {
                    changed = true;
                }
            }
            if (changed) {
                Grid.logCandidateRetention(matchingCells, subset, getName());
            }
        }
        return changed;
    }
}
