import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static ArrayList<String> GetFileNames(String path) {
        var fileNames = new ArrayList<String>();

        File[] fileList = new File(path).listFiles();

        assert fileList != null;

        for (var file : fileList) {
            var isFile = file.isFile();

            if (!isFile) {
                continue;
            }

            var fileName = file.getName();
            fileNames.add(fileName);
        }

        return fileNames;
    }

    @SuppressWarnings("InfiniteLoopStatement")
    public static void ForeverCountFiles(String path, int sleepSeconds) throws InterruptedException {
        var sleepValue = sleepSeconds * 1000;

        while (true) {
            var fileNames = GetFileNames(path);

            var fileCount = fileNames.size();

            System.out.println(fileCount);

            Thread.sleep(sleepValue);
        }
    }

    @SuppressWarnings("InfiniteLoopStatement")
    public static void ForeverLookupFile(String path) {
        var scanner = new Scanner(System.in);

        while (true) {
            System.out.println("შემოიტანე საძიებო სიტყვა: ");
            String inputStr = scanner.nextLine();

            var message = "შემოტანილი სიტყვაა: " + inputStr;
            System.out.println(message);

            var fileNames = GetFileNames(path);

            for (String s : fileNames) {
                if (s.contains(inputStr)) {
                    var outputMessage = "მოიძებნა " + s;
                    System.out.println(outputMessage);
                }
            }
        }
    }

    public static void main(String[] args) {
        var curLocation = System.getProperty("user.dir");

        var path = curLocation + "\\src\\BTU_DOCUMENT";

        var sleepSecondConfig = 5;

        Runnable thread = () -> {
            try {
                ForeverCountFiles(path, sleepSecondConfig);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        };

        new Thread(thread).start();

        ForeverLookupFile(path);
    }
}