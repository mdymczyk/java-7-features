package pl.mdymczyk.nio2;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.Collections;
import java.util.List;

public class ReadWriteCreateFile {

	public List<String> readAllLines(String pathString) {
		Path path = Paths.get(pathString);
		try {
			return Files.readAllLines(path, Charset.forName("UTF-8"));
		} catch (IOException e) {
			e.printStackTrace();
		}

		return Collections.emptyList();
	}

	public byte[] readAllBytes(String pathString) {
		Path path = Paths.get(pathString);
		try {
			return Files.readAllBytes(path);
		} catch (IOException e) {
			e.printStackTrace();
		}

		return new byte[0];
	}

	public void writeToFile(String pathString, byte[] toWrite,
			List<String> stringsToWrite) {
		Path path = Paths.get(pathString);
		try {
			Files.write(path, toWrite, StandardOpenOption.APPEND);
			Files.write(path, stringsToWrite, Charset.forName("UTF-8"),
					StandardOpenOption.WRITE,
					StandardOpenOption.TRUNCATE_EXISTING);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void bufferedRead(String pathString) {
		Path path = Paths.get(pathString);
		Charset chs = Charset.forName("UTF-8");

		try (BufferedReader reader = Files.newBufferedReader(path, chs)) {
			String line = null;
			while ((line = reader.readLine()) != null) {
				System.out.println(line);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void bufferedWrite(String pathString, String write) {
		Path path = Paths.get(pathString);
		Charset chs = Charset.forName("UTF-8");

		try (BufferedWriter writer = Files.newBufferedWriter(path, chs)) {
			writer.write(write, 0, write.length());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
