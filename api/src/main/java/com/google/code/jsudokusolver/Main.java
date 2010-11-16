package com.google.code.jsudokusolver;

import java.util.ServiceLoader;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Main {
	private static Logger LOGGER = LoggerFactory.getLogger("DISCOVERY");
	
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
        Grid grid = Grid.fromString(puzzle);
        
        Solver solver = new Solver();
        ServiceLoader<SolverStrategy> strategies = ServiceLoader.load(SolverStrategy.class);
        for (SolverStrategy strategy : strategies) {
        	LOGGER.info("DISCOVERED " + strategy.getClass().getName());
        	solver.addStrategy(strategy);
        }
        solver.solve(grid);
        
        System.out.println(grid);
    }
}
