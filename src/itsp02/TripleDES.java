package itsp02;

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
 * 
 * 
 * 
 * 
 * 
 * 
 * @author Etienne Onasch
 * @author Nelli Welker
 *
 */
public final class TripleDES {
	
	private static final String ENCRYPT = "encrypt";
	private static final String DECRYPT = "decrypt";
	
	private static byte[] key1 = new byte[8];
	private static byte[] key2 = new byte[8];
	private static byte[] key3 = new byte[8];
	private static byte[] iv = new byte[8];
	
	
	
	private TripleDES() {
	}
	
	private static void encrypt(byte[]source, byte[]target) {
		
		DES des1 = new DES(key1);
		DES des2 = new DES(key2);
		DES des3 = new DES(key3);
		
		byte[] first = new byte[8];
		byte[] second = new byte[8];
		
		des1.encrypt(source, 0, first, 0);
		des2.decrypt(first, 0, second, 0);
		des3.encrypt(second, 0, target, 0);
		
	}
	
	
	private static void decrypt(byte[]source, byte[]target) {
		
		DES des1 = new DES(key1);
		DES des2 = new DES(key2);
		DES des3 = new DES(key3);
		
		byte[] first = new byte[8]; 
		byte[] second = new byte[8];
		
		des3.decrypt(source, 0, first, 0);
		des2.encrypt(first, 0, second, 0);
		des1.decrypt(second, 0, target, 0);
		
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
		
		if(!crypt.equals(ENCRYPT) || !crypt.equals(DECRYPT)){
			throw new IllegalArgumentException("4th argument must be \""+ ENCRYPT +"\" or \""+ DECRYPT +"\"");
		}
		
		
		readKeyIVFromFile(keyFile);
	}

	private static void readKeyIVFromFile(String keyFile) {
		
		
	}
	
	
	
}
