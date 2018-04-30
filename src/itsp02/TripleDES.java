package itsp02;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Arrays;

/**
 * 
 * 
 * A class that encrypts / decrypts a file with the method CFB (Cipher Feedback). <br>
 * 
 * 
 * The encryption function used in it is TripleDES.<br><br>
 * 
 * 
 * <b><u>CFB:</b></u><br><br>
 * 
 * C_0 := init_vector<br>
 * keys := 3 different keys (1 key = 8byte) <br><br>
 * 
 * 
 * TripleDES(C_n, keys) XOR M_n+1 -> Cn+1 <br>
 * 

 * @author Etienne Onasch
 * @author Nelli Welker
 *
 */
public final class TripleDES {
	
	private static final String ENCRYPT = "encrypt";
	private static final String DECRYPT = "decrypt";
	
	private static final int BLOCK_SIZE = 8;
	
	private static int inFileLen = 0;
	
	
	private static byte[] key1 = new byte[BLOCK_SIZE];
	private static byte[] key2 = new byte[BLOCK_SIZE];
	private static byte[] key3 = new byte[BLOCK_SIZE];
	private static byte[] iv = new byte[BLOCK_SIZE];
	
	private TripleDES() {
	}
	
	
	
	/**
	 * Therefore, Triple DES uses a "key bundle" that comprises three DES keys, K1,
	 * K2 and K3, each of 56 bits (excluding parity bits). The encryption algorithm
	 * is:<br>
	 * <br>
	 * <b>
	 *
	 * ciphertext = EK3(DK2(EK1(plaintext))) </b><br>
	 * <br>
	 * 
	 * I.e., DES encrypt with K1, DES decrypt with K2, then DES encrypt with K3.
	 *
	 * Decryption is the reverse:<br>
	 * <br>
	 * <b>
	 *
	 * plaintext = DK1(EK2(DK3(ciphertext)))</b> <br>
	 * <br>
	 *
	 * I.e., decrypt with K3, encrypt with K2, then decrypt with K1.
	 *
	 * Each triple encryption encrypts one block of 64 bits of data.
	 * 
	 * @param source the current source byte
	 * @param target
	 * @param crypt
	 */
	private static void tripleDES(byte[]source, byte[]target) {
		
		DES des1 = new DES(key1);
		DES des2 = new DES(key2);
		DES des3 = new DES(key3);
		
		byte[] first = new byte[BLOCK_SIZE]; 
		byte[] second = new byte[BLOCK_SIZE];
		
		//if(crypt.equals(ENCRYPT)) {
		
		des1.encrypt(source, 0, first, 0);
		des2.decrypt(first, 0, second, 0);
		des3.encrypt(second, 0, target, 0);
			
			
		//}
		
//		else if(crypt.equals(DECRYPT)) {
//			
//			des3.decrypt(source, 0, first, 0);
//			des2.encrypt(first, 0, second, 0);
//			des1.decrypt(second, 0, target, 0);
//			
//		}
//		else {
//			throw new IllegalArgumentException("Argument crypt must be \""+ ENCRYPT + "\" or \"" + DECRYPT + "\"");
//		}
		
		
		
	}
	
	public static void main(String...args) {

		if(args.length != 4) {
			throw new IllegalArgumentException("Arguments must be: 1. File that should be encrypted,"
					+ " 2. A keyfile with 3 Keys and one init vector, "
					+ "3. output file name and 4. \"" + ENCRYPT + "\" | \"" + DECRYPT + "\" (w/o marks)");
		}

		String inFile = args[0];
		String keyFile = args[1];
		String outFile = args[2];
		String crypt = args[3];
		
		
		System.out.println("\nFirst arg (input-file): " + inFile);
		System.out.println("Second arg (key-file): " + keyFile);
		System.out.println("Third arg (output-file): " + outFile);
		System.out.println("Fourth arg (" + ENCRYPT + " | " + DECRYPT + "): " + crypt + "\n");
		
		
		if(!crypt.equals(ENCRYPT) && !crypt.equals(DECRYPT)){
			throw new IllegalArgumentException("4th argument must be \""+ ENCRYPT +"\" or \""+ DECRYPT +"\"");
		}
		
		readKeyIVFromFile(keyFile);
		
		
		
		// Cipher Feedback
		byte[] outPut = iv;
		byte[] result;
		
		try(InputStream in = new FileInputStream(inFile); 
				OutputStream out = new FileOutputStream(outFile)){
			
			byte[] buffer = new byte[BLOCK_SIZE];
			int len;
			result = new byte[BLOCK_SIZE];
			
			tripleDES(outPut, result);
			
			
			while((len = in.read(buffer)) > 0) {
				
				inFileLen += len;

				xor(result, buffer, outPut);
				
				out.write(outPut, 0, len);
				
				
				if(crypt.equals(ENCRYPT)) {
					tripleDES(outPut, result);
				}else {
					tripleDES(buffer, result);
				}
		
				
				//out.write(currentChiffre);
				
				
			}
		
			
			System.out.println("\nLength of " + inFile + ": " + inFileLen + " Bytes");
			
			
			
		} catch (FileNotFoundException e) {	
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		
		
	}

	/**
	 * Reading key1,key2,key3 and the iv from the keyfile and
	 * storing into the variables.
	 * 
	 * @param keyFile
	 */
	private static void readKeyIVFromFile(String keyFile) {
		
		try(FileInputStream in = new FileInputStream(keyFile)) {
					
			in.read(key1, 0, BLOCK_SIZE);
			in.read(key2, 0, BLOCK_SIZE);
			in.read(key3, 0, BLOCK_SIZE);
			in.read(iv, 0, BLOCK_SIZE);
			
			System.out.println("From " + keyFile + ":");
			System.out.println("key1: " + Arrays.toString(key1));
			System.out.println("key2: " + Arrays.toString(key2));
			System.out.println("key3: " + Arrays.toString(key3));
			System.out.println("init vector: " + Arrays.toString(iv));
			
			

			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	
	private static void xor(byte[] first, byte[] second, byte[] dest) {

		// Prec
		int aLen = first.length;
		if (second.length != aLen || dest.length != aLen) {
			throw new IllegalArgumentException("All byte Arrays must be of same length!");
		}

//		for (int i = 0; i < aLen; i++) {
//			dest[i] = (byte) (first[i] ^ second[i]);
//		}
		
		DES.writeBytes(DES.makeLong(first, 0, aLen) ^ DES.makeLong(second, 0, aLen), 
				dest, 0, aLen);
		
		
		
		
	}
	
	
	
}
