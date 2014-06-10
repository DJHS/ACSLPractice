package practice.ternaryboolean;

import static org.junit.Assert.*;

import org.junit.Test;


public class TernaryBooleanTest {

	@Test
	public void testHandleSingleLine() {
		String line = "4, A7, B, B3, BCD, C5, BCD, D0, CD, C";
		
		TernaryBoolean b = new TernaryBoolean();
		b.handleSingleLine(line);
		assertEquals(b, null);
	}

}
