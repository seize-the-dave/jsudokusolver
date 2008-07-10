package com.google.code.jsudokusolver.strategy;

import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.TreeSet;

import com.google.code.jsudokusolver.Cell;
import com.google.code.jsudokusolver.Grid;
import com.google.code.jsudokusolver.House;
import com.google.code.jsudokusolver.SolvingStrategy;

/**
 * @author David Grant
 */
abstract public class AbstractBasicFish implements SolvingStrategy {
    private Grid grid;

    /**
     * {@inheritDoc}
     */
    public void setGrid(Grid grid) {
        this.grid = grid;
    }
    
    /**
     * Returns the size of the fish.
     * 
     * The size of the fist denotes the maximum numbers of cells
     * that the implementation should look for, e.g. 2 for X-Wing 
     * and 3 for Swordfish.
     * 
     * @return the size of this fish
     */
    abstract protected int getFishSize();
    
    /**
     * {@inheritDoc}
     */
    public boolean solve() {
        List<House> rows = grid.getRows();
        List<House> columns = grid.getColumns();
        for (int i = 1; i <= grid.getSize(); i++) {
            if (solveRow(i, rows)) {
                return true;
            }
            if (solveColumn(i, columns)) {
                return true;
            }
        }
        return false;
    }

    private boolean solveRow(int candidate, List<House> rows) {
        Map<Integer, Set<Integer>> rowMap = new TreeMap<Integer, Set<Integer>>();
        Set<Integer> columnPopulation = new TreeSet<Integer>();
        for (House row : rows) {
            Set<Cell> cells = row.getCellsWithCandidate(candidate);
            Set<Integer> columnSet = new TreeSet<Integer>();
            for (Cell cell : cells) {
                columnSet.add(cell.getColumn().getOffset());
            }
            // Ignore singles
            // We could probably optimise here to remove
            // rows that can't possibly fit into the combinations below
            if (columnSet.size() > 1) {
                columnPopulation.addAll(columnSet);
                rowMap.put(row.getOffset(), columnSet);
            }
        }
        // There needs to be more rows than this fish size, otherwise
        // there will be no cells to remove the candidate from.
        if (tooSmall(rowMap)) {
            return false;
        }

        Set<Set<Integer>> combinations = new TreeSet<Set<Integer>>();
        combinations = Cell.generateCombinations(columnPopulation, 
                                                 combinations, 
                                                 getFishSize());
        
        for (Set<Integer> combination : combinations) {
            Set<Map.Entry<Integer, Set<Integer>>> matches = findMatches(rowMap, combination);
            if (wrongSize(matches)) {
                continue;
            }
            Set<Cell> fish = new TreeSet<Cell>();
            for (Map.Entry<Integer, Set<Integer>> entry : matches) {
                House row = rows.get(entry.getKey() - 1);
                for (Integer column : entry.getValue()) {
                    fish.add(row.getCells().get(column - 1));
                }
                rowMap.remove(entry.getKey());
            }
            boolean solved = false;
            for (Map.Entry<Integer, Set<Integer>> entry : rowMap.entrySet()) {
                entry.getValue().retainAll(combination);
                House row = rows.get(entry.getKey() - 1);
                for (Integer column : entry.getValue()) {
                    Cell cell = row.getCells().get(column - 1);
                    cell.remove(candidate);
                    Grid.logCandidateRemoval(cell, candidate, getName(), fish);
                    solved = true;
                }
            }
            if (solved) {
                return true;
            }
        }
        return false;
    }

    private Set<Map.Entry<Integer, Set<Integer>>> findMatches(Map<Integer, Set<Integer>> rowMap, Set<Integer> combination) {
        Set<Map.Entry<Integer, Set<Integer>>> matches = new HashSet<Map.Entry<Integer, Set<Integer>>>();
        for (Map.Entry<Integer, Set<Integer>> entry : rowMap.entrySet()) {
            if (combination.containsAll(entry.getValue())) {
                matches.add(entry);
            }
        }
        return matches;
    }
    
    private boolean solveColumn(int candidate, List<House> columns) {
        Map<Integer, Set<Integer>> columnMap = new TreeMap<Integer, Set<Integer>>();
        Set<Integer> rowPopulation = new TreeSet<Integer>();
        for (House column : columns) {
            Set<Cell> cells = column.getCellsWithCandidate(candidate);
            Set<Integer> rowSet = new TreeSet<Integer>();
            for (Cell cell : cells) {
                rowSet.add(cell.getRow().getOffset());
            }
            // Ignore singles
            // We could probably optimise here to remove
            // rows that can't possibly fit into the combinations below
            if (rowSet.size() > 1) {
                rowPopulation.addAll(rowSet);
                columnMap.put(column.getOffset(), rowSet);
            }
        }
        // There needs to be more rows than this fish size, otherwise
        // there will be no cells to remove the candidate from.
        if (tooSmall(columnMap)) {
            return false;
        }

        Set<Set<Integer>> combinations = new TreeSet<Set<Integer>>();
        combinations = Cell.generateCombinations(rowPopulation, 
                                                 combinations, 
                                                 getFishSize());
        
        for (Set<Integer> combination : combinations) {
            Set<Map.Entry<Integer, Set<Integer>>> matches = findMatches(columnMap, combination);
            if (wrongSize(matches)) {
                continue;
            }
            Set<Cell> fish = new TreeSet<Cell>();
            for (Map.Entry<Integer, Set<Integer>> entry : matches) {
                House column = columns.get(entry.getKey() - 1);
                for (Integer row : entry.getValue()) {
                    fish.add(column.getCells().get(row - 1));
                }
                columnMap.remove(entry.getKey());
            }
            boolean solved = false;
            for (Map.Entry<Integer, Set<Integer>> entry : columnMap.entrySet()) {
                entry.getValue().retainAll(combination);
                House column = columns.get(entry.getKey() - 1);
                for (Integer row : entry.getValue()) {
                    Cell cell = column.getCells().get(row - 1);
                    cell.remove(candidate);
                    Grid.logCandidateRemoval(cell, candidate, getName(), fish);
                    solved = true;
                }
            }
            if (solved) {
                return true;
            }
        }
        return false;
    }
    
    private boolean tooSmall(Map<Integer, Set<Integer>> map) {
        return map.size() <= getFishSize();
    }
    
    private boolean wrongSize(Set<Map.Entry<Integer, Set<Integer>>> set) {
        return set.size() != getFishSize();
    }
}
