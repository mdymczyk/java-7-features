package pl.mdymczyk.nio2;

import static java.nio.file.FileVisitOption.FOLLOW_LINKS;
import static java.nio.file.FileVisitResult.CONTINUE;

import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.FileVisitOption;
import java.nio.file.FileVisitResult;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.PathMatcher;
import java.nio.file.Paths;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.EnumSet;

public class PrintFiles extends SimpleFileVisitor<Path> {

	private final PathMatcher matcher;
	
	public PrintFiles(String pattern) {
		matcher = FileSystems.getDefault().getPathMatcher(pattern);
	}
	
	@Override
	public FileVisitResult visitFile(Path file, BasicFileAttributes attr) {
		if(attr.isSymbolicLink()) {
			System.out.format("Symbilic link: %s ", attr);
		} else if(attr.isRegularFile()) {
			System.out.format("Regular file: %s ", attr);
		} else {
			System.out.format("Other: %s ", attr);
		}
		
		Path name = file.getFileName();
		if(name != null && matcher.matches(file)) {
			System.out.println("File matches the pattern!");
		}
		
		System.out.println("(" + attr.size() + " bytes)");
		
		return CONTINUE;
	}
	
	@Override
	public FileVisitResult postVisitDirectory(Path path, IOException ex) {
		System.out.format("Directory: %s ", path);
		return CONTINUE;
	}

	@Override
	public FileVisitResult visitFileFailed(Path path, IOException ex) {
		System.err.println(ex);
		return CONTINUE;
	}
	
	public static void main(String[] args) {
		Path startingPoint = Paths.get("/");
		try {
			EnumSet<FileVisitOption> opts = EnumSet.of(FOLLOW_LINKS);
			// the documentation shows that you should also pass in a "pattern"
			// here but it seems it isn't the case...
			Files.walkFileTree(startingPoint, opts, 4, new PrintFiles("glob:*.{java,class}"));
			//			Files.walkFileTree(startingPoint, new PrintFiles());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
