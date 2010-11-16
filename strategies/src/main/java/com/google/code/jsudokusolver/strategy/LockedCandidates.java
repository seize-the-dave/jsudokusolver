package com.google.code.jsudokusolver.strategy;

import java.util.Collection;
import java.util.List;

import com.google.code.jsudokusolver.Box;
import com.google.code.jsudokusolver.Cell;
import com.google.code.jsudokusolver.Column;
import com.google.code.jsudokusolver.Grid;
import com.google.code.jsudokusolver.House;
import com.google.code.jsudokusolver.Row;
import com.google.code.jsudokusolver.SolverStrategy;

/**
 * @see http://www.sudopedia.org/wiki/Locked_Candidates
 */
public class LockedCandidates implements SolverStrategy 
{
    /**
     * {@inheritDoc}
     */
    public boolean solve(Grid grid)
    {
    	if (solvePointing(grid)) 
    	{
    		return true;
    	}
    	return solveClaiming(grid);
    }

    public boolean solvePointing(Grid grid)
    {
        List<Box> boxes = grid.getBoxes();
        for (int i = 1; i <= 9; i++) 
        {
            for (House box : boxes) 
            {
                Collection<Cell> cells = box.getCellsWithCandidate(i);
                // We're not interested in boxes with only zero or one candidate
                // as they have either been solved, or are immediately solvable.
                if (cells.size() < 2) 
                {
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
                    Collection<Cell> columnCells = search.getCellsWithCandidate(i);
                    if (columnCells.equals(cells) == false) {
                        columnCells.removeAll(cells);
                        for (Cell cell : columnCells) {
                            cell.remove(i);
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
                	Collection<Cell> rowCells = search.getCellsWithCandidate(i);
                    if (rowCells.equals(cells) == false) {
                        rowCells.removeAll(cells);
                        for (Cell cell : rowCells) {
                            cell.remove(i);
                        }
                        return true;
                    }
                }
            }
        }
        return false;
    }
    
    public boolean solveClaiming(Grid grid) 
    {
    	boolean solved = false;
    	List<Column> columns = grid.getColumns();
    	List<Row> rows = grid.getRows();
    	for (int i = 1; i <= 9; i++) {
    		for (House column : columns) {
    			Collection<Cell> cells = column.getCellsWithCandidate(i);
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
    				Collection<Cell> boxCells = search.getCellsWithCandidate(i);
                    if (boxCells.equals(cells) == false) {
                        boxCells.removeAll(cells);
                        for (Cell cell : boxCells) {
                            cell.remove(i);
                        }
                        return true;
                    }
                }
    		}
    		for (House row : rows) {
    			Collection<Cell> cells = row.getCellsWithCandidate(i);
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
    				Collection<Cell> boxCells = search.getCellsWithCandidate(i);
                    if (boxCells.equals(cells) == false) {
                        boxCells.removeAll(cells);
                        for (Cell cell : boxCells) {
                            cell.remove(i);
                        }
                        return true;
                    }
                }
    		}
    	}
    	return solved;
    }
}
