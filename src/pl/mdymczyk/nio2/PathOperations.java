package pl.mdymczyk.nio2;

import java.io.IOException;
import java.net.URI;
import java.nio.file.LinkOption;
import java.nio.file.Path;
import java.nio.file.Paths;

public class PathOperations {

	// create path from a string
	public Path createPath(String path) {
		// FileSystems.getDefault().getPath(path);
		return Paths.get(path);
	}

	// create path from a number of strings
	public Path createPathFromStrings(String path, String name) {
		// FileSystems.getDefault().getPath(path, name);
		return Paths.get(path, name);
	}

	public Path createPathFromURI(String uriString) {
		return Paths.get(URI.create(uriString));
	}

	public void showPathInfo(Path path) {
		System.out.format("toString: %s%n", path.toString());
		System.out.format("getFileName: %s%n", path.getFileName());
		System.out.format("getName(0): %s%n", path.getName(0));
		System.out.format("getName(0): %d%n", path.getNameCount());
		System.out.format("subpath(0,2): %s%n", path.subpath(0, 2));
		System.out.format("getParent(): %s%n", path.getParent());
		System.out.format("getRoot(): %s%n", path.getRoot());
	}

	public Path toAbsolutePaht(Path path) {
		// can throw an exception when the file system is not accessible for
		// some reason
		// simply appends the working directory to the beginning
		return path.toAbsolutePath();
	}

	public Path toRealPath(Path path) {
		try {
			// won't resolve symlinks
			return path.toRealPath(LinkOption.NOFOLLOW_LINKS);
		} catch (IOException ex) {
			System.err.format("File does not exist or other problems occured!");
			ex.printStackTrace();
		}

		return null;
	}

	public Path resolvePaths(String path1, String path2) {
		return Paths.get(path1).resolve(path2);
	}

	public Path createPathBetween(String one, String two) {
		return Paths.get(one).relativize(Paths.get(two));
	}
	
	public void comparePaths() {
		Path path = Paths.get("/Users/mdymczyk/test");
		Path pathTwo = Paths.get(System.getenv("HOME") ,"test");
		Path beginning = Paths.get("/Users");
		Path ending = Paths.get("test");
		
		if(path.equals(pathTwo)) {
			System.out.println("Paths equal");
		}
		
		if(path.startsWith(beginning)) {
			System.out.println("Path stats with: " + beginning.toString());
		}
		
		if(path.endsWith(ending)) {
			System.out.println("Path ends with: " + ending.toString());
		}
		
		for(Path name : path) {
			System.out.println("Name: " + name);
		}
		
		System.out.println(path + " compared to " + pathTwo + ": " + path.compareTo(pathTwo));
	}

	public static void main(String[] args) {
		PathOperations pathOperations = new PathOperations();
		// *NIX systems!
		System.out.println("========== PATH INFO ==========");
		Path absoluteHomePath = pathOperations
				.createPath(System.getenv("HOME"));
		pathOperations.showPathInfo(absoluteHomePath);

		Path relativePath = pathOperations.createPath("test/path");
		System.out.println("========== PATH INFO ==========");
		pathOperations.showPathInfo(relativePath);

		System.out.println("========== ABSOLUT PATH ==========");
		System.out.println(pathOperations.toAbsolutePaht(relativePath));

		System.out.println("========== RESOLVE PATH ==========");
		System.out
				.println(pathOperations.resolvePaths("/one/two", "two/three"));

		System.out.println("========== RELATIVIZE PATH ==========");
		System.out.println("Relativize: "
				+ pathOperations.createPathBetween("one", "two"));
		
		System.out.println("========== COMPARE PATH ==========");
		pathOperations.comparePaths();
	}
}
