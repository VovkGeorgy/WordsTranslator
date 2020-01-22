package by.home.edt.services.interfaces;

import java.io.FileInputStream;
import java.util.List;

public interface IFileReader {

    List<String> readFile(FileInputStream pathToFile);
}
