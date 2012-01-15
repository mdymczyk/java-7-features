package pl.mdymczyk.nio2;

import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.attribute.BasicFileAttributes;
import java.nio.file.attribute.DosFileAttributes;
import java.nio.file.attribute.FileAttribute;
import java.nio.file.attribute.FileTime;
import java.nio.file.attribute.PosixFileAttributes;
import java.nio.file.attribute.PosixFilePermission;
import java.nio.file.attribute.PosixFilePermissions;
import java.nio.file.attribute.UserPrincipal;
import java.nio.file.attribute.UserPrincipalLookupService;
import java.util.HashSet;
import java.util.Set;

public class Metadata {

	public void printFileAttributes(Path path) {
		try {
			System.out.println(Files.size(path));
			// can take LinkOption to disregard symlinks
			System.out.println(Files.isDirectory(path));
			// can take LinkOption to disregard symlinks
			System.out.println(Files.isRegularFile(path));
			System.out.println(Files.isSymbolicLink(path));
			System.out.println(Files.isHidden(path));
			// can take LinkOption to disregard symlinks
			System.out.println(Files.getLastModifiedTime(path));
			System.out.println(Files.setLastModifiedTime(path,
					FileTime.fromMillis(1000)));

			// can take LinkOption to disregard symlinks
			System.out.println(Files.getOwner(path));
			UserPrincipalLookupService lookupService = FileSystems.getDefault()
					.getUserPrincipalLookupService();
			UserPrincipal user = lookupService
					.lookupPrincipalByName("mdymczyk");
			System.out.println(Files.setOwner(path, user));

			// can take LinkOption to disregard symlinks
			System.out.println(Files.getPosixFilePermissions(path));
			System.out.println(Files.setPosixFilePermissions(path,
					new HashSet<PosixFilePermission>()));

			System.out.println(Files.getAttribute(path, "unix:uid"));
			System.out.println(Files.setAttribute(path, "unix:uid", 1));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void fileAttributes(Path path) {
		try {
			// supported by all implementations
			BasicFileAttributes basic = Files.readAttributes(path,
					BasicFileAttributes.class);
			System.out.println(basic.fileKey());
			// supported by DOS and DOS-like (e.g. Samba)
			DosFileAttributes dos = Files.readAttributes(path,
					DosFileAttributes.class);
			System.out.println(dos.fileKey());
			// supported by POSIX complaint file systems
			PosixFileAttributes posix = Files.readAttributes(path,
					PosixFileAttributes.class);
			System.out.format("%s %s %s%n", posix.owner().getName(), posix
					.group().getName(), PosixFilePermissions.fromString(posix
					.toString()));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void createFileCopyingAttributes(String fromStr, String toStr) {
		Path from = Paths.get(fromStr);
		Path to = Paths.get(toStr);
		try {
			PosixFileAttributes attributes = Files.readAttributes(from,
					PosixFileAttributes.class);
			FileAttribute<Set<PosixFilePermission>> attr = PosixFilePermissions
					.asFileAttribute(attributes.permissions());
			Files.createFile(to, attr);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
