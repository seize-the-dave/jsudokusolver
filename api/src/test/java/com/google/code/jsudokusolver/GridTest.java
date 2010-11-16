package com.google.code.jsudokusolver;

import org.junit.Ignore;
import org.junit.Test;

public class GridTest 
{
	@Test(expected = RepeatedDigitException.class)
	public void testFromStringSameBox()
	{
		Grid.fromString("..9.7...5..21..9..1...28....7...5..1..851.....5....3.......3..68........21.....87");
	}

	@Test(expected = RepeatedDigitException.class)
	public void testFromStringSameRow()
	{
		Grid.fromString(".4.1..35.............2.5......4.89..26.....12.5.3....7..4...16.6....7....1..8..2.");
	}
	
	@Test(expected = RepeatedDigitException.class)
	public void testFromStringSameColumn()
	{
		Grid.fromString("6.159.....9..1............4.7.314..6.24.....5..3....1...6.....3...9.2.4......16..");
	}
	
	@Ignore @Test(expected = NoCandidatesException.class)
	public void testFromStringNoCandidates()
	{
		Grid g = Grid.fromString("..9.287..8.6..4..5..3.....46.........2.71345.........23.....5..9..4..8.7..125.3..");
		Solver s = new Solver();
		s.solve(g);
	}
}
