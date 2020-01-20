package by.home.edt.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class FileUtils {

    public static FileInputStream getFileStream(String pathToFile) {
        File file = new File(pathToFile);
        try {
            return new FileInputStream(file.getAbsolutePath());
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }
}
