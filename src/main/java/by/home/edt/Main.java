package by.home.edt;

import by.home.edt.services.impl.DocFileReader;
import by.home.edt.utils.FileUtils;

import java.io.FileInputStream;

public class Main {
    private static final String filePath = "D:\\Prog\\TEMP\\EDT\\inputDocHere\\The Wire 102.docx";

    public static void main(String[] args) {
        FileInputStream inputStream = FileUtils.getFileStream(filePath);
        DocFileReader docFileReader = new DocFileReader();
        docFileReader.readFile(inputStream);
    }
}
