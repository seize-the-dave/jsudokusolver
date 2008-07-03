package com.google.code.jsudokusolver.strategy;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
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
            Set<Cell> fishCells = new HashSet<Cell>();
            for (House row : rows) {
                Set<Cell> cells = row.getCellsWithCandidate(i);
                // Filter rows with the wrong number of cells
                if (cells.size() > getFishSize() || cells.size() < 2) {
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
                    fishCells.add(cell);
                }
            }
            
            Set<Integer> colElements = new HashSet<Integer>(); 
            for (Cell cell : fishCells) {
            	colElements.add(cell.getColumn().getOffset());
            }
            Set<Integer> rowElements = new HashSet<Integer>(); 
            for (Cell cell : fishCells) {
            	rowElements.add(cell.getRow().getOffset());
            }
            
            Set<Set<Integer>> colCombinations = new HashSet<Set<Integer>>();
            colCombinations = Cell.generateCombinations(colElements, colCombinations, getFishSize());
            Set<Set<Integer>> rowCombinations = new HashSet<Set<Integer>>();
            rowCombinations = Cell.generateCombinations(rowElements, rowCombinations, getFishSize());

            for (Set<Integer> colCombination : colCombinations) {
               	for (Set<Integer> rowCombination : rowCombinations) {
               		Map<Integer, Integer> rowFrequency = getFrequencyMap(rowCombination);
               		Map<Integer, Integer> colFrequency = getFrequencyMap(colCombination);
               		Set<Cell> matches = new HashSet<Cell>();
               		for (Cell cell : fishCells) {
               			int row = cell.getRow().getOffset();
            			int col = cell.getColumn().getOffset();
            			if (rowCombination.contains(row) && colCombination.contains(col)) {
            				matches.add(cell);
            				incrementFrequencyMap(rowFrequency, row);
            				incrementFrequencyMap(colFrequency, col);
            			}
               		}
                	if (validateFrequencyMap(rowFrequency) && validateFrequencyMap(colFrequency)) {
                		boolean changed = false;
    	            	for (Cell cell : matches) {
    	            		House column = cell.getColumn();
    	            		Set<Cell> colCells = new HashSet<Cell>(column.getCellsWithCandidate(i));
    	            		colCells.removeAll(matches);
    	            		for (Cell colCell : colCells) {
    	            			if (colCell.remove(i)) {
    	            				changed = true;
    	                            Grid.logCandidateRemoval(colCell, i, getName() + "-R", matches);
    	            			}
    	            		}
    	            	}
    	            	if (changed) {
    	                    return true;
    	                }
                	}
                }
            }
        }
        return false;
    }
    
    private boolean solveColumn() {
        List<House> columns = grid.getColumns();
        for (int i = 1; i <= grid.getSize(); i++) {
            Set<Cell> fishCells = new HashSet<Cell>();
            for (House column : columns) {
                Set<Cell> cells = column.getCellsWithCandidate(i);
                // Filter columns with the wrong number of cells
                if (cells.size() > getFishSize() || cells.size() < 2) {
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
                    fishCells.add(cell);
                }
            }
            Set<Integer> rowElements = new HashSet<Integer>(); 
            for (Cell cell : fishCells) {
            	rowElements.add(cell.getRow().getOffset());
            }
            Set<Integer> colElements = new HashSet<Integer>(); 
            for (Cell cell : fishCells) {
            	colElements.add(cell.getColumn().getOffset());
            }
            
            Set<Set<Integer>> rowCombinations = new HashSet<Set<Integer>>();
            rowCombinations = Cell.generateCombinations(rowElements, rowCombinations, getFishSize());
            Set<Set<Integer>> colCombinations = new HashSet<Set<Integer>>();
            colCombinations = Cell.generateCombinations(colElements, colCombinations, getFishSize());

            for (Set<Integer> rowCombination : rowCombinations) {
               	for (Set<Integer> colCombination : colCombinations) {
               		Map<Integer, Integer> rowFrequency = getFrequencyMap(rowCombination);
               		Map<Integer, Integer> colFrequency = getFrequencyMap(colCombination);
               		Set<Cell> matches = new HashSet<Cell>();
               		for (Cell cell : fishCells) {
               			int row = cell.getRow().getOffset();
            			int col = cell.getColumn().getOffset();
            			if (rowCombination.contains(row) && colCombination.contains(col)) {
            				matches.add(cell);
            				incrementFrequencyMap(rowFrequency, row);
            				incrementFrequencyMap(colFrequency, col);
            			}
               		}
                	if (validateFrequencyMap(rowFrequency) && validateFrequencyMap(colFrequency)) {
                		boolean changed = false;
    	            	for (Cell cell : matches) {
    	            		House row = cell.getRow();
    	            		Set<Cell> rowCells = new HashSet<Cell>(row.getCellsWithCandidate(i));
    	            		rowCells.removeAll(matches);
    	            		for (Cell rowCell : rowCells) {
    	            			if (rowCell.remove(i)) {
    	            				changed = true;
    	                            Grid.logCandidateRemoval(rowCell, i, getName() + "-C", matches);
    	            			}
    	            		}
    	            	}
    	            	if (changed) {
    	                    return true;
    	                }
                	}
                }
            }
        }
        return false;
    }
    
    private Map<Integer, Integer> getFrequencyMap(Set<Integer> keys) {
    	Map<Integer, Integer> frequencyMap = new HashMap<Integer, Integer>();
    	for (Integer key : keys) {
    		frequencyMap.put(key, 0);
    	}
    	return frequencyMap;
    }
    
    private void incrementFrequencyMap(Map<Integer, Integer> frequencyMap, int key) {
    	frequencyMap.put(key, frequencyMap.get(key) + 1);
    }
    
    private boolean validateFrequencyMap(Map<Integer, Integer> frequencyMap) {
    	for (Integer frequency : frequencyMap.values()) {
    		if (frequency > getFishSize() || frequency < 2) {
    			return false;
    		}
    	}
    	return true;
    }
}
