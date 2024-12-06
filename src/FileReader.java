import java.io.File;
import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Scanner;

public class FileReader implements Iterable<String> {
    private final ArrayList<String> data;
    private final Scanner scanner;
    private boolean isRead;

    public FileReader(String filepath) throws IOException {
        this(FileSystems.getDefault().getPath(filepath));
    }

    public FileReader(Path filepath) throws IOException {
        this.data = new ArrayList<>();
        File file = filepath.toFile();
        this.handleFile(file);
        this.scanner = new Scanner(file);
    }

    private void handleFile(File file) throws IOException, SecurityException {
        if (!file.isFile()) {
            throw new IOException("Given path is not a proper file.");
        }
        if (!file.exists()) {
            throw new IOException("Given path does not exist.");
        }

        if (!file.canRead()) {
            throw new IOException("Given path is not readable.");
        }
    }


    @Override
    public Iterator<String> iterator() {
        return new Iterator<String>() {

            @Override
            public boolean hasNext() {
                return FileReader.this.isRead = FileReader.this.scanner.hasNext();
            }

            @Override
            public String next() {
                var toReturn = FileReader.this.scanner.next().replaceAll("\\W", "");
                FileReader.this.data.add(toReturn);
                return toReturn;
            }
        };
    }

    private void readComplete() {
        while (this.scanner.hasNext()) {
            var next = this.scanner.next().replaceAll("\\W", "");
            this.data.add(next);
        }
        this.isRead = false;
    }

    public ArrayList<String> getData() {
        return this.data;
    }

    public ArrayList<String> getCompleteData() {
        if (!this.isRead) {
            this.readComplete();
        }
        return this.data;
    }

}
