package com.google.code.jsudokusolver;

import java.util.Arrays;
import java.util.Collection;
import java.util.TreeSet;
import java.util.logging.Logger;

public class Util 
{
	private static final Logger LOGGER = Logger.getLogger(Grid.class.getCanonicalName());
    private static int step = 1;
    
    private static void log(String message) {
        LOGGER.info(message);
    }

	public static void logCandidateRemoval(Cell cell, int candidate, String strategy, Collection<Cell> reference) {
	    log(step + " " + strategy + ": " + cell.getPosition() + " cannot contain " + candidate + " due to " + strategy + " in " + formatCells(reference));
	}

	public static void logCandidateRemoval(Cell cell, Collection<Integer> candidates, String strategy, Collection<Cell> reference) {
	    log(step + " " + strategy + ": " + cell.getPosition() + " cannot contain " + formatCandidates(candidates, false) + " due to " + strategy + " in " + formatCells(reference));
	}

	public static void logCandidateRemoval(Cell cell, Collection<Integer> candidates, String strategy, Cell... reference) {
		Collection<Cell> referenceSet = new TreeSet<Cell>(Arrays.asList(reference));
	    log(step + " " + strategy + ": " + cell.getPosition() + " cannot contain " + formatCandidates(candidates, false) + " due to " + strategy + " in " + formatCells(referenceSet));
	}

	public static void logCandidateRetention(Cell cell, int candidate, String strategy) {
	    log(step + " " + strategy + ": " + cell.getPosition() + " can only contain " + candidate);
	}

	public static void logCandidateRetention(Cell cell, Collection<Integer> candidates, String strategy) {
	    log(step + " " + strategy + ": " + cell.getPosition() + " can only contain " + formatCandidates(candidates, true));
	}

	public static void logCandidateRetention(Collection<Cell> cells, Collection<Integer> candidates, String strategy) {
	    log(step + " " + strategy + ": " + formatCells(cells) + " can only contain " + formatCandidates(candidates, true));
	}

	public static String formatCells(Collection<Cell> cells) 
	{
	    StringBuffer sb = new StringBuffer();
	    Cell[] cellArray = cells.toArray(new Cell[]{});
	    for (int i = 0; i < cellArray.length; i++) 
	    {
	        sb.append(cellArray[i].getPosition());
	        if (cellArray.length - i == 2) 
	        {
	            sb.append(" and ");
	        }
	        if (cellArray.length - i > 2) 
	        {
	            sb.append(", ");
	        }
	    }
	    return sb.toString();
	}

	public static String formatCandidates(Collection<Integer> cells, boolean and) {
	    StringBuffer sb = new StringBuffer();
	    Integer[] cellArray = cells.toArray(new Integer[]{});
	    for (int i = 0; i < cellArray.length; i++) {
	        sb.append(cellArray[i]);
	        if (cellArray.length - i == 2) {
	            if (and) {
	                sb.append(" and ");
	            } else {
	                sb.append(" or ");
	            }
	        }
	        if (cellArray.length - i > 2) {
	            sb.append(", ");
	        }
	    }
	    return sb.toString();
	}

}
