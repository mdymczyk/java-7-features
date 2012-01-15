package pl.mdymczyk.nio2;

import java.io.IOException;
import java.nio.file.DirectoryIteratorException;
import java.nio.file.DirectoryStream;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.attribute.FileAttribute;
import java.nio.file.attribute.PosixFilePermission;
import java.nio.file.attribute.PosixFilePermissions;
import java.util.Set;

public class CreateReadDirectories {

	public void printRootDirectories() {
		Iterable<Path> dirs = FileSystems.getDefault().getRootDirectories();
		for (Path path : dirs) {
			System.out.println(path);
		}
	}

	// this method created a directory even if a parent directory does not exist
	// yet
	public void createDirectory() {
		Path dir = Paths.get("test/test/tes");
		Set<PosixFilePermission> perms = PosixFilePermissions
				.fromString("rwxr-x---");
		FileAttribute<Set<PosixFilePermission>> attr = PosixFilePermissions
				.asFileAttribute(perms);
		try {
			Files.createDirectories(dir, attr);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void printDirContents(String path) {
		Path dir = Paths.get(path);
		try (DirectoryStream<Path> dirs = Files.newDirectoryStream(dir)) {
			for (Path name : dirs) {
				System.out.println(name);
			}
		} catch (IOException | DirectoryIteratorException e) {
			e.printStackTrace();
		}
	}

	public void printDirJavaContents(String path) {
		Path dir = Paths.get(path);
		try (DirectoryStream<Path> dirs = Files.newDirectoryStream(dir,
				"*.{java,jar,class}")) {
			for (Path name : dirs) {
				System.out.println(name);
			}
		} catch (IOException | DirectoryIteratorException e) {
			e.printStackTrace();
		}
	}

	public void printDirectoriesDirectories(String path) {
		Path dir = Paths.get(path);
		try (DirectoryStream<Path> dirs = Files.newDirectoryStream(dir,
				new DirectoryStream.Filter<Path>() {
					@Override
					public boolean accept(Path entry) throws IOException {
						return Files.isDirectory(entry);
					}
				})) 
		{
			for (Path name : dirs) {
				System.out.println(name);
			}
		} catch (IOException | DirectoryIteratorException e) {
			e.printStackTrace();
		}
	}
}
