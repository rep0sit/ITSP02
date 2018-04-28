package itsp02;

import java.nio.file.Path;
import java.nio.file.Paths;

final class Helpers {
	private Helpers() {}
	
	public static String createOutputPath(String path, String mod) {
		Path p = Paths.get(path);
		
		String fileName = p.getFileName().toString();
		String parent = p.getParent().toString();
		
		String[] nameAndEnding = fileName.split("\\.");
		
		String ending = nameAndEnding.length > 1 ? "." + nameAndEnding[nameAndEnding.length - 1] : "";
		
		// Wenn parent einfach nur Laufwerk, dann ist automatisch ein Slash "angefuegt". Sonst nicht!
		String slash = parent.length() > 3 ? "\\/" : "";
		
		return parent + slash + fileName + mod + ending;
	}
}
