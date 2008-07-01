package com.google.code.jsudokusolver.strategy;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.TreeSet;
import java.util.logging.Logger;

import com.google.code.jsudokusolver.Cell;
import com.google.code.jsudokusolver.Grid;
import com.google.code.jsudokusolver.House;
import com.google.code.jsudokusolver.SolvingStrategy;
import java.util.Collections;

/**
*
* @author David Grant
*/
public class XWing implements SolvingStrategy {
    private final static String NAME = "X-Wing";
    private static final Logger LOGGER = Logger.getLogger(NakedTriple.class.getCanonicalName());
    private Grid grid;
    
    public String getName() {
        return NAME;
    }

    public void setGrid(Grid grid) {
        this.grid = grid;
    }
    
    public boolean solve() {
        if (solveForRows()) {
            return true;
        }
        return solveForColumns();
    }

    public boolean solveForRows() {
        boolean solved = false;
        List<House> rows = grid.getRows();
        List<House> columns = grid.getColumns();
        for (int i = 1; i <= grid.getSize(); i++) {
            Map<Integer, Set<Integer>> matches = new TreeMap<Integer, Set<Integer>>();
            for (House row : rows) {
                Set<Integer> rowMatches = new TreeSet<Integer>();
                for (Cell cell : row.getUnsolvedCells()) {
                    if (cell.contains(i)) {
                        rowMatches.add(cell.getColumn().getOffset());
                    }
                }
                if (rowMatches.isEmpty() == false && rowMatches.size() == 2) {
                    matches.put(row.getOffset(), rowMatches);
                }
            }
            if (matches.size() < 2) {
                continue;
            }
//            System.out.println(matches);
            for (Map.Entry<Integer, Set<Integer>> entry : matches.entrySet()) {
                Set<Integer> matchSet = entry.getValue();
                Set<Integer> matchingRows = new TreeSet<Integer>();
                matchingRows.add(entry.getKey());
                for (Map.Entry<Integer, Set<Integer>> searchEntry : matches.entrySet()) {
                    if (entry.getKey() >= searchEntry.getKey()) {
                        continue;
                    }
//                    System.out.println("Is " + searchEntry + " a match?");
                    Set<Integer> searchSet = new HashSet<Integer>(searchEntry.getValue());
                    searchSet.removeAll(matchSet);
                    if (searchSet.size() == 0) {
                        matchingRows.add(searchEntry.getKey());
//                        System.out.println("Yes!");
                    }
                }
                if (matchingRows.size() != 2) {
                    // Not the right number of matched keys
                    continue;
                }
                Integer[] rowArray = matchingRows.toArray(new Integer[]{});
                Integer[] colArray = matchSet.toArray(new Integer[]{});
                for (Integer columnOffset : matchSet) {
                    House column = columns.get(columnOffset - 1);
                    for (Cell cell : column.getUnsolvedCells()) {
                        if (matchingRows.contains(cell.getRow().getOffset())) {
                            continue;
                        }
                        if (cell.remove(i)) {
                            solved = true;
                            LOGGER.info(NAME + ": (" + columnOffset + "," + cell.getRow().getOffset() + ") cannot contain [" + i + "] due to X-Wing in (" + colArray[0] + ", " + rowArray[0] + "), (" + colArray[0] + ", " + rowArray[1] + "), (" + colArray[1] + ", " + rowArray[0] + ") and (" + colArray[1] + ", " + rowArray[1] + ")");
                        }
                    }
                }
                if (solved == true) {
                    return true;
                }
            }
        }
        return false;
    }
    

    public boolean solveForColumns() {
        boolean solved = false;
        List<House> rows = grid.getRows();
        List<House> columns = grid.getColumns();
        for (int i = 1; i <= grid.getSize(); i++) {
            Map<Integer, Set<Integer>> matches = new TreeMap<Integer, Set<Integer>>();
            for (House column : columns) {
                Set<Integer> columnMatches = new TreeSet<Integer>();
                for (Cell cell : column.getUnsolvedCells()) {
                    if (cell.contains(i)) {
                        columnMatches.add(cell.getRow().getOffset());
                    }
                }
                if (columnMatches.isEmpty() == false && columnMatches.size() == 2) {
                    matches.put(column.getOffset(), columnMatches);
                }
            }
            if (matches.size() < 2) {
                continue;
            }
//            System.out.println(matches);
            for (Map.Entry<Integer, Set<Integer>> entry : matches.entrySet()) {
                Set<Integer> matchSet = entry.getValue();
                Set<Integer> matchingColumns = new TreeSet<Integer>();
                matchingColumns.add(entry.getKey());
                for (Map.Entry<Integer, Set<Integer>> searchEntry : matches.entrySet()) {
                    if (entry.getKey() >= searchEntry.getKey()) {
                        continue;
                    }
//                    System.out.println("Is " + searchEntry + " a match?");
                    Set<Integer> searchSet = new HashSet<Integer>(searchEntry.getValue());
                    searchSet.removeAll(matchSet);
                    if (searchSet.size() == 0) {
                        matchingColumns.add(searchEntry.getKey());
//                        System.out.println("Yes!");
                    }
                }
                if (matchingColumns.size() != 2) {
                    // Not the right number of matched keys
                    continue;
                }
                Integer[] colArray = matchingColumns.toArray(new Integer[]{});
                Integer[] rowArray = matchSet.toArray(new Integer[]{});
                for (Integer rowOffset : matchSet) {
                    House row = rows.get(rowOffset - 1);
                    for (Cell cell : row.getUnsolvedCells()) {
                        if (matchingColumns.contains(cell.getColumn().getOffset())) {
                            continue;
                        }
                        if (cell.remove(i)) {
                            solved = true;
                            LOGGER.info(NAME + ": (" + rowOffset + ", " + cell.getRow().getOffset() + ") cannot contain [" + i + "] due to X-Wing in (" + colArray[0] + ", " + rowArray[0] + "), (" + colArray[0] + ", " + rowArray[1] + "), (" + colArray[1] + ", " + rowArray[0] + ") and (" + colArray[1] + ", " + rowArray[1] + ")");
                        }
                    }
                }
                if (solved == true) {
                    return true;
                }
            }
        }
        return false;
    }
}