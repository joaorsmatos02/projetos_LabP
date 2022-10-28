import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;

import org.junit.jupiter.api.Test;

class TestFileSystem {

	public static void main(String[] args) {
		testCreate();
		testExists();
		testRemove();
		testFind();
		TestFileSystemDataGenerator.createFileSystem(5, "testfiles.txt", 10, 5);
	}

	@Test
	static void testCreate() {
		IFileSystem fs = new FileSystem();
		String p1 = "/home/aluno/labp/project.java";
		assertEquals(true, fs.createFile(p1));
		assertEquals(false, fs.createFile(p1));
		System.out.println(fs.toString() + "\r\n");
	}

	@Test
	static void testExists() {
		IFileSystem fs = new FileSystem();
		String p1 = "/home/aluno/labp/project.java";
		String p2 = "/aluno/labp/project.java";
		fs.createFile(p1);
		assertEquals(true, fs.existsFile(p1));
		assertEquals(false, fs.existsFile(p2));
		System.out.println(fs.toString() + "\r\n");
	}

	@Test
	static void testRemove() {
		IFileSystem fs = new FileSystem();
		String p1 = "/home/aluno/labp/project.java";
		fs.createFile(p1);
		assertEquals(true, fs.existsFile(p1));
		fs.removeFile(p1);
		assertEquals(false, fs.existsFile(p1));
		System.out.println(fs.toString() + "\r\n");
	}

	@Test
	static void testFind() {
		IFileSystem fs = new FileSystem();
		String p1 = "/home/aluno/labp/project.java";
		String p2 = "/home/aluno/aes/project.java";
		String p3 = "/home/project.java";
		fs.createFile(p1);
		fs.createFile(p2);
		fs.createFile(p3);
		List<String> paths = fs.find("project.java");

		assertEquals(3, paths.size());
		assertEquals(true, paths.contains(p1));
		assertEquals(true, paths.contains(p2));
		assertEquals(true, paths.contains(p3));
		System.out.println(fs.toString() + "\r\n");
	}

}
