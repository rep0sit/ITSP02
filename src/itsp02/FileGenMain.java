package itsp02;

public final class FileGenMain {
	private FileGenMain() {}
	
	
	public static void main(String[] args) {
		
		String keys1 = "keys1";
		String keys2 = "keys2";
		String keys3 = "keys3";
		
		Helpers.createKeyFileForTripleDES(keys1);
		Helpers.createKeyFileForTripleDES(keys2);
		Helpers.createKeyFileForTripleDES(keys3);
		
	}

}
