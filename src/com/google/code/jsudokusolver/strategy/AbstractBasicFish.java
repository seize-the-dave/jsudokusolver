package com.google.code.jsudokusolver.strategy;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.google.code.jsudokusolver.Cell;
import com.google.code.jsudokusolver.Grid;
import com.google.code.jsudokusolver.House;
import com.google.code.jsudokusolver.SolvingStrategy;

/**
 * @author David Grant
 */
abstract public class AbstractBasicFish implements SolvingStrategy {
    private Grid grid;

    public void setGrid(Grid grid) {
        this.grid = grid;
    }
    
    abstract protected int getFishSize();
    
    public boolean solve() {
        if (solveRow()) {
            return true;
        }
        return solveColumn();
    }

    private boolean solveRow() {
        List<House> rows = grid.getRows();
        for (int i = 1; i <= grid.getSize(); i++) {
            Set<House> fishColumns = new HashSet<House>();
            Set<Cell> fishCells = new HashSet<Cell>();
            Set<House> fishRows = new HashSet<House>();
            for (House row : rows) {
                Set<Cell> cells = row.getCellsWithCandidate(i);
                // Filter rows with the wrong number of cells
                if (cells.size() > getFishSize() && cells.size() < 2) {
                    continue;
                }
                int chute = 0;
                boolean valid = false;
                for (Cell cell : cells) {
                    int cellChute = cell.getColumn().getChute();
                    if (chute == 0) {
                        chute = cellChute;
                    }
                    if (chute != cellChute) {
                        // One of the cells is in a different chute,
                        // so we can proceed.
                        valid = true;
                        break;
                    }
                }
                // We can't continue when all the cells are in the same chute.
                if (valid == false) {
                    continue;
                }
                for (Cell cell : cells) {
                    fishColumns.add(cell.getColumn());
                    fishRows.add(row);
                    fishCells.add(cell);
                }
            }
            if (fishColumns.size() == getFishSize() && fishRows.size() >= getFishSize()) {
                boolean changed = false;
                for (House column : fishColumns) {
                    Set<Cell> columnCells = new HashSet<Cell>(column.getCellsWithCandidate(i));
                    columnCells.removeAll(fishCells);
                    for (Cell cell : columnCells) {
                        if (cell.remove(i)) {
                            changed = true;
                            Grid.logCandidateRemoval(cell, i, getName(), fishCells);
                        }
                    }
                }
                if (changed) {
                    return true;
                }
            }
        }
        return false;
    }
    
    private boolean solveColumn() {
        List<House> columns = grid.getColumns();
        for (int i = 1; i <= grid.getSize(); i++) {
            Set<House> fishRows = new HashSet<House>();
            Set<Cell> fishCells = new HashSet<Cell>();
            Set<House> fishColumns = new HashSet<House>();
            for (House column : columns) {
                Set<Cell> cells = column.getCellsWithCandidate(i);
                // Filter columns with the wrong number of cells
                if (cells.size() > getFishSize() && cells.size() < 2) {
                    continue;
                }
                int chute = 0;
                boolean valid = false;
                for (Cell cell : cells) {
                    int cellChute = cell.getRow().getChute();
                    if (chute == 0) {
                        chute = cellChute;
                    }
                    if (chute != cellChute) {
                        // One of the cells is in a different chute,
                        // so we can proceed.
                        valid = true;
                        break;
                    }
                }
                // We can't continue when all the cells are in the same chute.
                if (valid == false) {
                    continue;
                }
                for (Cell cell : cells) {
                    fishRows.add(cell.getRow());
                    fishColumns.add(column);
                    fishCells.add(cell);
                }
            }
            if (fishRows.size() == getFishSize() && fishColumns.size() >= getFishSize()) {
                boolean changed = false;
                for (House row : fishRows) {
                    Set<Cell> rowCells = new HashSet<Cell>(row.getCellsWithCandidate(i));
                    rowCells.removeAll(fishCells);
                    for (Cell cell : rowCells) {
                        if (cell.remove(i)) {
                            changed = true;
                            Grid.logCandidateRemoval(cell, i, getName(), fishCells);
                        }
                    }
                }
                if (changed) {
                    return true;
                }
            }
        }
        return false;
    }
}
