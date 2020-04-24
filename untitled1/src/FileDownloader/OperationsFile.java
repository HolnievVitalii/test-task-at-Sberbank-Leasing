package FileDownloader;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class OperationsFile {
    private ArrayList<Operations> operations;
    private String fileName;

    public OperationsFile(String fileName) {
        this.fileName = fileName;
        this.operations = new ArrayList<>();

        File operationFile = new File(fileName);
        if (operationFile.exists() && operationFile.isFile()) {
            try (FileReader reader = new FileReader(operationFile)) {
                char[] clipboard2 = new char[(int) operationFile.length()];
                reader.read(clipboard2);
                pars(clipboard2);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }


    public void setOperations(ArrayList<Operations> operations) {
        this.operations = operations;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getFileName() {
        return fileName;
    }


    public ArrayList<Operations> getOperations() {
        return operations;
    }

    private void pars(char[] buffer) {
        String parsedFile = new String(buffer);
        String[] words = parsedFile.trim().replaceAll("\r", " ").split(" ");
        for (int i = 0; i < words.length - 1; i += 2) {
            Operations operations = new Operations(words[i], words[i + 1]);
            this.operations.add(operations);
        }
    }


}
