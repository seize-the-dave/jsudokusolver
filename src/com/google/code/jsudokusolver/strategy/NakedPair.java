package com.google.code.jsudokusolver.strategy;

/**
 * @author David Grant
 */
public class NakedPair extends AbstractNakedSubset {
    private static final String NAME = "Naked Pair";
    
    public String getName() {
        return NAME;
    }
    
    @Override
    protected int getSetSize() {
        return 2;
    }
}
