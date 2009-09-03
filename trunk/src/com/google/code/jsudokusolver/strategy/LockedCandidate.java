package com.google.code.jsudokusolver.strategy;

import java.util.List;
import java.util.Set;

import com.google.code.jsudokusolver.Cell;
import com.google.code.jsudokusolver.Grid;
import com.google.code.jsudokusolver.House;
import com.google.code.jsudokusolver.ReferenceReason;
import com.google.code.jsudokusolver.SolvingStrategy;

/**
 * @author David Grant
 */
public class LockedCandidate implements SolvingStrategy {
    private static final String NAME = "Locked Candidate";
    private Grid grid;

    /**
     * {@inheritDoc}
     */
    public String getName() {
        return NAME;
    }

    /**
     * {@inheritDoc}
     */
    public void setGrid(Grid grid) {
        this.grid = grid;
    }
    
    /**
     * {@inheritDoc}
     */
    public boolean solve() {
    	if (solveType1()) {
    		return true;
    	}
    	return solveType2();
    }

    public boolean solveType1() {
        List<House> boxes = grid.getBoxes();
        for (int i = 1; i <= grid.getSize(); i++) {
            for (House box : boxes) {
                Set<Cell> cells = box.getCellsWithCandidate(i);
                // We're not interested in boxes with only zero or one candidate
                // as they have either been solved, or are immediately solvable.
                if (cells.size() < 2) {
                    continue;
                }
                House search = null;
                for (Cell cell : cells) {
                    House column = cell.getColumn();
                    if (search == null) {
                        search = column;
                    } else if (search.equals(column) == false) {
                        search = null;
                        break;
                    }
                }
                if (search != null) {
                    Set<Cell> columnCells = search.getCellsWithCandidate(i);
                    ReferenceReason reason = new ReferenceReason(NAME, cells);
                    if (columnCells.equals(cells) == false) {
                        columnCells.removeAll(cells);
                        for (Cell cell : columnCells) {
                            cell.remove(i, reason);
                        }
                        return true;
                    }
                }
                for (Cell cell : cells) {
                    House row = cell.getRow();
                    if (search == null) {
                        search = row;
                    } else if (search.equals(row) == false) {
                        search = null;
                        break;
                    }
                }
                if (search != null) {
                    Set<Cell> rowCells = search.getCellsWithCandidate(i);
                    if (rowCells.equals(cells) == false) {
                        rowCells.removeAll(cells);
                        ReferenceReason reason = new ReferenceReason(NAME, cells);
                        for (Cell cell : rowCells) {
                            cell.remove(i, reason);
                        }
                        return true;
                    }
                }
            }
        }
        return false;
    }
    
    public boolean solveType2() {
    	boolean solved = false;
    	List<House> columns = grid.getColumns();
    	List<House> rows = grid.getColumns();
    	for (int i = 1; i <= grid.getSize(); i++) {
    		for (House column : columns) {
    			Set<Cell> cells = column.getCellsWithCandidate(i);
    			House search = null;
    			for (Cell cell : cells) {
    				House box = cell.getBox();
                    if (search == null) {
                        search = box;
                    } else if (search.equals(box) == false) {
                        search = null;
                        break;
                    }
    			}
    			if (search != null) {
                    Set<Cell> boxCells = search.getCellsWithCandidate(i);
                    if (boxCells.equals(cells) == false) {
                        boxCells.removeAll(cells);
                        ReferenceReason reason = new ReferenceReason(NAME, cells);
                        for (Cell cell : boxCells) {
                            cell.remove(i, reason);
                        }
                        return true;
                    }
                }
    		}
    		for (House row : rows) {
    			Set<Cell> cells = row.getCellsWithCandidate(i);
    			House search = null;
    			for (Cell cell : cells) {
    				House box = cell.getBox();
                    if (search == null) {
                        search = box;
                    } else if (search.equals(box) == false) {
                        search = null;
                        break;
                    }
    			}
    			if (search != null) {
                    Set<Cell> boxCells = search.getCellsWithCandidate(i);
                    if (boxCells.equals(cells) == false) {
                        boxCells.removeAll(cells);
                        ReferenceReason reason = new ReferenceReason(NAME, cells);
                        for (Cell cell : boxCells) {
                            cell.remove(i, reason);
                        }
                        return true;
                    }
                }
    		}
    	}
    	return solved;
    }
}