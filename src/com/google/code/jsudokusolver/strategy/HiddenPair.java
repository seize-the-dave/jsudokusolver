package com.google.code.jsudokusolver.strategy;

/**
 * @author David Grant
 */
public class HiddenPair extends AbstractHiddenSubset {
    private static final String NAME = "Hidden Pair";

    public String getName() {
        return NAME;
    }
    
    /**
     * {@inheritDoc}
     */
    protected int getSetSize() {
        return 2;
    }
}
