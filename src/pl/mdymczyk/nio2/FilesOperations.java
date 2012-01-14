package pl.mdymczyk.nio2;

import static java.nio.file.StandardCopyOption.ATOMIC_MOVE;
import static java.nio.file.StandardCopyOption.REPLACE_EXISTING;
import static java.nio.file.StandardOpenOption.APPEND;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.charset.Charset;
import java.nio.file.CopyOption;
import java.nio.file.DirectoryNotEmptyException;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.nio.file.Path;

public class FilesOperations {

	public void writeToFile(Path path, String value, String charserName) {
		Charset charset = Charset.forName(charserName);
		try (BufferedWriter writer = Files.newBufferedWriter(path, charset,
				APPEND)) {
			writer.write(value, 0, value.length());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void moveFile(Path from, Path to) {
		try {
			Files.move(from, to, REPLACE_EXISTING, ATOMIC_MOVE);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void checkIsExists(Path path) {
		if (Files.exists(path)) {
			System.out.println("File exists");
		} else if (Files.notExists(path)) {
			System.out.println("File does not exist");
		} else {
			System.out.println("Not sure");
		}
	}

	public boolean isRegulatExecutableFile(Path path) {
		return Files.isReadable(path) & Files.isExecutable(path)
				& Files.isRegularFile(path);
	}

	public boolean isSameFiles(Path path1, Path path2) {
		try {
			return Files.isSameFile(path1, path2);
		} catch (IOException e) {
			e.printStackTrace();
		}

		return false;
	}

	public void deleteFile(Path path) {
		try {
			Files.delete(path);
		} catch (NoSuchFileException ex) {
			System.err.println("No such file!");
		} catch (DirectoryNotEmptyException exc) {
			System.err.println("Directory has to be empty!");
		} catch (IOException ex) {
			System.err.println("Well this is bad...");
		}
	}

	public void deleteFileIfExists(Path path) {
		try {
			Files.deleteIfExists(path);
		} catch (DirectoryNotEmptyException exc) {
			System.err.println("Directory has to be empty!");
		} catch (IOException e) {
			System.err.println("Something bad happened");
		}
	}

	public void copyPath(Path from, Path to, CopyOption... options) {
		// Options:
		// StandardCopyOption.ATOMIC_MOVE;
		// StandardCopyOption.COPY_ATTRIBUTES;
		// StandardCopyOption.REPLACE_EXISTING;
		// LinkOption.NOFOLLOW_LINKS
		try {
			Files.copy(from, to, options);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void copyPathTpStream(Path from, OutputStream out,
			CopyOption... options) {
		try {
			Files.copy(from, out);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void copyPathFromStream(InputStream in, Path to,
			CopyOption... options) {
		try {
			Files.copy(in, to, options);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void movePath(Path from, Path to,
			CopyOption... options) {
		try {
			Files.move(from, to, options);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
