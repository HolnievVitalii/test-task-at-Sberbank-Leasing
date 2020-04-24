package FileDownloader;

import java.io.IOException;

public interface FIleHTTPDownloaderInterface {
    byte[] getFile(String pathToURL) throws IOException;
}
