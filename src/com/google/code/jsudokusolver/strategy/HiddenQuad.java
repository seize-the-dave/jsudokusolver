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
public class HiddenQuad implements SolvingStrategy {
    private static final Logger LOGGER = Logger.getLogger(HiddenQuad.class.getCanonicalName());
    private static final String NAME = "Hidden Quad";
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
            for (int j = i + 1; j <= grid.getSize(); j++) {
                for (int k = j + 1; k <= grid.getSize(); k++) {
                    for (int l = k + 1; l <= grid.getSize(); l++) {
                        // Generate quad to search for
                        Set<Integer> quad = Cell.generateCandidateSet(i, j, k, l);
                        for (House house : houses) {
                            if (house.getUnsolvedCells().size() < 5) {
                                continue;
                            }
                            if (searchHouse(house, quad)) {
                                return true;
                            }
                        }
                    }
                }
            }
        }
        return false;
    }

    private boolean searchHouse(House house, Set<Integer> quad) {
        boolean changed = false;
        final Set<Cell> matchingCells = new HashSet<Cell>();
        for (Cell cell : house.getCells()) {
            if (cell.isSolved()) {
                continue;
            }
            Set<Integer> cellCandidates 
                    = new HashSet<Integer>(cell.getCandidates());
            cellCandidates.retainAll(quad);
            if (cellCandidates.size() != 0) {
                matchingCells.add(cell);
            }
        }
        if (matchingCells.size() == 4) {
            // Make sure all of the quad if covered
            Set<Integer> quadQopy = new HashSet<Integer>(quad);
            for (Cell cell : matchingCells) {
                quadQopy.removeAll(cell.getCandidates());
            }
            // Some left, so this is false
            if (quadQopy.size() != 0) {
                return false;
            }
            for (Cell cell : matchingCells) {
                if (cell.retainAll(quad)) {
                    changed = true;
                }
            }
            if (changed) {
                grid.logCandidateRetention(matchingCells, quad, NAME);
            }
        }
        return changed;
    }
}