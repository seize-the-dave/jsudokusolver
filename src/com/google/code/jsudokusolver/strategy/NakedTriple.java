package com.google.code.jsudokusolver.strategy;

/**
 * @author David Grant
 */
public class NakedTriple extends AbstractNakedSubset {
    private static String NAME = "Naked Triple";

    public String getName() {
        return NAME;
    }
    
    @Override
    protected int getSetSize() {
        return 3;
    }
}
