package com.google.code.jsudokusolver;

import com.google.code.jsudokusolver.strategy.HiddenPair;
import com.google.code.jsudokusolver.strategy.HiddenQuad;
import com.google.code.jsudokusolver.strategy.HiddenSingle;
import com.google.code.jsudokusolver.strategy.HiddenTriple;
import com.google.code.jsudokusolver.strategy.Jellyfish;
import com.google.code.jsudokusolver.strategy.LockedCandidate;
import com.google.code.jsudokusolver.strategy.NakedPair;
import com.google.code.jsudokusolver.strategy.NakedQuad;
import com.google.code.jsudokusolver.strategy.NakedSingle;
import com.google.code.jsudokusolver.strategy.NakedTriple;
import com.google.code.jsudokusolver.strategy.Swordfish;
import com.google.code.jsudokusolver.strategy.XWing;

public class Main {
    public static void main(String[] args) throws Exception {
        String puzzle = "801006094" +
                        "300009080" +
                        "970080500" +
                        "547062030" +
                        "632000050" +
                        "198375246" + 
                        "083620915" +
                        "065198000" +
                        "219500008";
        if (args.length == 1) 
        {
		    puzzle = args[0];
	    }
        Grid grid = Grid.parseGrid(puzzle);
        
        Solver solver = new Solver();
        solver.addSolvingStrategy(new HiddenSingle());
        solver.addSolvingStrategy(new HiddenPair());
        solver.addSolvingStrategy(new HiddenTriple());
        solver.addSolvingStrategy(new HiddenQuad());
        solver.addSolvingStrategy(new NakedSingle());
        solver.addSolvingStrategy(new NakedPair());
        solver.addSolvingStrategy(new NakedTriple());
        solver.addSolvingStrategy(new NakedQuad());
        solver.addSolvingStrategy(new LockedCandidate());
//        solver.addSolvingStrategy(new XWing());
//        solver.addSolvingStrategy(new Swordfish());
//        solver.addSolvingStrategy(new Jellyfish());
        solver.solve(grid);
        
        System.out.println(grid);
        System.out.println("Export: " + grid.getPuzzle());
    }
}
