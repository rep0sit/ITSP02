package itsp02;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.file.Path;
import java.nio.file.Paths;

final class Helpers {
	private Helpers() {}
	
	public static String createOutputPath(String path, String mod) {
//		Path p = Paths.get(path);
//		
//		String fileName = p.getFileName().toString();
//		String parent = p.getParent().toString();
//		
		String[] nameAndEnding = path.split("\\.");
		
		String ending = nameAndEnding.length > 1 ? "." + nameAndEnding[nameAndEnding.length - 1] : "";
		
		
		
		
		// Wenn parent einfach nur Laufwerk, dann ist automatisch ein Slash "angefuegt". Sonst nicht!
		//String slash = parent.length() > 3 ? "\\" : "";
		
		return path + mod + ending;
		
	}
	
	
	/**
	 * 
	 * This method creates a file where:<br>
	 * 
	 * The first 24 Bytes are 3 DES-Keys (3 * 8 Byte);<br>
	 * Bytes 25 - 32: 8 Bytes for the IV (init vector) for CFB - mode <br>
	 * 
	 * @param file fully qualified name of the file that should be created. 
	 * 
	 */
	
	
	public static void createKeyFileForTripleDES(String file) {
		
		byte [][] keys = {	
							(new DES()).getKey(),
							(new DES()).getKey(), 
							(new DES()).getKey(), 
							(new DES()).getKey()
						};
		
		
		try (OutputStream out = new FileOutputStream(file)){
			
			for(byte [] b : keys) {
				out.write(b);
			}
			
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
}
