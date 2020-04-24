package FileDownloader;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

/**
 * @author Holniev Vitalii.
 * <p>
 * Console utility for downloading files via HTTP
 * Accepts: a list of links in a text file
 * Action: download these files and put them in the specified folder on the local drive.
 * It should be able to download several files at the same time (in several streams, for example, 3 streams)
 * and withstand the specified limit on the download speed, for example, 500 kilobytes per second.
 */
public class FileHTTPDownloaderMain {
    public static String errorMessage = "input parameters :\n" + "\n" + "  -t number of threads (1,2,3)\n" + "  -l download speed limit(500 kb per sec.) \n" + "  -f file path \n"
            + "  -n destination folder name \n";


    public static void main(String[] args) {
        int numberOfFlows;
        int downloadSpeedLimit;
        String filePath;
        String dirName;

        if (args.length != 4) {
            System.out.printf(errorMessage);
            return;
        } else {
            try {
                numberOfFlows = Integer.valueOf(args[0].replaceAll("[^0-9]", ""));

                String suffix = args[1].replaceAll("[^a-zA-Z]", "").replaceAll("-", "").toLowerCase(); // обработаем суффикс k - килобайты, m - мегабайты
                downloadSpeedLimit = Integer.valueOf(args[1].replaceAll("[^0-9]", ""));
                switch (suffix) {
                    case "k":
                        downloadSpeedLimit = downloadSpeedLimit * 1024;
                        break;
                    case "m":
                        downloadSpeedLimit = downloadSpeedLimit * 1024 * 1024;
                        break;
                }
                filePath = args[2].replaceAll("-", "");
                dirName = args[3].replaceAll("-", "");
            } catch (Exception exc) {
                System.out.printf("%s", exc.toString());
                return;
            }
        }

        System.out.printf("Parameters: \n");
        System.out.printf("Folder name: %s\n\n", dirName);
        System.out.printf("File path: %s\n", filePath);
        System.out.printf("Number of threads: %d\n", numberOfFlows);
        System.out.printf("Speed Limit: %d \n", downloadSpeedLimit);
        OperationsFile operationsFile = new OperationsFile(filePath); //

        System.out.printf("What to download: \n");
        for (Operations operations : operationsFile.getOperations()) {
            System.out.printf("File path: %s file: %s\n", operations.getPath(), operations.getFileName());
        }
        System.out.printf("\n");

        File file = new File(dirName);
        if (!file.exists() || !file.isDirectory()) {
            System.out.printf("wrong folder name: %s", dirName);
            return;
        }


        for (Operations operations : operationsFile.getOperations()) {
            FileHTTPDownloader fileHTTPDownloader = new FileHTTPDownloader();
            try {
                fileHTTPDownloader.getFile(operations.getPath());
            } catch (IOException exc) {
                System.out.printf("%s", exc.toString());
                return;
            }
            String pathToWriteFile = dirName + "/" + operations.getFileName();
            File fileToWrite = new File(pathToWriteFile);
            try {
                FileWriter fileWriter = new FileWriter(fileToWrite);
                fileWriter.write(fileHTTPDownloader.getCharsClipboard());
                fileWriter.close();
            } catch (IOException e) {
                System.out.printf("%s", e.toString());
                return;
            }
            System.out.printf("content: %s downloaded in: %s\n", operations.getPath(), pathToWriteFile);
        }


    }
}
