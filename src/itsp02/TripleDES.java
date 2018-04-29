package itsp02;
/**
 * @author Etienne Onasch
 * @author Nelli Welker
 *
 */
public final class TripleDES {
	private TripleDES() {}
	
	private static void encrypt(byte[]key1, byte[]key2, byte[]key3, byte[]source, byte[]target) {
		DES des1 = new DES(key1);
		DES des2 = new DES(key2);
		DES des3 = new DES(key3);
		
		
		byte[] first = new byte[8];
		byte[] second = new byte[8];
		
		des1.encrypt(source, 0, first, 0);
		des2.decrypt(first, 0, second, 0);
		des3.encrypt(second, 0, target, 0);
		
		
	}
	
	
	private static void decrypt(byte[]key1, byte[]key2, byte[]key3, byte[]source, byte[]target) {
		
	}
	
	
	
	
	public static void main(String...args) {
		
	}
	
	
	
}
