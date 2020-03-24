package by.home.edt.utils;

import org.apache.commons.io.FileUtils;

import java.io.File;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class FileReadUtils {

    /**
     * Method get list of files By extensions
     *
     * @return files
     */
    public static List<File> getFilesByExtensions(String inputFolder, String[] filesExtensions, int maxReadFiles) {
        Iterator<File> it = FileUtils.iterateFiles(new File(inputFolder), filesExtensions, false);
        ArrayList<File> receivedFiles = new ArrayList<>();
        for (int filesCount = 0; filesCount < maxReadFiles & it.hasNext(); filesCount++) {
            receivedFiles.add(it.next());
        }
        return receivedFiles;
    }
}
