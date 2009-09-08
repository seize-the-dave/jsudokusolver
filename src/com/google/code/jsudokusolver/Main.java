package com.google.code.jsudokusolver;

import com.google.code.jsudokusolver.strategy.Given;
import com.google.code.jsudokusolver.strategy.NakedSingle;

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
        solver.addSolvingStrategy(new Given());
        solver.addSolvingStrategy(new NakedSingle());
        solver.solve(grid);
        
        System.out.println(grid);
    }
}
