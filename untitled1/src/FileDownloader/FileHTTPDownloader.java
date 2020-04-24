package FileDownloader;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class FileHTTPDownloader implements FIleHTTPDownloaderInterface {
    private byte[] clipboard;

    @Override
    public byte[] getFile(String urlPath) throws IOException {
        URL connection = new URL(urlPath);
        HttpURLConnection urlConn;
        urlConn = (HttpURLConnection) connection.openConnection();
        urlConn.setRequestMethod("GET");
        urlConn.connect();
        InputStream inputStream = urlConn.getInputStream();

        int clipboardSize = inputStream.available();
        clipboard = new byte[clipboardSize];
        inputStream.read(clipboard);

        inputStream.close();

        return clipboard;
    }

    public FileHTTPDownloader() {
    }

    public byte[] getClipboard() {
        return clipboard;
    }

    public void setClipboard(byte[] clipboard) {
        this.clipboard = clipboard;
    }

    public char[] getCharsClipboard() {
        char[] chars = new char[this.clipboard.length];
        for (int i = 0; i<this.clipboard.length; i++) {
            chars[i] = (char) this.clipboard[i];
        }
        return chars;
    }
}
