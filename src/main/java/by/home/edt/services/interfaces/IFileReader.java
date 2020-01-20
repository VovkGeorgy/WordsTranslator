package by.home.edt.services.interfaces;

import java.io.File;
import java.io.FileInputStream;

public interface IFileReader {

    File readFile(FileInputStream pathToFile);
}
