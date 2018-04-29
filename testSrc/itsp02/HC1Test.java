/**
 * 
 */
package itsp02;

import static org.junit.Assert.*;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;

import org.junit.Test;

/**
 * @author Nelli Welker
 * @author Etienne Onasch
 *
 */
public class HC1Test {

	@Test
	public void testFileContents() throws IOException {
//		HC1 hc1 = new HC1();
//		HC1.encodeHC1(9, "textB.txt");
//		HC1.encodeHC1(9, "textB.txt_HC1.txt_HC1.txt");
		
		assertEquals(new String(Files.readAllBytes(Paths.get("textB.txt")), StandardCharsets.UTF_8), 
				new String(Files.readAllBytes(Paths.get("textB.txt_HC1.txt_HC1.txt")), StandardCharsets.UTF_8));

		assertNotEquals(new String(Files.readAllBytes(Paths.get("textB.txt")), StandardCharsets.UTF_8), 
				new String(Files.readAllBytes(Paths.get("textB.txt_HC1.txt_HC2.txt")), StandardCharsets.UTF_8));

	}
	
	@Test
	public void testFileContentsSecureRandom() throws IOException{
//		HC1.encodeHC1SR("textB.txt");
//		HC1.encodeHC1SR("textB.txt_HC1SR.txt");
		
//		assertEquals(new String(Files.readAllBytes(Paths.get("textB.txt")), StandardCharsets.UTF_8), 
//				new String(Files.readAllBytes(Paths.get("textB.txt_HC1SR.txt_HC1SR.txt"))), StandardCharsets.UTF_8);
	}

}
