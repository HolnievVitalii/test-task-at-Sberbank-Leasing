package FileDownloader;

public class Operations {
    private String fileName;
    private String filePath;

    public Operations(String path, String fileName) {
        this.filePath = path;
        this.fileName = fileName;
    }

    public String getPath() {
        return filePath;
    }

    public void setPath(String path) {
        this.filePath = path;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }
}
