package by.home.edt;

import by.home.edt.services.impl.DocFileReader;
import by.home.edt.services.impl.FileTranslator;
import by.home.edt.utils.FileUtils;

import java.io.FileInputStream;
import java.util.List;

public class Main {
    private static final String filePath = "D:\\Prog\\TEMP\\EDT\\inputDocHere\\The Wire 102.docx";

    public static void main(String[] args) {
        final DocFileReader docFileReader = new DocFileReader();
        final FileTranslator fileTranslator = new FileTranslator();

        FileInputStream inputStream = FileUtils.getFileStream(filePath);
        final List<String> stringsWithNumbers = docFileReader.readFile(inputStream);
        fileTranslator.translate(stringsWithNumbers);
    }
}
