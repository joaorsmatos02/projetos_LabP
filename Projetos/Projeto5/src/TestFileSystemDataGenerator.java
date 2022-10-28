import java.util.Random;

public class TestFileSystemDataGenerator {

    private final int directoryNameLength;
    public TestFileSystemDataGenerator(int directoryNameLength ) {
        this.directoryNameLength = directoryNameLength;
    }

    private final Random random = new Random();
    private static final String DIRECTORY_CHARS = "qwertyuiopasdfghjklzxcvbnm1234567890";
    private  String getRandomDirectoryName () {
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i<this.directoryNameLength; i++)
            builder.append(DIRECTORY_CHARS.charAt(random.nextInt(DIRECTORY_CHARS.length()-1)));
        return builder.toString();
    }

    public static IFileSystem createFileSystem(int directoryNameLength, String testFileName, int treeHeight, int childrenCount) {
        IFileSystem fileSystem = new FileSystem();
        TestFileSystemDataGenerator generator = new TestFileSystemDataGenerator(directoryNameLength);
        generator.fillFileSystemWithData(fileSystem, "", testFileName, treeHeight, childrenCount);
        return fileSystem;
    }

    public void fillFileSystemWithData(IFileSystem fs, String currentPath, String filename, int treeHeight, int childrenCount) {

        if (treeHeight==0) return;

        for (int i=0; i<childrenCount; i++) {
            StringBuilder currentFile = new StringBuilder(currentPath).append("/");
            currentFile.append(getRandomDirectoryName());
            fillFileSystemWithData(fs, currentFile.toString(), filename, treeHeight-1, childrenCount);
            currentFile.append("/").append(filename);
            fs.createFile(currentFile.toString());

        }
    }
}
