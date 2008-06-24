/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.google.code.jsudokusolver;

import java.util.List;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Dave
 */
public class GridTest {
    private Grid grid;

    @Before
    public void setUp() {
        grid = new Grid(9);
    }

    /**
     * Test of fill method, of class Grid.
     */
    @Test
    public void testFill() throws Exception {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 81; i++) {
            sb.append("0");
        }
        grid.fill(sb.toString());
    }

    /**
     * Test of getRows method, of class Grid.
     */
    @Test
    public void testGetRows() {
        assertEquals(9, grid.getRows().size());
    }

    /**
     * Test of getColumns method, of class Grid.
     */
    @Test
    public void testGetColumns() {
        assertEquals(9, grid.getColumns().size());
    }

    /**
     * Test of getBoxes method, of class Grid.
     */
    @Test
    public void testGetBoxes() {
        assertEquals(9, grid.getBoxes().size());
    }

    /**
     * Test of getSize method, of class Grid.
     */
    @Test
    public void testGetSize() {
        assertEquals(9, grid.getSize());
    }

    /**
     * Test of registerStrategy method, of class Grid.
     */
    @Test
    public void testRegisterStrategy() {
        SolvingStrategy testStrategy = new SolvingStrategy() {
            public String getName(){
                return "Anonymous Strategy";
            }
            
            public void setGrid(Grid grid) {
            }

            public boolean solve() {
                return false;
            }
        };
        grid.registerStrategy(testStrategy);
        assertFalse(grid.solve());
    }
}