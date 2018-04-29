package itsp02;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;


/**
 * 
 * @author Etienne Onasch
 * @author Nelli Welker
 *
 */
public class HC1 {
	

	public static void main(String...args) {
		int key = 0;
		
		String path = "";
		
		if(args.length != 2) {
			throw new IllegalArgumentException("This program needs an integer value as a key and a path to a file that you want to be encrypted!");
		}
		
		path = args[1];
		try {
			key = Integer.parseInt(args[0]);
			
		}catch(NumberFormatException n) {
			n.printStackTrace();
			throw new IllegalArgumentException("The fist value must be an integer value!");
			
		}
		
		
		LCG lcg = new LCG(key);
		
		try(InputStream in = new FileInputStream(path); 
				OutputStream out = new FileOutputStream(Helpers.createOutputPath(path, "_HC1"))){
			
			
			int nextByte = in.read();
			while(nextByte >= 0) {
				
				out.write(nextByte ^ lcg.nextInt());
				nextByte = in.read();
				
			}
				
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		
		
		
		
	}
}
