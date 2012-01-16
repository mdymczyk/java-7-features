package pl.mdymczyk.nio2;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class SymLinks {

	public void createSymLink(String from, String to) {
		Path newFile = Paths.get(from);
		Path targetFile = Paths.get(to);
		try {
			Files.createSymbolicLink(newFile, targetFile);
			// create hard link
			// Files.createLink(newFile, targetFile);
			System.out.println(Files.readSymbolicLink(newFile));
		} catch (IOException e) {
			e.printStackTrace();
		} catch (UnsupportedOperationException e) {
			e.printStackTrace();
		}
	}

}
