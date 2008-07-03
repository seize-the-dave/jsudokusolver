package com.google.code.jsudokusolver.strategy;

/**
 * @author David Grant
 */
public class NakedQuad extends AbstractNakedSubset {
    private static final String NAME = "Naked Quad";
    
    public String getName() {
        return NAME;
    }

    @Override
    protected int getSetSize() {
        return 4;
    }
}