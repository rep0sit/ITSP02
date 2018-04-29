package itsp02;
/**
 * @author Etienne Onasch
 * @author Nelli Welker
 *
 */
public final class TripleDES {
	
	private static byte[] key1 = new byte[8];
	private static byte[] key2 = new byte[8];
	private static byte[] key3 = new byte[8];
	
	private static DES des1 = new DES(key1);
	private static DES des2 = new DES(key2);
	private static DES des3 = new DES(key3);
	
	private TripleDES() {
	}
	
	private static void encrypt(byte[]source, byte[]target) {
		
		byte[] first = new byte[8];
		byte[] second = new byte[8];
		
		des1.encrypt(source, 0, first, 0);
		des2.decrypt(first, 0, second, 0);
		des3.encrypt(second, 0, target, 0);
		
	}
	
	
	private static void decrypt(byte[]source, byte[]target) {
		
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
					+ "3. output file name and 4. \"encrypt\" | \"decrypt\" (w/o marks)");
		}

	}
	
	
	
}
