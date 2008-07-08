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
import com.google.code.jsudokusolver.strategy.Squirmbag;
import com.google.code.jsudokusolver.strategy.Swordfish;
import com.google.code.jsudokusolver.strategy.XWing;

public class Main {
    public static void main(String[] args) throws Exception {
        Grid grid = new Grid(9);
        String puzzle = "801006094" +
                        "300009080" +
                        "970080500" +
                        "547062030" +
                        "632000050" +
                        "198375246" + 
                        "083620915" +
                        "065198000" +
                        "219500008";
        if (args.length == 1) {
		    puzzle = args[0];
	    }
        grid.fill(puzzle);

        grid.registerStrategy(new HiddenSingle());
        grid.registerStrategy(new HiddenPair());
        grid.registerStrategy(new HiddenTriple());
        grid.registerStrategy(new HiddenQuad());
        grid.registerStrategy(new NakedSingle());
        grid.registerStrategy(new NakedPair());
        grid.registerStrategy(new NakedTriple());
        grid.registerStrategy(new NakedQuad());
        grid.registerStrategy(new LockedCandidate());
        grid.registerStrategy(new XWing());
        grid.registerStrategy(new Swordfish());
        grid.registerStrategy(new Jellyfish());
//        grid.registerStrategy(new Squirmbag());

        grid.solve();
        System.out.println(grid);
        System.out.println("Export: " + grid.getPuzzle());
    }
}
