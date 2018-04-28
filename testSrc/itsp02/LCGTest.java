package itsp02;

import static org.junit.Assert.*;

import org.junit.Test;

public class LCGTest {
	/*
	 * +++++++++++++++++++
	 * 		CONSTANTS
	 * +++++++++++++++++++
	 */
	
	private static final int BYTE = 256;
	
	/*
	 * +++++++++++++++++++
	 * 		TESTS
	 * +++++++++++++++++++
	 */
	
	@Test
	public void testNextInt() {
		int startWert = (int) (Math.random() * 10000);
		
		LCG lcg = new LCG(startWert);
		
		for(int i = 1; i <= BYTE; i++) {
			
			int nextFirstByte = lcg.nextInt() & (0x000000FF); 
			
			assertTrue(nextFirstByte >= 0 && nextFirstByte < BYTE);
			
			System.out.println(i + ". Zahl = " + nextFirstByte);
		}
		
		System.out.println("Der Startwert war: " + startWert);
	}

}
