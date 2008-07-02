/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.google.code.jsudokusolver.strategy;

/**
 *
 * @author David Grant
 */
public class HiddenTriple extends AbstractHiddenSubset {
    private static final String NAME = "Hidden Triple";

    public String getName() {
        return NAME;
    }
    
    /**
     * {@inheritDoc}
     */
    protected int getSetSize() {
        return 3;
    }
}
